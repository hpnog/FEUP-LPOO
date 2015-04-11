package maze.logic;

import java.io.*;

import javax.swing.JFileChooser;

import maze.gui.MazeDisplay;
import maze.logic.Jogo;

public interface SaveAndLoad {
	public static void saveGame(Jogo jogo) throws IOException {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("src/maze/logic/saves/"));
		int result = fileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			File chosen = fileChooser.getSelectedFile();
			String path = chosen.getAbsolutePath();
			try
			{
				FileOutputStream tempo = new FileOutputStream(path);
				ObjectOutputStream objectOut = new ObjectOutputStream(tempo);
				objectOut.writeObject(jogo);
				objectOut.flush();
				objectOut.close();
				tempo.close();
			}
			catch (IOException a) { a.printStackTrace();}
		}

	}

	public static Jogo loadGame(Jogo arg) throws IOException, ClassNotFoundException {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("src/maze/logic/saves/"));
		int result = fileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			File chosen = fileChooser.getSelectedFile();
			String path = chosen.getAbsolutePath();
			try
			{
				FileInputStream tempo = new FileInputStream(path);
				ObjectInputStream objectIn = new ObjectInputStream(tempo);
				arg = (Jogo) objectIn.readObject();
				System.out.printf("\nSize of maze: %d\n", arg.getLabirinto().getSize());
				MazeDisplay.setJogoG(arg);
				objectIn.close();
				tempo.close();
				arg.setInter(3);
			}
			catch (IOException a) {	a.printStackTrace(); }
			return arg;
		}
		return null;
	}
}
