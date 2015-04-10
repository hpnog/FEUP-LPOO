package maze.gui;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import maze.gui.MazeCreatorGrid;
import maze.logic.Lab;
import maze.logic.Object;
import maze.logic.Jogo.GamePreferences;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MazeCreator extends JFrame implements ComponentListener, MouseListener{
	private static final long serialVersionUID = 2204086892323041058L;
	private JPanel mazeGenerator;
	private MazeCreatorGrid mazeCreatorGrid;
	private JPanel buttons;
	private CreatorSideBar sideBar;
	private JButton back;
	private JButton finish;

	public MazeCreator() {
		setTitle("Create your Maze");
		addComponentListener(this);
		setFocusable(true);
		addMouseListener(this);


		mazeGenerator = new JPanel();
		mazeGenerator.setLayout(new BorderLayout(0, 0));
		setBounds(100, 100, 800, 900);
		mazeCreatorGrid = new MazeCreatorGrid(getContentPane().getWidth()-100, getContentPane().getHeight()-50);
		buttons = new JPanel();
		back = new JButton("back");

		finish = new JButton("Finish");

		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				returnFunc();
				requestFocus();
			}
		});
		finish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				if (MazeCreatorGrid.getPhase().isFinished()) {
					int answer = JOptionPane.showConfirmDialog(null, "Are you sure you wish to Finish?");
					if (answer == JOptionPane.YES_OPTION) {
						try {
							setVisible(false);
							MazeDisplay frame = new MazeDisplay(true);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "You have not placed everything yet");
				}
			}
		});

		buttons.add(back);

		JButton validateMaze;
		validateMaze = new JButton("Validate");
		buttons.add(validateMaze);
		validateMaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				if (MazeCreatorGrid.getPhase().isMazeDone())
					JOptionPane.showMessageDialog(null, "This maze has already been Validated.");
				else if (!MazeCreatorGrid.getPhase().isExitPlaced())
					JOptionPane.showMessageDialog(null, "The exit is not placed yet.");
				else {
					if (checkIfMazeIsValid()) {
						MazeCreatorGrid.getPhase().setMazeDone(true);
						JOptionPane.showMessageDialog(null, "This seems to be a Valid Maze");
					}
					else
						JOptionPane.showMessageDialog(null, "This is not a Valid Maze");
				}
				requestFocus();
			}

			private boolean checkIfMazeIsValid() {
				char[][] badWalls = {
						{'X', 'X'},
						{'X', 'X'}};
				char[][] badSpaces = {
						{' ', ' '},
						{' ', ' '}};
				char[][] badDiag1 = {
						{'X', ' '},
						{' ', 'X'}};
				char[][] badDiag2 = {
						{' ', 'X'},
						{'X', ' '}};
				if (checkBoundaries(MazeDisplay.getJogoG().getLabirinto()))
					if (!hasSquare(MazeDisplay.getJogoG().getLabirinto(), badWalls))
						if (!hasSquare(MazeDisplay.getJogoG().getLabirinto(), badSpaces))
							if (!hasSquare(MazeDisplay.getJogoG().getLabirinto(), badDiag1))
								if (!hasSquare(MazeDisplay.getJogoG().getLabirinto(), badDiag2))
									if (checkExitReachable(MazeDisplay.getJogoG().getLabirinto()))
										return true;

				return false;
			}

			/* a) the maze boundaries must have exactly one exit and everything else walls
			// b) the exist cannot be a corner*/
			public boolean checkBoundaries(Lab m) {
				int countExit = 0;
				int n = m.getSize();
				for (int i = 0; i < n; i++)
					for (int j = 0; j < n; j++)
						if (i == 0 || j == 0 || i == n - 1 || j == n - 1)
							if (m.getChar(i, j) == 'S')
								if ((i == 0 || i == n-1) && (j == 0 || j == n-1))
									return false;
								else
									countExit++;
							else if (m.getChar(i, j) != 'X')
								return false;
				return countExit == 1;
			}

			/* d) there cannot exist 2x2 (or greater) squares with blanks only 
			// e) there cannot exit 2x2 (or greater) squares with blanks in one diagonal and walls in the other
			// d) there cannot exist 3x3 (or greater) squares with walls only*/
			public boolean hasSquare(Lab maze, char[][] square) {
				char [][] m = maze.getMatrix();
				for (int i = 0; i < m.length - square.length; i++)
					for (int j = 0; j < m.length - square.length; j++) {
						boolean match = true;
						for (int x = 0; x < square.length; x++)
							for (int y = 0; y < square.length; y++) {
								if (m[i+x][j+y] != square[x][y])
									match = false;
							}
						if (match)
							return true;
					}		
				return false; 
			}

			// c) there must exist a path between any blank cell and the maze exit 
			public boolean checkExitReachable(Lab maze) {
				Object p = maze.getExitPosition();
				char [][] m = deepClone(maze.getMatrix());
				visit(m, p.getX_coord(), p.getY_coord());

				for (int i = 0; i < m.length; i++)
					for (int j = 0; j < m.length; j++) {				
						if (m[i][j] != 'X' && m[i][j] != 'V') {

							return false;
						}
					}

				return true; 
			}

			/* auxiliary method used by checkExitReachable
			// marks a cell as visited (V) and proceeds recursively to its neighbors*/
			public void visit(char[][] m, int i, int j) {
				if (i < 0 || i >= m.length || j < 0 || j >= m.length)
					return;
				if (m[i][j] == 'X' || m[i][j] == 'V')
					return;
				m[i][j] = 'V';
				visit(m, i-1, j);
				visit(m, i+1, j);
				visit(m, i, j-1);
				visit(m, i, j+1);
			}

			/* Auxiliary method used by checkExitReachable.
			// Gets a deep clone of a char matrix.*/
			public char[][] deepClone(char[][] m) {
				char[][] c = m.clone();
				for (int i = 0; i < m.length; i++)
					c[i] = m[i].clone();
				return c;
			}

		});

		buttons.add(finish);
		mazeGenerator.add(mazeCreatorGrid);
		
		sideBar = new CreatorSideBar();
		
		this.getContentPane().add(sideBar, BorderLayout.EAST);
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
		mazeCreatorGrid.setSize(getContentPane().getWidth()-100, getContentPane().getHeight()-50);		
		mazeCreatorGrid.game();
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	
	public CreatorSideBar getSideBar() {
		return sideBar;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		MazeDisplay.getJogoG().getPrefs();
		if (!MazeCreatorGrid.getPhase().isExitPlaced())
			mazeCreatorGrid.putExit(arg0);
		else if (!MazeCreatorGrid.getPhase().isMazeDone())
			mazeCreatorGrid.makeMaze(arg0);
		else if (!MazeCreatorGrid.getPhase().isHeroPlaced())
			mazeCreatorGrid.placeHero(arg0);
		else if (!MazeCreatorGrid.getPhase().isShieldPlaced())
			mazeCreatorGrid.placeShield(arg0);
		else if (!MazeCreatorGrid.getPhase().isSwordPlaced())
			mazeCreatorGrid.placeSword(arg0);
		else if (MazeCreatorGrid.getPhase().getNumberOfDragonsPlaced() < GamePreferences.getNumberOfDragons())
			mazeCreatorGrid.placeDragon(arg0, MazeCreatorGrid.getPhase().getNumberOfDragonsPlaced());
		else if (MazeCreatorGrid.getPhase().getNumberOfDardsPlaced() < GamePreferences.getNumberOfDragons())
			mazeCreatorGrid.placeDards(arg0, MazeCreatorGrid.getPhase().getNumberOfDardsPlaced());
		mazeCreatorGrid.game();
		
		sideBar.update();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
