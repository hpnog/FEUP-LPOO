package maze.gui;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import maze.logic.Default_maze;
import maze.logic.Dragao;
import maze.logic.Espada;
import maze.logic.Heroi;
import maze.logic.Jogo;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;

import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MazeDisplay extends JFrame implements KeyListener, ComponentListener{
	private Jogo jogo;
	private JPanel game;
	private MazeGrid mazeGrid;
	private JPanel buttons;
	private JButton quit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MazeDisplay frame = new MazeDisplay();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
		
	/**
	 * Inicia o jogo
	 */
	public void createGame() {

		jogo = new Jogo(10);
		jogo.labirinto = new Default_maze(10);
		jogo.heroi = new Heroi();
		jogo.dragoes = new Dragao[1];
		jogo.dragoes[0] = new Dragao(2);
		jogo.espada = new Espada();
		jogo.heroi.random_start();
		jogo.dragoes[0].random_dragao();
		jogo.espada.random_sword();

	}
	
	/**
	 * Create the frame.
	 */
	public MazeDisplay() {
		setTitle("Defeat the dragons!");
		addComponentListener(this);
		addKeyListener(this);
		
		createGame();
		game = new JPanel();
		mazeGrid = new MazeGrid();
		buttons = new JPanel();
		quit = new JButton("Quit");
		quit.addActionListener(new QuitListener());
		buttons.add(quit);
		game.setLayout(new BorderLayout(0, 0));
		game.add(mazeGrid);
		this.getContentPane().add(buttons, BorderLayout.SOUTH);
		this.getContentPane().add(game, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 600);
		mazeGrid.setSize(getWidth(), getHeight());
		mazeGrid.game();
	}

	@Override
	public void keyPressed(KeyEvent arg) {
		int choice = -1;
		if (arg.getKeyChar() == 'q')
			System.exit(0);

		choice = interpretaOpcao(arg);
		choice = Jogo.moveAndSpit_dragoes(choice);
		if (choice == 10 || choice == 5)
			System.exit(0);
		if (choice != -1)
			choice = Jogo.interpreta_opcao(choice);
		choice = Jogo.endOfTurn(choice);
		if (choice == 10 || choice == 5)
			System.exit(0);
		mazeGrid.setSize(getWidth(), getHeight());
		mazeGrid.game();
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
		if (arg.getKeyChar() == 'w')
			return 1;
		else if (arg.getKeyChar() == 'a')
			return 3;
		else if (arg.getKeyChar() == 'd')
			return 4;
		else if (arg.getKeyChar() == 's')
			return 2;
		else if (arg.getKeyChar() == 'y')
			return 101;
		else if (arg.getKeyChar() == 'h')
			return 102;
		else if (arg.getKeyChar() == 'g')
			return 104;
		else if (arg.getKeyCode() == 'j')
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

	public class QuitListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
		}
	}
}
