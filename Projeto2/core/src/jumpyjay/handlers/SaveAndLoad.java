package jumpyjay.handlers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.badlogic.gdx.Gdx;


/**
 * The Class SaveAndLoad.
 */
public class SaveAndLoad {

	/**
	 * Instantiates a new save and load.
	 */
	private SaveAndLoad() {}

	/**
	 * Saves game.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void saveGame() throws IOException {
		try
		{
			FileOutputStream tempo = new FileOutputStream(Gdx.files.local("savedGame.dat").file().getAbsolutePath());
			ObjectOutputStream objectOut = new ObjectOutputStream(tempo);
			objectOut.writeObject(SingletonVandC.totalScore);
			objectOut.flush();
			objectOut.close();
			tempo.close();
		}
		catch (IOException a) 
		{ 
			a.printStackTrace();
		}
	}

	/**
	 * Loads game.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException the class not found exception
	 */
	public static void loadGame() throws IOException, ClassNotFoundException {
		int[] toRet;
		if (!Gdx.files.local("savedGame.dat").file().exists())
		{
			toRet = new int[10];
			for (int i = 0; i < 10; i++)
				toRet[i] = -1;
			SingletonVandC.totalScore = toRet;
		}
		else
		{
			try
			{
				FileInputStream tempo = new FileInputStream(Gdx.files.local("savedGame.dat").file().getAbsolutePath());
				ObjectInputStream objectIn = new ObjectInputStream(tempo);
				toRet = (int[]) objectIn.readObject();
				SingletonVandC.totalScore = toRet;
				objectIn.close();
				tempo.close();
			}
			catch (IOException a) {
				a.printStackTrace();
			}
		}
	}

}
