
public class FileSystem {
	String type;
	Folder root = new Folder();
	
	public FileSystem(String n) {
		type = n;
	}

	public String getType() {
		return type;
	}

	public Folder getRoot() {
		return root;
	}
	
	

}
