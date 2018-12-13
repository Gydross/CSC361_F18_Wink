package com.mygdx.bullethell.game.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.bullethell.game.Assets;

public class Frond extends EnemyParent
{
	public Frond()
	{
		init();
	}
	
	private void init()
	{
		dim.set(4,6);
		health = 20;
	}
	
	@Override
	protected TextureRegion getTexReg() {
		return Assets.instance.frond.frond;
	}

}
