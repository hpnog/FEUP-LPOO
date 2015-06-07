package jumpyjay.gamestates;

import jumpyjay.handlers.Animation;
import jumpyjay.handlers.Assets;
import jumpyjay.handlers.SingletonVandC;
import jumpyjay.handlers.SoundHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * The Class MenuState.
 */
public class MenuState extends GameState {

	/** The batch. */
	SpriteBatch batch;	
	
	/** The singleton. */
	SingletonVandC singleton;
	
	/** The cam. */
	OrthographicCamera cam;

	/** The moving. */
	private int moving = 0;
	
	/** The robot x. */
	private int robotX = 0;

	/** The robot. */
	Animation robot;
	
	/** The tap. */
	Animation tap;

	/**
	 * Instantiates a new menu state.
	 *
	 * @param gameStateManager the game state manager
	 */
	public MenuState(GameStateManager gameStateManager) {
		super(gameStateManager);
		init();
	}

	/**
	 * Init initializes the menu state
	 * 
	 * Initializes the singleton, batch, camera, robot and tap to start animation.
	 */
	@Override
	public void init() {
		singleton = SingletonVandC.getSingleton();
		batch = new SpriteBatch();
		cam = new OrthographicCamera();

		robot = new Animation();
		tap = new Animation();

		TextureRegion[] sprites = TextureRegion.split(Assets.manager.get(Assets.robotRight), 21, 21)[0];
		robot.setFrames(sprites,  1 / 12f);

		sprites = TextureRegion.split(Assets.manager.get(Assets.tapToStart), 218, 60)[0];
		tap.setFrames(sprites,  1 / 5f);

		cam.setToOrtho(false, singleton.SCREEN_WIDTH, singleton.SCREEN_HEIGHT);
		cam.update();

		robotX = singleton.SCREEN_WIDTH / 14;
	}

	/**
	 * update updates the state of the menu state
	 * 
	 * @param dt time since the last update
	 */
	@Override
	public void update(float dt) {
		if (moving == 1)
			robotX += 10;
		robot.update(dt);
		tap.update(dt);
		handleInput();

		if (robotX > singleton.SCREEN_WIDTH)
			gameStateManager.setState(singleton.LEVEL);
	}

	/**
	 * render renders on screen the robot, tap to start animation and background
	 */
	@Override
	public void render() {
		Gdx.gl20.glClearColor(217 / (float) 256, 208 / (float) 256, 179 / (float) 256, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		float frac = singleton.SCREEN_WIDTH / 600;

		batch.setProjectionMatrix(cam.combined);

		batch.begin();
		batch.draw(Assets.manager.get(Assets.backgroundMenu), 0, 0, singleton.SCREEN_WIDTH, singleton.SCREEN_HEIGHT);
		batch.draw(robot.getFrame(), robotX, singleton.SCREEN_HEIGHT / 4.5f, robot.getFrame().getRegionWidth() * 2 * frac, robot.getFrame().getRegionHeight() * 2 * frac);
		batch.draw(tap.getFrame(), (singleton.SCREEN_WIDTH / 2) - (frac * tap.getFrame().getRegionWidth() / 2), singleton.SCREEN_HEIGHT / 2 - frac * tap.getFrame().getRegionHeight() / 2, tap.getFrame().getRegionWidth() * frac, tap.getFrame().getRegionHeight() * frac);

		renderSoundRelatedButtons();

		batch.end();
	}

	/**
	 * Render renders sound related buttons.
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
	 * handle Input handles the users input considering the buttons
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
			else
				moving = 1;
		}
	}

	/**
	 * dispose disposes the batch
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
