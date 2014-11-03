import java.io.FileWriter;
import java.io.PrintWriter;

public class SplayTreeAlt {
	private Node root;
	public SplayTreeAlt(){
		this.root = new Node(-1, null);
	}
	public void rotation(Node n){
		Node p = n.getParent();
		if(n == this.root)
			return;
		else if(p == this.root){
			if(p.left == n)
				zig(n);
			else
				zag(n);
		}
		else if(p.parent != null){
			if(p.left == n && p.parent.left == p)
				zigzig(n);
			else if(p.right == n && p.parent.right == p)
				zagzag(n);
			else if(p.left == n && p.parent.right == p)
				zigzag(n);
			else if(p.right == n && p.parent.left == p)
				zagzig(n);
			rotation(n);
		}
	}
	public void zig(Node n){
		Node p = n.getParent();
		n.parent = null;
		this.root = n;
		if(n.right != null)
			p.left = n.right;
		n.right = p;
	}
	public void zag(Node n){
		Node p = n.getParent();
		n.parent = null;
		this.root = n;
		if(n.left != null)
			p.right = n.left;
		n.left = p;
	}
	public void zigzig(Node n){
		Node p = n.getParent();
		zig(p);
		zig(n);
	}
	public void zagzag(Node n){
		Node p = n.getParent();
		zag(p);
		zag(n);
	}
	public void zigzag(Node n){
		zig(n);
		zag(n);
	}
	public void zagzig(Node n){
		zag(n);
		zig(n);
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
			if(n.left == null){
				n.left = new Node(x, n);
				rotation(n.left);
			}
			else
				insert(x, n.left);
		}
		else if(x > n.key){
			if(n.right == null){
				n.right = new Node(x, n);
				rotation(n.right);
			}
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
		SplayTreeAlt tree = new SplayTreeAlt();
		//int N = (int)Math.pow(2, 10);
		int N = 20;
		int max = (int)(Math.pow(2, 22)-1);
		int[] keys = new int[N];
		pw.println("Keys Array:");
		pw.println("");
		for(int i = 0; i < N; i++){
			keys[i] = tree.numRandom(0, max);
			pw.println(""+keys[i]);
		}
		pw.println("");
		pw.println("Tree:");
		pw.println("");
		for(int i = 0; i < N; i++){
			tree.insert(keys[i], tree.root);
		}
		tree.preOrder(tree.root, pw);
		/*pw.println("");
		pw.println("Zig Test");
		pw.println("");
		tree.zig(tree.root.left);
		tree.preOrder(tree.root, pw);*/
		//System.out.println(max);
		pw.close();
	}
}
