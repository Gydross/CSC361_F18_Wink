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
	@Override
	protected TextureRegion getTexReg() {
		return Assets.instance.pickups.pickup[0];
	}

	@Override
	protected void specialFunction() {
		WorldController.lives += 1;
	}

}
