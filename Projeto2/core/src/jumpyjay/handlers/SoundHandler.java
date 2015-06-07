package jumpyjay.handlers;

public class SoundHandler {
	
	private SoundHandler() {}
	
	public static void playMusic()
	{
		SingletonVandC.musicId = Assets.manager.get(Assets.music).loop(0.3f);
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
			Assets.manager.get(Assets.music).stop(SingletonVandC.musicId);
		}
		else
		{
			SingletonVandC.music = true;
			SingletonVandC.musicId = Assets.manager.get(Assets.music).loop(0.3f);
		}
	}

	public static void changeSoundState() {
		if (SingletonVandC.sound)
			SingletonVandC.sound = false;
		else
			SingletonVandC.sound = true;
	}
}
