package com.mygdx.bullethell.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.bullethell.game.objects.PowerLarge;
import com.mygdx.bullethell.game.objects.ScoreLarge;
import com.mygdx.bullethell.game.Stage;
import com.mygdx.bullethell.game.WorldController;
import com.mygdx.bullethell.game.objects.Bomb;
import com.mygdx.bullethell.game.objects.ExtraLife;

public abstract class EnemyParent extends AbstractGameObject
{
	public boolean outOfBounds;
	private boolean isDead;
	protected int health;
	//protected BulletScript pattern;  // Where the enemy's attacks would go
	
	public EnemyParent()
	{
		init();
	}
	
	private void init()
	{
		outOfBounds = false;
		isDead = false;
		health = 1;
	}
	
	/**
	 * Lowers the enemy's health by a specified amount.
	 * @param amount: The amount of damage the enemy has taken.
	 */
	public void lowerHealth(int amount)
	{
		if (health > 0)
			health -= amount;
		else {
			health = 0;
			if (!isDead)
				killed();
		}
	}
	
	/**
	 * Handles functionality for when the enemy gets killed by the player.
	 */
	private void killed()
	{
		isDead = true;
		genLoot();
	}

	/**
	 * Generates a random item from the pool of all items.
	 *   Small score = 50%
	 *   Small power = 20%
	 *   Large score or large power = 10% (5% each)
	 *   Bomb = 7.5%
	 *   Extra life = 2.5%
	 */
	private void genLoot() {
		ItemParent item = null;
		double rng = Math.random();
		
		if (rng >= 0.5) {
			item = new ScoreSmall();
		} else if (rng >= 0.2) {
			item = new PowerSmall();
		} else if (rng >= 0.1) {
			double rng2 = Math.random();
			if (rng2 >= 0.5) {
				item = new ScoreLarge();
			} else {
				item = new PowerLarge();
			}
		} else if (rng >= 0.075) {
			item = new Bomb();
		} else {
			item = new ExtraLife();
		}
		WorldController.stage.items.add(item);
	}

	@Override
	public void update(float dt)
	{
		
	}
	
	@Override
	public void render(SpriteBatch bat) {
		scale.set(0.625f * 0.25f,0.25f);
	    TextureRegion reg = new TextureRegion();
	    reg.setRegion(this.getTexReg());
	    
	    bat.draw(reg.getTexture(), pos.x, pos.y, origin.x, origin.y, 
	    	dim.x, dim.y, scale.x, scale.y,  rot, reg.getRegionX(), 
	    	reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), 
	    	false, false);
	}

	protected abstract TextureRegion getTexReg();	// Retrieves the texture for the enemy.
}
