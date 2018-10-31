package com.mygdx.bullethell.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.bullethell.BulletHellGame;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

public class DesktopLauncher
{
    private static boolean rebuildAtlas = true;
    
	public static void main (String[] arg)
	{
	    if (rebuildAtlas)
	    {
	        Settings s = new Settings();
	        s.maxWidth = 1024;
	        s.maxHeight = 1024;
	        s.duplicatePadding = false;
	        
	        TexturePacker.process(s, "assets-raw/images", "../core/assets/images", "bhgame");	        
	    }
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "Bullet Hell Game";
		config.width = 800;
		config.height = 640;
		
		new LwjglApplication(new BulletHellGame(), config);
	}
}
