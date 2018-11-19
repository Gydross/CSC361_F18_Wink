package com.mygdx.bullethell.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.bullethell.game.objects.AbstractGameObject;
import com.mygdx.bullethell.game.objects.Boundary;
import com.mygdx.bullethell.game.objects.Sky;

/**
 * The class that loads levels and handles populating them with objects 
 *   based on a level image.
 *   
 * @author Aaron Wink
 */
public class Stage
{
    public static final String TAG = Stage.class.getName();
    
    public enum BLOCK_TYPE
    {
        EMPTY(0, 0, 0),  // Black
        PLAYER(255, 255, 255),  // White
        BOSS(255, 0, 0);  // Red
        
        private int color;
        
        private BLOCK_TYPE(int r, int g, int b)
        {
            color = r << 24 | g << 16 | b << 8 | 0xff;
        }
        
        public boolean sameColor(int color)
        {
            return this.color == color;
        }
        
        public int getColor()
        {
            return color;
        }
    }
    
    // Objects
    public Boundary bounds;
    public Sky sky;
    
    // Decoration
    
    public Stage(String filename)
    {
        init(filename);
    }
    
    private void init(String filename)
    {
        // Objects
        bounds = new Boundary();
        sky = null;
        
        // Load level file
        Pixmap px = new Pixmap(Gdx.files.internal(filename));
        
        // Scan pixels, top left bottom right
        int lastpx = -1;
        for (int pxY = 0; pxY < px.getHeight(); pxY++)
        {
            for (int pxX = 0; pxX < px.getWidth(); pxX++)
            {
                AbstractGameObject obj = null;
                float offsetHeight = 0;
                
                // Height grows, bottom to top
                float baseHeight = px.getHeight() - pxY;
                
                // Get color of pixel
                int curpx = px.getPixel(pxX, pxY);
                
                if (BLOCK_TYPE.EMPTY.sameColor(curpx))  // Empty space
                {} 
                else if (BLOCK_TYPE.PLAYER.sameColor(curpx))  // Player
                {
                    obj = new Sky();
                    obj.pos.set(pxX, baseHeight * obj.dim.y + offsetHeight);
                    sky = (Sky)obj;
                }
                else if (BLOCK_TYPE.BOSS.sameColor(curpx))  // Boss
                {
                    
                }
                else  // Unknown
                {
                    Gdx.app.error(TAG, "Object not recognized, please double check the ENUM.");
                }
                lastpx = curpx;
            }
        }
        
        // Decoration
        
        // Free up some memory
        px.dispose();
        Gdx.app.debug(TAG, "Stage loaded.");
    }
    
    public void update(float dt)
    {
    	bounds.update(dt);
    	sky.update(dt);
    }
    
    public void render(SpriteBatch bat)
    {
        bounds.render(bat);
        sky.render(bat);
    }
}
