package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Element {
	
	private float x;
	private float y;
	private float elementHeight;
	private float elementWidth;
	private Sprite sprites[];
	
	public Element(Texture tex[], float x, float y, float width, float height)
	{
		this.x = x;
		this.y = y;
		this.elementWidth = width;
		this.elementHeight = height;
		this.sprites = new Sprite[tex.length];
		for (int i = 0; i < tex.length; i++)
			sprites[i] = new Sprite(tex[i], (int) this.x, (int) this.y, (int) this.elementWidth,(int) this.elementHeight);
	}

	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getElementHeight() {
		return elementHeight;
	}

	public void setElementHeight(float elementHeight) {
		this.elementHeight = elementHeight;
	}

	public float getElementWidth() {
		return elementWidth;
	}

	public void setElementWidth(float elementWidth) {
		this.elementWidth = elementWidth;
	}

	public Sprite[] getSprites() {
		return sprites;
	}

	public void setSprites(Sprite[] sprites) {
		this.sprites = sprites;
	}

}
