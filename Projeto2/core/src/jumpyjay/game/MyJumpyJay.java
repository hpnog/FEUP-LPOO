package jumpyjay.game;

import jumpyjay.gamestates.GameStateManager;
import jumpyjay.handlers.Assets;
import jumpyjay.handlers.SingletonVandC;
import jumpyjay.handlers.SoundHandler;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

// TODO: Auto-generated Javadoc
/**
 * The Class MyJumpyJay.
 */
public class MyJumpyJay extends ApplicationAdapter {

	/** The singleton. */
	private SingletonVandC singleton;
	
	/** The cam. */
	public static OrthographicCamera cam;
	
	/** The game state manager. */
	private GameStateManager gameStateManager;
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.ApplicationAdapter#create()
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
	 * Inits the.
	 */
	public void init()
	{
		singleton = SingletonVandC.getSingleton();
		
		singleton.SCREEN_WIDTH = Gdx.graphics.getWidth();
		singleton.SCREEN_HEIGHT = Gdx.graphics.getHeight();
	}
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.ApplicationAdapter#render()
	 */
	@Override
	public void render () {		
		gameStateManager.update(Gdx.graphics.getDeltaTime());
		gameStateManager.draw();
	}
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.ApplicationAdapter#pause()
	 */
	@Override
	public void pause () {
		SingletonVandC.paused = -2;
		gameStateManager.pause();
	}
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.ApplicationAdapter#resume()
	 */
	@Override
	public void resume () {
		Assets.manager.finishLoading();
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.ApplicationAdapter#resize(int, int)
	 */
	@Override
	public void resize (int width, int height) {
		
	}
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.ApplicationAdapter#dispose()
	 */
	@Override
	public void dispose () {

	}
}
