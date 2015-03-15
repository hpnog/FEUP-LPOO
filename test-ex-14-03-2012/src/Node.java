
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
}
