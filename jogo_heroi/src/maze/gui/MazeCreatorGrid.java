package maze.gui;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import maze.logic.Jogo;
import maze.logic.Lab;
import maze.logic.Jogo.GamePreferences;

public class MazeCreatorGrid extends JPanel implements MouseListener{
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
		
		CreatorPhase() {
			exitPlaced = false;
		}
				
		public boolean isExitPlaced() { 
			return exitPlaced; 
		}
		public void setExitPlaced(boolean a) {
			exitPlaced = a;
		}
	}

	MazeCreatorGrid(int width, int height) {
		super();
		phase = new CreatorPhase();
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setSize(width, height);
		addMouseListener(this);
		
		//Varible for Panes size
		int paneSize = 1;
		MazeDisplay.setJogoG(new Jogo());
		MazeDisplay.getJogoG().getPrefs();
		MazeDisplay.getJogoG().setLabirinto(new Lab(GamePreferences.getMazeSize()));
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

	private void loadImages() {
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

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (!phase.isExitPlaced()) {
			int xCoord = getMouseXCoord(arg0);
			int yCoord = getMouseYCoord(arg0);
			System.out.printf("\nXCoord:%d\tyCoord:%d", xCoord, yCoord);
			if (xCoord == 0 || xCoord == getLab().getSize()-1) {
				if (yCoord != 0 && yCoord != getLab().getSize()-1) {
					getLab().setLabCell(xCoord, yCoord, 'S');
					phase.setExitPlaced(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "You can not place the exit there");
				}
			}
			else if (yCoord == 0 || yCoord == getLab().getSize()-1) {
				if (xCoord != 0 && xCoord != getLab().getSize()-1) {		
					getLab().setLabCell(xCoord, yCoord, 'S');
					phase.setExitPlaced(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "You can not place the exit there");
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "You can not place the exit there");
			}
		}
		game();
	}

	private int getMouseYCoord(MouseEvent arg) {
		int ycoord = arg.getY();
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
