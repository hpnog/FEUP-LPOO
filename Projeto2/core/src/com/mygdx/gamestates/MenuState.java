package com.mygdx.gamestates;

import resCode.SimpleButton;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SingletonVandC;

public class MenuState extends GameState {

	Texture newGameTexture;
	Texture exitTexture;

	SimpleButton newGame;
	SimpleButton exit;

	SpriteBatch batch;
	
	SingletonVandC singleton;

	public MenuState(GameStateManager gameStateManager) {
		super(gameStateManager);
		init();
	}

	@Override
	public void init() {
		singleton = SingletonVandC.getSingleton();
		
		newGameTexture = new Texture(Gdx.files.internal("images/singleplayer.png"));
		exitTexture = new Texture(Gdx.files.internal("images/exit.png"));

		//singleton.assetManager.load("newGameTexture", Texture.class, Gdx.files.internal("images/singleplayer.png");
		
		
		newGame = new SimpleButton(newGameTexture,
				singleton.SCREEN_WIDTH / 2 - singleton.SCREEN_WIDTH / 8,
				singleton.SCREEN_HEIGHT / 2 - 3 * singleton.SCREEN_HEIGHT / 10,
				singleton.SCREEN_WIDTH / 4,
				singleton.SCREEN_HEIGHT / 20 );
		exit = new SimpleButton(exitTexture,
				singleton.SCREEN_WIDTH / 2 - singleton.SCREEN_WIDTH / 16,
				singleton.SCREEN_HEIGHT / 2 - 4 * singleton.SCREEN_HEIGHT / 10,
				singleton.SCREEN_WIDTH / 8,
				singleton.SCREEN_HEIGHT / 20);

		batch = new SpriteBatch();
	}

	@Override
	public void update(float dt) {
		handleInput();
	}

	@Override
	public void render() {
		Gdx.gl20.glClearColor(217 / (float) 256, 208 / (float) 256, 179 / (float) 256, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		batch.begin();
		batch.draw(newGameTexture,
				singleton.SCREEN_WIDTH / 2 - singleton.SCREEN_WIDTH / 8,
				singleton.SCREEN_HEIGHT / 2 - 3 * singleton.SCREEN_HEIGHT / 10,
				singleton.SCREEN_WIDTH / 4,
				singleton.SCREEN_HEIGHT / 20 );
		batch.draw(exitTexture,
				singleton.SCREEN_WIDTH / 2 - singleton.SCREEN_WIDTH / 16,
				singleton.SCREEN_HEIGHT / 2 - 4 * singleton.SCREEN_HEIGHT / 10,
				singleton.SCREEN_WIDTH / 8,
				singleton.SCREEN_HEIGHT / 20);
		batch.end();

	}

	@Override
	public void handleInput() {
		if (Gdx.input.isTouched())
		{
			if (newGame.checkIfClicked(Gdx.input.getX(), singleton.SCREEN_HEIGHT - Gdx.input.getY()))
			{
				gameStateManager.setState(singleton.PLAY);
			}
			if (exit.checkIfClicked(Gdx.input.getX(), singleton.SCREEN_HEIGHT - Gdx.input.getY()))
			{
				Gdx.app.exit();
			}
		}
	}

	@Override
	public void dispose() {
		newGameTexture.dispose();
		exitTexture.dispose();
		batch.dispose();
	}

}
