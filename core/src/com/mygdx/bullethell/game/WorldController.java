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
import com.mygdx.bullethell.game.objects.ItemParent;
import com.mygdx.bullethell.util.CameraHelper;
import com.mygdx.bullethell.util.Constants;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.bullethell.game.objects.Sky;
import com.mygdx.bullethell.game.objects.UIoverlay;

/**
 * Controls the world and the locations of objects within it.
 * 
 * @author Aaron Wink
 */
public class WorldController extends InputAdapter
{
    private static final String TAG = WorldController.class.getName();
    
    public CameraHelper ch;
	public static Stage stage;
	public static int lives;
	public static int bombs;
	public static int power;
	public static int score;
	public static int highscore;
    public static World b2world;
    
    // Rectangles for collision detection
    private Rectangle r1 = new Rectangle();
    private Rectangle r2 = new Rectangle();
	
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
        initPhysics();
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
        	float mspeed = 1028 * dt;
            if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
                mspeed = 112 * dt;
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
            	stage.sky.vel.y = mspeed+0.325f;
            } else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
            	stage.sky.vel.y = -(mspeed+0.325f);
            } else {
            	stage.sky.vel.y = 0;
            }
            
            if (Gdx.input.isKeyPressed(Keys.Z))
                stage.sky.setShooting(true);
            else
                stage.sky.setShooting(false);
        }
    }
    
    /**
     * Initializes the box2D physics.
     */
    protected void initPhysics()
    {
    	if (b2world != null)
    		b2world.dispose();
    	b2world = new World(new Vector2(0, 0), true);
    	
    	// Item drops
    	Vector2 origin = new Vector2();
    	for (ItemParent item : stage.items)
    	{
    		BodyDef def = new BodyDef();
        	def.type = BodyType.DynamicBody;
        	def.position.set(item.pos);
        	
        	Body body = b2world.createBody(def);
        	item.body = body;
        	PolygonShape ps = new PolygonShape();
        	origin.x = item.bounds.width / 2.0f;
        	origin.y = item.bounds.height / 2.0f;
        	
        	ps.setAsBox(item.bounds.width / 2.0f, 
        			item.bounds.height / 2.0f, origin, 0);
        	
        	FixtureDef fd = new FixtureDef();
        	fd.shape = ps;
        	body.createFixture(fd);
        	ps.dispose();
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
    
    private void skyBorderColl(UIoverlay ui) {};
    private void skyCollectItem(ItemParent ip) {};
    
    private void collisions(float dt)
    {
    	r1.set(stage.sky.pos.x, stage.sky.pos.y, 
    			stage.sky.bounds.width, stage.sky.bounds.height);
    	
    	// Collision: Sky <-> UI Overlay (boundaries of game)
    	//for ()
    	{
    		
    	}
    		
    	// Collision: Sky <-> All items
    	for (ItemParent item : stage.items)
    	{
    		item.collected = true;
    		item.specialFunction();
    	}
    }
}