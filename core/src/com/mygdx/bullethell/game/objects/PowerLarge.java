package com.mygdx.bullethell.game.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.bullethell.game.Assets;
import com.mygdx.bullethell.game.WorldController;

/**
 * The large power pickup.
 * 
 * @author Aaron Wink
 */
public class PowerLarge extends ItemParent
{
	/**
	 * Create
	 */
	public PowerLarge()
	{
		init();
	}
	
	private void init()
	{
		this.myReg = getTexReg();
		dim.set(16 * boundScale, 16 * boundScale);
		origin.set(dim.x / 2, dim.y / 2);
		bounds.set(0, 0, dim.x, dim.y);
		termVel.y = 0.075f;
		
		collected = false;
		outOfBounds = false;
	}
	
	@Override
	protected TextureRegion getTexReg() {
		return Assets.instance.pickups.pickup[3];
	}

	@Override
	public void specialFunction() {
		WorldController.power += 10;
	}

}
