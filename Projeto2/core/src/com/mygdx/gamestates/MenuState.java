package com.mygdx.gamestates;

import handlers.Animation;
import handlers.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.SingletonVandC;

public class MenuState extends GameState {

	SpriteBatch batch;	
	SingletonVandC singleton;
	OrthographicCamera cam;
	
	private int moving = 0;
	private int robotX = 0;
	
	Animation robot;
	Animation tap;
	
	public MenuState(GameStateManager gameStateManager) {
		super(gameStateManager);
		init();
	}

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
		batch.end();
	}

	@Override
	public void handleInput() {
		if (Gdx.input.justTouched())
			moving = 1;
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

}
