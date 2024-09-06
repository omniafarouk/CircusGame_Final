package DesignPatterns;

public class HardLevel implements LevelStrategy{
     private int speed = 7;
    private int bombNumber = 10;
    private int giftNumber = 5;
    private int timerNumber = 7;

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