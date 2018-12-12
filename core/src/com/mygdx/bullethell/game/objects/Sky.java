package com.mygdx.bullethell.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.bullethell.game.Assets;
import com.mygdx.bullethell.util.Constants;

/**
 * The player character, Sky.
 * 
 * @author Aaron Wink
 */
public class Sky extends AbstractGameObject
{
	public static final String TAG = Sky.class.getName();
	
	public boolean isFocus;			// Is the player moving slowly?
	public boolean isMoving;		// Is the player moving?
	public boolean isShooting;		// Is the player shooting?
	public boolean isInvincible;	// Is the player invulnerable?
	public float timeInvincible;	// How long is the player invincible?
	public int power;				// Player's current power level, 0 - 255.
	
	public enum MOVE_DIR { LEFT, CENTER, RIGHT }
	
	private final float mspeed = 2;		// Regular movement speed
	private final float focusSpeed = 1;	// Focused movement speed
	
	private TextureRegion sky;
	public MOVE_DIR movedir;

	/**
	 * Create
	 */
	public Sky()
	{
		init();
	}
	
	public void init()
	{
		dim.set(24 * boundScale, 32 * boundScale);
		this.sky = Assets.instance.sky.sky;
		
		origin.set(dim.x / 2, dim.y / 2);
		
		bounds.set(9 * boundScale, 13 * boundScale, 15 * boundScale, 19 * boundScale);
		
		isFocus = false;
		isMoving = false;
		isShooting = false;
		isInvincible = false;
		timeInvincible = -1;
		power = 0;
	}
	
	/**
	 * Changes moving states depending on whether Sky is moving or not.
	 * @param leftPressed - Is the left key pressed?
	 * @param rightPressed - Is the right key pressed?
	 */
	public void setMoving(boolean leftPressed, boolean rightPressed)
	{
		switch (movedir)
		{
			case LEFT:
				if (leftPressed && !rightPressed)
					movedir = MOVE_DIR.LEFT;
				break;
				
			case RIGHT:
				if (!leftPressed && rightPressed)
					movedir = MOVE_DIR.RIGHT;
				break;
				
			default:
				movedir = MOVE_DIR.CENTER;
				break;
		}
	}
	
	public void setFocused(boolean focused)
	{
		isFocus = focused;
	}
	
	public boolean getFocused()
	{
		return isFocus;
	}
	
	/**
	 * If the player is to become invincible, set the appropriate boolean
	 *   and set timeInvincible.
	 * @param invincible - Is the player character supposed to be invincible
	 *   now or not?
	 */
	public void setInvincible(boolean invincible)
	{
		isInvincible = invincible;
		if (invincible)
			timeInvincible = Constants.INVINCIBILITY_DURATION;
	}
	
	public boolean getInvincible()
	{
		return isInvincible;
	}
	
	@Override
	public void update(float dt)
	{
		super.update(dt);
		if (vel.x != 0)
			movedir = vel.x < 0 ? MOVE_DIR.LEFT : MOVE_DIR.RIGHT;
		
		if (timeInvincible > 0)
		{
			timeInvincible -= dt;
			if (timeInvincible < 0) {
				timeInvincible = 0;
				setInvincible(false);
			}
		}
	}
	
	@Override
	public void render(SpriteBatch bat) 
	{
		TextureRegion reg = null;
		
		if (isInvincible)
			bat.setColor(0.8f, 0.0f, 0.8f, 0.75f);
		scale.set(2+(sc),3+(sc*9));
		reg = sky;
		bat.draw(reg.getTexture(), pos.x, pos.y, origin.x, origin.y, 
				dim.x, dim.y, scale.x, scale.y, rot, reg.getRegionX(),
				reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(),
				false, false);
		
		bat.setColor(1, 1, 1, 1);
	}

}
