package com.mygdx.bullethell.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.bullethell.game.Assets;
import com.mygdx.bullethell.util.Constants;

/**
 * The level background object.
 * 
 * @author Aaron Wink
 */
public class BackgroundNormal extends AbstractGameObject
{
	private TextureRegion regBG;
	private float length;
	private float width;
	
	public BackgroundNormal()
	{
		length = Constants.PLAY_HEIGHT * Constants.SCALETWO;
		width = Constants.PLAY_WIDTH * Constants.SCALETWO;
		init();
	}
	
	private void init()
	{
		dim.set(width, length);
		
		regBG = Assets.instance.levelDecoration.stage_01_bg;
		origin.set(0,0);
	}
	
	@Override
	public void render(SpriteBatch bat)
	{
		TextureRegion reg = null;
		reg = regBG;
		bat.draw(reg.getTexture(), pos.x + origin.x, pos.y + origin.y, origin.x, origin.y,
			dim.x, dim.y, scale.x, scale.y, rot, reg.getRegionX(), reg.getRegionY(), 
			reg.getRegionWidth(), reg.getRegionHeight(), false, false);
	}

}
