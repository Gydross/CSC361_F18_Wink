package com.mygdx.bullethell.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.bullethell.util.Constants;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Draws the world.
 * 
 * @author Aaron Wink
 */
public class WorldRenderer implements Disposable
{
    private OrthographicCamera cam;
    private SpriteBatch bat;
    private WorldController wc;
    
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
        cam = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        cam.position.set(0,0,0);
        cam.update();
    }
    
    /**
     * Renders the game world.
     */
    public void render()
    {
        renderTestObjects();
    }
    
    /**
     * Renders the ugly test squares.
     */
    private void renderTestObjects()
    {
        bat.setProjectionMatrix(cam.combined);
        bat.begin();
        for (Sprite sprite : wc.testSprites)
            sprite.draw(bat);
        
        bat.end();
    }
    
    /**
     * Resizes the window.
     * @param w - The desired width.
     * @param h - The desired height.
     */
    public void resize(int w, int h)
    {
        cam.viewportWidth = (Constants.VIEWPORT_HEIGHT / h) * w;
        cam.update();
    }
    
    @Override
    public void dispose()
    {
        bat.dispose();
    }
}