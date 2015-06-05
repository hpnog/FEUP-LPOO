package jumpyjay.handlers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation {
	
	private TextureRegion[] frames;
	private float time;
	private float delay;
	private int currentFrame;
	private int timesPlayed;
	
	private boolean loop;
	
	public Animation() {}
	
	public Animation(TextureRegion[] frames) {
		this(frames, 1 / 2f);
	}
	
	public Animation(TextureRegion[] frames, float delay) {
		setFrames(frames, delay);
	}
	
	public void setFrames(TextureRegion[] frames, float delay) {
		this.frames = frames;
		time = 0;
		this.delay = delay;
		currentFrame = 0;
		timesPlayed = 0;
		loop = true;
	}
	
	public void setFrames(TextureRegion[] frames, float delay, boolean loop) {
		this.frames = frames;
		time = 0;
		this.delay = delay;
		currentFrame = 0;
		timesPlayed = 0;
		this.loop = loop;
	}
	
	public void update(float dt) {
		if (!loop && timesPlayed > 0) return;
		if (delay <= 0) return;
		time += dt;
		while (time >= delay) {
			step();
		}
	}
	
	private void step() {
		int tempo = currentFrame;
		time -= delay;
		currentFrame++;
		if (currentFrame == frames.length) {
			currentFrame = 0;
			timesPlayed++;
		}
		if (timesPlayed > 0 && !loop)
			currentFrame = tempo;
	}
	
	public TextureRegion getFrame() {
		return frames[currentFrame];
	}
	
	public int getTimeplayed() {
		return timesPlayed;
	}
	
}
