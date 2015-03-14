
public class Node {
	Folder parent;
	String name;
	
	Node(Folder a, String b, boolean bool) {
		parent = a;
		name = b;
		if (a != null && bool)
			parent.addChild(this);
	}

	public Folder getParent() {
		return parent;
	}

	public String getName() {
		return name;
	}
	
}
