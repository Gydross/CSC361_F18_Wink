package com.mygdx.bullethell.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.bullethell.util.Constants;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
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
     * Compact texture atlas asset loader for Sky.
     * @author Aaron Wink
     */
    public class AssetSky {
        public final AtlasRegion sky;
        public AssetSky(TextureAtlas a) {
            sky = a.findRegion("sky");
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
        public AssetFrond(TextureAtlas a) {
            frond = a.findRegion("frond");
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
    
    
}