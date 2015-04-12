package maze.gui;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import maze.logic.Jogo.GamePreferences;

// TODO: Auto-generated Javadoc
//TODO: Auto-generated Javadoc
/**
* The Class CreatorSideBar.
*/
public class CreatorSideBar extends JPanel{
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -615709073421396065L;
	
	/** The dragon. */
	private ImageIcon dragon;
	
	/** The hero. */
	private ImageIcon hero;
	
	/** The closed exit. */
	private ImageIcon closedExit;
	
	/** The dart. */
	private ImageIcon dard;
	
	/** The shield. */
	private ImageIcon shield;
	
	/** The sword. */
	private ImageIcon sword;
	
	/** The check. */
	private ImageIcon check;

	/**
	 * Instantiates a new creator side bar.
	 */
	CreatorSideBar() {
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
		hero =  scaleImage(new ImageIcon(this.getClass().getResource("res/hero.png")));
		closedExit =  scaleImage(new ImageIcon(this.getClass().getResource("res/closedExit.png")));
		dard =  scaleImage(new ImageIcon(this.getClass().getResource("res/dart.png")));
		shield =  scaleImage(new ImageIcon(this.getClass().getResource("res/shield.png")));
		sword =  scaleImage(new ImageIcon(this.getClass().getResource("res/sword.png")));
		check =  scaleImage(new ImageIcon(this.getClass().getResource("res/check.png")));

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
		this.add(new JLabel(closedExit));
		if (!MazeCreatorGrid.getPhase().isExitPlaced())
			this.add(new JLabel("   1"));
		else
			this.add(new JLabel(check));

		this.add(new JLabel(hero));
		if (!MazeCreatorGrid.getPhase().isHeroPlaced())
			this.add(new JLabel("   1"));
		else
			this.add(new JLabel(check));
		
		this.add(new JLabel(shield));
		if (!MazeCreatorGrid.getPhase().isShieldPlaced())
			this.add(new JLabel("   1"));
		else
			this.add(new JLabel(check));

		this.add(new JLabel(sword));
		if (!MazeCreatorGrid.getPhase().isSwordPlaced())
			this.add(new JLabel("   1"));
		else
			this.add(new JLabel(check));

		this.add(new JLabel(dragon));
		int missingDragons = GamePreferences.getNumberOfDragons() - MazeCreatorGrid.getPhase().getNumberOfDragonsPlaced();
				MazeDisplay.getJogoG().getPrefs();
		if (missingDragons > 0)
			this.add(new JLabel("   " + missingDragons));
		else
			this.add(new JLabel(check));

		this.add(new JLabel(dard));	
		int missingDarts = GamePreferences.getNumberOfDragons() - MazeCreatorGrid.getPhase().getNumberOfDartsPlaced();
		MazeDisplay.getJogoG().getPrefs();
		if (missingDarts > 0)
			this.add(new JLabel("   " + missingDarts));
		else
			this.add(new JLabel(check));

		
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