
public class Folder extends Node{
	
	private Node [] childs;
	private int num;
	
	public Folder(Folder a) {
		super(a, null, true);
		childs = new Node[0];
		num = 0;
	}

	public Folder(Folder root, String string) {
		super(root, string, true);
	}

	public void addChild(Node node) {
		num++;
		Node [] temp = new Node [num];
		for (int i = 0; i < num-1; i++)
			temp[i] = childs[i];
		temp[num-1] = node;		
		childs = temp;
	}

	public Node[] getChild() {
		return childs;
	}
	
}
