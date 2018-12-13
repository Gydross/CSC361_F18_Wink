package com.mygdx.bullethell.game.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.bullethell.game.Assets;
import com.mygdx.bullethell.game.WorldController;

/**
 * The small power pickup.
 * 
 * @author Aaron Wink
 */
public class PowerSmall extends ItemParent
{
	/**
	 * Create
	 */
	public PowerSmall()
	{
		super();
		init();
	}
	
	private void init()
	{
		myReg = getTexReg();
		dim.set(8 * boundScale, 8 * boundScale);
		origin.set(dim.x / 2, dim.y / 2);
		bounds.set(0, 0, dim.x, dim.y);
		termVel.y = 0.075f;
		
		collected = false;
		outOfBounds = false;
	}
	
	@Override
	protected TextureRegion getTexReg() {
		return Assets.instance.pickups.pickup[2];
	}

	@Override
	protected void specialFunction() {
		WorldController.power += 1;
	}

}
