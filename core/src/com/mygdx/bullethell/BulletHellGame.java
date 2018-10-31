package com.mygdx.bullethell;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
<<<<<<< Updated upstream
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BulletHellGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
=======
import com.badlogic.gdx.assets.AssetManager;
import com.mygdx.bullethell.game.Assets;


/**
 * Loads up the game.
 * @author Aaron Wink
 */
public class BulletHellGame implements ApplicationListener
{
    @SuppressWarnings("unused")
    private static final String TAG = BulletHellGame.class.getName();
    private WorldController wc;
    private WorldRenderer wr;
    private boolean isPause;
    
    @Override
    public void create()
    {
        // Set LibGDX log level to DEBUG.
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        
        // Loads assets.
        Assets.instance.init(new AssetManager());
        
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
        Gdx.gl.glClearColor(170.0f/255.0f, 170.0f/255.0f, 170.0f/255.0f, 255.0f);
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
        Assets.instance.dispose();
    }

>>>>>>> Stashed changes
}
