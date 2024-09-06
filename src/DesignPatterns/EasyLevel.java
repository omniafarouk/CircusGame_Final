
package DesignPatterns;

public class EasyLevel implements LevelStrategy {

    private int speed = 28;
    private int bombNumber = 5;
    private int giftNumber = 15;
    private int timerNumber = 16;

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