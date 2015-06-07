package jumpyjay.gamestates;

/**
 * The Class GameState.
 * 
 * This is the abstract class for every gamestate
 */
public abstract class GameState {
	
	/** The game state manager. */
	protected GameStateManager gameStateManager;
	
	/**
	 * Instantiates a new game state.
	 *
	 * @param gameStateManager
	 */
	protected GameState(GameStateManager gameStateManager) {
		this.gameStateManager = gameStateManager;
	}
	
	/**
	 * Inits the gamestate.
	 */
	public abstract void init();
	
	/**
	 * Update.
	 *
	 * @param dt the time since the last update
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
