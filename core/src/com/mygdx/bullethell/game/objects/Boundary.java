package com.mygdx.bullethell.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    float scaleOpt = Constants.SCALEONE;
    
    /**
     * The individual boundary internal class.
     */
    private class Bound extends AbstractGameObject
    {
        float scaleX = 1;
        float scaleY = 1;
        
        /**
         * Create
         * @param scaleX - The horizontal scaling (for rendering)
         * @param scaleY - The vertical scaling (for rendering)
         */
        public Bound(float scaleX, float scaleY) 
        {
            this.scaleX = scaleX;
            this.scaleY = scaleY;
        }
        
        /**
         * A customized render method to support the unique needs of this object.
         * @param bat - The SpriteBatch for rendering.
         * @param x - The x coordinate the texture will be rendered at.
         * @param y - The y coordinate the texture will be rendered at.
         * @param scaleX - The horizontal scaling of the texture.
         * @param scaleY - The vertical scaling of the texture.
         */
        public void render(SpriteBatch bat, float x, float y, float scaleX, float scaleY)
        {
            bat.draw(Assets.instance.boundary.bound, x, y, scaleX, scaleY);
        }

        // Does nothing; implemented for compatibility
        @Override
        public void render(SpriteBatch bat) {}
        
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
     * The play space is 384px x 368px, so let's work off of that.
     * 
     * Average level length is 3232px.
     */
    private void init()
    {
        bounds = new Array<Bound>(numBounds);
        
        createBox(2, Constants.PLAY_HEIGHT/8, 0, 2);
        createBox(Constants.PLAY_WIDTH/8, 1, 2, 8);
        createBox(Constants.PLAY_WIDTH/8, 1, 2, 960);
        createBox(2, Constants.PLAY_HEIGHT/8, 789, Constants.PLAY_HEIGHT);
    }
    
    
    /**
     * Creates each boundary, assigns them properties, and adds them to the array list.
     * @param w - The width of the boundary
     * @param h - The height of the boundary
     * @param f - The desired x position of the boundary
     * @param g - The desired y position of the boundary
     */
    private void createBox(float w, float h, float f, float g)
    {
        
        Bound box = new Bound(w * scaleOpt, h * scaleOpt);
        
        // Ensures origin is set at (0, 0)
        Vector2 orig = new Vector2();
        orig.x = 0;
        orig.y = 0;
        
        box.origin.set(orig);
        
        bounds.add(box);
    }
    
    @Override
    public void render(SpriteBatch bat)
    {
        for (Bound bound : bounds)
            bound.render(bat, bound.pos.x, bound.pos.y, bound.scaleX, bound.scaleY);
        
        
    }

}
