package com.mygdx.bullethell.screens;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.bullethell.util.Constants;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import com.badlogic.gdx.math.Interpolation;

/**
 * A class for the menu screen that will display options
 * for the player to choose.
 * 
 * @author Aaron Wink
 */
public class MenuScreen extends AbstractGameScreen 
{
	private static final String TAG = MenuScreen.class.getName();

	private Stage stage;
	private Skin skinLibgdx;
	private Skin skinSkyGame;

	// Main menu objects
	private Image imgBackground;
	private Button btnMenuPlay;
	private Button btnMenuOptions;

	// Options menu objects
	private Window winOptions;
	private TextButton btnWinOptSave;
	private TextButton btnWinOptCancel;
	private CheckBox chkSound;
	private Slider sldSound;
	private CheckBox chkMusic;
	private Slider sldMusic;

	// Debug objects
	private final float DEBUG_REBUILD_INTERVAL = 5.0f;
	private boolean debugEnabled = false;
	private float debugRebuildStage;


	public MenuScreen(Game game)
	{
		super(game);
	}
	
	/**
	 * Draws a specified menu.
	 * @param deltaTime - The current game time elapsed
	 */
	@Override
	public void render(float deltaTime)
	{
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (debugEnabled)
		{
			debugRebuildStage -= deltaTime;
			if (debugRebuildStage <= 0)
			{
				debugRebuildStage = DEBUG_REBUILD_INTERVAL;
				rebuildStage();
			}
		}
		stage.act(deltaTime);
		stage.draw();
		//Table.drawDebug(stage);
	}
	
	/**
	 * Loads settings from prefs.ini into the game.
	 */
	/*private void loadSettings() 
	{
		GamePreferences prefs = GamePreferences.instance;
		prefs.load();
		chkSound.setChecked(prefs.sound);
		sldSound.setValue(prefs.volSound);
		chkMusic.setChecked(prefs.music);
		sldMusic.setValue(prefs.volMusic);
		selCharSkin.setSelectedIndex(prefs.charSkin);
		onCharSkinSelected(prefs.charSkin);
		chkShowFpsCounter.setChecked(prefs.showFpsCounter);
	}*/
	
	/**
	 * Saves settings to prefs.ini.
	 */
	/*private void saveSettings()
	{
		GamePreferences prefs = GamePreferences.instance;
		prefs.sound = chkSound.isChecked();
		prefs.volSound = sldSound.getValue();
		prefs.music = chkMusic.isChecked();
		prefs.volMusic = sldMusic.getValue();
		prefs.charSkin = selCharSkin.getSelectedIndex();
		prefs.showFpsCounter = chkShowFpsCounter.isChecked();
		prefs.save();
	}*/

	/**
	 * Builds the audio settings menu.
	 * @return tbl - The data structure detailing how to draw the audio menu.
	 */
	private Table buildOptWinAudioSettings () 
	{
		Table tbl = new Table();
		
		// Add title: "Audio"
		tbl.pad(10, 10, 0, 10);
		tbl.add(new Label("Audio", skinLibgdx, "default-font", Color.ORANGE)).colspan(3);
		tbl.row();
		tbl.columnDefaults(0).padRight(10);
		tbl.columnDefaults(1).padRight(10);
		
		// Add checkbox, "Sound" label, sound volume slider
		chkSound = new CheckBox("", skinLibgdx);
		tbl.add(chkSound);
		tbl.add(new Label("Sound", skinLibgdx));
		sldSound = new Slider(0.0f, 1.0f, 0.1f, false, skinLibgdx);
		tbl.add(sldSound);
		tbl.row();
		
		// Add checkbox, "Music" label, music volume slider
		chkMusic = new CheckBox("", skinLibgdx);
		tbl.add(chkMusic);
		tbl.add(new Label("Music", skinLibgdx));
		sldMusic = new Slider(0.0f, 1.0f, 0.1f, false, skinLibgdx);
		tbl.add(sldMusic);
		tbl.row();
		
		return tbl;
	}

	/**
	 * Builds the skin selection portion of the options menu.
	 * @return tbl - The data structure detailing how to draw the skin selection submenu. 
	 */
	private Table buildOptWinSkinSelection () 
	{
		Table tbl = new Table();
		
		// Add title: "Character Skin"
		tbl.pad(10, 10, 0, 10);
		tbl.add(new Label("Character Skin", skinLibgdx, "default-font", Color.ORANGE)).colspan(2);
		tbl.row();
		
		// Add drop down box filled with skin items
		/*selCharSkin = new SelectBox<CharacterSkin>(skinLibgdx);
		selCharSkin.setItems(CharacterSkin.values());
		selCharSkin.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) 
			{
				onCharSkinSelected(((SelectBox<CharacterSkin>)actor).getSelectedIndex());
			}
		});
		tbl.add(selCharSkin).width(120).padRight(20);
		
		// Add skin preview image
		imgCharSkin = new Image(Assets.instance.bunny.head);
		tbl.add(imgCharSkin).width(50).height(50);
		*/
		return tbl;
	}

	/**
	 * Builds a table that contains the save button, the cancel button, and a separator.
	 * @return tbl - The data structure detailing how to draw the save and cancel buttons.
	 */
	private Table buildOptWinButtons ()
	{
		Table tbl = new Table();
	    Label lbl = null;
	    
		// Add separator
		lbl = new Label("", skinLibgdx);
		lbl.setColor(0.75f, 0.75f, 0.75f, 1);
		lbl.setStyle(new LabelStyle(lbl.getStyle()));
		lbl.getStyle().background = skinLibgdx.newDrawable("white");
		tbl.add(lbl).colspan(2).height(1).width(220).pad(0, 0, 0, 1);
		tbl.row();
		
		lbl = new Label("", skinLibgdx);
		lbl.setColor(0.5f, 0.5f, 0.5f, 1);
		lbl.setStyle(new LabelStyle(lbl.getStyle()));
		lbl.getStyle().background = skinLibgdx.newDrawable("white");
		tbl.add(lbl).colspan(2).height(1).width(220).pad(0, 1, 5, 0);
		tbl.row();
		
		// Add save button with event handler
		btnWinOptSave = new TextButton("Save", skinLibgdx);
		tbl.add(btnWinOptSave).padRight(30);
		btnWinOptSave.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor actor) 
			{
				onSaveClicked();
			}
		});
		
		// Add cancel button with event handler
		btnWinOptCancel = new TextButton("Cancel", skinLibgdx);
		tbl.add(btnWinOptCancel);
		btnWinOptCancel.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor actor)
			{
				onCancelClicked();
			}
		});
		
		return tbl;
	}


	/**
	 * Sets the size of the menu relative to the viewport.
	 * @param width - The desired width of the menu
	 * @param height - The desired height of the menu
	 */
	@Override
	public void resize(int width, int height)
	{
		stage.getViewport().update(width, height, true);
	}

	/**
	 * Shows the menu.
	 */
	@Override
	public void show() 
	{
		stage = new Stage(new StretchViewport(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT));
		Gdx.input.setInputProcessor(stage);
		rebuildStage();
	}
	
	/**
	 * Hides the menu.
	 */
	@Override
	public void hide() 
	{
		stage.dispose();
		skinLibgdx.dispose();
		//skinSkyGame.dispose();
	}

	/**
	 * Pauses the game. (empty method)
	 */
	@Override
	public void pause() 
	{
	}

	/**
	 * Constructs the menu screen.
	 */
	private void rebuildStage () 
	{
		
		skinSkyGame = new Skin(Gdx.files.internal(Constants.TEXTURE_ATLAS_UI),
				new TextureAtlas(Constants.TEXTURE_ATLAS_UI));
		
		skinLibgdx = new Skin(Gdx.files.internal(Constants.TEXTURE_ATLAS_LIBGDX_UI),
				new TextureAtlas(Constants.TEXTURE_ATLAS_LIBGDX_UI));
		
		// Build all layers
		Table layerBackground = buildBackgroundLayer();
		Table layerControls = buildControlsLayer();
		Table layerOptionsWindow = buildOptionsWindowLayer();
		
		// Assemble stage for menu screen
		stage.clear();
		Stack stack = new Stack();
		stage.addActor(stack);
		//stack.setSize(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT);
		stack.add(layerBackground);
		stack.add(layerControls);
		stage.addActor(layerOptionsWindow);
	}
	
	/**
	 * Builds the background layer of the main menu.
	 * @return layer - The background layer
	 */
	private Table buildBackgroundLayer () 
	{
		Table layer = new Table();
		
		// + Background
		imgBackground = new Image(skinLibgdx, "background");
		layer.add(imgBackground);
		
		return layer;
	}
	
	/**
	 * Builds the menu buttons layer.
	 * @return layer - The buttons layer
	 */
	private Table buildControlsLayer ()
	{
		Table layer = new Table();
		layer.right().bottom();
		
		// Add play button
		btnMenuPlay = new Button(skinLibgdx, "Play");
		layer.add(btnMenuPlay);
		
		btnMenuPlay.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor actor) 
			{
				onPlayClicked();
			}
		});
		layer.row();
		
		// Add options button
		btnMenuOptions = new Button(skinSkyGame, "Options");
		layer.add(btnMenuOptions);
		
		btnMenuOptions.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor actor)
			{
				onOptionsClicked();
			}
		});
		
		return layer;
	}
	
	/**
	 * Builds the options menu.
	 * @return winOptions -  The data structure containing specifications for 
	 *   drawing the options window.
	 */
	private Table buildOptionsWindowLayer () 
	{
		winOptions = new Window("Options", skinLibgdx);
		
		// + Audio Settings: Sound/Music CheckBox and Volume Slider
		winOptions.add(buildOptWinAudioSettings()).row();
		
		// + Separator and Buttons (Save, Cancel)
		winOptions.add(buildOptWinButtons()).pad(10, 0, 10, 0);
		
		// Make options window slightly transparent
		winOptions.setColor(1, 1, 1, 0.8f);
		
		// Hide options window by default
		winOptions.setVisible(false);
		if (debugEnabled) winOptions.debug();
		
		// Let TableLayout recalculate widget sizes and positions
		winOptions.pack();
		
		// Move options window to bottom right corner
		//winOptions.setPosition
		//(Constants.VIEWPORT_GUI_WIDTH - winOptions.getWidth() - 50, 50);
		
		// Make options window slightly transparent
		//winOptions.setColor(1, 1, 1, 0.8f);
		
		// Hide options window by default
		showOptionsWindow(false, false);
		if (debugEnabled)
		    winOptions.debug();
		
		// Let TableLayout recalculate widget sizes and positions
		winOptions.pack();
		
		// Move options window to bottom right corner
		//winOptions.setPosition(Constants.VIEWPORT_GUI_WIDTH - winOptions.getWidth() - 50, 50);
		
		return winOptions;
	}
	
	/**
	 * Begins the game.
	 */
	private void onPlayClicked () 
	{
		game.setScreen(new GameScreen(game));
	}
	
	/**
	 * Displays the options menu.
	 */
	private void onOptionsClicked () 
	{
		 //loadSettings();
		 showMenuButtons(false);
		 showOptionsWindow(true, true);
	}
	
    /**
     * Sets the color of the bunny.
     * @param index - Index value of the skin that was selected.
     */
    private void onCharSkinSelected(int index)
    {
        //CharacterSkin skin = CharacterSkin.values()[index];
        //imgCharSkin.setColor(skin.getColor());
    }

    /**
     * When the save button is interacted with, saves settings and exits to the main menu.
     */
    private void onSaveClicked() 
    {
        //saveSettings();
        onCancelClicked();
        //AudioManager.instance.onSettingsUpdated();
    }

    /**
     * Returns to the main menu, ignoring any changes made.
     */
    private void onCancelClicked() 
    {
        showMenuButtons(true);
        showOptionsWindow(false, true);
        //AudioManager.instance.onSettingsUpdated();
    }
	
	/**
	 * Displays the buttons of the main menu, if visible is true.
	 * @param visible - Whether or not the menu UI should be drawn.
	 */
	private void showMenuButtons(boolean visible)
	{
	    float moveDuration = 1.0f;
	    Interpolation moveEasing = Interpolation.swing;
	    float delayOptionsButton = 0.25f;
	    
	    float moveX = 300 * (visible ? -1 : 1);
	    float moveY = 0 * (visible ? -1 : 1);
	    final Touchable touchEnabled = visible ? Touchable.enabled: Touchable.disabled;
	    
	    btnMenuPlay.addAction(moveBy(moveX, moveY, moveDuration, moveEasing));
	    btnMenuOptions.addAction(sequence(delay(delayOptionsButton), moveBy(moveX, moveY, moveDuration, moveEasing)));
	    
	    // Allows the buttons to be "touchable," or interactable
	    SequenceAction seq = sequence();
	    if (visible)
	    {
	        seq.addAction(delay(delayOptionsButton + moveDuration));
	        seq.addAction(run(new Runnable() {
	            public void run() {
	                btnMenuPlay.setTouchable(touchEnabled);
	                btnMenuOptions.setTouchable(touchEnabled);
	            }
	        }));
	    }
	    stage.addAction(seq);
	}
	
	/**
	 * Draws and animates the options window.
	 * @param visible - Whether or not the options window should be drawn.
	 * @param animated - Whether or not the window should animate.
	 */
	private void showOptionsWindow(boolean visible, boolean animated)
	{
	    float alphaTo = visible ? 0.8f : 0.0f;    // Determines the opacity of the window
	    float duration = animated ? 1.0f : 0.0f;  // Determines the duration of the animation
	    
	    Touchable touchEnabled = visible ? Touchable.enabled : Touchable.disabled;
	    
	    winOptions.addAction(sequence(touchable(touchEnabled), alpha(alphaTo, duration)));
	}
}
