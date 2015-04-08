package maze.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import maze.logic.Jogo;
import maze.logic.Jogo.GamePreferences;

import java.awt.SystemColor;
import javax.swing.SpinnerNumberModel;

public class OptionsPanel extends JDialog implements ComponentListener{
	private static final long serialVersionUID = 6355967352362695786L;
	private JPanel buttons;
	private JButton back;
	private JButton saveChanges;
	private JButton createMaze;
	private JPanel optionsPanel;
	
	private JSpinner mazeSize;
	private JComboBox<String> dragonType;
	private JSlider numberOfDragons;
	private JTextField moveUp;
	private JTextField moveDown;
	private JTextField moveLeft;
	private JTextField moveRight;
	private JTextField shootUp;
	private JTextField shootDown;
	private JTextField shootLeft;
	private JTextField shootRight;
	private JTextField exitKey;
	
	/**
	 * Create the frame.
	 */
	public OptionsPanel() {
		setResizable(false);
		setTitle("Defeat the dragons! - Options Panel");
		addComponentListener(this);
		setFocusable(true);
		buttons = new JPanel();
		optionsPanel = new JPanel();
		
		addAllPanelsAndButtons();
	
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				try {
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				requestFocus();
			}
		});
		saveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int dragonNumber = numberOfDragons.getValue();
				int sizeOfMaze = (int) mazeSize.getValue();
				int typeOfDragon = dragonTypeInterpretation((String) dragonType.getSelectedItem());
				String up = moveUp.getText();
				String down = moveDown.getText();
				String left = moveLeft.getText();
				String right = moveRight.getText();
				String upShoot = shootUp.getText();
				String downShoot = shootDown.getText();
				String leftShoot = shootLeft.getText();
				String rightShoot = shootRight.getText();
				String keyExit = exitKey.getText();
				//Verificar se o tamanho do labirinto é Ímpar
				if ((sizeOfMaze % 2) == 0 && sizeOfMaze != 0)
					JOptionPane.showMessageDialog(null, "The size of the Maze must be as Odd number, wich it is not");
				else {
					if (up.length() == 0) {
						MazeDisplay.setJogo(new Jogo());
						MazeDisplay.getJogoG().getPrefs();
						if (sizeOfMaze == 0) {
							sizeOfMaze = 9;
							JOptionPane.showMessageDialog(null, "You've written 0 on the size of the maze.\nIt will be set to 9.");
						}
						GamePreferences.setMazeSize(sizeOfMaze);
						GamePreferences.setNumberOfDragons(dragonNumber);
						GamePreferences.setType(typeOfDragon);
						
						//Go back
						setVisible(false);
						try {
							MainMenu frame = new MainMenu();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
						requestFocus();
					}
					//Verifica tamanho das teclas
					else if (up.length() != 1 || 
							down.length() != 1 ||
							left.length() != 1 ||
							right.length() != 1 ||
							upShoot.length() != 1 ||
							downShoot.length() != 1 ||
							leftShoot.length() != 1 ||
							rightShoot.length() != 1 ||
							keyExit.length() != 1)
						JOptionPane.showMessageDialog(null, "One of the moves, shots or exit buttons was incorrectly written.");
					else {
						MazeDisplay.setJogo(new Jogo());
						MazeDisplay.getJogoG().getPrefs();
						if (sizeOfMaze == 0) {
							sizeOfMaze = 9;
							JOptionPane.showMessageDialog(null, "You've written 0 on the size of the maze.\nIt will be set to 9.");
						}
						GamePreferences.setMazeSize(sizeOfMaze);
						GamePreferences.setNumberOfDragons(dragonNumber);
						GamePreferences.setType(typeOfDragon);
						GamePreferences.setUp(up.toCharArray()[0]);
						GamePreferences.setDown(down.toCharArray()[0]);
						GamePreferences.setLeft(left.toCharArray()[0]);
						GamePreferences.setRight(right.toCharArray()[0]);
						GamePreferences.setsUp(upShoot.toCharArray()[0]);
						GamePreferences.setsDown(downShoot.toCharArray()[0]);
						GamePreferences.setsLeft(leftShoot.toCharArray()[0]);
						GamePreferences.setsRight(rightShoot.toCharArray()[0]);
						GamePreferences.setExitKey(keyExit.toCharArray()[0]);
						
						//Go back
						setVisible(false);
						try {
							MainMenu frame = new MainMenu();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
						requestFocus();
					}
				}
			}

			private int dragonTypeInterpretation(String a) {
				if (a == "Sleeps and Moves")
					return 0;
				else if (a == "Moves but does not sleep")
					return 2;
				else
					return 3;
			}
		});
		createMaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dragonNumber = numberOfDragons.getValue();
				int sizeOfMaze = (int) mazeSize.getValue();
				int typeOfDragon = dragonTypeInterpretation((String) dragonType.getSelectedItem());
				String up = moveUp.getText();
				String down = moveDown.getText();
				String left = moveLeft.getText();
				String right = moveRight.getText();
				String upShoot = shootUp.getText();
				String downShoot = shootDown.getText();
				String leftShoot = shootLeft.getText();
				String rightShoot = shootRight.getText();
				String keyExit = exitKey.getText();
				//Verificar se o tamanho do labirinto é Ímpar
				if ((sizeOfMaze % 2) == 0 && sizeOfMaze != 0)
					JOptionPane.showMessageDialog(null, "The size of the Maze must be as Odd number, wich it is not");
				else {
					//Verifica tamanho das teclas
					if (up.length() == 0) {
						MazeDisplay.setJogo(new Jogo());
						MazeDisplay.getJogoG().getPrefs();
						if (sizeOfMaze == 0) {
							sizeOfMaze = 9;
							JOptionPane.showMessageDialog(null, "You've written 0 on the size of the maze.\nIt will be set to 9.");
						}
						GamePreferences.setMazeSize(sizeOfMaze);
						GamePreferences.setNumberOfDragons(dragonNumber);
						GamePreferences.setType(typeOfDragon);
						
						//Go back
						setVisible(false);
						try {
							MazeCreator frame = new MazeCreator();
							frame.setVisible(true);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						requestFocus();
					}
					//Verifica tamanho das teclas
					else if (up.length() != 1 || 
							down.length() != 1 ||
							left.length() != 1 ||
							right.length() != 1 ||
							upShoot.length() != 1 ||
							downShoot.length() != 1 ||
							leftShoot.length() != 1 ||
							rightShoot.length() != 1 ||
							keyExit.length() != 1)
						JOptionPane.showMessageDialog(null, "One of the moves, shots or exit buttons was incorrectly written.");
					else {
						MazeDisplay.setJogo(new Jogo());
						MazeDisplay.getJogoG().getPrefs();
						if (sizeOfMaze == 0) {
							sizeOfMaze = 9;
							JOptionPane.showMessageDialog(null, "You've written 0 on the size of the maze.\nIt will be set to 9.");
						}
						GamePreferences.setMazeSize(sizeOfMaze);
						GamePreferences.setNumberOfDragons(dragonNumber);
						GamePreferences.setType(typeOfDragon);
						GamePreferences.setUp(up.toCharArray()[0]);
						GamePreferences.setDown(down.toCharArray()[0]);
						GamePreferences.setLeft(left.toCharArray()[0]);
						GamePreferences.setRight(right.toCharArray()[0]);
						GamePreferences.setsUp(upShoot.toCharArray()[0]);
						GamePreferences.setsDown(downShoot.toCharArray()[0]);
						GamePreferences.setsLeft(leftShoot.toCharArray()[0]);
						GamePreferences.setsRight(rightShoot.toCharArray()[0]);
						GamePreferences.setExitKey(keyExit.toCharArray()[0]);
						
						//Go back
						setVisible(false);
						try {
							MazeCreator frame = new MazeCreator();
							frame.setVisible(true);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						requestFocus();
					}
				}
				
			}

			private int dragonTypeInterpretation(String a) {
				if (a == "Sleeps and Moves")
					return 0;
				else if (a == "Moves but does not sleep")
					return 2;
				else
					return 3;
			}
		});
		
		this.getContentPane().add(buttons, BorderLayout.SOUTH);
		this.getContentPane().add(optionsPanel, BorderLayout.CENTER);
		setBounds(100, 100, 500, 375);
	}
	
	private void addAllPanelsAndButtons() {
		
		back = new JButton("back");
		buttons.add(back);

		saveChanges = new JButton("Save Changes");
		buttons.add(saveChanges);
		
		createMaze = new JButton("Create your own Maze");
		buttons.add(createMaze);
		
		optionsPanel.setLayout(null);
		
		JLabel label = new JLabel("Size of the Random Maze:");
		label.setBounds(75, 25, 150, 14);
		optionsPanel.add(label);
		mazeSize = new JSpinner();
		mazeSize.setModel(new SpinnerNumberModel(15, 9, 51, 2));
		mazeSize.setToolTipText("");
		mazeSize.setBounds(250, 22, 60, 20);
		mazeSize.setBackground(SystemColor.inactiveCaptionBorder);
		optionsPanel.add(mazeSize);
		
		JLabel label_1 = new JLabel("Number of Dragons:");
		label_1.setBounds(75, 55, 150, 14);
		optionsPanel.add(label_1);
		
		JLabel label_2 = new JLabel("Type of Dragons:");
		label_2.setBounds(75, 90, 150, 14);
		optionsPanel.add(label_2);
		dragonType = new JComboBox<String>();
		dragonType.setBounds(250, 86, 200, 22);
		dragonType.addItem("Sleeps and Moves");
		dragonType.addItem("Moves but does not sleep");
		dragonType.addItem("Does not Move and does not Sleep");
		optionsPanel.add(dragonType);
		
		JLabel label_3 = new JLabel("Move up:");
		label_3.setBounds(21, 164, 150, 14);
		optionsPanel.add(label_3);
		moveUp = new JTextField();
		moveUp.setBounds(180, 161, 35, 20);
		optionsPanel.add(moveUp);
		
		JLabel label_4 = new JLabel("Move down:");
		label_4.setBounds(21, 189, 150, 14);
		optionsPanel.add(label_4);
		moveDown = new JTextField();
		moveDown.setBounds(180, 186, 35, 20);
		optionsPanel.add(moveDown);
		
		JLabel label_5 = new JLabel("Move left:");
		label_5.setBounds(21, 214, 150, 14);
		optionsPanel.add(label_5);
		moveLeft = new JTextField();
		moveLeft.setBounds(180, 211, 35, 20);
		optionsPanel.add(moveLeft);
		
		JLabel label_6 = new JLabel("Move right:");
		label_6.setBounds(21, 239, 150, 14);
		optionsPanel.add(label_6);
		moveRight = new JTextField();
		moveRight.setBounds(180, 236, 35, 20);
		optionsPanel.add(moveRight);
		
		JLabel label_7 = new JLabel("Shoot dard up:");
		label_7.setBounds(250, 164, 150, 14);
		optionsPanel.add(label_7);
		shootUp = new JTextField();
		shootUp.setBounds(415, 161, 35, 20);
		optionsPanel.add(shootUp);
		
		JLabel label_8 = new JLabel("Shoot dard down:");
		label_8.setBounds(250, 189, 150, 14);
		optionsPanel.add(label_8);
		shootDown = new JTextField();
		shootDown.setBounds(415, 186, 35, 20);
		optionsPanel.add(shootDown);
		
		JLabel label_9 = new JLabel("Shoot dard left:");
		label_9.setBounds(250, 214, 150, 14);
		optionsPanel.add(label_9);
		shootLeft = new JTextField();
		shootLeft.setBounds(415, 211, 35, 20);
		optionsPanel.add(shootLeft);
		
		JLabel label_10 = new JLabel("Shoot dard right:");
		label_10.setBounds(250, 239, 150, 14);
		optionsPanel.add(label_10);
		numberOfDragons = new JSlider();
		numberOfDragons.setBounds(250, 50, 200, 31);
		numberOfDragons.setPaintTicks(true);
		numberOfDragons.setMajorTickSpacing(5);
		numberOfDragons.setMaximum(51);
		numberOfDragons.setMinimum(0);
		numberOfDragons.setValue(5);
		optionsPanel.add(numberOfDragons);
		shootRight = new JTextField();
		shootRight.setBounds(415, 236, 35, 20);
		optionsPanel.add(shootRight);
		
		JLabel label_11 = new JLabel("Exit:");
		label_11.setBounds(21, 264, 150, 14);
		optionsPanel.add(label_11);
		exitKey = new JTextField();
		exitKey.setBounds(180, 261, 35, 20);
		optionsPanel.add(exitKey);
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
