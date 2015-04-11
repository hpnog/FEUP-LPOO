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

public class MainMenu extends JFrame implements ComponentListener{
	private static final long serialVersionUID = -9019263358824896833L;
	private JPanel buttons;
	private JPanel menuImage;
	private JButton quit;
	private JButton newGame;
	private JButton options;
	private JButton loadGame;
	private ImageIcon background;

	/**
	 * Launch the application.
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
	
	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent arg0) {
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

}
