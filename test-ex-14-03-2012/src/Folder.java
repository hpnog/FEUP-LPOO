
public class Folder extends Node{
	Node [] child;
	
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
	
	

}
