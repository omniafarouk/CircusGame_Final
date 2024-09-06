package circusgame;

import DesignPatterns.GameIntializer;
import DesignPatterns.GameSubject;
import DesignPatterns.ShapeFactory;
import GameObject.ImageObject;
import GameObject.*;
import static circusgame.GameStatus.GAME_IN_PROGRESS;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import java.util.LinkedList;
import java.util.List;
import DesignPatterns.LevelStrategy;
import DesignPatterns.Observer;
import DesignPatterns.ShapeIterator;
import java.util.ArrayList;

public class CircusGame implements World, GameIntializer, GameSubject {

    static CircusGame instance = null;

    private static int MAX_TIME = 1 * 60 * 1000;
    private long gameStartTime = System.currentTimeMillis();
    private final List<GameObject> constantObjects = new LinkedList<>();
    private List<GameObject> movableObjects = new LinkedList<>();
    private final List<GameObject> controlableObjects = new LinkedList<>();
    private final int screenWidth = 900;
    private final int screenHeight = 491;
    GameStatus gameStatus = GAME_IN_PROGRESS;
    private static LevelStrategy gameStrategy;
    private GameObject clownObject;
    private ClownStick leftHandStick, rightHandStick;

    private ArrayList<Shape> leftArrayList = new ArrayList<>();
    private ArrayList<Shape> rightArrayList = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();
    private int score = 0;

    private CircusGame() {

        // constantObjects.add(new ImageObject(0, 0, "/mint.jpg", true));
        //   clownObject = new ImageObject(0, (int) (screenHeight * 0.51), "/clown-circus.png", true);
        this.Intialize();
    }

    public static CircusGame getInstance(LevelStrategy gameStrategy) {
        if (instance == null) {
            CircusGame.gameStrategy = gameStrategy;
            instance = new CircusGame();
        }
        return instance;
    }

    @Override
    public void Intialize() {
        constantObjects.add(new ImageObject(0, 0, "/mint.jpg", true));
        clownObject = new ImageObject(0, (int) (screenHeight * 0.51), "/clown-circus.png", true);
        leftHandStick = new ClownStick(0, clownObject.getHeight() - 40, StickSide.LEFT_STICK);
        rightHandStick = new ClownStick(leftHandStick.getX() + 80, clownObject.getHeight() - 40, StickSide.RIGHT_STICK);
        constantObjects.add(leftHandStick);
        constantObjects.add(rightHandStick);
        controlableObjects.add(clownObject);
    }

    @Override
    public List<GameObject> getConstantObjects() {
        return constantObjects;
    }

    @Override
    public List<GameObject> getMovableObjects() {
        return movableObjects;
    }

    @Override
    public List<GameObject> getControlableObjects() {
        return controlableObjects;
    }

    @Override
    public int getWidth() {
        return screenWidth;
    }

    @Override
    public int getHeight() {
        return screenHeight;
    }

    @Override
    public String getStatus() {
        return "SCORE =  " + Math.max(0, score) + " |||   TIME = " + Math.max(0, (MAX_TIME - (System.currentTimeMillis() - gameStartTime)) / 1000);	// /1000 converts msec to sec
    }

    @Override
    public int getSpeed() {
        return gameStrategy.getSpeed();
    }

    @Override
    public int getControlSpeed() {
        return 30;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    private void checkGameStatus() {
        if (System.currentTimeMillis() - gameStartTime > MAX_TIME) {
            this.gameStatus = GameStatus.GAME_ENDED;
        }
    }

    public void checkKind(Shape gameObject, ArrayList side) {

        switch (gameObject.getShapeType()) {
            case BOMB -> {

                instance.updateScore(-10);
                gameObject.setVisible(false);
            }
            case GIFT -> {
                instance.updateScore(10);
                gameObject.setVisible(false);
            }
            case CLOCK -> {
                MAX_TIME += 1000;
                gameObject.setVisible(false);
            }
            case PLATE -> {
                constantObjects.add(gameObject);
                ShapeIterator sideIterator = new ShapeIterator(side);
                plateHandler(sideIterator, side, gameObject);
            }
        }
    }

    public void plateHandler(ShapeIterator sideIterator, ArrayList side, Shape gameObject) {
      
        side.add(gameObject);
       // System.out.println(sideIterator.previousIndex());
        if (sideIterator.previousIndex() > 1) {
           // System.out.println("ana aho");
            if (!side.isEmpty() && sideIterator.isSameColor((Plate) sideIterator.previousShape(sideIterator.previousIndex()), (Plate) sideIterator.beforePreviousShape(sideIterator.previousIndex()))
                    && sideIterator.isSameColor((Plate) sideIterator.previousShape(sideIterator.previousIndex()), (Plate) sideIterator.before2PreviousShape(sideIterator.previousIndex()))) //CheckScore(gameObject,side);
            { //display();
                instance.updateScore(30);

                sideIterator.previousShape(sideIterator.previousIndex()).setVisible(false);
                sideIterator.beforePreviousShape(sideIterator.previousIndex()).setVisible(false);
                sideIterator.before2PreviousShape(sideIterator.previousIndex()).setVisible(false);
                if (sideIterator.previousIndex() == 2) {
                    side.set(0, null);
                    side.set(1, null);
                    side.set(2, null);
                   // System.out.println("ana fady");
                    
                }
                side.remove(sideIterator.previousIndex());
                side.remove(sideIterator.previousIndex());
                side.remove(sideIterator.previousIndex());
            }
        }
    }

    public void display() {
        if (!leftArrayList.isEmpty()) {
            for (int i = 0; i < leftArrayList.size(); i++) {
                leftArrayList.get(i).setX(leftHandStick.getX() - 20);
                leftArrayList.get(i).setY(leftHandStick.getY() + 160 - i * 20);
            }
        }
        if (!rightArrayList.isEmpty()) {
            for (int i = 0; i < rightArrayList.size(); i++) {
                rightArrayList.get(i).setX(rightHandStick.getX() + 60);
                rightArrayList.get(i).setY(rightHandStick.getY() + 160 - i * 20);
            }
        }
    }

    @Override
    public boolean refresh() {
        removeFromMovableObject();
        rightHandStick.setX(clownObject.getX() - 77);
        rightHandStick.setY(clownObject.getY() - 160);
        leftHandStick.setX(clownObject.getX() + 110);
        leftHandStick.setY(clownObject.getY() - 160);
        checkGameStatus();
        List<GameObject> toRemove = new ArrayList<>();
        if (gameStatus.equals(GameStatus.GAME_IN_PROGRESS)) {
            for (GameObject gameObject : movableObjects) {
                if (!gameObject.isVisible()) {
                    toRemove.add(gameObject);
                }
                checkIntersection((Shape) gameObject);
                display();
                if (gameObject instanceof Shape && !constantObjects.contains(gameObject)) {
                    gameObject.setY(gameObject.getY() + 1); //move down // y tezid be wahed 3ashan tetharak
                    if (gameObject.getY() == getHeight()) {
                        gameObject.setY(-1 / 2 * (int) (Math.random() * getHeight()));
                        gameObject.setX((int) (Math.random() * (getWidth() - 120)));
                    }
                }
            }
            if (movableObjects.size() < 8) {
                movableObjects.add(ShapeFactory.CreatingRandomly(screenWidth, screenHeight, gameStrategy));
            }

            movableObjects.removeAll(toRemove);

            return true;
        }
        return false;
    }

    public void removeInvisibleObjects() {
        for (GameObject gameObject : movableObjects) {
            if (!gameObject.isVisible()) {
                movableObjects.remove(gameObject);
            }
        }
    }

    public void removeFromMovableObject() {

        if (constantObjects.size() > 3) {
            for (GameObject leftObj : leftArrayList) {
                for (GameObject constantObj : constantObjects) {

                    if (leftObj.equals(constantObj)) {
                        movableObjects.remove(constantObj);
                    }
                }
            }
            for (GameObject rightObj : rightArrayList) {
                for (GameObject constantObj : constantObjects) {
                    if (rightObj.equals(constantObj)) {
                        movableObjects.remove(constantObj);
                    }
                }
            }
        }
    }

    public void checkIntersection(Shape gameObject) {

        if (leftArrayList.isEmpty() && rightArrayList.isEmpty()) {
            if (intersect(gameObject, leftHandStick)) {
                checkKind(gameObject, leftArrayList);
            } //put on top of stick
            else if (intersect(gameObject, rightHandStick)) {
                checkKind(gameObject, rightArrayList);
            }
            //put on top of stick
        } else if (leftArrayList.isEmpty()) { // rightArray Not empty
            if (intersect(gameObject, leftHandStick)) {
                checkKind(gameObject, leftArrayList);
            } //put on top of stick
            else if (intersect(gameObject, rightArrayList.get(rightArrayList.size() - 1))) {
                checkKind(gameObject, rightArrayList);
            }
            //put on top of right      
        } else if (rightArrayList.isEmpty()) { // leftArray Not empty
            if (intersect(gameObject, rightHandStick)) {
                checkKind(gameObject, rightArrayList);
            } else if (intersect(gameObject, leftArrayList.get(leftArrayList.size() - 1))) {
                checkKind(gameObject, leftArrayList);
            }
        } else {
            if (intersect(gameObject, leftArrayList.get(leftArrayList.size() - 1))) {
                checkKind(gameObject, leftArrayList);
            } else if (intersect(gameObject, rightArrayList.get(rightArrayList.size() - 1))) {
                checkKind(gameObject, rightArrayList);
            }

        }
    }

    public boolean intersect(GameObject o1, GameObject o2) {
        return (Math.abs((o1.getX() + o1.getWidth() / 2) - (o2.getX() + o2.getWidth() / 2)) <= o1.getWidth()) && (Math.abs((o1.getY() + o1.getHeight() / 2) - (o2.getY() + o2.getHeight() / 2)) <= 10);
    }
    
    public void setClownObject(GameObject clownObject) {
        this.clownObject = clownObject;
    }

    @Override

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.updateScore(score);
        }
    }

    public void updateScore(int points) {
        score += points;

        notifyObservers();

    }
}
