package GameObject;

public class Bomb extends Shape {

    public Bomb(int posX, int posY, String path) {
        super(posX, posY, 40, 40, path);
    }

    @Override
    public ShapeType getShapeType() {
        return ShapeType.BOMB;
    }

}
