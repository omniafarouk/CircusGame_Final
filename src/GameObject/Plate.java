package GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Plate extends Shape implements ShapeWithColor {
    
    private Color color;
    
    public Plate(int posX , int posY , String Path , Color color)
    {
        super(posX , posY , 40 , 40 , Path);
        this.color=color;
    }    
    @Override
    public ShapeType getShapeType() {
        return ShapeType.PLATE;
    }
    
    @Override
    public Color getColor() {
        return this.color;
    }
    
}