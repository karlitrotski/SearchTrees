import java.io.FileWriter;
import java.io.PrintWriter;

public class SplayTree {
	Node root;
	public SplayTree(){
		this.root = new Node(-1, null);
	}
	public void zig(Node n){
		Node aux = n;
		Node paux = n.parent;
		if(n.parent != null){
			aux.parent = n.parent.parent;
			if(n.parent.parent != null){
				if(n.parent.parent.left == n.parent)
					n.parent.parent.left = aux;
				else
					n.parent.parent.right = aux;
			}
		}
		paux.left = n.right;
		aux.right = paux;
	}
	public void zag(Node n){
		Node aux = n;
		Node paux = n.parent;
		if(n.parent != null){
			aux.parent = n.parent.parent;
			if(n.parent.parent != null){
				if(n.parent.parent.left == n.parent)
					n.parent.parent.left = aux;
				else
					n.parent.parent.right = aux;
			}
		}
		paux.right = n.left;
		aux.left = paux;
	}
	public void zigzig(){
		
	}
	public void zagzag(){
		
	}
	public void zigzag(){
		
	}
	public void zagzig(){
		
	}
	public Node search(int x, Node n){
		if(n == null)
			return null;
		else if(n.key == -1)
			return null;
		else if(x == n.key)
			return n;
		else if(x < n.key)
			return search(x, n.left);
		else
			return search(x, n.right);
	}
	public void insert(int x, Node n){
		if(n.key == -1)
			n.key = x;
		else if(x < n.key){
			if(n.left == null)
				n.left = new Node(x, n);
			else
				insert(x, n.left);
		}
		else if(x > n.key){
			if(n.right == null)
				n.right = new Node(x, n);
			else
				insert(x, n.right);
		}
		return;
	}
	public void preOrder(Node n, PrintWriter pw){
		if(n != null){
			pw.println(n.key);
			if(n.left != null)
				preOrder(n.left, pw);
			if(n.right != null)
				preOrder(n.right, pw);
		}
	}
	public int numRandom(int min, int max){
		return ((int)(Math.random()*max-min+1));
	}
	static public void main(String[] args) throws Exception{
		PrintWriter pw = new PrintWriter(new FileWriter("splaytree_test.txt"));
		pw.println("SplayTree Test");
		pw.println("");
		SplayTree tree = new SplayTree();
		//int N = (int)Math.pow(2, 10);
		int N = 5;
		int max = (int)(Math.pow(2, 22)-1);
		for(int i = 0; i < N; i++){
			tree.insert(tree.numRandom(0, max), tree.root);
		}
		tree.preOrder(tree.root, pw);
		pw.println("");
		pw.println("Zig Test");
		pw.println("");
		tree.zig(tree.root.left);
		tree.preOrder(tree.root, pw);
		//System.out.println(max);
		pw.close();
	}
}
