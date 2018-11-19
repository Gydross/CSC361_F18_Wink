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
	@Override
	protected TextureRegion getTexReg() {
		return Assets.instance.pickups.pickup[2];
	}

	@Override
	protected void specialFunction() {
		WorldController.power += 1;
	}

}
