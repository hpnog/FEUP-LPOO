package maze.gui;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import maze.logic.Dart;
import maze.logic.Dragao;
import maze.logic.Escudo;
import maze.logic.Espada;
import maze.logic.Heroi;
import maze.logic.Jogo;
import maze.logic.Jogo.GamePreferences;
import maze.logic.Random_generator;
import maze.logic.SaveAndLoad;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class MazeDisplay extends JFrame implements KeyListener, ComponentListener{
	private static final long serialVersionUID = -191089706890276055L;
	private static Jogo jogoG;
	private JPanel game;
	private GameSideBar sideBar;
	private MazeGrid mazeGrid;
	private JPanel buttons;
	private JButton quit;
	private JButton newGame;
	private JButton saveGame;
	private JButton loadGame;

	/**
	 * Inicia o jogo
	 */
	public void createGame() {

		jogoG = new Jogo();
		jogoG.getPrefs();
		jogoG.setLabirinto(new Random_generator(GamePreferences.getMazeSize()));
		jogoG.setDragoes(new Dragao[GamePreferences.getNumberOfDragons()]);
		jogoG.setDardos(new Dart [jogoG.getDragoes().length]);
		jogoG.setInter(3);
		jogoG.setHeroi(new Heroi());
		jogoG.setEspada(new Espada());
		jogoG.setEscudo(new Escudo());

		for (int i = 0; i < jogoG.getDragoes().length; i++)
			jogoG.setDragao(i, new Dragao(GamePreferences.getType()));

		jogoG.random_hero_start();
		jogoG.random_sword();
		jogoG.shield_random_start();

		for (int i = 0; i < jogoG.getDragoes().length; i++) {
			jogoG.setDart(i, new Dart(1, 1));
			jogoG.random_dardo(i);
		}

		for (int i = 0; i < jogoG.getDragoes().length; i++)
			jogoG.random_dragao(i);
	}

	public void fillGame() {
		jogoG = new Jogo();
		jogoG.getPrefs();
		jogoG.setDragoes(new Dragao[GamePreferences.getNumberOfDragons()]);
		jogoG.setDardos(new Dart [jogoG.getDragoes().length]);
		jogoG.setInter(3);
		jogoG.setHeroi(new Heroi());
		jogoG.setEspada(new Espada());
		jogoG.setEscudo(new Escudo());

		for (int i = 0; i < jogoG.getDragoes().length; i++)
			jogoG.setDragao(i, new Dragao(GamePreferences.getType()));

		jogoG.random_hero_start();
		jogoG.random_sword();
		jogoG.shield_random_start();

		for (int i = 0; i < jogoG.getDragoes().length; i++) {
			jogoG.setDart(i, new Dart(1, 1));
			jogoG.random_dardo(i);
		}

		for (int i = 0; i < jogoG.getDragoes().length; i++)
			jogoG.random_dragao(i);
	}

	/**
	 * Create the frame.
	 * @param toLoad 
	 */
	public MazeDisplay(boolean load) {
		setTitle("Defeat the dragons!");
		addComponentListener(this);
		addKeyListener(this);
		setFocusable(true);
		if (!load)
			createGame();
		game = new JPanel();
		mazeGrid = new MazeGrid();
		buttons = new JPanel();
		newGame = new JButton("New Game");
		loadGame = new JButton("Load game");
		saveGame = new JButton("Save game");
		quit = new JButton("Quit to Main Menu");
		saveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				try {
					SaveAndLoad.saveGame(jogoG);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "An error as occured loading your game");
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Game saved");
				requestFocus();

			}
		});
		loadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				Jogo tempo = null;
				try {
					tempo = SaveAndLoad.loadGame(tempo);
				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(null, "An error as occured loading your game");
					e.printStackTrace();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "An error as occured loading your game");
					e.printStackTrace();
				}
				if (tempo != null) {
					jogoG = tempo;
					setVisible(false);
					try {
						MazeDisplay frame = new MazeDisplay(true);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Game loaded");
				}
				requestFocus();
			}
		});
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				int answer = JOptionPane.showConfirmDialog(null, "Are you sure you wish to start a New Game?");
				if (answer == JOptionPane.YES_OPTION) {
					createGame();
					mazeGrid.game();
					requestFocus();
				}
				else
					requestFocus();
			}
		});
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int answer = JOptionPane.showConfirmDialog(null, "Are you sure you wish\nto quit to MainMenu?");
				if (answer == JOptionPane.YES_OPTION) {
					setVisible(false);
					try {
						MainMenu frame = new MainMenu();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else {}
			}
		});
		buttons.add(newGame);
		buttons.add(saveGame);
		buttons.add(loadGame);
		buttons.add(quit);

		sideBar = new GameSideBar();

		game.setLayout(new BorderLayout(0, 0));
		game.add(mazeGrid);
		this.getContentPane().add(sideBar, BorderLayout.EAST);
		this.getContentPane().add(buttons, BorderLayout.SOUTH);
		this.getContentPane().add(game, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 900);
		mazeGrid.setSize(getWidth(), getHeight());
		mazeGrid.game();
	}

	@Override
	public void keyPressed(KeyEvent arg) {
		jogoG.getPrefs();
		if (arg.getKeyChar() == GamePreferences.getExitKey())
			returnFunc();
		mazeGrid.game();	
		int choice = -1;
		choice = interpretaOpcao(arg);					//Vai buscar o valor de choice
		if (choice > 99 && getJogoG().getHeroi().getDardos() > 0) {
			char tempo [][] = showShotOnScreen(choice);
			mazeGrid.gameShot();
			jogoG.getLabirinto().setLab(tempo);
			JOptionPane.showMessageDialog(null, "You shot a dard");
		}
		if (choice != -1)								//Se choice for valido
			choice = jogoG.interpreta_opcao(choice);	//Reage em funcao de choice
		mazeGrid.game();								//Atualiza o labirinto
		if (choice == 10) {
			returnFunc();
			return;
		}
		if (choice != 5 && choice != 10) {
			choice = jogoG.endOfTurn(choice);			//Verifica se o heroi morreu
			mazeGrid.game();
		}
		if (choice == 5) {								//Se o heroi morreu
			sideBar.update();							//atualiza a barra
			youDied();									//Mostra mensage de morte
			returnFunc();								//termina o jogo 
			return;
		}
		else if (choice == 10) {
			sideBar.update();
			returnFunc();
			return;
		}
		else {
			sideBar.update();							//atualiza a barra
		}

		choice = jogoG.moveAndSpit_dragoes(choice);		//Move dragoes
		char [][] original = deepClone(jogoG.getLabirinto().getLab());
		displaySpits();
		jogoG.getLabirinto().setLab(original);
		if (choice == 10) {								//Se o heroi morrer por fogo
			sideBar.update();
			killedByFire();
			returnFunc();
			return;
		}
		else {
			sideBar.update();
		}
		choice = jogoG.endOfTurn(choice);				//Verifica se o heroi morreu
		if (choice == 5) {								//Se o heroi morreu
			sideBar.update();							//atualiza a barra
			youDied();									//Mostra mensage de morte
			returnFunc();								//termina o jogo
			return;
		}
		else {
			sideBar.update();							//atualiza a barra
		}

		mazeGrid.setSize(getWidth(), getHeight());
	}

	private void displaySpits() {
		for (int i = 0; i < jogoG.getDragoes().length; i++) {
			if (jogoG.getDragoes()[i].isAlive()) {
				if (jogoG.getDragoes()[i].getSpit() == "left")
					showSpit(jogoG.getDragoes()[i], -1, 0);
				else if (jogoG.getDragoes()[i].getSpit() == "right") 
					showSpit(jogoG.getDragoes()[i], 1, 0);
				else if (jogoG.getDragoes()[i].getSpit() == "up") 
					showSpit(jogoG.getDragoes()[i], 0, -1);
				else if (jogoG.getDragoes()[i].getSpit() == "down") 
					showSpit(jogoG.getDragoes()[i], 0, 1);
				if (jogoG.getDragoes()[i].getSpit() != "")
					jogoG.getDragoes()[i].setSpit("");
			}
		}
	}

	private void showSpit(Dragao dragao, int xDiff, int yDiff) {
		char tempo [][] = jogoG.getLabirinto().getLab();

		int choice;
		if (yDiff == -1)
			choice = 102;
		else if (yDiff == 1)
			choice = 101;
		else if (xDiff == 1)
			choice = 103;
		else
			choice = 104;
		int counter = 0;
		int x = dragao.getX_coord();
		int y =dragao.getY_coord();
		while (counter < 3 && x+xDiff < MazeDisplay.getJogoG().getLabirinto().getSize() && x+xDiff > 0 && y+yDiff > 0 && y+yDiff < MazeDisplay.getJogoG().getLabirinto().getSize()) {
			if (tempo[xDiff+x][yDiff+y] == 'X')
				break;
			else if (tempo[xDiff+x][y+yDiff] == 'H' || tempo[xDiff+x][y+yDiff] == 'A') {
				tempo[x+xDiff][y+yDiff] = 'P';
				break;
			}
			else
				tempo[x+xDiff][y+yDiff] = 'V';

			if (choice == 102)
				yDiff--;
			else if (choice == 101)
				yDiff++;
			else if (choice == 103) 
				xDiff++;
			else 
				xDiff--;
			counter++;
		}
		jogoG.displayDragoes();
		mazeGrid.gameShot();
		jogoG.getLabirinto().setLab(tempo);
	}

	public char[][] deepClone(char[][] m) {
		char[][] c = m.clone();
		for (int i = 0; i < m.length; i++)
			c[i] = m[i].clone();
		return c;
	}

	private char[][] showShotOnScreen(int choice) {
		char [][] original = deepClone(jogoG.getLabirinto().getLab());

		if (choice < 100)
			return null;

		int xDiff;
		int yDiff;

		if (choice == 101) {
			xDiff = 0;
			yDiff = -1;
		}
		else if (choice == 102) {
			xDiff = 0;
			yDiff = 1;
		}
		else if (choice == 103) {
			xDiff = 1;
			yDiff = 0;
		}
		else {
			xDiff = -1;
			yDiff = 0;
		}
		char[][] tempo = MazeDisplay.getJogoG().getLabirinto().getLab();
		int x = MazeDisplay.getJogoG().getHeroi().getX_coord();
		int y = MazeDisplay.getJogoG().getHeroi().getY_coord();
		while (x+xDiff < MazeDisplay.getJogoG().getLabirinto().getSize() && x+xDiff > 0 && y+yDiff > 0 && y+yDiff < MazeDisplay.getJogoG().getLabirinto().getSize()) {
			if (tempo[xDiff+x][yDiff+y] == 'X')
				break;
			else if (tempo[xDiff+x][y+yDiff] == 'd' || tempo[xDiff+x][y+yDiff] == 'D' || tempo[xDiff+x][y+yDiff] == 'F') {
				tempo[x+xDiff][y+yDiff] = 'Q';
				break;
			}
			else
				tempo[x+xDiff][y+yDiff] = 'W';

			if (choice == 101)
				yDiff--;
			else if (choice == 102)
				yDiff++;
			else if (choice == 103) 
				xDiff++;
			else 
				xDiff--;
		}


		//FAZER AQUI A DIFERENCIAÇAO DE DIREÇOES, NESTE MOMENTO SO PREVISTA PARA CIMA

		//-------------------------------------------------------------------------------------------------------------------

		return original;
	}

	public void returnFunc() {
		setVisible(false);
		try {
			MainMenu frame = new MainMenu();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		return;
	}

	@Override
	public void keyTyped(KeyEvent arg) {
		return;
	}

	public int interpretaOpcao(KeyEvent arg) {
		jogoG.getPrefs();

		if (Character.toLowerCase(arg.getKeyChar()) == GamePreferences.getUp())
			return 1;
		else if (Character.toLowerCase(arg.getKeyChar()) == GamePreferences.getLeft())
			return 3;
		else if (Character.toLowerCase(arg.getKeyChar()) == GamePreferences.getRight())
			return 4;
		else if (Character.toLowerCase(arg.getKeyChar()) == GamePreferences.getDown())
			return 2;
		else if (Character.toLowerCase(arg.getKeyChar()) == GamePreferences.getsUp())
			return 101;
		else if (Character.toLowerCase(arg.getKeyChar()) == GamePreferences.getsDown())
			return 102;
		else if (Character.toLowerCase(arg.getKeyChar()) == GamePreferences.getsLeft())
			return 104;
		else if (Character.toLowerCase(arg.getKeyCode()) == GamePreferences.getsRight())
			return 103;
		return -1;
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * When window is resized
	 */
	@Override
	public void componentResized(ComponentEvent arg0) {
		mazeGrid.setSize(getWidth(), getHeight());
		mazeGrid.game();
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static void messageDragonKilled() {
		JOptionPane.showMessageDialog(null, "You just killed a dragon!");		
	}

	public static void way_out() {
		JOptionPane.showMessageDialog(null, "You just found the way out!");
	}
	public static void dragon_still_alive() {
		JOptionPane.showMessageDialog(null, "You cannot exit. \nThere are dragons still alive!");
	}
	public static void dragon_sleeping() {
		JOptionPane.showMessageDialog(null, "This dragon is asleep");
	}
	public static void wall() {
		JOptionPane.showMessageDialog(null, "You cannot go through a wall");
	}
	public static void noDarts() {
		JOptionPane.showMessageDialog(null, "You have no darts");
	}
	public static void killedByFire() {
		JOptionPane.showMessageDialog(null, "You just died by fire!");
	}
	public static void youDied() {
		JOptionPane.showMessageDialog(null, "You just died!");
	}

	public static void setJogo(Jogo readObject) {
		jogoG = readObject;
	}

	public static Jogo getJogoG() {
		return jogoG;
	}

	public static void setJogoG(Jogo jogoG) {
		MazeDisplay.jogoG = jogoG;
	}



}
