package maze.gui;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
//TODO: Auto-generated Javadoc
/**
 * The Class GameSideBar.
 */
public class GameSideBar extends JPanel{
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -615709073421396065L;
	
	/** The dragon. */
	private ImageIcon dragon;
	
	/** The dart. */
	private ImageIcon dard;
	
	/** The shield. */
	private ImageIcon shield;
	
	/** The sword. */
	private ImageIcon sword;
	
	/** The check. */
	private ImageIcon check;
	
	/** The error. */
	private ImageIcon error;

	/**
	 * Instantiates a new game side bar.
	 */
	GameSideBar() {
		super();
		loadImages();
		this.setLayout(new GridLayout(6,2));
		addDataToSideBar();
	}

	/**
	 * Load images.
	 */
	private void loadImages() {
		dragon =  scaleImage(new ImageIcon(this.getClass().getResource("res/dragon.png")));
		dard =  scaleImage(new ImageIcon(this.getClass().getResource("res/dart.png")));
		shield =  scaleImage(new ImageIcon(this.getClass().getResource("res/shield.png")));
		sword =  scaleImage(new ImageIcon(this.getClass().getResource("res/sword.png")));
		check =  scaleImage(new ImageIcon(this.getClass().getResource("res/check.png")));
		error =  scaleImage(new ImageIcon(this.getClass().getResource("res/error.png")));

	}

	/**
	 * Scale image.
	 *
	 * @param im the image icon
	 * @return the image icon
	 */
	private ImageIcon scaleImage(ImageIcon im) {
		Image img = im.getImage();
		Image newimg = img.getScaledInstance(50, 50,java.awt.Image.SCALE_FAST);
		return new ImageIcon(newimg);
	}

	/**
	 * Adds the data to side bar.
	 */
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

	/**
	 * Update.
	 */
	public void update() {
		removeAll();
		//Repaints the content
		repaint();
		//Reintroduces the correct content
		addDataToSideBar();
		//Revalidates the content
		revalidate();
	}
}
