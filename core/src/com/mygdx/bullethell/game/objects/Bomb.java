package com.mygdx.bullethell.game.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.bullethell.game.Assets;
import com.mygdx.bullethell.game.WorldController;

/**
 * The bomb object.
 * 
 * @author Aaron Wink
 */
public class Bomb extends ItemParent
{
	@Override
	protected TextureRegion getTexReg() {
		return Assets.instance.pickups.pickup[1];
	}

	@Override
	protected void specialFunction() {
		WorldController.bombs += 1;
	}

}
