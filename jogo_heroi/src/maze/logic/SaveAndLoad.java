package maze.logic;

import java.io.*;

import maze.gui.MazeDisplay;
import maze.logic.Jogo;

public interface SaveAndLoad {
	public static void saveGame(Jogo jogo) throws IOException {
		try
		{
		FileOutputStream tempo = new FileOutputStream("src/maze/logic/saves/save.ds");
		ObjectOutputStream objectOut = new ObjectOutputStream(tempo);
		objectOut.writeObject(jogo);
		objectOut.flush();
		objectOut.close();
		tempo.close();
		}
		catch (IOException a) { a.printStackTrace();}
		
	}
	
	public static Jogo loadGame(Jogo arg) throws IOException, ClassNotFoundException {
		try
		{
			FileInputStream tempo = new FileInputStream("src/maze/logic/saves/save.ds");
			ObjectInputStream objectIn = new ObjectInputStream(tempo);
			arg = (Jogo) objectIn.readObject();
			System.out.printf("\nSize of maze: %d\n", arg.getLabirinto().getSize());
			MazeDisplay.setJogoG(arg);
			objectIn.close();
			tempo.close();
		}
		catch (IOException a) {	a.printStackTrace(); }
		return arg;
	}
}
