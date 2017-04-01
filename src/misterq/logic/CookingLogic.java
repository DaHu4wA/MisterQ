package misterq.logic;

import misterq.gui.TextUpdateCallback;
import misterq.serial.QCommunicator;

import java.util.Timer;
import java.util.TimerTask;


public class CookingLogic {

    private static final int TIME_CONST = 15;

    private QCommunicator qComm;

    private TextUpdateCallback tempLabelUpdate;

    private ThreadLocal<Integer> surroundingTemp = new ThreadLocal<>();

    private volatile boolean iAmOnFire = false;

    private IncomingValueHandler incomingValueHandler = new IncomingValueHandler() {

        @Override
        public void messageIncoming(String incomingText) {
            // move up when Fire
            if (incomingText.equals("Fire")) {

                if (!iAmOnFire) {
                    qComm.moveUp(true);
                    iAmOnFire = true;

                    Timer resetTimer = new Timer();
                    resetTimer.schedule(new TimerTask() {
                        int counterSeconds = 5;

                        @Override
                        public void run() {
                            if (counterSeconds == 0) {
                                iAmOnFire = false;
                                qComm.moveHalfDown();
                                resetTimer.cancel();
                            }
                            counterSeconds--;
                        }


                    }, 0, 1000);
                }
            }

            if (incomingText.startsWith("Temp=")) {
                surroundingTemp.set(Integer.valueOf(incomingText.substring(5)));
                if (tempLabelUpdate != null) {
                    tempLabelUpdate.updateText("Surface temperature: " + surroundingTemp.get() + "°C");
                }
            }
        }
    };

    public CookingLogic(TextUpdateCallback tempLabelUpdate) {
        qComm = new QCommunicator(incomingValueHandler);
        qComm.initialize();
        this.tempLabelUpdate = tempLabelUpdate;
    }

    public void startCooking(Food food, DoneGrade doneGrade, int weight, TextUpdateCallback bigLabelUpdate) {
        qComm.servoZero();
        int realTimeOneSide = computeRealTime(food.getSeconds(), weight);

        Timer initialTimer = new Timer();
        initialTimer.schedule(new TimerTask() {
            int counterSeconds = realTimeOneSide;

            @Override
            public void run() {
                bigLabelUpdate.updateText("Food will be turned in: " + counterSeconds + "sec");
                if (counterSeconds == 0) {
                    firstTurnFood(realTimeOneSide, bigLabelUpdate);
                    initialTimer.cancel();
                }
                counterSeconds--;
            }


        }, 0, 1000);

    }

    private void firstTurnFood(int realTimeOneSide, TextUpdateCallback bigLabelUpdate) {
        qComm.servo180();
        Timer turnedTimer = new Timer();
        turnedTimer.schedule(new TimerTask() {
            int counterSeconds = realTimeOneSide;

            @Override
            public void run() {
                bigLabelUpdate.updateText("Food will be done in: " + counterSeconds + "sec");

                if (counterSeconds == 0) {
                    qComm.servoZero();
                    checkIfFoodIsDone(realTimeOneSide, bigLabelUpdate);
                    turnedTimer.cancel();
                }
                counterSeconds--;
            }
        }, 0, 1000);
    }

    private void checkIfFoodIsDone(int realTimeOneSide, TextUpdateCallback bigLabelUpdate) {
        bigLabelUpdate.updateText("Checking core temperature...");
        Timer textTimer = new Timer();
        textTimer.schedule(new TimerTask() {
            int counterSeconds = 3;

            @Override
            public void run() {
                if (counterSeconds == 0) {

                    boolean isItDone = false;//new Random().nextBoolean();

                    if (isItDone) {
                        bigLabelUpdate.updateText("Perfect temperature reached! Enjoy your meal.");
                    } else {
                        qComm.servo180();
                        foodNotDoneYet(bigLabelUpdate);
                    }
                    textTimer.cancel();
                }
                counterSeconds--;
            }
        }, 0, 1000);
    }

    private void foodNotDoneYet(TextUpdateCallback bigLabelUpdate) {
        bigLabelUpdate.updateText("Not done yet, we need some time...");
        Timer textTimer = new Timer();
        textTimer.schedule(new TimerTask() {
            int counterSeconds = 20;

            @Override
            public void run() {
                bigLabelUpdate.updateText("Food will be done in: " + counterSeconds + "sec");
                if (counterSeconds == 0) {
                    bigLabelUpdate.updateText("Perfect temperature reached!");
                    qComm.servoZero();
                    textTimer.cancel();
                }
                counterSeconds--;
            }
        }, 0, 1000);
    }

    public static double convertWeight(int weight, int min, int max, int a, int b) {
        return ((((double) b - (double) a) * ((double) weight - (double) min)) / ((double) max - (double) min)) + (double) a;

    }

    public static int computeRealTime(int initialTime, int weight) {
        int realTime = 0;

        // convert weight from 50 - 500 to 0-1
        double convertedWeight = convertWeight(weight, 50, 500, 0, 1);
        realTime = (int) (convertedWeight * initialTime + TIME_CONST);
        System.out.println(realTime);

        return realTime;
    }
}
