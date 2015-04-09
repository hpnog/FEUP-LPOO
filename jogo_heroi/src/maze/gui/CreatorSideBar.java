package maze.gui;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import maze.logic.Jogo.GamePreferences;

public class CreatorSideBar extends JPanel{
	private static final long serialVersionUID = -615709073421396065L;
	private ImageIcon dragon;
	private ImageIcon hero;
	private ImageIcon closedExit;
	private ImageIcon dard;
	private ImageIcon shield;
	private ImageIcon sword;
	private ImageIcon check;

	CreatorSideBar() {
		super();
		loadImages();
		this.setLayout(new GridLayout(6,2));
		addDataToSideBar();
	}

	private void loadImages() {
		dragon =  scaleImage(new ImageIcon(this.getClass().getResource("res/dragon.png")));
		hero =  scaleImage(new ImageIcon(this.getClass().getResource("res/hero.png")));
		closedExit =  scaleImage(new ImageIcon(this.getClass().getResource("res/closedExit.png")));
		dard =  scaleImage(new ImageIcon(this.getClass().getResource("res/dard.png")));
		shield =  scaleImage(new ImageIcon(this.getClass().getResource("res/shield.png")));
		sword =  scaleImage(new ImageIcon(this.getClass().getResource("res/sword.png")));
		check =  scaleImage(new ImageIcon(this.getClass().getResource("res/check.png")));

	}

	private ImageIcon scaleImage(ImageIcon im) {
		Image img = im.getImage();
		Image newimg = img.getScaledInstance(50, 50,java.awt.Image.SCALE_FAST);
		return new ImageIcon(newimg);
	}

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
		int missingDarts = GamePreferences.getMazeSize()/4 - MazeCreatorGrid.getPhase().getNumberOfDardsPlaced();
		MazeDisplay.getJogoG().getPrefs();
		if (missingDarts > 0)
			this.add(new JLabel("   " + missingDarts));
		else
			this.add(new JLabel(check));

		
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