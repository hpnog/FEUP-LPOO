package com.mygdx.gamestates;

import com.mygdx.gamestates.GameState;;

public class GameStateManager {
	
	//Current gamestate
	private GameState currentGameState;
	
	public static final int MENU = 0;
	public static final int PLAY = 1;
	
	public GameStateManager() {
		setState(MENU);
	}
	
	public void setState(int state) {
		if (currentGameState != null)
			currentGameState.dispose();
		if (state == MENU) {
			currentGameState = new MenuState(this);
		}
		else if (state == PLAY) {
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
