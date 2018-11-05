package com.mygdx.bullethell.game.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.bullethell.game.WorldController;
import com.mygdx.bullethell.util.Constants;

/**
 * Establishes boundaries within which the game must be played.
 * 
 * @author Aaron Wink
 */
public class Boundary extends AbstractGameObject
{
    WorldController wc;
    public Sprite[] boxes;            // All bounding boxes
    public int box;                   // The current bounding box
    private final int numBounds = 4;  // There are only four boundaries around the play area
    public int visible = 1;           // May be toggled here for testing.
    
    /**
     * Create
     * Calls the init() method.
     */
    public Boundary()
    {
        init();
    }
    
    /**
     * Creates all of the boxes.
     * Based on the assumption that the drawing origin is the lower left of the screen.
     * The play space is 773 x 900, so let's work off of that.
     * 
     * Average level length is 3232px.
     */
    private void init()
    {
        boxes = new Sprite[numBounds];
        int w;  // The width of the boundary
        int h;  // The height of the boundary
        
        for (box = 0; box < numBounds; box++)
        {
            switch (box)
            {
                case 0: 
                    // This is the left boundary.
                    w = 16;
                    h = 960;
                    
                    boxes[box] = createBox(w,h);
                    boxes[box].setOrigin(0, 0);
                    boxes[box].setPosition(0, h);
                    break;

                case 1:
                 // This is the upper boundary.
                    w = 773;
                    h = 8;
                    
                    boxes[box] = createBox(w,h);
                    boxes[box].setOrigin(0, 0);
                    boxes[box].setPosition(16, 8);  
                    break;

                case 2:
                 // This is the lower boundary.
                    w = 773;
                    h = 8;
                    
                    boxes[box] = createBox(w,h);
                    boxes[box].setOrigin(0, 0);
                    boxes[box].setPosition(16, h);
                    break;

                case 3:
                 // This is the right boundary.
                    w = 16;
                    h = 960;
                    
                    boxes[box] = createBox(w,h);
                    boxes[box].setOrigin(0, 0);
                    boxes[box].setPosition(789, h);
                    break;
            }
        }
    }
    
    
    /**
     * Creates a test box texture for the boundaries.
     * @param w - The width of the box
     * @param h - The height of the box
     * @return spr - The sprite and its properties
     */
    private Sprite createBox(int w, int h)
    {
        Pixmap px = new Pixmap(w, h, Format.RGBA8888);
        px.setColor(0.75f, 0, 0.75f, (0.5f * visible));
        px.fill();
        
        px.setColor(1, 1, 0, visible);
        px.drawLine(0, 0, w, h);
        px.drawLine(w, 0, 0, h);
        
        px.setColor(0, 1, 1, visible);
        px.drawRectangle(0, 0, w, h);
        
        Texture tex = new Texture(px);
        Sprite spr = new Sprite(tex);
        
        spr.setSize(spr.getWidth() * Constants.SCALETWO, 
                spr.getHeight() * Constants.SCALETWO);
        
        return spr;
    }
    
    @Override
    public void render(SpriteBatch b)
    {
        //b.setProjectionMatrix(wc.camera.combined);
        
    }

}
