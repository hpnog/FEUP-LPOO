
public class FileSystem {
	String type;
	NameFormatter formatter;
	Folder root = new Folder(this);
	
	public FileSystem(String n) {
		type = n;
	}

	public String getType() {
		return type;
	}

	public Folder getRoot() {
		return root;
	}

	public void setNameFormatter(NameFormatter unix) {
		formatter = unix;
	}
	
	

}
