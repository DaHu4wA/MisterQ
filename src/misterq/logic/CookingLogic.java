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

    public void startCooking(String food, int weight, String howDone){
        moveDown();
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

    public void moveUp(){
        try {
            qComm.sendData("um");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveDown(){
        try {
            qComm.sendData("dm");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
