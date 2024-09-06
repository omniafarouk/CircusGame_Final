
package GameObject;

public class Gift extends Shape {
    
     public Gift(int posX ,int posY , String Path)
     {
         super(posX , posY , 40 , 40 , Path);
     }

    @Override
    public ShapeType getShapeType() {
        return ShapeType.GIFT;
    }
}