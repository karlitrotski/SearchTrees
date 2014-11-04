import java.io.FileWriter;
import java.io.PrintWriter;

public class SplayTree {
	private Node root;
	public Node zig(Node p) {
        Node n = p.left;
        p.left = n.right;
        n.right = p;
        return n;
    }
    public Node zag(Node p) {
        Node n = p.right;
        p.right = n.left;
        n.left = p;
        return n;
    }
    public void insert(int key) {
        if (this.root == null) {
            this.root = new Node(key);
            return;
        }
        this.root = splay(this.root, key);
        if (key < this.root.key){
            Node n = new Node(key);
            n.left = this.root.left;
            n.right = this.root;
            this.root.left = null;
            this.root = n;
        }
        else if(key > this.root.key){
            Node n = new Node(key);
            n.right = this.root.right;
            n.left = this.root;
            this.root.right = null;
            this.root = n;
        }
    }
    public Node splay(Node n, int key) {
        if(n == null)
        	return null;
        if(key < n.key){
            if(n.left == null){
                return n;
            }
            if(key < n.left.key){
                n.left.left = splay(n.left.left, key);
                n = zig(n);
            }
            else if(key > n.left.key){
                n.left.right = splay(n.left.right, key);
                if(n.left.right != null)
                    n.left = zag(n.left);
            }
            if(n.left == null)
            	return n;
            else
            	return zig(n);
        }
        else if(key > n.key){
            if(n.right == null){
                return n;
            }
            if (key < n.right.key){
                n.right.left  = splay(n.right.left, key);
                if (n.right.left != null)
                    n.right = zig(n.right);
            }
            else if (key > n.right.key){
                n.right.right = splay(n.right.right, key);
                n = zag(n);
            } 
            if (n.right == null)
            	return n;
            else
            	return zag(n);
        }
        else
        	return n;
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
		int N = (int)Math.pow(2, 20);
		//int N = 20;
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
		long ti, tf;
		ti = System.currentTimeMillis();
		for(int i = 0; i < N; i++){
			tree.insert(keys[i]);
		}
		tf = System.currentTimeMillis();
		pw.println("Tiempo Inserción: "+(tf-ti));
		pw.println("");
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