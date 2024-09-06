
package GameObject;
import eg.edu.alexu.csd.oop.game.GameObject;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class Shape implements GameObject{

	private int MAX_MSTATE = 1;
	// an array of sprite images that are drawn sequentially
	private BufferedImage[] ShapeImages = new BufferedImage[MAX_MSTATE];
	private int x;
	private int y;
	private int width;
        private int height;

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
        private boolean visible;
	
    public Shape(int posX, int posY , int width , int height){
        
		this.x = posX;
		this.y = posY;
		this.visible = true;
                this.width = width;
                this.height = height;
                ShapeImages[0] = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
		
    }
    public Shape(int posX, int posY , int width , int height , String Path )
    {
       this( posX, posY , width , height);
            try {
                ShapeImages[0] = ImageIO.read(getClass().getResourceAsStream(Path));
            } catch (IOException ex) {
                System.out.println("error in printing shape");
            }

    }
    public void setMAX_MSTATE(int MAX_MSTATE) {
        this.MAX_MSTATE = MAX_MSTATE;
    }
    public abstract ShapeType getShapeType();
    
    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getWidth() {
        return this.width;
    }
    

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public boolean isVisible() {
        return this.visible;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return this.ShapeImages;
    }
}