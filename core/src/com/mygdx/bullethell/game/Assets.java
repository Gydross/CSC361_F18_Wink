package com.mygdx.bullethell.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.bullethell.util.Constants;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

/**
 * Manages assets for the game.
 * 
 * @author Aaron Wink
 */
public class Assets implements Disposable, AssetErrorListener
{
    public static final String TAG = Assets.class.getName();
    
    public static final Assets instance = new Assets();
    
    private AssetManager am;
    
    // Instances of asset internal classes.
    public AssetSky sky;
    public AssetSkyBullet skybullet;
    public AssetFrond frond;
    public AssetFern fern;
    public AssetBulletGrey bullets_grey;
    public AssetBounds boundary;
    public AssetPickup pickups;
    
    public AssetLevelDecoration levelDecoration;
    
    // Singleton
    private Assets() {}
    
    public void init(AssetManager am)
    {
        this.am = am;
        
        // Sets the asset error handler.
        am.setErrorListener(this);
        
        // Loads the texture atlas.
        am.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        
        // Start loading assets, wait until finished.
        am.finishLoading();
        
        // Debug output
        Gdx.app.debug(TAG, "# of assets loaded: " + am.getAssetNames().size);
        for (String a : am.getAssetNames())
            Gdx.app.debug(TAG, "asset: " + a);
        
        TextureAtlas a = am.get(Constants.TEXTURE_ATLAS_OBJECTS);
        
        // Create the game resource objects.
        sky = new AssetSky(a);
        skybullet = new AssetSkyBullet(a);
        frond = new AssetFrond(a);
        fern = new AssetFern(a);
        bullets_grey = new AssetBulletGrey(a);
        boundary = new AssetBounds(a);
        pickups = new AssetPickup(a);
        levelDecoration = new AssetLevelDecoration(a);
    }
    
    /**
     * Handles fatal errors resulting from trying to load assets.
     */
    @Override
    public void error(AssetDescriptor asset, Throwable throwable)
    {
        Gdx.app.error(TAG, "Couldn't load asset'" + asset.fileName + "'", (Exception)throwable);
    }

    @Override
    public void dispose()
    {
        am.dispose();
    }
    
    /**
     * Compact texture atlas asset loader for the level backgrounds.
     * @author Aaron Wink
     */
    public class AssetLevelDecoration {
    	public final Texture stage_01_bg_raw;
    	public final TextureRegion stage_01_bg;
    	public final Texture ui_overlay_raw;
    	public final TextureRegion ui_overlay;
    	
    	public AssetLevelDecoration(TextureAtlas a) {
    		stage_01_bg_raw = new Texture(Constants.STAGE_01_BG);
    		stage_01_bg = new TextureRegion(stage_01_bg_raw, 0, 0, 384, 3108);
    		ui_overlay_raw = new Texture(Constants.UI_OVERLAY);
    		ui_overlay = new TextureRegion(ui_overlay_raw, 0, 0, 639, 397);
    	}
    }
    
    /**
     * Compact texture atlas asset loader for Sky.
     * Loads her idle animations, as well as the animations that play when she moves.
     * @author Aaron Wink
     */
    public class AssetSky {
        public final AtlasRegion sky;
        public final Animation skyNormal;
        public final Animation skyLeft;
        public final Animation skyRight;
        public AssetSky(TextureAtlas a) {
            sky = a.findRegion("sky");
            
            Array<AtlasRegion> regions = null;
            
            // Animation: Sky Idle
            regions = a.findRegions("sky_normal");
            skyNormal = new Animation(1.0f / 10.0f, regions, Animation.PlayMode.LOOP);
            
            // Animation: Sky Left
            regions = a.findRegions("sky_left");
            skyLeft = new Animation(1.0f / 10.0f, regions, Animation.PlayMode.LOOP);
            
            // Animation: Sky Right
            regions = a.findRegions("sky_right");
            skyRight = new Animation(1.0f / 10.0f, regions, Animation.PlayMode.LOOP);
            
            //regions = new Array<AtlasRegion>();
        }
    }
    
    /**
     * Compact texture atlas asset loader for Sky's bullets.
     * @author Aaron Wink
     */
    public class AssetSkyBullet {
        public final AtlasRegion skybullet;
        public AssetSkyBullet(TextureAtlas a) {
            skybullet = a.findRegion("sky_bullet");
        }
    }
    
    /**
     * Compact texture atlas asset loader for Frond.
     * @author Aaron Wink
     */
    public class AssetFrond {
        public final AtlasRegion frond;
        public final Animation frondSpin;
        public AssetFrond(TextureAtlas a) {
            frond = a.findRegion("frond");
            
            Array<AtlasRegion> regions = null;
            
            // Animation: Spin!
            regions = a.findRegions("frond_spin");
            frondSpin = new Animation(1.0f / 10.0f, regions);
        }
    }
    
    /**
     * Compact texture atlas asset loader for Fern.
     * @author Aaron Wink
     */
    public class AssetFern {
        public final AtlasRegion fern;
        public AssetFern(TextureAtlas a) {
            fern = a.findRegion("fern");
        }
    }
    
    /**
     * Compact texture atlas asset loader for all grey bullets.
     * @author Aaron Wink
     */
    public class AssetBulletGrey {
        public final AtlasRegion[] b_grey = new AtlasRegion[1];
        public AssetBulletGrey(TextureAtlas a) {
            // Looks for all bullet_grey_# files and loads them into the array.
            for (int i = 1; i < 2; i++)
            {
                String name = "bullet_grey_" + i;
                b_grey[i-1] = a.findRegion(name);
            }
        }
    }
    
    /**
     * Compact texture atlas asset loader for a test boundary image.
     * @author Aaron Wink
     */
    public class AssetBounds {
        public final AtlasRegion bound;
        public AssetBounds(TextureAtlas a) {
            bound = a.findRegion("bounds_collision_testmarker");
        }
    }
    
    /**
     * Compact texture atlas asset loader for all items.
     * @author Aaron Wink
     */
    public class AssetPickup {
        public final AtlasRegion[] pickup = new AtlasRegion[6];
        public AssetPickup(TextureAtlas a) {
        	pickup[0] = new AtlasRegion(a.findRegion("extra_life"));
        	pickup[1] = new AtlasRegion(a.findRegion("bomb"));
        	pickup[2] = new AtlasRegion(a.findRegion("power_small"));
        	pickup[3] = new AtlasRegion(a.findRegion("power_large"));
        	pickup[4] = new AtlasRegion(a.findRegion("score_small"));
        	pickup[5] = new AtlasRegion(a.findRegion("score_large"));
        }
    }
    
    /**
	 * Loads sound effects into the game engine and assigns them to instances
	 */
	public class AssetSounds
	{
		public final Sound bossDie;
		public final Sound lifeUp;
		public final Sound playerDie;
		public final Sound playerShoot;
		public final Sound powerUp;
		public final Sound selected;
		public final Sound twirl;

		public AssetSounds(AssetManager am)
		{
			bossDie = am.get("sounds/enep01.wav", Sound.class);
			lifeUp = am.get("sounds/extend.wav", Sound.class);
			playerDie = am.get("sounds/pldead00.wav", Sound.class);
			playerShoot = am.get("sounds/plst00.wav", Sound.class);
			powerUp = am.get("sounds/powerup.wav", Sound.class);
			selected = am.get("sounds/ok00.wav", Sound.class);
			twirl = am.get("sounds/kira00.wav", Sound.class);
		}
	}


	/**
	 * Loads music into game engine and assigns it to an instance
	 */
	public class AssetMusic
	{
		public final Music song01;

		public AssetMusic(AssetManager am)
		{
			song01 = am.get("music/dream_land.mp3", Music.class);
		}
	}
}