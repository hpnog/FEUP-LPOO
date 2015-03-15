
public class Folder extends Node{
	Node [] child;
	int size = 0;
	
	Folder() {
		super();
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
		for (int i = 0; i < child.length; i++)
			if (child[i].getName() == n)
				return child[i];
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
	
	

}
