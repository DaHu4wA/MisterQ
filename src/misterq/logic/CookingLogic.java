package misterq.logic;

import misterq.serial.QCommunicator;

import java.io.IOException;

/**
 * Created by stefa on 31.03.2017.
 */
public class CookingLogic {

    private QCommunicator qComm;

    public CookingLogic() {
        qComm = new QCommunicator();
        qComm.initialize();
    }

    public void startCooking(String food, int weight, String howDone) {
        //moveDown();

//        servozero();
//
//        try {
//            thread.sleep(5000);
//        } catch (interruptedexception e) {
//            e.printstacktrace();
//        }

        //servo180();

//        try {
//            moveDown();
//            Thread.sleep(100);
//            moveDown();
//            Thread.sleep(100);
//            moveDown();
//            Thread.sleep(100);
//            moveDown();
//            Thread.sleep(100);
//            moveDown();
//            Thread.sleep(100);
//        } catch (interruptedexception e) {
//            e.printstacktrace();
//        }

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
