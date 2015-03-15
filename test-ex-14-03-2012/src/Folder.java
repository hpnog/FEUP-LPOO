import java.util.Arrays;


public class Folder extends Node{
	Node [] child;
	int size = 0;
	
	Folder() {
		super();
		child = new Node[0];
	}
	
	Folder(FileSystem formatter) {
		super(formatter);
		child = new Node[0];
	}

	Folder(Folder rt, String nam) throws DuplicateNameException {
		super(nam, rt);
	}

	public Node[] getChild() {
		return child;
	}

	public void setChild(Node[] child) {
		this.child = child;
	}

	public Node getChildByName(String n) {
		if (child != null) {
			if (child.length != 0) {
				for (int i = 0; i < child.length; i++)
					if (child[i].getName() == n)
						return child[i];
			}
		}
		return null;
	}

	public void increaseSize(int size2) {
		size += size2;
		if (parent != null)
			parent.increaseSize(size2);
	}

	public int getSize() {
		return size;
	}

	public String getPath() {
String path = "";
		
		String sep = parent.getSeparator();

		path = sep + name;
		
		if (parent != null)
			if (parent.name != null)
			path = parent.getPath() + path;
		return path;
	}

	String getSeparator() {
		String path = "";
		if (parent == null)
			path = String.valueOf(formatter.formatter.getSeparator());
		else
			path = parent.getSeparator();
		return path;
	}


	public Folder clone(Folder r, String nam) throws DuplicateNameException {
		Folder fim = new Folder(r, nam);
		if (child != null) {
			if (child.length != 0) {
				for (int i = 0; i < child.length; i++)
					child[i].cloner(this, child[i].getName());
			}
		}
		return fim;
	}

	public void cloner(Folder r, String nam) throws DuplicateNameException {
		Folder fim = new Folder(r, nam);
		for (int i = 0; i < child.length; i++)
			child[i].cloner(this, child[i].getName());
		return;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(child);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Folder))
			return false;
		Folder other = (Folder) obj;
		if (!Arrays.equals(child, other.child))
			return false;
		return true;
	}
	
	
	
}
