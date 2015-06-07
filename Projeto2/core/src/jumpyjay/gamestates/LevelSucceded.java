package jumpyjay.gamestates;

import jumpyjay.handlers.Animation;
import jumpyjay.handlers.Assets;
import jumpyjay.handlers.SingletonVandC;
import jumpyjay.handlers.SoundHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The Class LevelSucceded.
 */
public class LevelSucceded extends GameState {

	/** The batch. */
	SpriteBatch batch;	
	
	/** The singleton. */
	SingletonVandC singleton;
	
	/** The cam. */
	OrthographicCamera cam;

	/** The screen. */
	private Texture screen;
	
	/** The robot. */
	Animation robot;

	/**
	 * Instantiates a new level succeeded.
	 *
	 * @param gameStateManager the game state manager
	 */
	public LevelSucceded(GameStateManager gameStateManager) {
		super(gameStateManager);
		init();
	}

	/**
	 * Init initializes the screen, singleton, batch and camera
	 */
	@Override
	public void init() {
		screen = Assets.manager.get(Assets.successScreen);
		singleton = SingletonVandC.getSingleton();
		batch = new SpriteBatch();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, singleton.SCREEN_WIDTH, singleton.SCREEN_HEIGHT);
		cam.update();
	}

	/** 
	 * update 
	 */
	@Override
	public void update(float dt) {
		handleInput();
	}

	/** 
	 * render renders on screen the info in the end of a succesful level
	 */
	@Override
	public void render() {

		batch.setProjectionMatrix(cam.combined);

		batch.begin();
		batch.draw(screen, singleton.SCREEN_WIDTH / 4, singleton.SCREEN_HEIGHT / 6, (singleton.SCREEN_WIDTH - (singleton.SCREEN_WIDTH / 2)), (singleton.SCREEN_HEIGHT - (singleton.SCREEN_HEIGHT / 3)));

		renderSoundRelatedButtons();

		batch.end();
	}

	/**
	 * Render sound related buttons.
	 */
	private void renderSoundRelatedButtons() {
		if (SingletonVandC.music)
			batch.draw(Assets.manager.get(Assets.enabledMusic), singleton.SCREEN_WIDTH - singleton.SCREEN_WIDTH / 15, singleton.SCREEN_HEIGHT / 30, singleton.SCREEN_WIDTH / 20, singleton.SCREEN_HEIGHT / 12);
		else
			batch.draw(Assets.manager.get(Assets.disabledMusic), singleton.SCREEN_WIDTH - singleton.SCREEN_WIDTH / 15, singleton.SCREEN_HEIGHT / 30, singleton.SCREEN_WIDTH / 20, singleton.SCREEN_HEIGHT / 12);
		if (SingletonVandC.sound)
			batch.draw(Assets.manager.get(Assets.enabledSound), singleton.SCREEN_WIDTH - 2 * singleton.SCREEN_WIDTH / 15, singleton.SCREEN_HEIGHT / 30, singleton.SCREEN_WIDTH / 20, singleton.SCREEN_HEIGHT / 12);
		else
			batch.draw(Assets.manager.get(Assets.disabledSound), singleton.SCREEN_WIDTH - 2 * singleton.SCREEN_WIDTH / 15, singleton.SCREEN_HEIGHT / 30, singleton.SCREEN_WIDTH / 20, singleton.SCREEN_HEIGHT / 12);

	}


	/**
	 * handle Input
	 * 
	 *  handles the input of the user and checks if he clicks on any of the buttons
	 */
	@Override
	public void handleInput() {
		if (Gdx.input.justTouched())
		{
			if ((Gdx.input.getX() > (singleton.SCREEN_WIDTH - singleton.SCREEN_WIDTH / 15)) && (Gdx.input.getX() < (singleton.SCREEN_WIDTH - singleton.SCREEN_WIDTH / 15 + singleton.SCREEN_WIDTH / 20)) &&
					(Gdx.input.getY() < (singleton.SCREEN_HEIGHT - singleton.SCREEN_HEIGHT / 30)) && (Gdx.input.getY() > (singleton.SCREEN_HEIGHT - singleton.SCREEN_HEIGHT / 30 - singleton.SCREEN_HEIGHT / 12)))
				SoundHandler.changeMusicState();
			else if ((Gdx.input.getX() > (singleton.SCREEN_WIDTH - 2 * (singleton.SCREEN_WIDTH / (float) 15))) && (Gdx.input.getX() < (singleton.SCREEN_WIDTH - 2 * (singleton.SCREEN_WIDTH / (float) 15) + singleton.SCREEN_WIDTH / 20)) &&
					(Gdx.input.getY() < (singleton.SCREEN_HEIGHT - singleton.SCREEN_HEIGHT / 30)) && (Gdx.input.getY() > (singleton.SCREEN_HEIGHT - singleton.SCREEN_HEIGHT / 30 - singleton.SCREEN_HEIGHT / 12)))
				SoundHandler.changeSoundState();
		}
	}

	/** 
	 * Dispose disposes the batch
	 */
	@Override
	public void dispose() {
		batch.dispose();
	}

	/** 
	 * pause
	 */
	@Override
	public void pause() {}

}
