package com.mygdx.bullethell.util;

/**
 * Stores constants the game may wish to track.
 *
 * @author Aaron Wink
 */
public class Constants
{
    // Visible game world is 5m wide, 5m tall
    public static final float VIEWPORT_WIDTH = 16.0f;
    public static final float VIEWPORT_HEIGHT = 10.0f;
    public static final float SCALE = VIEWPORT_WIDTH/VIEWPORT_HEIGHT;
    
    // GUI things.
    public static final float VIEWPORT_GUI_WIDTH = 640.0f;
    public static final float VIEWPORT_GUI_HEIGHT = 400.0f;
// the font will go here when im ready for it
    
    // Play area dimensions.
    public static final int PLAY_WIDTH = 384;
    public static final int PLAY_HEIGHT = 368;
    
    // Location of texture atlas
    public static final String TEXTURE_ATLAS_OBJECTS = "images/bhgame.atlas";
    public static final String UI_OVERLAY = "images/uioverlay.png";
    
    // Location of stage file 1
    public static final String STAGE_01 = "stages/stage_01.png";
    public static final String STAGE_01_BG = "stages/stage_01_bg.png";
    
    // Pixels-to-Meters conversion factors
    public static final float SCALEONE = 0.00775f;  // 1x
    public static final float SCALETWO = 0.0155f;   // 2x
    
    // Gameplay things
    public static final int LIVES_START = 3;
    public static final int BOMBS_START = 3;
	public static final float INVINCIBILITY_DURATION = 700; // Time in ms
	public static final float GAME_OVER_DELAY = 3;
}
