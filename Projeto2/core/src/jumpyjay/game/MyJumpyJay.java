package jumpyjay.game;

import jumpyjay.gamestates.GameStateManager;
import jumpyjay.handlers.Assets;
import jumpyjay.handlers.SingletonVandC;
import jumpyjay.handlers.SoundHandler;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * The Class MyJumpyJay.
 * 
 * This class is the one that "starts" the application in the first place
 */
public class MyJumpyJay extends ApplicationAdapter {

	/** The singleton. */
	private SingletonVandC singleton;
	
	/** The cam. */
	public static OrthographicCamera cam;
	
	/** The game state manager. */
	private GameStateManager gameStateManager;
	
	/** 
	 * Create
	 * 
	 * Initializes da camera, calls the method that loads the assets and starts playing the background music.
	 * Initializes also the GameManager.
	 */
	@Override
	public void create () {
		init();
		
		cam = new OrthographicCamera(singleton.SCREEN_WIDTH, singleton.SCREEN_HEIGHT);
		cam.translate(singleton.SCREEN_WIDTH / 2, singleton.SCREEN_HEIGHT / 2);
		cam.update();
		
		Assets.load();
		
		SoundHandler.playMusic();
		
		gameStateManager = new GameStateManager();
	}

	/**
	 * Inits the singleton.
	 */
	public void init()
	{
		singleton = SingletonVandC.getSingleton();
		
		singleton.SCREEN_WIDTH = Gdx.graphics.getWidth();
		singleton.SCREEN_HEIGHT = Gdx.graphics.getHeight();
	}
	
	/**
	 * Render
	 * 
	 * Responsible for rendering the screen
	 */
	@Override
	public void render () {		
		gameStateManager.update(Gdx.graphics.getDeltaTime());
		gameStateManager.draw();
	}
	
	/**
	 * Pause
	 * 
	 * Controls what happens when the user pauses the Aplication / receives a call or anything else that may force the Aplication to pause
	 */
	@Override
	public void pause () {
		SingletonVandC.paused = -2;
		gameStateManager.pause();
	}
	
	/**
	 * Resume
	 * 
	 * After Pause, the user may return to the Aplication, in which case this method resumes the action
	 */
	@Override
	public void resume () {
		Assets.manager.finishLoading();
	}

	/**
	 * Resize
	 */
	@Override
	public void resize (int width, int height) {
		
	}
	
	/**
	 * Dispose
	 */
	@Override
	public void dispose () {

	}
}
