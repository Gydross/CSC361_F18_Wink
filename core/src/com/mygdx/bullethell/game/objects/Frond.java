package com.mygdx.bullethell.game.objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.bullethell.game.Assets;

public class Frond extends EnemyParent
{
	private boolean canSpawn = true;
	public Animation spin;
	
	public Frond()
	{
		init();
	}
	
	private void init()
	{
		dim.set(4,6);
		health = 20;
		
		spin = Assets.instance.frond.frondSpin;
	}
	
	/**
	 * Spawns a set number of items before stopping entirely.
	 * Strictly implemented for testing purposes.
	 */
	public void spawnItems()
	{
		canSpawn = false;
		for (int num = 0; num < 10000; num++)
		{
			if ((num%500) == 0)
			{
				genLoot();
			}
		}
		
	}
	
	@Override
	protected TextureRegion getTexReg() {
		return Assets.instance.frond.frond;
	}

}
