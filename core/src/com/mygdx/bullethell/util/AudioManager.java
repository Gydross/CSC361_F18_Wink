package com.mygdx.bullethell.util;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Provides methods for playing music and sound effects.
 * 
 * @author Aaron Wink
 */
public class AudioManager 
{
	/**
	 * Singleton reference to the instance of AudioManager
	 */
	public static final AudioManager instance = new AudioManager();
	private Music playingMusic;
	
	// Singleton.
	private AudioManager() {}
	
	/**
	 * Various methods for playing a specified sound effect.
	 * @param sound - The desired sound.
	 * @param volume - The volume the sound is to be played at.
	 * @param pitch - The desired pitch of the sound.
	 * @param pan - The desired pan of the sound.
	 */
	public void play(Sound sound)
	{
		play(sound, 1);
	}
	
	public void play(Sound sound, float volume) 
	{
		play(sound, volume, 1);
	}
	
	public void play(Sound sound, float volume, float pitch) 
	{
		play(sound, volume, pitch);
	}
	
	public void play(Sound sound, float volume, float pitch, float pan)
	{
		sound.play(volume, pitch, pan);
	}

	/**
	 * Stops any currently playing song and begins playing a new,
	 *   specified one.
	 * @param music - The song to be played.
	 * @param volume - The desired volume of the song.
	 */
	public void play(Music music, float volume) 
	{
		stopMusic();
		playingMusic = music;
		
		music.setLooping(true);
		music.setVolume(volume);
		music.play();
	}
	/**
	 * Simply stops the currently playing music.
	 */
	public void stopMusic() 
	{
		if (playingMusic != null) 
			playingMusic.stop();
	}
}