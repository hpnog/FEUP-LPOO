package jumpyjay.gamestates;

public abstract class GameState {
	
	protected GameStateManager gameStateManager;
	
	protected GameState(GameStateManager gameStateManager) {
		this.gameStateManager = gameStateManager;
	}
	
	public abstract void init();
	public abstract void update (float dt);
	public abstract void render();
	public abstract void handleInput();
	public abstract void dispose();
	public abstract void pause();

}
