package maze.gui;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameSideBar extends JPanel{
	private static final long serialVersionUID = -615709073421396065L;
	private ImageIcon dragon;
	private ImageIcon dard;
	private ImageIcon shield;
	private ImageIcon sword;
	private ImageIcon check;
	private ImageIcon error;

	GameSideBar() {
		super();
		loadImages();
		this.setLayout(new GridLayout(6,2));
		addDataToSideBar();
	}

	private void loadImages() {
		dragon =  scaleImage(new ImageIcon(this.getClass().getResource("res/dragon.png")));
		dard =  scaleImage(new ImageIcon(this.getClass().getResource("res/dard.png")));
		shield =  scaleImage(new ImageIcon(this.getClass().getResource("res/shield.png")));
		sword =  scaleImage(new ImageIcon(this.getClass().getResource("res/sword.png")));
		check =  scaleImage(new ImageIcon(this.getClass().getResource("res/check.png")));
		error =  scaleImage(new ImageIcon(this.getClass().getResource("res/error.png")));

	}

	private ImageIcon scaleImage(ImageIcon im) {
		Image img = im.getImage();
		Image newimg = img.getScaledInstance(50, 50,java.awt.Image.SCALE_FAST);
		return new ImageIcon(newimg);
	}

	private void addDataToSideBar() {
		
		this.add(new JLabel(shield));
		if (!MazeDisplay.getJogoG().getHeroi().isShielded())
			this.add(new JLabel(error));
		else
			this.add(new JLabel(check));

		this.add(new JLabel(sword));
		if (!MazeDisplay.getJogoG().getHeroi().isArmado())
			this.add(new JLabel(error));
		else
			this.add(new JLabel(check));

		this.add(new JLabel(dragon));
		if (MazeDisplay.getJogoG().checkIfDragonsAreAlive())
			this.add(new JLabel(error));
		else
			this.add(new JLabel(check));

		this.add(new JLabel(dard));	
			this.add(new JLabel("   " + MazeDisplay.getJogoG().getHeroi().getDardos()));
	}

	public void update() {
		removeAll();
		//Repaints the content
		repaint();
		//Reintroduces the correct content
		addDataToSideBar();
		//Valida novamente o conteudo
		revalidate();
	}
}
