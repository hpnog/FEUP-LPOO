package com.mygdx.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyJumpyJay;

public class MenuState extends GameState {
	
	private SpriteBatch batch;
	private Texture singlePlayer;
	
	public MenuState(GameStateManager gameStateManager) {
		super(gameStateManager);
		init();
	}

	@Override
	public void init() {
		batch = new SpriteBatch();
		singlePlayer = new Texture(Gdx.files.internal("images/singleplayer.png"));
	}
	@Override
	public void update(float dt) {

	}
	@Override
	public void draw() {
		Gdx.gl20.glClearColor(217 / (float) 256, 208 / (float) 256, 179 / (float) 256, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		batch.begin();
		batch.draw(singlePlayer, MyJumpyJay.WIDTH / 2 - 100, 150, 200, 25);
		batch.end();
	}
	@Override
	public void handleInput() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
