package maze.gui;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JFrame;

import maze.gui.MazeCreatorGrid;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MazeCreator extends JFrame implements ComponentListener{
	private static final long serialVersionUID = 2204086892323041058L;
	private JPanel mazeGenerator;
	private MazeCreatorGrid mazeCreatorGrid;
	private JPanel buttons;
	private JButton back;
	private JButton validate;
	private JButton finish;

	public MazeCreator() {
		setTitle("Create your Maze");
		addComponentListener(this);
		setFocusable(true);

		mazeGenerator = new JPanel();
		mazeGenerator.setLayout(new BorderLayout(0, 0));
		setBounds(100, 100, 800, 900);
		mazeCreatorGrid = new MazeCreatorGrid(getWidth(), getHeight());
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
		mazeGenerator.add(mazeCreatorGrid);
		this.getContentPane().add(buttons, BorderLayout.SOUTH);
		this.getContentPane().add(mazeGenerator, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		mazeCreatorGrid.setSize(getWidth(), getHeight());		
		mazeCreatorGrid.game();
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
