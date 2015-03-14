
public class FileSystem extends Node {
	
	FileSystem(String t) {
		super(new Folder(null, null), t, false);
	}
	
	FileSystem(String t, Folder a) {
		super(a, t, false);
	}

	public Folder getRoot() {
		return getParent();
	}

	public String getType() {
		return getName();
	}
}
