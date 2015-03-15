
public class FileSystem extends Folder{
	String type;
	Folder root = new Folder();
	
	public FileSystem(String n) {
		super();
		type = n;
	}

	public String getType() {
		return type;
	}

	public Folder getRoot() {
		return root;
	}
	
	

}
