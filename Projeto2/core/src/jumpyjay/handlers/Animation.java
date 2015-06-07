package jumpyjay.handlers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * The Class Animation.
 * 
 * This class is responsible for managing several animation - the robots for example.
 */
public class Animation {
	
	/** The frames.
	 * 
	 * Where the multiple frames are stored as a TextRegion. 
	 */
	private TextureRegion[] frames;
	
	/** The time
  	 *
	 *To control when to change frame. 
	 */
	private float time;
	
	/** The delay.
	 * 
	 * The time it takes to change the frame.
	 */
	private float delay;
	
	/** The current frame. */
	private int currentFrame;
	
	/** The times played. */
	private int timesPlayed;
	
	/** The loop.
	 * 
	 * If the animation is to repeat itself or not.
	 */
	private boolean loop;
	
	/**
	 * Instantiates a new animation.
	 * 
	 * Empty constructor.
	 */
	public Animation() {}
	
	/**
	 * Instantiates a new animation.
	 *
	 * @param frames TextureRegion with the multiple frames of the animation
	 */
	public Animation(TextureRegion[] frames) {
		this(frames, 1 / 2f);
	}
	
	/**
	 * Instantiates a new animation.
	 *
	 * @param frames TextureRegion with the multiple frames of the animation
	 * @param delay Time it takes to change frame
	 */
	public Animation(TextureRegion[] frames, float delay) {
		setFrames(frames, delay);
	}
	
	/**
	 * Sets the frames.
	 *
	 * @param frames TextureRegion with the multiple frames of the animation
	 * @param delay Time it takes to change frame
	 */
	public void setFrames(TextureRegion[] frames, float delay) {
		this.frames = frames;
		time = 0;
		this.delay = delay;
		currentFrame = 0;
		timesPlayed = 0;
		loop = true;
	}
	
	/**
	 * Sets the frames.
	 *
	 * @param frames TextureRegion with the multiple frames of the animation
	 * @param delay Time it takes to change frame
	 * @param loop Boolean that defines if the animation is to loop or not
	 */
	public void setFrames(TextureRegion[] frames, float delay, boolean loop) {
		this.frames = frames;
		time = 0;
		this.delay = delay;
		currentFrame = 0;
		timesPlayed = 0;
		this.loop = loop;
	}
	
	/**
	 * Update.
	 *
	 * @param dt The time that has passed since the last update
	 */
	public void update(float dt) {
		if (!loop && timesPlayed > 0) return;
		if (delay <= 0) return;
		time += dt;
		while (time >= delay) {
			step();
		}
	}
	
	/**
	 * Step.
	 */
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
	
	/**
	 * Gets the current frame.
	 *
	 * @return the current frame
	 */
	public TextureRegion getFrame() {
		return frames[currentFrame];
	}
	
	/**
	 * Gets the timeplayed.
	 *
	 * @return the timeplayed
	 */
	public int getTimeplayed() {
		return timesPlayed;
	}
	
}
