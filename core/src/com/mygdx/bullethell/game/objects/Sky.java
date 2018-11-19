package com.mygdx.bullethell.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.bullethell.game.Assets;

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
		dim.set(1, 1);
		this.sky = Assets.instance.sky.sky;
		
		origin.set(dim.x / 2, dim.y / 2);
		bounds.set(0, 0, dim.x, dim.y);
		
		isFocus = false;
		isMoving = false;
		isShooting = false;
		isInvincible = false;
		timeInvincible = -1;
		power = 0;
	}
	
	public void setFocused(boolean focused)
	{
		isFocus = focused;
	}
	
	public boolean getFocused()
	{
		return isFocus;
	}
	
	public void setInvincible(boolean invincible)
	{
		isInvincible = invincible;
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
		
		reg = sky;
		bat.draw(reg.getTexture(), pos.x, pos.y, origin.x, origin.y, 
				dim.x, dim.y, scale.x, scale.y, rot, reg.getRegionX(),
				reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(),
				false, false);
		
		bat.setColor(1, 1, 1, 1);
	}

}
