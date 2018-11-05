package com.mygdx.bullethell.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

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
    
    public AbstractGameObject()
    {
        pos = new Vector2();
        dim = new Vector2(1, 1);
        origin = new Vector2();
        scale = new Vector2(1, 1);
        rot = 0;
    }
    
    public void update(float dt) {}
    
    public abstract void render(SpriteBatch b);
}
