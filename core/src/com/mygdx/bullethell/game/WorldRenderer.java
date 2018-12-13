package com.mygdx.bullethell.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.bullethell.game.objects.Boundary;
import com.mygdx.bullethell.util.Constants;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Draws the world.
 * 
 * @author Aaron Wink
 */
public class WorldRenderer implements Disposable
{
    public static OrthographicCamera cam;
    private SpriteBatch bat;
    private WorldController wc;
    public Boundary bounds;
    public static final int ppm = 16;
    
    /**
     * Create
     * @param wc - The WorldController instance the renderer tracks.
     */
    public WorldRenderer(WorldController wc)
    {
        this.wc = wc;
        init();
    }
    
    private void init()
    {
        bat = new SpriteBatch();
        cam = new OrthographicCamera(Constants.VIEWPORT_WIDTH / ppm, Constants.VIEWPORT_HEIGHT / ppm);
        cam.position.set(0, 0, 0);
        cam.project(cam.position);
        cam.setToOrtho(false, Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        cam.position.set(Constants.VIEWPORT_WIDTH/2, Constants.VIEWPORT_HEIGHT/2, 0);
        
        cam.update();
    }
    
    /**
     * Renders the game world.
     */
    public void render()
    {
        renderWorld(bat);
    }
    
    private void renderWorld(SpriteBatch bat)
    {
        //wc.ch.applyTo(cam);
        bat.setProjectionMatrix(cam.combined);
        bat.begin();
        wc.stage.render(bat);
        bat.end();
    }
    
    /**
     * Resizes the window.
     * @param w - The desired width.
     * @param h - The desired height.
     */
    public void resize(int w, int h)
    {
    	float aspectRatio = w/h;
    	
        //cam.position.set(wc.stage.sky.pos.x, wc.stage.sky.pos.y, 0);
        cam.update();
    }
    
    public static Vector3 project(Vector3 in)
    {
    	return cam.project(in);
    }
    
    @Override
    public void dispose()
    {
        bat.dispose();
    }
}