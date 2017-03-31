package misterq.logic;

/**
 * Created by stefa on 01.04.2017.
 */
public enum DoneGrade {

    RARE(60),

    MEDIUM(71),

    DONE(77);

    private int degreesFarenheit;

    DoneGrade(int degreesFarenheit) {
        this.degreesFarenheit = degreesFarenheit;
    }

    public int getDegreesFarenheit() {
        return degreesFarenheit;
    }
}
