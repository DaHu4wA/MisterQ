package misterq.logic;

import misterq.gui.TextUpdateCallback;
import misterq.serial.QCommunicator;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by stefa on 31.03.2017.
 */
public class CookingLogic {

    private QCommunicator qComm;

    public CookingLogic() {
        qComm = new QCommunicator();
        qComm.initialize();
    }

    public void startCooking(Food food, DoneGrade doneGrade, int weight, TextUpdateCallback callback) {

        new Timer().schedule(new TimerTask() {
            int second = 60;

            @Override
            public void run() {
                callback.updateText("Food is done in: " + second + "sec");

                if (second == 60) {
                    servoZero();
                }

                if (second == 50) {
                    servo180();
                }
                second--;
            }
        }, 0, 1000);


    }

    public void moveUp() {
        try {
            qComm.sendData("um");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveDown() {
        try {
            qComm.sendData("dm");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void servoZero() {
        try {
            qComm.sendData("s0");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void servo180() {
        try {
            qComm.sendData("s1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
