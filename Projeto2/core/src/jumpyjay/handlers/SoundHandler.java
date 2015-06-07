package jumpyjay.handlers;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundHandler {
	public static Music music;
	
	private SoundHandler() {}
	
	public static void playMusic()
	{
		music = Assets.manager.get(Assets.music);
		music.setVolume(0.5f);
		music.play();
		music.setLooping(true);
	}
	
	public static void playCaught()
	{
		if (SingletonVandC.sound)
			Assets.manager.get(Assets.diamondSound).play(1.0f);
	}
	
	public static void playWon()
	{
		if (SingletonVandC.sound)
			Assets.manager.get(Assets.wonSound).play(1.0f);
	}
	
	public static void playJump()
	{
		if (SingletonVandC.sound)
			Assets.manager.get(Assets.jumpSound).play(1.0f);
	}
	
	public static void playLostLife()
	{
		if (SingletonVandC.sound)
			Assets.manager.get(Assets.lostLifeSound).play(1.0f);
	}
	
	public static void playDead()
	{
		if (SingletonVandC.sound)
			Assets.manager.get(Assets.diedSound).play(1.0f);
	}

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

	public static void changeSoundState() {
		if (SingletonVandC.sound)
			SingletonVandC.sound = false;
		else
			SingletonVandC.sound = true;
	}
}
