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
import maze.logic.Lab;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame implements ComponentListener{
	private JPanel buttons;
	private JPanel menuImage;
	private JButton quit;
	private JButton newGame;
	private JButton options;
	private ImageIcon background;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jogo jogo = new Jogo(Jogo.gamePreferences.mazeSize);
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
		quit = new JButton("Quit");
		options = new JButton("Options");
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
					MazeDisplay frame = new MazeDisplay();
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
