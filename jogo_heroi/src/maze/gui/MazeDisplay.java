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

// TODO: Auto-generated Javadoc
/**
 * The Class MazeDisplay.
 */
public class MazeDisplay extends JFrame implements KeyListener, ComponentListener{
	

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -191089706890276055L;
	
	/** The gameG. */
	private static Jogo jogoG;
	
	/** The game. */
	private JPanel game;
	
	/** The side bar. */
	private GameSideBar sideBar;
	
	/** The maze grid. */
	private MazeGrid mazeGrid;
	
	/** The buttons. */
	private JPanel buttons;
	
	/** The quit. */
	private JButton quit;
	
	/** The new game. */
	private JButton newGame;
	
	/** The save game. */
	private JButton saveGame;
	
	/** The load game. */
	private JButton loadGame;

	/**
	 * Inicializes the game.
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

	/**
	 * Fill game.
	 */
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
	 * Creates the frame.
	 *
	 * @param load the load
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

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
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
		mazeGrid.game();		//Atualiza o labirinto
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
			sideBar.update();							//updates the sidebar
		}
		
		choice = jogoG.moveAndSpit_dragoes(choice);		//move dragons
		char [][] original = deepClone(jogoG.getLabirinto().getLab());
		
		mazeGrid.game();
		
		displaySpits();
		jogoG.getLabirinto().setLab(original);
		if (choice == 10) {								//if hero dies by fire
			sideBar.update();
			killedByFire();
			returnFunc();
			return;
		}
		else {
			sideBar.update();
		}
		choice = jogoG.endOfTurn(choice);				//checks if hero died
		if (choice == 5) {								//if hero died
			sideBar.update();							//updates sidebar
			youDied();									//shows death message
			returnFunc();								//ends game
			return;
		}
		else {
			sideBar.update();							//updates sidebar
		}

		mazeGrid.setSize(getWidth(), getHeight());
	}

	/**
	 * Display spits.
	 */
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

	/**
	 * Show spit.
	 *
	 * @param dragao the dragon
	 * @param xDiff the x diff
	 * @param yDiff the y diff
	 */
	private void showSpit(Dragao dragao, int xDiff, int yDiff) {
		char tempo [][] = jogoG.getLabirinto().getLab();

		int choice;
		if (yDiff == -1)
			choice = 102;
		else if (yDiff == 1)
			choice = 101;
		else if (xDiff == -1)
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
				xDiff--;
			else 
				xDiff++;
			counter++;
		}
		jogoG.displayDragoes();
		mazeGrid.gameShot();
		jogoG.getLabirinto().setLab(tempo);
	}

	/**
	 * Deep clone.
	 *
	 * @param m the m
	 * @return the char[][]
	 */
	public char[][] deepClone(char[][] m) {
		char[][] c = m.clone();
		for (int i = 0; i < m.length; i++)
			c[i] = m[i].clone();
		return c;
	}

	/**
	 * Show shot on screen.
	 *
	 * @param choice the choice
	 * @return the char[][]
	 */
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

		return original;
	}

	/**
	 * Returns to main menu.
	 */
	public void returnFunc() {
		setVisible(false);
		try {
			MainMenu frame = new MainMenu();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {
		return;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent arg) {
		return;
	}

	/**
	 * Interprets option.
	 *
	 * @param arg the arg
	 * @return the int
	 */
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

	/* (non-Javadoc)
	 * @see java.awt.event.ComponentListener#componentHidden(java.awt.event.ComponentEvent)
	 */
	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.ComponentListener#componentMoved(java.awt.event.ComponentEvent)
	 */
	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * When window is resized.
	 *
	 * @param arg0 the arg0
	 */
	@Override
	public void componentResized(ComponentEvent arg0) {
		mazeGrid.setSize(getWidth(), getHeight());
		mazeGrid.game();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ComponentListener#componentShown(java.awt.event.ComponentEvent)
	 */
	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * Message dragon killed.
	 */
	public static void messageDragonKilled() {
		JOptionPane.showMessageDialog(null, "You just killed a dragon!");		
	}

	/**
	 * Way_out.
	 */
	public static void way_out() {
		JOptionPane.showMessageDialog(null, "You just found the way out!");
	}
	
	/**
	 * Dragon_still_alive.
	 */
	public static void dragon_still_alive() {
		JOptionPane.showMessageDialog(null, "You cannot exit. \nThere are dragons still alive!");
	}
	
	/**
	 * Dragon_sleeping.
	 */
	public static void dragon_sleeping() {
		JOptionPane.showMessageDialog(null, "This dragon is asleep");
	}
	
	/**
	 * Wall.
	 */
	public static void wall() {
		JOptionPane.showMessageDialog(null, "You cannot go through a wall");
	}
	
	/**
	 * No darts.
	 */
	public static void noDarts() {
		JOptionPane.showMessageDialog(null, "You have no darts");
	}
	
	/**
	 * Killed by fire.
	 */
	public static void killedByFire() {
		JOptionPane.showMessageDialog(null, "You just died by fire!");
	}
	
	/**
	 * You died.
	 */
	public static void youDied() {
		JOptionPane.showMessageDialog(null, "You just died!");
	}

	/**
	 * Sets the game.
	 *
	 * @param readObject the new game
	 */
	public static void setJogo(Jogo readObject) {
		jogoG = readObject;
	}

	/**
	 * Gets the game g.
	 *
	 * @return the game g
	 */
	public static Jogo getJogoG() {
		return jogoG;
	}

	/**
	 * Sets the game g.
	 *
	 * @param jogoG the new game g
	 */
	public static void setJogoG(Jogo jogoG) {
		MazeDisplay.jogoG = jogoG;
	}



}
