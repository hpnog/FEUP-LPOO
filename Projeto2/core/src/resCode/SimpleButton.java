package resCode;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SimpleButton {
	 
    private Sprite skin;
 
    public SimpleButton(Texture texture, float x, float y, float width, float height) {
      skin = new Sprite(texture); // your image
      skin.setPosition(x, y);
      skin.setSize(width, height);
    }
 
    public void update (float input_x, float input_y) {
        checkIfClicked(input_x, input_y);
    }
    
    public void draw(SpriteBatch b)
    {
    	b.draw(skin.getTexture(), skin.getX(), skin.getY(), skin.getWidth(), skin.getHeight());
    }
 
    public void draw(SpriteBatch b, int x, int y)
    {
    	b.draw(skin.getTexture(), x, y, skin.getWidth(), skin.getHeight());
    }
 
    
    public boolean checkIfClicked (float ix, float iy) {
        if (ix > skin.getX() && ix < skin.getX() + skin.getWidth()) {
            if (iy > skin.getY() && iy < skin.getY() + skin.getHeight()) {
                return true;
            }
        }
        return false;
    }
    
    public int getX()
    {
    	return (int) skin.getX();
    }
    
    public int getY()
    {
    	return (int) skin.getY();
    }
    
    public int getWidth()
    {
    	return (int) skin.getWidth();
    }
    
    public int getHeight()
    {
    	return (int) skin.getHeight();
    }
}