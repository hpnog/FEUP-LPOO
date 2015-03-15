
public class Node {
	FileSystem formatter;
	String name;
	Folder parent;
	static int number = 0;

	Node() {
		name = null;
		parent = null;
		number++;
	}

	Node(FileSystem form) {
		name = null;
		parent = null;
		number++;
		formatter = form;
	}

	Node(String n, Folder rt) throws DuplicateNameException {
		parent = rt;
		name = n;

		//Verifica duplicados
		if (parent != null) {
			if (parent.child != null) {
				if (parent.child.length != 0) {
					for (int i = 0; i < parent.child.length; i++) {
						if (parent.child[i].getName() == name) {
							throw new DuplicateNameException();
						}
					}
				}


				Node [] temporario = new Node[rt.child.length+1];
				for (int i = 0; i < rt.child.length; i++)
					temporario[i] = rt.child[i];
				temporario[rt.child.length] = this;
				rt.setChild(temporario);

			}
		}
		number++;
	}

	public String getName() {
		return name;
	}

	public Folder getParent() {
		return parent;
	}

	public static void resetNumbering(int i) {
		number = i;
	}

	public int getNumber() {
		return number;
	}

	public void cloner(Folder r, String nam) throws DuplicateNameException {
		return;
	}

	@Override

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Node))
			return false;
		Node other = (Node) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}



}
