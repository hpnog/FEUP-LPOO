package maze.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import maze.cli.console_interface;
import maze.logic.Default_maze;
import maze.logic.Dragao;
import maze.logic.Espada;
import maze.logic.Heroi;
import maze.logic.Jogo;
import maze.logic.Lab;

public class MazeDisplay
	extends JFrame
	implements KeyListener{

	//Para efeitos de jogo
	Jogo jogo;
	
	private JPanel contentPane;
	//Temporário - a substituir por imagens
	private JLabel tempo;

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
		addKeyListener(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		createGame();
		
		//Varible for Panes size
		int paneSize = 1;
		if (Lab.size > 0) paneSize = Lab.size;

		//Divide a frame numa grelha com o tamanho correto (layout)
		contentPane.setLayout(new GridLayout(paneSize, paneSize));

		game(contentPane);

	}

	private void game(JPanel content) {
		content.removeAll();
		content.repaint();
		for (int i = 0; i < Lab.size; i++) {
			for (int j = 0; j < Lab.size; j++) {
				tempo = new JLabel("" + Lab.lab[j][i]);
				content.add(tempo);
			}
		}
		content.revalidate();
	}

	@Override
	public void keyPressed(KeyEvent arg) {
		int choice = -1;
		if (arg.getKeyChar() == 'q')
			System.exit(0);

		choice = interpretaOpcao(arg);
		choice = Jogo.moveAndSpit_dragoes(choice);
		if (choice == 10)
			System.exit(0);
		if (choice != -1)
			choice = Jogo.interpreta_opcao(choice);
		choice = Jogo.endOfTurn(choice);
		refresh_maze();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
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
	public void keyTyped(KeyEvent arg) {
		return;
	}

	private void refresh_maze() {
		/*contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//Varible for Panes size
		int paneSize = 1;
		if (Lab.size > 0) paneSize = Lab.size;

		//Divide a frame numa grelha com o tamanho correto (layout)
		contentPane.setLayout(new GridLayout(paneSize, paneSize));*/

		game(contentPane);
		
		
	}

}
