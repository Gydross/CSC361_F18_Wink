package com.mygdx.bullethell.game;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
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
    
    /**
     * Creates a procedural pixmap.
     * @param w - The width of the pixmap.
     * @param h - The height of the pixmap.
     * @return px - The generated pixmap.
     */
    private Pixmap createProceduralPixmap(int w, int h)
    {
        Pixmap px = new Pixmap(w, h, Format.RGBA8888);
        
        // Fill square with red @ half alpha
        px.setColor(1,0,0,0.5f);
        px.fill();
        
        // Draw a yellow X on square
        px.setColor(1,1,0,1);
        px.drawLine(0, 0, w, h);
        px.drawLine(w, 0, 0, h);
        
        // Draw a cyan border on square
        px.setColor(0,1,1,1);
        px.drawRectangle(0, 0, w, h);
        
        return px;
    }
    
    public void update (float dt)
    {
        handleUserInput(dt);
        //updateRotation(dt);
        ch.update(dt);
    }
    
    /**
     * Processed the debug input.
     * @param dt - Change in time since last update.
     */
    private void handleUserInput(float dt)
    {
        if (Gdx.app.getType() != ApplicationType.Desktop)
            return;
        
        float mspeed = 2 * dt;
        if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT))
            mspeed = 1 * dt;
        
        if (Gdx.input.isKeyPressed(Keys.LEFT))
            moveSelectedSprite(-mspeed, 0);

        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            moveSelectedSprite(mspeed, 0);
        
        if (Gdx.input.isKeyPressed(Keys.UP))
            moveSelectedSprite(0, mspeed);
        
        if (Gdx.input.isKeyPressed(Keys.DOWN))
            moveSelectedSprite(0, -mspeed);
        
        //if (Gdx.input.isKeyPressed(Keys.Z))
        //  Sky.shoot();
    }
    
    /**
     * Another part of these annoying ass debug methods.
     * @param x - The desired x coord.
     * @param y - The desired y coord.
     */
    /*
    private void moveCamera(float x, float y)
    {
        x += ch.getPos().x;
        y += ch.getPos().y;
        ch.setPos(x, y);
    }*/
    
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
     * Updates the rotation of the test sprites.
     * @param dt - Change in time since last update
     */
    private void updateRotation(float dt)
    {
        float rot = testSprites[selSprite].getRotation();  // Current sprite rotation
        rot += 90 * dt;  // Rotates by 90deg/sec
        rot %= 360;      // Wraps rotation @ 360deg
        testSprites[selSprite].setRotation(rot);
    }
    
    @Override
    public boolean keyUp(int keycode)
    {
        // Reset the game world.
        if (keycode == Keys.R) 
        {
            init();
            Gdx.app.debug(TAG, "Game world has been reset.");
        } else if (keycode == Keys.SPACE) {
            selSprite = (selSprite + 1) % testSprites.length;
            // Updates the camera's target.
            if (ch.hasTarget())
                ch.setTarget(testSprites[selSprite]);
            Gdx.app.debug(TAG,  "Sprite #" + selSprite + "selected.");
        } else if (keycode == Keys.ENTER) {
            ch.setTarget(ch.hasTarget() ? null : testSprites[selSprite]);
            Gdx.app.debug(TAG, "Camera follow enabled: " + ch.hasTarget());
        }
            
        return false;
    }
}