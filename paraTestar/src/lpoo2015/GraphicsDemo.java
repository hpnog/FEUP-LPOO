package lpoo2015;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class GraphicsDemo extends JPanel 
implements MouseListener, MouseMotionListener {
	// Coordenadas do rectângulo envolvente da oval a desenhar
	int x1 = 0, y1 = 0, x2 = 0, y2 = 0;

	// Construtor, regista listeners de eventos do rato
	public GraphicsDemo() {
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
				
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // limpa fundo ...
		
		g.setColor(Color.BLUE);
		g.fillOval(x1, y1, x2 - x1 + 1, y2 - y1 + 1);
	}

	public void mousePressed(MouseEvent e) {
		x2 = x1 = e.getX();
		y2 = y1 = e.getY();
		repaint();
	}

	public void mouseDragged(MouseEvent e) {
		x2 = e.getX();
		y2 = e.getY();
		repaint();			
	}

	// Mais eventos do rato, que neste caso não interessam
	public void mouseReleased(MouseEvent e) {}
	public void mouseMoved(MouseEvent arg0) { }
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

	// Programa principal, lança frame com o painel de desenho
	public static void main(String[] args) {
		JFrame f = new JFrame("Graphics Demo");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		f.setPreferredSize(new Dimension(500,500));
		f.getContentPane().add(new GraphicsDemo());
        f.pack();
        f.setVisible(true);
	}
}