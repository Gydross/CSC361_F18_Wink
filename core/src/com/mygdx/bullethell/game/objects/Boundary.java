package com.mygdx.bullethell.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.bullethell.game.Assets;
import com.mygdx.bullethell.util.Constants;

/**
 * Establishes boundaries within which the game must be played.
 * 
 * @author Aaron Wink
 */
public class Boundary extends AbstractGameObject
{
    private Array<Bound> bounds;
    
    public int box;                   // The current bounding box
    private final int numBounds = 4;  // There are only four boundaries around the play area
    public int visible = 1;           // May be toggled here for testing.
    
    /**
     * The individual boundary internal class.
     */
    private class Bound extends AbstractGameObject
    {
        private TextureRegion regBound;
        
        public Bound() {}
        
        public void setRegion(TextureRegion region)
        {
            regBound = region;
        }
        
        @Override
        public void render(SpriteBatch bat)
        {
            TextureRegion reg = regBound;
            bat.draw(reg.getTexture(), pos.x + origin.x, pos.y + origin.y, origin.x, 
                    origin.y, dim.x, dim.y, scale.x, scale.y, rot, reg.getRegionX(),
                    reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(),
                    false, false);
        }
        
    }
    
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
     * The play space is 773 x 944, so let's work off of that.
     * 
     * Average level length is 3232px.
     */
    private void init()
    {
        bounds = new Array<Bound>(numBounds);
        int w;  // The width of the boundary
        int h;  // The height of the boundary
        
        createBox(16, Constants.PLAY_HEIGHT, 0, Constants.PLAY_HEIGHT);
        createBox(Constants.PLAY_WIDTH, 8, 16, 8);
        createBox(Constants.PLAY_WIDTH, 8, 16, 960);
        createBox(16, Constants.PLAY_HEIGHT, 789, Constants.PLAY_HEIGHT);
        /*
        for (box = 0; box < numBounds; box++)
        {
            switch (box)
            {
                case 0: 
                    // This is the left boundary.
                    w = 16;
                    h = Constants.PLAY_HEIGHT;
                    createBox(w, h, 0, h);
                    break;

                case 1:
                 // This is the upper boundary.
                    w = Constants.PLAY_WIDTH;
                    h = 8;
                    createBox(w, h, 16, 8);
                    break;

                case 2:
                 // This is the lower boundary.
                    w = Constants.PLAY_WIDTH;
                    h = 8;
                    createBox(w, h, 16, h);
                    break;

                case 3:
                 // This is the right boundary.
                    w = 16;
                    h = Constants.PLAY_HEIGHT;
                    createBox(w, h, 789, h);
                    break;
            }
        }*/
    }
    
    
    /**
     * Creates each boundary, assigns them properties, and adds them to the array list.
     * @param w - The width of the boundary
     * @param h - The height of the boundary
     * @param posx - The desired x position of the boundary
     * @param posy - The desired y position of the boundary
     */
    private void createBox(float w, float h, int posx, int posy)
    {
        Bound box = new Bound();
        
        // Sets the scaling for the boundary; SCALETWO is the 2x2px resolution 
        // conversion from meters to pixels, as specified in Constants.
        box.dim.set(w * Constants.SCALETWO, h * Constants.SCALETWO);
        box.setRegion(Assets.instance.boundary.bound);
        
        // Ensures origin is set at (0, 0)
        Vector2 orig = new Vector2();
        orig.x = 0;
        orig.y = 0;
        
        // Sets the new position
        Vector2 position = new Vector2();
        position.x = posx * Constants.SCALETWO;
        position.y = posy * Constants.SCALETWO;
        
        box.pos.set(position);
        box.origin.set(orig);
        
        bounds.add(box);
    }
    
    @Override
    public void render(SpriteBatch bat)
    {
        for (Bound bound : bounds)
            bound.render(bat);
    }

}
