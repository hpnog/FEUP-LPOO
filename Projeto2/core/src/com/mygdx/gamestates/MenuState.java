package com.mygdx.gamestates;

import resCode.SimpleButton;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyJumpyJay;

public class MenuState extends GameState {

	Texture newGameTexture;
	Texture exitTexture;
	
	SimpleButton newGame;
	SimpleButton exit;

	SpriteBatch batch;

	public MenuState(GameStateManager gameStateManager) {
		super(gameStateManager);
		init();
	}

	@Override
	public void init() {
		newGameTexture = new Texture(Gdx.files.internal("images/singleplayer.png"));
		exitTexture = new Texture(Gdx.files.internal("images/exit.png"));
		
		newGame = new SimpleButton(newGameTexture,
				MyJumpyJay.WIDTH / 2 - MyJumpyJay.WIDTH / 8,
				MyJumpyJay.HEIGHT / 2 - 3 * MyJumpyJay.HEIGHT / 10,
				MyJumpyJay.WIDTH / 4,
				MyJumpyJay.HEIGHT / 20 );
		exit = new SimpleButton(exitTexture,
				MyJumpyJay.WIDTH / 2 - MyJumpyJay.WIDTH / 16,
				MyJumpyJay.HEIGHT / 2 - 4 * MyJumpyJay.HEIGHT / 10,
				MyJumpyJay.WIDTH / 8,
				MyJumpyJay.HEIGHT / 20);
		
		batch = new SpriteBatch();
	}
	@Override
	public void update(float dt) {
		if (Gdx.input.isTouched())
		{
			if (newGame.checkIfClicked(Gdx.input.getX(), MyJumpyJay.HEIGHT - Gdx.input.getY()))
			{
				gameStateManager.setState(GameStateManager.PLAY);
			}
			if (exit.checkIfClicked(Gdx.input.getX(), MyJumpyJay.HEIGHT - Gdx.input.getY()))
			{
				this.dispose();
				Gdx.app.exit();
			}
		}
	}
	@Override
	public void render() {
		Gdx.gl20.glClearColor(217 / (float) 256, 208 / (float) 256, 179 / (float) 256, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		batch.begin();
		batch.draw(newGameTexture,
				MyJumpyJay.WIDTH / 2 - MyJumpyJay.WIDTH / 8,
				MyJumpyJay.HEIGHT / 2 - 3 * MyJumpyJay.HEIGHT / 10,
				MyJumpyJay.WIDTH / 4,
				MyJumpyJay.HEIGHT / 20 );
		batch.draw(exitTexture,
				MyJumpyJay.WIDTH / 2 - MyJumpyJay.WIDTH / 16,
				MyJumpyJay.HEIGHT / 2 - 4 * MyJumpyJay.HEIGHT / 10,
				MyJumpyJay.WIDTH / 8,
				MyJumpyJay.HEIGHT / 20);
		batch.end();

	}
	@Override
	public void handleInput() {
		// TODO Auto-generated method stub

	}
	@Override
	public void dispose() {
		batch.dispose();
		newGameTexture.dispose();
		exitTexture.dispose();
	}

}
