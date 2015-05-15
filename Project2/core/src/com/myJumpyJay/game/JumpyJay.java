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
		
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	public void pause() { }
	
	public void resume() { }
	
	public void dispose() { }
	
}
