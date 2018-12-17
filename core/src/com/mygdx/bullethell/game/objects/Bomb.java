package com.mygdx.bullethell.game.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.bullethell.game.Assets;
import com.mygdx.bullethell.game.WorldController;
import com.mygdx.bullethell.util.AudioManager;

/**
 * The bomb object.
 * 
 * @author Aaron Wink
 */
public class Bomb extends ItemParent
{
	/**
	 * Create
	 */
	public Bomb()
	{
		init();
	}
	
	private void init()
	{
		this.myReg = getTexReg();
		dim.set(1,1);
		origin.set(dim.x / 2, dim.y / 2);
		bounds.set(0, 0, dim.x, dim.y);
		termVel.y = 0.075f;
		
		collected = false;
		outOfBounds = false;
	}
	
	@Override
	protected TextureRegion getTexReg() {
		return Assets.instance.pickups.pickup[1];
	}

	@Override
	public void specialFunction() {
		WorldController.bombs += 1;
		AudioManager.instance.play(Assets.instance.sounds.powerUp);
	}
	

}
