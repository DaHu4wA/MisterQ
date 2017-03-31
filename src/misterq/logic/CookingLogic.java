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

    private IncomingValueHandler incomingValueHandler = new IncomingValueHandler() {

        @Override
        public void messageIncoming(String incomingText) {
//            System.out.println("Received: " + incomingText);

            // move up when Fire
            if (incomingText.equals("Fire")) {
                qComm.moveUp(false);
            }

            if (incomingText.startsWith("Temp=")) {
                surroundingTemp.set(Integer.valueOf(incomingText.substring(5)));
                if (tempLabelUpdate != null) {
                    tempLabelUpdate.updateText("Grill temp: " + surroundingTemp.get());
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
                    firstTurnFood();
                    initialTimer.cancel();
                }
                counterSeconds--;
            }

            private void firstTurnFood() {
                qComm.servo180();
                Timer turnedTimer = new Timer();
                turnedTimer.schedule(new TimerTask() {
                    int counterSeconds = realTimeOneSide;

                    @Override
                    public void run() {
                        bigLabelUpdate.updateText("Food will be done in: " + counterSeconds + "sec");

                        if (counterSeconds == 0) {
                            qComm.servoZero();
                            checkIfFoodIsDone(bigLabelUpdate);
                            turnedTimer.cancel();
                        }
                        counterSeconds--;
                    }
                }, 0, 1000);
            }
        }, 0, 1000);

    }

    private void checkIfFoodIsDone(TextUpdateCallback callback) {
        callback.updateText("D O N E (not really, we have to check)");


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
