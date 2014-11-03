
public class Node{
	int key;
	Node left;
	Node right;
	public Node(int key, Node parent){
		this.key = key;
		this.left = null;
		this.right = null;
	}
	public Node(int key){
		this.key = key;
		this.left = null;
		this.right = null;
	}
}
