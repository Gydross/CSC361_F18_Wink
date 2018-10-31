package com.mygdx.bullethell.util;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * Manipulates the camera used in world rendering.
 * 
 * @author Aaron Wink
 */
public class CameraHelper
{
<<<<<<< HEAD
    @SuppressWarnings("unused")
=======
>>>>>>> Milestone1
    private static final String TAG = CameraHelper.class.getName();
    
    private final float MAX_ZOOM_IN = 0.25f;
    private final float MAX_ZOOM_OUT = 10.0f;
    
    private Vector2 pos;
    private float zoom;
    private Sprite tg;
    
    /**
     * Create
     */
    public CameraHelper()
    {
        pos = new Vector2();
        zoom = 1.0f;
    }
    
    /**
     * Updates the position of the camera to the origin of the current target.
     * @param dt - Time since last update.
     */
    public void update (float dt)
    {
        if (!hasTarget())
            return;
        
        pos.x = tg.getX() + tg.getOriginX();
        pos.y = tg.getY() + tg.getOriginY();
    }
    
    /**
     * Sets the position of the camera.
     * @param x - The x coord.
     * @param y - The y coord.
     */
    public void setPos(float x, float y)
    {
        this.pos.set(x,y);
    }
    
    /**
     * Retrieves the current camera position.
     * @return pos - The camera's position.
     */
    public Vector2 getPos()
    {
        return pos;
    }
    
    /**
     * Increments the current zoom value.
     * @param amt - The amount to increment by.
     */
    public void addZoom(float amt)
    {
        setZoom(zoom + amt);
    }
    
    /**
     * Sets the current zoom to a specific value.
     * @param zoom - The desired zoom value.
     */
    public void setZoom(float zoom)
    {
        this.zoom = MathUtils.clamp(zoom, MAX_ZOOM_IN, MAX_ZOOM_OUT);
    }
    
    /**
     * Retrieves the zoom of the camera.
     * @return zoom - The current value of zoom.
     */
    public float getZoom()
    {
        return zoom;
    }
    
    /**
     * Sets the target to a specific sprite.
     * @param tg - The desired new target.
     */
    public void setTarget(Sprite tg)
    {
        this.tg = tg;
    }
    
    /**
     * Retrieves the current target.
     * @return tg - The current target.
     */
    public Sprite getTarget()
    {
        return tg;
    }
    
    /**
     * Is the camera tracking a target?
     * @return true if yes, false if no
     */
    public boolean hasTarget()
    {
        return tg != null;
    }
    
    /**
     * Is the camera tracking a target, and is it THIS target specifically?
     * @param tg - The target for comparison.
     * @return true if both criteria are true; false otherwise.
     */
    public boolean hasTarget(Sprite tg)
    {
        return hasTarget() && this.tg.equals(tg);
    }
    
    /**
     * Applies a new position and a new zoom value to the specified camera.
     * @param cam - The ortho. camera to be updated.
     */
    public void applyTo(OrthographicCamera cam)
    {
        cam.position.x = pos.x;
        cam.position.y = pos.y;
        cam.zoom = zoom;
        cam.update();
    }
}
