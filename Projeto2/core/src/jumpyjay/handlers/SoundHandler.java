package jumpyjay.handlers;

import com.badlogic.gdx.audio.Music;

// TODO: Auto-generated Javadoc
/**
 * The Class SoundHandler.
 */
public class SoundHandler {
	
	/** The music. */
	public static Music music;
	
	/**
	 * Instantiates a new sound handler.
	 */
	private SoundHandler() {}
	
	/**
	 * Play music.
	 */
	public static void playMusic()
	{
		music = Assets.manager.get(Assets.music);
		music.setVolume(0.5f);
		music.play();
		music.setLooping(true);
	}
	
	/**
	 * Play caught.
	 */
	public static void playCaught()
	{
		if (SingletonVandC.sound)
			Assets.manager.get(Assets.diamondSound).play(1.0f);
	}
	
	/**
	 * Play won.
	 */
	public static void playWon()
	{
		if (SingletonVandC.sound)
			Assets.manager.get(Assets.wonSound).play(1.0f);
	}
	
	/**
	 * Play jump.
	 */
	public static void playJump()
	{
		if (SingletonVandC.sound)
			Assets.manager.get(Assets.jumpSound).play(1.0f);
	}
	
	/**
	 * Play lost life.
	 */
	public static void playLostLife()
	{
		if (SingletonVandC.sound)
			Assets.manager.get(Assets.lostLifeSound).play(1.0f);
	}
	
	/**
	 * Play dead.
	 */
	public static void playDead()
	{
		if (SingletonVandC.sound)
			Assets.manager.get(Assets.diedSound).play(1.0f);
	}

	/**
	 * Change music state.
	 */
	public static void changeMusicState() {
		if (SingletonVandC.music)
		{
			SingletonVandC.music = false;
			music.stop();
		}
		else
		{
			SingletonVandC.music = true;
			music.setVolume(0.5f);
			music.play();
			music.setLooping(true);
		}
	}

	/**
	 * Change sound state.
	 */
	public static void changeSoundState() {
		if (SingletonVandC.sound)
			SingletonVandC.sound = false;
		else
			SingletonVandC.sound = true;
	}
}
