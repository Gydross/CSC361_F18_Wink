package com.mygdx.bullethell.game;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
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
    public World b2world;

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
        //initStage();
    }
    
    public void initStage()
    {
        score = 0;
        power = 0;
        stage = new Stage(Constants.STAGE_01);
    }
    
    public void update (float dt)
    {
        handleUserInput(dt);
        ch.update(dt);
        stage.update(dt);
    }
    
    /**
     * Processes the user's input.
     * @param dt - Change in time since last update.
     */
    private void handleUserInput(float dt)
    {
        if (Gdx.app.getType() != ApplicationType.Desktop)
            return;
        /*
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
        
        if (Gdx.input.isKeyPressed(Keys.Z))
            Sky.shoot(true);
        else
            Sky.shoot(false);
            */
    }
    
    /**
     * Initializes the box2D physics.
     */
    protected void initPhysics()
    {
    	if (b2world != null)
    		b2world.dispose();
    	
    	// UI Overlay
    	Vector2 origin = new Vector2();
    	BodyDef def = new BodyDef();
    	def.type = BodyType.StaticBody;
    	def.position.set(stage.overlay.pos);
    	
    	Body body = b2world.createBody(def);
    	stage.overlay.body = body;
    	PolygonShape ps = new PolygonShape();
    	
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
    
    
}