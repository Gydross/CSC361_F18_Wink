package com.mygdx.bullethell.game;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.bullethell.util.CameraHelper;
import com.mygdx.bullethell.util.Constants;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Controls the world and the locations of objects within it.
 * 
 * @author Aaron Wink
 */
public class WorldController extends InputAdapter
{
    @SuppressWarnings("unused")
    private static final String TAG = WorldController.class.getName();
    
    public Sprite[] testSprites;
    public int selSprite;  // The currently selected sprite.
    public CameraHelper ch;
    
    /**
     * Create
     */
    public WorldController()
    {
        init();
    }
    
    private void init()
    {
        Gdx.input.setInputProcessor(this);
        ch = new CameraHelper();
        initTestObjects();
    }
    
    /**
     * Initializes a variety of test objects to ensure the controller works.
     * Will be deleted later.
     */
    private void initTestObjects()
    {
        testSprites = new Sprite[5];
        Array<TextureRegion> reg = new Array<TextureRegion>();
        reg.add(Assets.instance.sky.sky);
        reg.add(Assets.instance.frond.frond);
        reg.add(Assets.instance.fern.fern);
        
        // Create the sprites.
        for (int i = 0; i < testSprites.length; i++)
        {
            Sprite spr = new Sprite(reg.random());
            
            // Scaling constants may be found in Constants.
            spr.setSize(spr.getWidth() * Constants.SCALETWO, 
                    spr.getHeight() * Constants.SCALETWO);
            spr.setOrigin(spr.getWidth() / 2.0f, spr.getHeight() / 2.0f);
            
            // Calculates a random position for the new sprite.
            float randX = MathUtils.random(-1.0f, 1.0f);
            float randY = MathUtils.random(-1.0f, 1.0f);
            spr.setPosition(randX, randY);
            
            testSprites[i] = spr;
        }
        
        selSprite = 0;  // Sets first sprite as currently selected.
    }
    
    public void update (float dt)
    {
        handleInput(dt);
        rotateBullet(dt);
        ch.update(dt);
    }
    
    /**
     * Processed the debug input.
     * @param dt - Change in time since last update.
     */
    private void handleInput(float dt)
    {
        if (Gdx.app.getType() != ApplicationType.Desktop)
            return;
        
        // Movement speed
        float mspeed = 3 * dt;
        if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT))
        {
            mspeed = 1.5f * dt;
        }
        
        if (Gdx.input.isKeyPressed(Keys.LEFT))
            moveSelectedSprite(-mspeed, 0);

        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            moveSelectedSprite(mspeed, 0);
        
        if (Gdx.input.isKeyPressed(Keys.UP))
            moveSelectedSprite(0, mspeed);
        
        if (Gdx.input.isKeyPressed(Keys.DOWN))
            moveSelectedSprite(0, -mspeed);
    }
    
    /**
     * Moves the currently selected sprite to a new position.
     * @param x - The desired x coord.
     * @param y - The desired y coord.
     */
    private void moveSelectedSprite(float x, float y)
    {
        testSprites[selSprite].translate(x,y);
    }
    
    /**
     * Updates the rotation of a specific bullet.
     * @param bullet - The sbullet to be rotated.
     * @param dt - Change in time since last update
     */
    private void rotateBullet(float dt)
    {
        //float rot = testSprites[selSprite].getRotation();  // Current sprite rotation
        //rot += 90 * dt;  // Rotates by 90deg/sec
        //rot %= 360;      // Wraps rotation @ 360deg
        //testSprites[selSprite].setRotation(rot);
    }
}
