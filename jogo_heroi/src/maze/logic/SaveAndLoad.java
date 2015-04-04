package maze.logic;

import java.io.*;

import maze.gui.MazeDisplay;
import maze.logic.Jogo;

public interface SaveAndLoad {
	public static void saveGame(Jogo jogo) throws IOException {
		try
		{
		ObjectOutputStream objectOut = new ObjectOutputStream(
				new FileOutputStream("src/maze/logic/saves/save.ds"));
		objectOut.writeObject(jogo);
		objectOut.flush();
		objectOut.close();
		}
		catch (IOException a) { a.printStackTrace();}
		
	}
	
	public static void loadGame(Jogo arg) throws IOException, ClassNotFoundException {
		try
		{
			ObjectInputStream objectIn = new ObjectInputStream(
					new FileInputStream("src/maze/logic/saves/save.ds"));
			MazeDisplay a = new MazeDisplay(true);
			arg = (Jogo) objectIn.readObject();
			objectIn.close();
		}
		catch (IOException a) {	a.printStackTrace(); }
	}
}
