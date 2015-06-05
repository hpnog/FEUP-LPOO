package jumpyjay.gamestates;

import jumpyjay.gamestates.GameState;
import jumpyjay.handlers.SingletonVandC;

public class GameStateManager {
	
	//Current gamestate
	private GameState currentGameState;
	private SingletonVandC singleton;
	
	public GameStateManager() {
		singleton = SingletonVandC.getSingleton();
		setState(singleton.MENU);
	}
	
	public void setState(int state) {
		if (currentGameState != null)
			currentGameState.dispose();
		if (state == singleton.MENU) {
			currentGameState = new MenuState(this);
		}
		else if (state == singleton.PLAY) {
			currentGameState = new PlayState(this);
		}
		else if (state == singleton.LEVEL)
			currentGameState = new LevelMenu(this);
		else if (state == singleton.SUCCESS)
			currentGameState = new LevelSucceded(this);
	}
	
	public void update (float dt) {
		currentGameState.update(dt);
	}
	
	public void draw () {
		currentGameState.render();
	}

	public void pause() {
		currentGameState.pause();
	}
}
