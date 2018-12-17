package com.mygdx.bullethell.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
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
	
	private TextureRegion sky;
    private  Animation skyNormal;
    private  Animation skyLeft;
    private  Animation skyRight;
	
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
		dim.set(3, 4);
		this.sky = Assets.instance.sky.sky;
		skyNormal = Assets.instance.sky.skyNormal;
		skyLeft = Assets.instance.sky.skyLeft;
		skyRight = Assets.instance.sky.skyRight;
		setAnimation(skyNormal);
		
		origin.set(dim.x / 2, dim.y / 2);
		
		bounds.set(1.125f, 1.625f, 1.875f, 2.375f);
		termVel.set(2.25f,2.25f);
		friction.set(0,0);
		
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
		if (leftPressed && !rightPressed) {
			movedir = MOVE_DIR.LEFT;
			setAnimation(skyLeft);
		} else if (!leftPressed && rightPressed) {
			movedir = MOVE_DIR.RIGHT;
			setAnimation(skyRight);
		} else {		
			movedir = MOVE_DIR.CENTER;
			setAnimation(skyNormal);
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
		
		//if (vel.x != 0)
		//	movedir = vel.x < 0 ? MOVE_DIR.LEFT : MOVE_DIR.RIGHT;
		
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
		
		scale.set(0.625f * 0.25f,0.25f);
		
		reg = (TextureRegion) anim.getKeyFrame(stateTime, true);
		
		bat.draw(reg.getTexture(), pos.x, pos.y, origin.x, origin.y, 
				dim.x, dim.y, scale.x, scale.y, rot, reg.getRegionX(),
				reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(),
				false, false);
		
		bat.setColor(1, 1, 1, 1);
	}

	/**
	 * Sets Sky's shooting state.
	 * @param shoot - Determines whether she is shooting or not.
	 */
	public void setShooting(boolean shoot) 
	{
		isShooting = shoot;
	}

}
