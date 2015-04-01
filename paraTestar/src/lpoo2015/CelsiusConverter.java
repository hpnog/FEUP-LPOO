package lpoo2015;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CelsiusConverter {

	public static void main(String[] args) {
		final JFrame frame = new JFrame("Celsius Converter");

		Container c = frame.getContentPane();
		c.setLayout(new GridLayout(3, 2));

		c.add(new JLabel("Celsius"));

		final JTextField celsius = new JTextField();
		c.add(celsius);

		c.add(new JLabel("Fahrenheit"));

		final JTextField fahrenheit = new JTextField();
		c.add(fahrenheit);
		fahrenheit.setEditable(false);

		JButton convert = new JButton("Convert");
		c.add(convert);

		convert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					double c = Double.parseDouble(celsius.getText());
					double f = c * 1.8 + 32;
					fahrenheit.setText(Double.toString(f));
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frame, "Conteúdo inválido");
				}
			}
		});

		JButton exit = new JButton("Exit");
		c.add(exit);
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				System.exit(0);
			}
		});

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}