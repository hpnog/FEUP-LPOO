package jumpyjay.resclasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The Class SimpleButton.
 */
public class SimpleButton {
	 
    /** The skin. */
    private Sprite skin;
 
    /**
     * Instantiates a new simple button.
     *
     * @param texture the texture
     * @param x the x coordinate
     * @param y the y coordinate
     * @param width the width
     * @param height the height
     */
    public SimpleButton(Texture texture, float x, float y, float width, float height) {
      skin = new Sprite(texture); // your image
      skin.setPosition(x, y);
      skin.setSize(width, height);
    }
 
    /**
     * Update.
     *
     * @param input_x the input_x
     * @param input_y the input_y
     */
    public void update (float input_x, float input_y) {
        checkIfClicked(input_x, input_y);
    }
    
    /**
     * Draw.
     *
     * @param b the batch
     */
    public void draw(SpriteBatch b)
    {
    	b.draw(skin.getTexture(), skin.getX(), skin.getY(), skin.getWidth(), skin.getHeight());
    }
 
    /**
     * Draw.
     *
     * @param b the batch
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public void draw(SpriteBatch b, int x, int y)
    {
    	b.draw(skin.getTexture(), x, y, skin.getWidth(), skin.getHeight());
    }
 
    
    /**
     * Check if clicked.
     *
     * @param ix the initial x coordinate
     * @param iy the initial y coordinate
     * @return true, if clicked
     */
    public boolean checkIfClicked (float ix, float iy) {
        if (ix > skin.getX() && ix < skin.getX() + skin.getWidth()) {
            if (iy > skin.getY() && iy < skin.getY() + skin.getHeight()) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Gets the x coordinate.
     *
     * @return the x coordinate
     */
    public int getX()
    {
    	return (int) skin.getX();
    }
    
    /**
     * Gets the y coordinate.
     *
     * @return the y coordinate
     */
    public int getY()
    {
    	return (int) skin.getY();
    }
    
    /**
     * Gets the width.
     *
     * @return the width
     */
    public int getWidth()
    {
    	return (int) skin.getWidth();
    }
    
    /**
     * Gets the height.
     *
     * @return the height
     */
    public int getHeight()
    {
    	return (int) skin.getHeight();
    }
}