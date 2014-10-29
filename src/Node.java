
public class Node{
	int key;
	Node left;
	Node right;
	Node parent;
	public Node(int key, Node parent){
		this.key = key;
		this.left = null;
		this.right = null;
		this.parent = parent;
	}
	public Node getParent(){
		return this.parent;
	}
}
