package maze.gui;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import maze.logic.Dardo;
import maze.logic.Default_maze;
import maze.logic.Dragao;
import maze.logic.Escudo;
import maze.logic.Espada;
import maze.logic.Heroi;
import maze.logic.Jogo;
import maze.logic.Lab;
import maze.logic.Random_generator;
import maze.logic.SaveAndLoad;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;

import java.awt.FlowLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.Struct;

public class MazeDisplay extends JFrame implements KeyListener, ComponentListener{
	public static Jogo jogo;
	private JPanel game;
	private MazeGrid mazeGrid;
	private JPanel buttons;
	private JButton quit;
	private JButton newGame;
	private JButton saveGame;

	/**
	 * Inicia o jogo
	 */
	public void createGame() {

		Jogo.labirinto = new Random_generator(Jogo.gamePreferences.mazeSize);
		Jogo.dragoes = new Dragao[Jogo.gamePreferences.numberOfDragons];
		Jogo.dardos = new Dardo [Lab.size / 4];
		Jogo.inter = 3;
		Jogo.heroi = new Heroi();
		Jogo.espada = new Espada();
		Jogo.escudo = new Escudo();

		for (int i = 0; i < Jogo.dragoes.length; i++)
			Jogo.dragoes[i] = new Dragao(Jogo.gamePreferences.type);

		Jogo.heroi.random_start();
		Jogo.espada.random_sword();
		Jogo.escudo.random_start();

		for (int i = 0; i < Lab.size / 4; i++) {
			Jogo.dardos[i] = new Dardo(1, 1);
			Jogo.dardos[i].random_dardo();
		}

		for (int i = 0; i < Jogo.dragoes.length; i++)
			Jogo.dragoes[i].random_dragao();


	}

	/**
	 * Create the frame.
	 */
	public MazeDisplay(boolean load) {
		setTitle("Defeat the dragons!");
		addComponentListener(this);
		addKeyListener(this);
		setFocusable(true);
		if (!load)
		{
			createGame();
		}
		game = new JPanel();
		mazeGrid = new MazeGrid();
		buttons = new JPanel();
		newGame = new JButton("New Game");
		saveGame = new JButton("Save game");
		quit = new JButton("Quit to Main Menu");
		saveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				try {
					SaveAndLoad.saveGame(MazeDisplay.jogo);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "An error as occured loading your game");
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Game saved");
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
		buttons.add(quit);
		game.setLayout(new BorderLayout(0, 0));
		game.add(mazeGrid);
		this.getContentPane().add(buttons, BorderLayout.SOUTH);
		this.getContentPane().add(game, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 900);
		mazeGrid.setSize(getWidth(), getHeight());
		mazeGrid.game();
	}
	
	@Override
	public void keyPressed(KeyEvent arg) {
		if (arg.getKeyChar() == Jogo.gamePreferences.exitKey)
			returnFunc();

		int choice = -1;

		choice = Jogo.moveAndSpit_dragoes(choice);
		if (choice == 10 || choice == 5)
			returnFunc();

		choice = interpretaOpcao(arg);
		if (choice == 10 || choice == 5)
			returnFunc();

		if (choice != -1)
			choice = Jogo.interpreta_opcao(choice);

		choice = Jogo.endOfTurn(choice);
		if (choice == 10 || choice == 5)
			returnFunc();

		mazeGrid.setSize(getWidth(), getHeight());
		mazeGrid.game();
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
		if (arg.getKeyChar() == Jogo.gamePreferences.up)
			return 1;
		else if (arg.getKeyChar() == Jogo.gamePreferences.left)
			return 3;
		else if (arg.getKeyChar() == Jogo.gamePreferences.right)
			return 4;
		else if (arg.getKeyChar() == Jogo.gamePreferences.down)
			return 2;
		else if (arg.getKeyChar() == Jogo.gamePreferences.sUp)
			return 101;
		else if (arg.getKeyChar() == Jogo.gamePreferences.sDown)
			return 102;
		else if (arg.getKeyChar() == Jogo.gamePreferences.sLeft)
			return 104;
		else if (arg.getKeyCode() == Jogo.gamePreferences.sRight)
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
	public static void noDards() {
		JOptionPane.showMessageDialog(null, "You have no dards");
	}
	public static void killedByFire() {
		JOptionPane.showMessageDialog(null, "You just died by fire!");
	}
	public static void youDied() {
		JOptionPane.showMessageDialog(null, "You just died!");
	}

}
