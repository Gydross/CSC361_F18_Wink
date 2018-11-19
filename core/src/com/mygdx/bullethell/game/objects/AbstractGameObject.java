package com.mygdx.bullethell.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.MathUtils;

/**
 * Holds attributes and method signatures common to all objects.
 * 
 * @author Aaron Wink
 */
public abstract class AbstractGameObject
{
    public Vector2 pos;
    public Vector2 dim;
    public Vector2 origin;
    public Vector2 scale;
    public float rot;  // The rotation of the object.
    public Vector2 vel;
    public Vector2 termVel;
    public Vector2 friction;
    
    public Vector2 accel;
    public Rectangle bounds;
    
    public AbstractGameObject()
    {
        pos = new Vector2();
        dim = new Vector2(1, 1);
        origin = new Vector2();
        scale = new Vector2(1, 1);
        rot = 0;
        vel = new Vector2();
        termVel = new Vector2(1, 1);
        friction = new Vector2();
        accel = new Vector2();
        bounds = new Rectangle();
        
    }
    
    /**
     * Updates the current horizontal speed of the object.
     * @param dt - Change in time
     */
    protected void updateMotionX(float dt)
    {
    	if (vel.x != 0)
    	{
    		// Apply friction to the object.
    		if (vel.x > 0)
    		{
    			vel.x = Math.max(vel.x - friction.x * dt, 0);
    		} else {
    			vel.x = Math.min(vel.x + friction.x * dt, 0);
    		}
    	}
    	
    	// Apply acceleration to the object.
    	vel.x += accel.x * dt;
    	vel.x = MathUtils.clamp(vel.x, -termVel.x, termVel.x);
    }
    
    /**
     * Update the object's vertical speed.
     * @param dt - Change in time
     */
    protected void updateMotionY(float dt)
    {
    	if (vel.y != 0)
    	{
    		// Apply friction to the object.
    		if (vel.y > 0)
    		{
    			vel.y = Math.max(vel.y - friction.y * dt, 0);
    		} else {
    			vel.y = Math.min(vel.y + friction.y * dt, 0);
    		}
    	}
    	
    	// Apply acceleration to the object.
    	vel.y += accel.y * dt;
    	vel.y = MathUtils.clamp(vel.y, -termVel.y, termVel.y);
    }
    
    public void update(float dt)
    {
    	updateMotionX(dt);
    	updateMotionY(dt);
    	
    	pos.x += vel.x * dt;
    	pos.y += vel.y * dt;
    }
    
    public abstract void render(SpriteBatch bat);

    //public abstract void render(SpriteBatch bat, float x, float y);
}
