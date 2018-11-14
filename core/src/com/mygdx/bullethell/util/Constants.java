package com.mygdx.bullethell.util;

/**
 * Stores constants the game may wish to track.
 *
 * @author Aaron Wink
 */
public class Constants
{
    // Visible game world is 5m wide, 5m tall
    public static final float VIEWPORT_WIDTH = 5.0f;
    public static final float VIEWPORT_HEIGHT = 5.0f;
    
    // GUI dimensions.
    public static final float VIEWPORT_GUI_WIDTH = 1280.0f;
    public static final float VIEWPORT_GUI_HEIGHT = 960.0f;
    
    // Play area dimensions.
    public static final int PLAY_WIDTH = 773;
    public static final int PLAY_HEIGHT = 944;
    
    // Location of texture atlas
    public static final String TEXTURE_ATLAS_OBJECTS = "images/bhgame.atlas";
    
    // Location of stage file 1
    public static final String STAGE_01 = "stages/stage_01.png";
    
    // Pixels-to-Meters conversion factors
    public static final float SCALEONE = 0.00775f;  // 1x
    public static final float SCALETWO = 0.0155f;   // 2x
    
    // Gameplay things
    public static final int LIVES_START = 3;
    public static final int BOMBS_START = 3;
}
