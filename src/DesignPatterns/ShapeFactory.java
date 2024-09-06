package DesignPatterns;

import GameObject.*;
import java.awt.Color;

public abstract class ShapeFactory {

    static int clockno, giftno, bombno;

    public static Shape CreatingRandomly(int screenHeight, int screenWidth, LevelStrategy strategy) {
        int min = 0;
        int max = 7;
        int randomNumber = (int) (Math.random() * (max - min) + min);
        switch (randomNumber % 6) {
            case 0 -> {
                return new Plate((int) (Math.random() * screenWidth), (int) (Math.random() * screenHeight / 5), "/greenplate.png", Color.GREEN);
            }
            case 1 -> {
                return new Plate((int) (Math.random() * screenWidth), (int) (Math.random() * screenHeight / 5), "/blueplate.png", Color.BLUE);
            }
            case 2 -> {
                return new Plate((int) (Math.random() * screenWidth), (int) (Math.random() * screenHeight / 5), "/purpleplate.png", Color.MAGENTA);
            }
            case 3 -> {
                if (strategy.getBombNumber() == bombno) {
                    return CreatingRandomly(screenHeight, screenWidth, strategy);
                } else {
                    bombno++;
                    return new Bomb((int) (Math.random() * screenWidth), (int) (Math.random() * screenHeight / 5), "/cutebomb.png");
                }
            }
            case 4 -> {
                if (strategy.getTimerNumber() == clockno) {
                    return CreatingRandomly(screenHeight, screenWidth, strategy);
                } else {
                    clockno++;
                    return new Clock((int) (Math.random() * screenWidth), (int) (Math.random() * screenHeight / 5), "/pinktimer.png");
                }
            }
            case 5 -> {
                if (strategy.getGiftNumber() == giftno) {
                    return CreatingRandomly(screenHeight, screenWidth, strategy);
                } else {
                    giftno++;
                    return new Gift((int) (Math.random() * screenWidth), (int) (Math.random() * screenHeight / 5), "/cutegift.png");
                }
            }
            default -> {
                return CreatingRandomly(screenHeight, screenWidth, strategy);
            }
        }
    }
}