
package GameObject;

public class Clock extends Shape {
    
    public Clock(int posX, int posY, String Path)
    {
        super(posX , posY , 40 , 40 , Path);
        
    }

    @Override
    public ShapeType getShapeType() {
        return ShapeType.CLOCK;
    }
}