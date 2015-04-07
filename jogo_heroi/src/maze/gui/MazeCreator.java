package maze.gui;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import maze.logic.Dardo;
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

public class MazeCreator extends JFrame {
	private JPanel mazeGenerator;
	private MazeCreatorGrid mazeCreatorGrid;
	private JPanel buttons;
	private JButton back;
	private JButton validate;
	private JButton finish;

	public class MazeCreatorGrid extends JPanel {
		MazeCreatorGrid() {
			super();
		}
		
		public void game() {
			//Remove all content
			removeAll();
			//Repaints the content
			repaint();
			//Reintroduces the correct content
										//introduzConteudo();
			//Valida novamente o conteudo
			revalidate();
		}

	};

	public MazeCreator() {
		setTitle("Create your Maze");
		setFocusable(true);
		
		mazeGenerator = new JPanel();
		mazeCreatorGrid = new MazeCreatorGrid();
		buttons = new JPanel();
		back = new JButton("back");
		validate = new JButton("Validate");
		finish = new JButton("Finish");

		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				returnFunc();
				requestFocus();
			}
		});

		//FINISH
		validate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				requestFocus();
			}
		});

		//FINISH
		finish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				requestFocus();
			}
		});
		
		buttons.add(back);
		buttons.add(validate);
		buttons.add(finish);
		mazeGenerator.setLayout(new BorderLayout(0, 0));
		mazeGenerator.add(mazeCreatorGrid);
		this.getContentPane().add(buttons, BorderLayout.SOUTH);
		this.getContentPane().add(mazeGenerator, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 900);
		mazeCreatorGrid.setSize(getWidth(), getHeight());
		mazeCreatorGrid.game();
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

}
