package com.mygdx.bullethell.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.bullethell.game.Assets;
import com.mygdx.bullethell.util.Constants;

/**
 * Encapsulates all shared functionality for all pick-ups.
 * Texture regions are sourced from each item object's getTexReg() method.
 * 
 * @author Aaron Wink
 */
public abstract class ItemParent extends AbstractGameObject
{
	protected TextureRegion myReg;
	public boolean collected;
	public boolean outOfBounds;

	public void render(SpriteBatch bat)
	{
		if (collected) 
			return;
		
	    TextureRegion reg = null;    
	    reg = myReg;    
	    bat.draw(reg.getTexture(), pos.x, pos.y, origin.x, origin.y, 
	    	dim.x, dim.y, scale.x, scale.y,  rot, reg.getRegionX(), 
	    	reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), 
	    	false, false);
	}
	
	protected abstract TextureRegion getTexReg();	// Retrieves the texture for the item.
	protected abstract void specialFunction();		// What the specific item does, i.e. giving an extra life.
}
