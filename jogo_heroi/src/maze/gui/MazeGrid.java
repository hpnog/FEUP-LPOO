package maze.gui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import maze.logic.Jogo;
import maze.logic.Lab;

public class MazeGrid extends JPanel {
	private ImageIcon dragon;
	private ImageIcon sleepingDragon;
	private ImageIcon hero;
	private ImageIcon armedHero;
	private ImageIcon shieldedHero;
	private ImageIcon armedAndShieldedHero;
	private ImageIcon wall;
	private ImageIcon exit;
	private ImageIcon dard;
	private ImageIcon shield;
	private ImageIcon fire;
	private ImageIcon sword;
	
	MazeGrid() {
		super();
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		//Varible for Panes size
		int paneSize = 1;
		if (Lab.size > 0) paneSize = Lab.size;
		//Divide a frame numa grelha com o tamanho correto (layout)
		this.setLayout(new GridLayout(paneSize, paneSize));
		loadImages();
	}
	
	private ImageIcon scaleImage(ImageIcon im) {
		Image img = im.getImage();
		Image newimg = img.getScaledInstance(this.getWidth()/Lab.size, this.getHeight()/Lab.size,  java.awt.Image.SCALE_FAST);
		return new ImageIcon(newimg);
	}
	
	private void loadImages() {
		dragon = new ImageIcon(this.getClass().getResource("res/dragon.png"));
		sleepingDragon = new ImageIcon(this.getClass().getResource("res/sleepingDragon.png"));
		hero = new ImageIcon(this.getClass().getResource("res/hero.png"));
		armedHero = new ImageIcon(this.getClass().getResource("res/armedHero.png"));
		shieldedHero = new ImageIcon(this.getClass().getResource("res/shieldedHero.png"));
		armedAndShieldedHero = new ImageIcon(this.getClass().getResource("res/armedAndShieldedHero.png"));
		wall = new ImageIcon(this.getClass().getResource("res/wall.png"));
		exit = new ImageIcon(this.getClass().getResource("res/wall.png")); //temporario
		dard = new ImageIcon(this.getClass().getResource("res/dard.png"));
		shield = new ImageIcon(this.getClass().getResource("res/shield.png"));
		fire = new ImageIcon(this.getClass().getResource("res/fire.png"));
		sword = new ImageIcon(this.getClass().getResource("res/sword.png"));
	}
	
	private void introduzConteudo() {
		ImageIcon scaledDragon = scaleImage(dragon);
		ImageIcon scaledSleepingDragon = scaleImage(sleepingDragon);
		ImageIcon scaledHero = scaleImage(hero);
		ImageIcon scaledArmedHero = scaleImage(armedHero);
		ImageIcon scaledShieldedHero = scaleImage(shieldedHero);
		ImageIcon scaledArmedAndShieldedHero = scaleImage(armedAndShieldedHero);
		ImageIcon scaledWall = scaleImage(wall);
		ImageIcon scaledExit = scaleImage(exit);
		ImageIcon scaledDard = scaleImage(dard);
		ImageIcon scaledShield = scaleImage(shield);
		ImageIcon scaledFire = scaleImage(fire);
		ImageIcon scaledSword = scaleImage(sword);
		for (int i = 0; i < Lab.size; i++) {
			for (int j = 0; j < Lab.size; j++) {
				if (Lab.lab[j][i] == 'X')
					this.add(new JLabel(scaledWall));
				else if (Lab.lab[j][i] == ' ')
					this.add(new JLabel(" "));
				else if (Lab.lab[j][i] == 'A' && !Jogo.heroi.get_shielded())
					this.add(new JLabel(scaledArmedHero));
				else if (Lab.lab[j][i] == 'A' && Jogo.heroi.get_shielded()) 
					this.add(new JLabel(scaledArmedAndShieldedHero));
				else if (Lab.lab[j][i] == 'H' && !Jogo.heroi.get_shielded())
					this.add(new JLabel(scaledHero));
				else if (Lab.lab[j][i] == 'H' && Jogo.heroi.get_shielded())
					this.add(new JLabel(scaledShieldedHero));
				else if (Lab.lab[j][i] == 'D') 
					this.add(new JLabel(scaledDragon));
				else if (Lab.lab[j][i] == 'd') 
					this.add(new JLabel(scaledSleepingDragon));
				else if (Lab.lab[j][i] == '\\')
					this.add(new JLabel(scaledDard));
				else if (Lab.lab[j][i] == 'E')
					this.add(new JLabel(scaledSword));
				else if (Lab.lab[j][i] == 'S') 
					this.add(new JLabel(" "));
				//--------------------------------FALTA-O-FOGO----------------------------------
			}
		}
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
}


