package maze.gui;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import maze.logic.Jogo;
import maze.logic.SaveAndLoad;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

// TODO: Auto-generated Javadoc
/**
 * The Class MainMenu.
 */
public class MainMenu extends JFrame implements ComponentListener{
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9019263358824896833L;
	
	/** The buttons. */
	private JPanel buttons;
	
	/** The menu image. */
	private JPanel menuImage;
	
	/** The quit. */
	private JButton quit;
	
	/** The new game. */
	private JButton newGame;
	
	/** The options. */
	private JButton options;
	
	/** The load game. */
	private JButton loadGame;
	
	/** The background. */
	private ImageIcon background;

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
		
	/**
	 * Create the frame.
	 */
	public MainMenu() {
		setResizable(false);
		setTitle("Defeat the dragons! - Main Menu");
		addComponentListener(this);
		setFocusable(true);
		
		background = new ImageIcon(this.getClass().getResource("res/menuBackground.png"));
		Image bg = background.getImage();
		Image newimg = bg.getScaledInstance(480, 400,  java.awt.Image.SCALE_FAST);
		menuImage = new JPanel();
		menuImage.add(new JLabel(new ImageIcon(newimg)));
		
		buttons = new JPanel();
		newGame = new JButton("New Game");
		loadGame = new JButton("Load game");
		quit = new JButton("Quit");
		options = new JButton("Options");
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
					MazeDisplay.setJogoG(tempo);
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
		options.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				try {
					OptionsPanel frame = new OptionsPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				requestFocus();
			}
		});
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				setVisible(false);
				try {
					MazeDisplay frame = new MazeDisplay(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				requestFocus();
			}
		});
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int answer = JOptionPane.showConfirmDialog(null, "Are you sure you wish to Quit?");
				if (answer == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
				else
					requestFocus();
			}
		});
		buttons.add(newGame);
		buttons.add(loadGame);
		buttons.add(options);
		buttons.add(quit);
		this.getContentPane().add(menuImage, BorderLayout.CENTER);
		this.getContentPane().add(buttons, BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
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

	/* (non-Javadoc)
	 * @see java.awt.event.ComponentListener#componentResized(java.awt.event.ComponentEvent)
	 */
	@Override
	public void componentResized(ComponentEvent arg0) {
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ComponentListener#componentShown(java.awt.event.ComponentEvent)
	 */
	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

}
