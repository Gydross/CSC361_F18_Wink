package com.mygdx.bullethell;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.mygdx.bullethell.game.WorldController;
import com.mygdx.bullethell.game.WorldRenderer;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.graphics.GL20;


/**
 * Loads up the game.
 * @author Aaron Wink
 */
public class BulletHellGame implements ApplicationListener
{
    private static final String TAG = BulletHellGame.class.getName();
    private WorldController wc;
    private WorldRenderer wr;
    private boolean isPause;
    
    @Override
    public void create()
    {
        // Set LibGDX log level to DEBUG.
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        
        // Initialize controller and renderer.
        wc = new WorldController();
        wr = new WorldRenderer(wc);
        isPause = false;
    }

    /**
     * Directs the WorldRenderer to resize the window.
     * @param w - The desired width.
     * @param h - The desired height.
     */
    @Override
    public void resize(int w, int h)
    {
        wr.resize(w, h);
    }

    /**
     * Updates the game world and directs the WorldRenderer to update rendered images.
     */
    @Override
    public void render()
    {
        // The game world must not update while paused.
        if (!isPause)
        {
            // Update game world
            wc.update(Gdx.graphics.getDeltaTime());
        }
        
        // Sets clear screen color to: some random color I decided would be fine.
        Gdx.gl.glClearColor(243.0f/255.0f, 34.0f/255.0f, 215.0f/255.0f, 255.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        // Render it!
        wr.render();
    }

    @Override
    public void pause()
    {
        isPause = true;
    }

    @Override
    public void resume()
    {
        isPause = false;
    }

    @Override
    public void dispose()
    {
        wr.dispose();
    }

}
