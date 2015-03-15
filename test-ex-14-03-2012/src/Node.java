
public class Node {
	String name;
	Folder parent;
	
	Node() {
		name = null;
		parent = null;
	}

	Node(String n, Folder rt) throws DuplicateNameException {
		parent = rt;
		name = n;
		
		//Verifica duplicados
		if (parent != null) {
			if (parent.child.length != 0) {
				for (int i = 0; i < parent.child.length; i++) {
					if (parent.child[i].getName() == name) {
						throw new DuplicateNameException();
					}
				}
			}
		}

		Node [] temporario = new Node[rt.child.length+1];
		for (int i = 0; i < rt.child.length; i++)
			temporario[i] = rt.child[i];
		temporario[rt.child.length] = this;
		rt.setChild(temporario);
		
		
	}

	public String getName() {
		return name;
	}

	public Folder getParent() {
		return parent;
	}
}
