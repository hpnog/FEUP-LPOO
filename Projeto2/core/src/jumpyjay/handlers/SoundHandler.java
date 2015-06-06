package jumpyjay.handlers;

public class SoundHandler {
	private SoundHandler() {}
	
	public static void playMusic()
	{
		Assets.manager.get(Assets.music).loop(0.3f);
	}
	
	public static void playCaught()
	{
		Assets.manager.get(Assets.diamondSound).play(1.0f);
	}
	
	public static void playWon()
	{
		Assets.manager.get(Assets.wonSound).play(1.0f);
	}
	
	public static void playJump()
	{
		Assets.manager.get(Assets.jumpSound).play(1.0f);
	}
	
	public static void playLostLife()
	{
		Assets.manager.get(Assets.lostLifeSound).play(1.0f);
	}
	
	public static void playDead()
	{
		Assets.manager.get(Assets.diedSound).play(1.0f);
	}
}
