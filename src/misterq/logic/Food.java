package misterq.logic;

/**
 * Created by stefa on 01.04.2017.
 */
public enum Food {

    STEAK(210),

    CHICKEN(210),

    BURGER(50),

    SAUSAGE(180);

    private int seconds;

    Food(int seconds) {
        this.seconds = seconds;
    }

    public int getSeconds() {
        return seconds;
    }
}
