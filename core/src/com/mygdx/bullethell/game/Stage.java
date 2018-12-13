package com.mygdx.bullethell.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.bullethell.game.objects.AbstractGameObject;
import com.mygdx.bullethell.game.objects.BackgroundNormal;
import com.mygdx.bullethell.game.objects.Bomb;
import com.mygdx.bullethell.game.objects.Boundary;
import com.mygdx.bullethell.game.objects.ExtraLife;
import com.mygdx.bullethell.game.objects.Frond;
import com.mygdx.bullethell.game.objects.ItemParent;
import com.mygdx.bullethell.game.objects.PowerLarge;
import com.mygdx.bullethell.game.objects.PowerSmall;
import com.mygdx.bullethell.game.objects.ScoreLarge;
import com.mygdx.bullethell.game.objects.ScoreSmall;
import com.mygdx.bullethell.game.objects.Sky;
import com.mygdx.bullethell.game.objects.UIoverlay;
import com.mygdx.bullethell.util.Constants;

/**
 * The class that loads levels and handles populating them with objects 
 *   based on a level image.
 *   
 * @author Aaron Wink
 */
public class Stage
{
    public static final String TAG = Stage.class.getName();
    
    public enum BLOCK_TYPE
    {
        EMPTY(0, 0, 0),  		// Black
        PLAYER(255, 255, 255),  // White
        BOSS(255, 0, 0),  		// Red
        
        // Temp colors
        POWERSMALL(128, 0, 64),	// Dark Maroon
        POWERLARGE(255, 0, 64),	// Hot Pink
        SCORESMALL(0, 64, 128),	// Navy Blue
        SCORELARGE(0, 64, 255),	// Ultramarine
        BOMB(0, 255, 0),		// Green
        LIFE(128, 0, 255);		// Purple
        
        private int color;
        
        private BLOCK_TYPE(int r, int g, int b)
        {
            color = r << 24 | g << 16 | b << 8 | 0xff;
        }
        
        public boolean sameColor(int color)
        {
            return this.color == color;
        }
        
        public int getColor()
        {
            return color;
        }
    }
    
    // Objects
    public Boundary bounds;
    public Frond frond;
    public Sky sky;
    public PowerSmall ps;
    public PowerLarge pl;
    public ScoreSmall ss;
    public ScoreLarge sl;
    public Bomb bomb;
    public ExtraLife up;
    public Array<ItemParent> items;
    
    // Decoration
    public BackgroundNormal bg;
    public UIoverlay overlay;
    
    public Stage(String filename)
    {
        init(filename);
    }
    
    private void init(String filename)
    {
        // Objects
        bounds = new Boundary();
        frond = null;
        sky = null;
        ps = null;
        pl = null;
        ss = null;
        sl = null;
        bomb = null;
        up = null;
        items = new Array<ItemParent>();
        
        // Load level file
        Pixmap px = new Pixmap(Gdx.files.internal(filename));
        
        // Scan pixels, top left bottom right
        int lastpx = -1;
        for (int pxY = 0; pxY < px.getHeight(); pxY++)
        {
            for (int pxX = 0; pxX < px.getWidth(); pxX++)
            {
                AbstractGameObject obj = null;
                float offsetHeight = 0;
                //float vecx = ((pxX / 16) / 8) * Constants.SCALE;
                //float vecy = ((pxY / 16) / 8) * 3 * Constants.SCALE;
                
                // Height grows, bottom to top
                float baseHeight = (px.getHeight() - pxY) / 16;
                
                // Get color of pixel
                int curpx = px.getPixel(pxX, pxY);
                
                if (BLOCK_TYPE.EMPTY.sameColor(curpx))  // Empty space
                {} 
                else if (BLOCK_TYPE.PLAYER.sameColor(curpx))  // Player
                {
                    obj = new Sky();
                    offsetHeight = -1.0f;
                    obj.pos.set(3f, 1);
                    sky = (Sky)obj;
                }
                else if (BLOCK_TYPE.BOSS.sameColor(curpx))  // Boss
                {
                    obj = new Frond();
                    obj.pos.set(3.25f,6);
                    frond = (Frond)obj;
                }
                else if (BLOCK_TYPE.POWERSMALL.sameColor(curpx))
                {
                	obj = new PowerSmall();
                	offsetHeight = -0.5f;
                	obj.pos.set(pxX, baseHeight * obj.dim.y + offsetHeight);
                	items.add((PowerSmall)obj);
                }
                else if (BLOCK_TYPE.POWERLARGE.sameColor(curpx))
                {
                	obj = new PowerLarge();
                	offsetHeight = -1;
                	obj.pos.set(pxX, baseHeight * obj.dim.y + offsetHeight);
                	items.add((PowerLarge)obj);
                }
                else if (BLOCK_TYPE.SCORESMALL.sameColor(curpx))
                {
                	obj = new ScoreSmall();
                	offsetHeight = -0.5f;
                	obj.pos.set(pxX, baseHeight * obj.dim.y + offsetHeight);
                	items.add((ScoreSmall)obj);
                }
                else if (BLOCK_TYPE.SCORELARGE.sameColor(curpx))
                {
                	obj = new ScoreLarge();
                	offsetHeight = -1;
                	obj.pos.set(pxX, baseHeight * obj.dim.y + offsetHeight);
                	items.add((ScoreLarge)obj);
                }
                else if (BLOCK_TYPE.BOMB.sameColor(curpx))
                {
                	obj = new Bomb();
                	offsetHeight = -1;
                	obj.pos.set(pxX, baseHeight * obj.dim.y + offsetHeight);
                	items.add((Bomb)obj);
                }
                else if (BLOCK_TYPE.LIFE.sameColor(curpx))
                {
                	obj = new ExtraLife();
                	offsetHeight = -1;
                	obj.pos.set(pxX, baseHeight * obj.dim.y + offsetHeight);
                	items.add((ExtraLife)obj);
                }
                else  // Unknown
                {
                    Gdx.app.error(TAG, "Object not recognized, please double check the ENUM.");
                }
                lastpx = curpx;
            }
        }
        
        // Decoration
        bg = new BackgroundNormal();
        bg.origin.set(0,0);
        bg.pos.set(0.5f,0.375f);
        overlay = new UIoverlay();
        overlay.origin.set(0,0);
        overlay.pos.set(0,0);
        
        // Free up some memory
        px.dispose();
        Gdx.app.debug(TAG, "Stage loaded.");
    }
    
    public void update(float dt)
    {
    	bounds.update(dt);
    	bg.update(dt);
    	frond.update(dt);
    	sky.update(dt);
    	for (ItemParent item : items)
    		item.update(dt);
    	overlay.update(dt);
    }
    
    public void render(SpriteBatch bat)
    {
        bounds.render(bat);
        bg.render(bat);
        frond.render(bat);
        sky.render(bat);
        for (ItemParent item : items)
        	item.render(bat);
        overlay.render(bat);
    }
}
