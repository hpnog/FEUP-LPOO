package com.myJumpyJay.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class JumpyJay extends ApplicationAdapter {
	
	public static int WIDTH;
	public static int HEIGHT;
	
	public static OrthographicCamera cam;
	
	SpriteBatch batch;
	Texture img;
	
	public void create () {
		
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		
		cam = new OrthographicCamera(WIDTH, HEIGHT);
		cam.translate(WIDTH / 2, HEIGHT / 2);
		cam.update();
		
		batch = new SpriteBatch();
		img = new Texture("plane.jpg");
	}

	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		batch.begin();
		batch.draw(img, WIDTH / 2 - img.getWidth() / 2, HEIGHT / 2 - img.getHeight() / 2);
		batch.end();
		
		cam.update();
	}
	
	public void pause() { }
	
	public void resume() { }
	
	public void dispose() { }
	
}
