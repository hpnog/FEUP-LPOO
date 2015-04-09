package maze.gui;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import maze.logic.Dart;
import maze.logic.Dragao;
import maze.logic.Escudo;
import maze.logic.Espada;
import maze.logic.Heroi;
import maze.logic.Jogo;
import maze.logic.Lab;
import maze.logic.Jogo.GamePreferences;

public class MazeCreatorGrid extends JPanel {
	private static final long serialVersionUID = 4097316773731482125L;
	private ImageIcon dragon;
	private ImageIcon sleepingDragon;
	private ImageIcon hero;
	private ImageIcon armedHero;
	private ImageIcon shieldedHero;
	private ImageIcon armedAndShieldedHero;
	private ImageIcon wall;
	private ImageIcon closedExit;
	private ImageIcon dard;
	private ImageIcon shield;
	private ImageIcon sword;
	private static CreatorPhase phase;

	protected class CreatorPhase {
		private boolean exitPlaced;
		private boolean mazeDone;
		private boolean heroPlaced;
		private boolean shieldPlaced;
		private boolean swordPlaced;
		private int numberOfDardsPlaced;
		private int numberOfDragonsPlaced;
		
		CreatorPhase() {
			exitPlaced = false;
			mazeDone = false;
			heroPlaced = false;
			swordPlaced = false;
			shieldPlaced = false;
			numberOfDardsPlaced = 0;
			numberOfDragonsPlaced = 0;
		}

		public int getNumberOfDardsPlaced() {
			return numberOfDardsPlaced;
		}
		public boolean isShieldPlaced() {
			return shieldPlaced;
		}
		public boolean isSwordPlaced() {
			return swordPlaced;
		}
		public boolean isExitPlaced() { 
			return exitPlaced; 
		}
		public void setExitPlaced(boolean a) {
			exitPlaced = a;
		}
		public boolean isMazeDone() {
			return mazeDone;
		}
		public void setMazeDone(boolean a) {
			mazeDone = a;
		}

		public void setNumberOfDardsPlaced(int a) {
			numberOfDardsPlaced = a;
		}
		public void setShieldPlaced(boolean a) {
			shieldPlaced = a;
		}
		public void setSwordPlaced(boolean a) {
			swordPlaced = a;
		}
		public boolean isHeroPlaced() {
			return heroPlaced;
		}
		public void setHeroPlaced (boolean a) {
			heroPlaced = a;
		}
		public void setNumberOfDragonsPlaced(int a) {
			numberOfDragonsPlaced = a;
		}
		public int getNumberOfDragonsPlaced() {
			return numberOfDragonsPlaced;
		}

		public boolean isFinished() {
			if (exitPlaced)
				if(mazeDone)
					if(heroPlaced)
						if(shieldPlaced)
							if(swordPlaced) {
								MazeDisplay.getJogoG().getPrefs();
								if(numberOfDragonsPlaced == GamePreferences.getNumberOfDragons())
									if (numberOfDardsPlaced == GamePreferences.getMazeSize()/4)
										return true;
							}
			return false;
		}
	}

	MazeCreatorGrid(int width, int height) {
		super();
		setPhase(new CreatorPhase());
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setSize(width, height);
		
		//Varible for Panes size
		int paneSize = 1;
		MazeDisplay.setJogoG(new Jogo());
		MazeDisplay.getJogoG().getPrefs();
		MazeDisplay.getJogoG().setLabirinto(new Lab(GamePreferences.getMazeSize()));
		MazeDisplay.getJogoG().setHeroi(new Heroi());
		MazeDisplay.getJogoG().setDragoes(new Dragao[GamePreferences.getNumberOfDragons()]);
		MazeDisplay.getJogoG().setDardos(new Dart[GamePreferences.getMazeSize()/4]);
		MazeDisplay.getJogoG().setEscudo(new Escudo());
		MazeDisplay.getJogoG().setEspada(new Espada());
		MazeDisplay.getJogoG().setInter(3);
		if (getLab().getSize() > 0) paneSize = getLab().getSize();
		//Divide a frame numa grelha com o tamanho correto (layout)
		this.setLayout(new GridLayout(paneSize, paneSize));
		loadImages();
		fillGridWithWalls();
		game();
	}

	private void fillGridWithWalls() {
		for (int i = 0; i < getLab().getSize(); i++)
			for (int j = 0; j < getLab().getSize(); j++)
				getLab().setLabCell(i, j, 'X');
	}

	public void game() {
		//Remove all content
		removeAll();
		//Repaints the content
		repaint();
		//Reintroduces the correct content
		introduzConteudo();
		//Valida novamente o conteudo
		revalidate();
	}

	private ImageIcon scaleImage(ImageIcon im) {
		Image img = im.getImage();
		Image newimg = img.getScaledInstance(this.getWidth()/getLab().getSize(),
				this.getHeight()/getLab().getSize(),
				java.awt.Image.SCALE_FAST);
		return new ImageIcon(newimg);
	}

	private void introduzConteudo() {
		ImageIcon scaledDragon = scaleImage(dragon);
		ImageIcon scaledSleepingDragon = scaleImage(sleepingDragon);
		ImageIcon scaledHero = scaleImage(hero);
		ImageIcon scaledArmedHero = scaleImage(armedHero);
		ImageIcon scaledShieldedHero = scaleImage(shieldedHero);
		ImageIcon scaledArmedAndShieldedHero = scaleImage(armedAndShieldedHero);
		ImageIcon scaledWall = scaleImage(wall);
		ImageIcon scaledClosedExit = scaleImage(closedExit);
		ImageIcon scaledDard = scaleImage(dard);
		ImageIcon scaledShield = scaleImage(shield);
		//ImageIcon scaledFire = scaleImage(fire);---------------------------------AINDA PARA USAR
		ImageIcon scaledSword = scaleImage(sword);
		for (int i = 0; i < getLab().getSize(); i++) {
			for (int j = 0; j < getLab().getSize(); j++) {
				if (getLab().getLab()[j][i] == 'X')
					this.add(new JLabel(scaledWall));
				else if (getLab().getLab()[j][i] == ' ')
					this.add(new JLabel(" "));
				else if (getLab().getLab()[j][i] == 'A' && !MazeDisplay.getJogoG().getHeroi().isShielded())
					this.add(new JLabel(scaledArmedHero));
				else if (getLab().getLab()[j][i] == 'A' && MazeDisplay.getJogoG().getHeroi().isShielded()) 
					this.add(new JLabel(scaledArmedAndShieldedHero));
				else if (getLab().getLab()[j][i] == 'H' && !MazeDisplay.getJogoG().getHeroi().isShielded())
					this.add(new JLabel(scaledHero));
				else if (getLab().getLab()[j][i] == 'H' && MazeDisplay.getJogoG().getHeroi().isShielded())
					this.add(new JLabel(scaledShieldedHero));
				else if (getLab().getLab()[j][i] == 'D')
					this.add(new JLabel(scaledDragon));
				else if (getLab().getLab()[j][i] == 'd') 
					this.add(new JLabel(scaledSleepingDragon));
				else if (getLab().getLab()[j][i] == '\\')
					this.add(new JLabel(scaledDard));
				else if (getLab().getLab()[j][i] == 'E')
					this.add(new JLabel(scaledSword));
				else if (getLab().getLab()[j][i] == 'S') {
					this.add(new JLabel(scaledClosedExit));
				}
				else if (getLab().getLab()[j][i] == 'O')
					this.add(new JLabel(scaledShield));
				//--------------------------------FALTA-O-FOGO----------------------------------
			}
		}
	}

	void loadImages() {
		dragon = new ImageIcon(this.getClass().getResource("res/dragon.png"));
		sleepingDragon = new ImageIcon(this.getClass().getResource("res/sleepingDragon.png"));
		hero = new ImageIcon(this.getClass().getResource("res/hero.png"));
		armedHero = new ImageIcon(this.getClass().getResource("res/armedHero.png"));
		shieldedHero = new ImageIcon(this.getClass().getResource("res/shieldedHero.png"));
		armedAndShieldedHero = new ImageIcon(this.getClass().getResource("res/armedAndShieldedHero.png"));
		wall = new ImageIcon(this.getClass().getResource("res/wall.png"));
		dard = new ImageIcon(this.getClass().getResource("res/dard.png"));
		shield = new ImageIcon(this.getClass().getResource("res/shield.png"));
		sword = new ImageIcon(this.getClass().getResource("res/sword.png"));
		closedExit = new ImageIcon(this.getClass().getResource("res/closedExit.png")); 
	}

	void placeDards(MouseEvent arg0, int ind) {
		int xCoord = getMouseXCoord(arg0);
		int yCoord = getMouseYCoord(arg0);
		
		if (getLab().getChar(xCoord, yCoord) != ' ')
			JOptionPane.showMessageDialog(null, "You can only place any dard in an empty space");
		else {
			MazeDisplay.getJogoG().getPrefs();
			MazeDisplay.getJogoG().setDart(ind, new Dart(xCoord, yCoord));
			MazeDisplay.getJogoG().change_dardo_pos(ind);
			phase.setNumberOfDardsPlaced(phase.getNumberOfDardsPlaced()+1);
		}
	}

	void placeSword(MouseEvent arg0) {
		int xCoord = getMouseXCoord(arg0);
		int yCoord = getMouseYCoord(arg0);
		if (getLab().getChar(xCoord, yCoord) != ' ')
			JOptionPane.showMessageDialog(null, "You can only place the Sword in an empty space");
		else {
			MazeDisplay.getJogoG().getEspada().setX_coord(xCoord);
			MazeDisplay.getJogoG().getEspada().setY_coord(yCoord);
			MazeDisplay.getJogoG().change_sword_pos();
			getPhase().setSwordPlaced(true);
		}
	}

	void placeShield(MouseEvent arg0) {
		int xCoord = getMouseXCoord(arg0);
		int yCoord = getMouseYCoord(arg0);
		if (getLab().getChar(xCoord, yCoord) != ' ')
			JOptionPane.showMessageDialog(null, "You can only place the Shield in an empty space");
		else {
			MazeDisplay.getJogoG().getEscudo().setX_coord(xCoord);
			MazeDisplay.getJogoG().getEscudo().setY_coord(yCoord);
			MazeDisplay.getJogoG().change_escudo_pos();
			getPhase().setShieldPlaced(true);
		}
	}

	void placeDragon(MouseEvent arg0, int ind) {
		int xCoord = getMouseXCoord(arg0);
		int yCoord = getMouseYCoord(arg0);
		
		if (getLab().getChar(xCoord, yCoord) != ' ')
			JOptionPane.showMessageDialog(null, "You can only place any dragon in an empty space");
		else {
			MazeDisplay.getJogoG().getPrefs();
			MazeDisplay.getJogoG().setDragao(ind, new Dragao(GamePreferences.getType()));
			MazeDisplay.getJogoG().getDragoes()[ind].setX_coord(xCoord);
			MazeDisplay.getJogoG().getDragoes()[ind].setY_coord(yCoord);
			MazeDisplay.getJogoG().change_dragon_pos(ind);
			phase.setNumberOfDragonsPlaced(phase.getNumberOfDragonsPlaced()+1);
		}
	}
	
	void placeHero(MouseEvent arg0) {
		int xCoord = getMouseXCoord(arg0);
		int yCoord = getMouseYCoord(arg0);
		if (getLab().getChar(xCoord, yCoord) != ' ')
			JOptionPane.showMessageDialog(null, "You can only place the hero in an empty space");
		else {
			MazeDisplay.getJogoG().getHeroi().setX_coord(xCoord);
			MazeDisplay.getJogoG().getHeroi().setY_coord(yCoord);
			MazeDisplay.getJogoG().change_hero_pos();
			getPhase().setHeroPlaced(true);
		}
	}

	void makeMaze(MouseEvent arg0) {
		int xCoord = getMouseXCoord(arg0);
		int yCoord = getMouseYCoord(arg0);
		if (xCoord == 0 || xCoord == (getLab().getSize()-1) || yCoord == 0 || yCoord == (getLab().getSize()-1))
			JOptionPane.showMessageDialog(null, "You can not change the maze in these coordinates\n"
					+ "Coord X: " + xCoord + "\nCoord Y: " + yCoord);
		else {
			if (getLab().getChar(xCoord, yCoord) == ' ')
				getLab().setLabCell(xCoord, yCoord, 'X');
			else
				getLab().setLabCell(xCoord, yCoord, ' ');
		}
	}

	void putExit(MouseEvent arg0) {
		int xCoord = getMouseXCoord(arg0);
		int yCoord = getMouseYCoord(arg0);
		System.out.printf("\nXCoord:%d\tyCoord:%d", xCoord, yCoord);
		if (xCoord == 0 || xCoord == getLab().getSize()-1) {
			if (yCoord != 0 && yCoord != getLab().getSize()-1) {
				getLab().setLabCell(xCoord, yCoord, 'S');
				getPhase().setExitPlaced(true);
			}
			else {
				JOptionPane.showMessageDialog(null, "You can not place the exit there");
			}
		}
		else if (yCoord == 0 || yCoord == getLab().getSize()-1) {
			if (xCoord != 0 && xCoord != getLab().getSize()-1) {		
				getLab().setLabCell(xCoord, yCoord, 'S');
				getPhase().setExitPlaced(true);
			}
			else {
				JOptionPane.showMessageDialog(null, "You can not place the exit there");
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "You can not place the exit there");
		}
	}

	private int getMouseYCoord(MouseEvent arg) {
		int ycoord = arg.getY()-30;
		int fin = 0;
		for (int i = 0; i < getLab().getSize(); i++) {
			if (ycoord < (fin*((double) this.getHeight()/getLab().getSize())+
					(this.getHeight()/(double) getLab().getSize())))
				return fin;
			else
				fin++;
		}
		return 0;
	}

	private int getMouseXCoord(MouseEvent arg) {
		int xcoord = arg.getX();
		int fin = 0;
		for (int i = 0; i < getLab().getSize(); i++) {
			if (xcoord < (fin*((double) this.getWidth()/getLab().getSize())+
					((double) this.getWidth()/getLab().getSize())))
				return fin;
			else
				fin++;
		}
		return 0;
	}

	public Lab getLab() {
		return MazeDisplay.getJogoG().getLabirinto();
	}
	
	public static CreatorPhase getPhase() {
		return phase;
	}

	public static void setPhase(CreatorPhase phase) {
		MazeCreatorGrid.phase = phase;
	}



	public ImageIcon getDragon() {
		return dragon;
	}

	public ImageIcon getSleepingDragon() {
		return sleepingDragon;
	}

	public ImageIcon getHero() {
		return hero;
	}

	public ImageIcon getArmedHero() {
		return armedHero;
	}

	public ImageIcon getShieldedHero() {
		return shieldedHero;
	}

	public ImageIcon getArmedAndShieldedHero() {
		return armedAndShieldedHero;
	}

	public ImageIcon getWall() {
		return wall;
	}

	public ImageIcon getClosedExit() {
		return closedExit;
	}

	public ImageIcon getDard() {
		return dard;
	}

	public ImageIcon getShield() {
		return shield;
	}

	public ImageIcon getSword() {
		return sword;
	}

	
}
