package com.mygdx.gamestates;

import com.mygdx.game.SingletonVandC;
import com.mygdx.gamestates.GameState;

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
	}
	
	public void update (float dt) {
		currentGameState.update(dt);
	}
	
	public void draw () {
		currentGameState.render();
	}
}
