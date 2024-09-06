package DesignPatterns;

public class MediumLevel implements LevelStrategy{
    private int speed = 14;
    private int bombNumber = 7;
    private int giftNumber = 8;
    private int timerNumber = 8;

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public int getBombNumber() {
        return bombNumber;
    }

    @Override
    public int getGiftNumber() {
        return giftNumber;
    }

    @Override
    public int getTimerNumber() {
        return timerNumber;
    }
}