package com.mygdx.bullethell.game;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.bullethell.game.objects.Boundary;
import com.mygdx.bullethell.util.CameraHelper;
import com.mygdx.bullethell.util.Constants;

/**
 * Controls the world and the locations of objects within it.
 * 
 * @author Aaron Wink
 */
public class WorldController extends InputAdapter
{
    private static final String TAG = WorldController.class.getName();
    
    public CameraHelper ch;
	public Stage stage;
	public static int lives;
	public static int bombs;
	public static int power;
	public static int score;
	public static int highscore;
	
	private float gameOverDelay;


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
        lives = Constants.LIVES_START;
        bombs = Constants.BOMBS_START;
        gameOverDelay = 0;
        initStage();
    }
    
    public void initStage()
    {
        score = 0;
        power = 0;
        stage = new Stage(Constants.STAGE_01);
    }
    
    public void update (float dt)
    {
        if (isGameOver())
        {
        	gameOverDelay -= dt;
        	if (gameOverDelay < 0)
        		init();
        } else {
        	handleUserInput(dt);
        }
        stage.update(dt);
        ch.update(dt);
        
        if (isGameOver())
        	gameOverDelay = Constants.GAME_OVER_DELAY;
    }
    
    /**
     * Processes the user's input.
     * @param dt - Change in time since last update.
     */
    private void handleUserInput(float dt)
    {
        //if (ch.hasTarget(stage.sky)) 
        {
        	float mspeed = 64 * dt;
            if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
                mspeed = 28 * dt;
                stage.sky.setFocused(true);
            }
            
            if (Gdx.input.isKeyPressed(Keys.LEFT)) {
                stage.sky.vel.x = -mspeed;
                stage.sky.setMoving(true, false);
            } else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
                stage.sky.vel.x = mspeed;
                stage.sky.setMoving(false, true);
            } else {
            	stage.sky.vel.x = 0;
            	stage.sky.setMoving(false, false);
            }
            
            if (Gdx.input.isKeyPressed(Keys.UP)) {
            	stage.sky.vel.y = mspeed;
            } else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
            	stage.sky.vel.y = -mspeed;
            } else {
            	stage.sky.vel.y = 0;
            }
            
            if (Gdx.input.isKeyPressed(Keys.Z))
                stage.sky.setShooting(true);
            else
                stage.sky.setShooting(false);
        }
    }
    
    @Override
    public boolean keyUp(int keycode)
    {
        // Reset the game world.
        if (keycode == Keys.R) 
        {
            init();
            Gdx.app.debug(TAG, "Game world has been reset.");
        }
        return false;
    }
    
    public boolean isGameOver()
    {
    	return lives < 0;
    }
}