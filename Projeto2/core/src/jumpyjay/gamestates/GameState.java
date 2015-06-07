package jumpyjay.gamestates;

// TODO: Auto-generated Javadoc
/**
 * The Class GameState.
 */
public abstract class GameState {
	
	/** The game state manager. */
	protected GameStateManager gameStateManager;
	
	/**
	 * Instantiates a new game state.
	 *
	 * @param gameStateManager the game state manager
	 */
	protected GameState(GameStateManager gameStateManager) {
		this.gameStateManager = gameStateManager;
	}
	
	/**
	 * Inits the.
	 */
	public abstract void init();
	
	/**
	 * Update.
	 *
	 * @param dt the dt
	 */
	public abstract void update (float dt);
	
	/**
	 * Render.
	 */
	public abstract void render();
	
	/**
	 * Handle input.
	 */
	public abstract void handleInput();
	
	/**
	 * Dispose.
	 */
	public abstract void dispose();
	
	/**
	 * Pause.
	 */
	public abstract void pause();

}
