package com.mygdx.bullethell.game.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.bullethell.BulletHellGame;
import com.mygdx.bullethell.game.Assets;
import com.mygdx.bullethell.game.WorldController;

/**
 * The extra life object, or 1-Up.
 * Adds one life to the current number of lives.
 * 
 * @author Aaron Wink
 */
public class ExtraLife extends ItemParent
{
	/**
	 * Create
	 */
	public ExtraLife()
	{
		init();
	}
	
	private void init()
	{
		myReg = getTexReg();
		dim.set(16 * boundScale, 16 * boundScale);
		origin.set(dim.x / 2, dim.y / 2);
		bounds.set(0, 0, dim.x, dim.y);
		termVel.y = 0.075f;
		
		collected = false;
		outOfBounds = false;
	}
	
	@Override
	protected TextureRegion getTexReg() {
		return Assets.instance.pickups.pickup[0];
	}

	@Override
	protected void specialFunction() {
		WorldController.lives += 1;
	}

}
