package com.mygdx.bullethell.game.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.bullethell.game.Assets;
import com.mygdx.bullethell.game.WorldController;

/**
 * The small score pickup.
 * 
 * @author Aaron Wink
 */
public class ScoreSmall extends ItemParent
{
	@Override
	protected TextureRegion getTexReg() {
		return Assets.instance.pickups.pickup[4];
	}

	@Override
	protected void specialFunction() {
		WorldController.score += 10;
	}

}
