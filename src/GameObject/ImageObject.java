package GameObject;

import eg.edu.alexu.csd.oop.game.GameObject;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageObject implements GameObject{
    
    private static final int MAX_MSTATE = 1;
    private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
    private int x;
    private int y;
    private boolean visible = true ;
    boolean horizontalOnly;
    String path;

    public ImageObject(int x, int y, String path , boolean horizontalOnly) {
        this.x = x;
        this.y = y;
        this.horizontalOnly=horizontalOnly;
        this.path =path;
        try {
		spriteImages[0] = ImageIO.read(getClass().getResourceAsStream(path));
	} catch (IOException e) {
            System.out.println("error");
	}
    }
    @Override public int getX() {   return this.x; }

    @Override  public void setX(int x) { this.x = x;   }

    @Override public int getY() {  return this.y ; }

    @Override public void setY(int y) {  
        if(horizontalOnly)
            return;
        this.y = y ;
    }
    @Override  public int getWidth() { return this.getSpriteImages()[0].getWidth(); }
    
    @Override public int getHeight() { return this.getSpriteImages()[0].getHeight(); }

    @Override public boolean isVisible() { return this.visible; }

    @Override public BufferedImage[] getSpriteImages() { return spriteImages; }
    
    
}