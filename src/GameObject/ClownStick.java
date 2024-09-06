package GameObject;

import circusgame.StickSide;
import eg.edu.alexu.csd.oop.game.GameObject;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class ClownStick implements GameObject{
    private static final int MAX_MSTATE = 1;
    private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
    private int x;
    private int y;
    private boolean visible;
    
    public ClownStick(int posX, int posY , StickSide side) {
        
        this.x = posX;
        this.y= posY;
        this.visible = true;
        spriteImages[0] = new BufferedImage(posX+4 , posY+110, BufferedImage.TYPE_INT_ARGB);
        BufferedImage image = this.spriteImages[0];
        Graphics2D g2 = image.createGraphics();
        Color pinkishRed = new Color(255, 10, 100);

        g2.setColor(pinkishRed.darker());
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);        
        int x1 = x;
        int y1 =y;
        Polygon polygon = new Polygon(new int[] {x1, x1+4 ,x1+4, x1}, new int[] {y1, y1, y1+100 , y1+100}, 4);
        
        g2.fill(polygon);
        // Draw the outline of the polygon
        g2.draw(polygon);
        g2.dispose();
    }
    
    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x){
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getWidth() {
        return this.getSpriteImages()[0].getWidth();
    }

    @Override
    public int getHeight() {
        return this.getSpriteImages()[0].getHeight(); 
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return spriteImages;
    }

}