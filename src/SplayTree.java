import java.io.FileWriter;
import java.io.PrintWriter;

public class SplayTree {
	private Node root;
	public SplayTree(){
		this.root = new Node(-1);
	}
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
    public void insert(int x) {
        if (this.root == null){
            this.root = new Node(x);
            return;
        }
        if (this.root.key == -1){
            this.root.key = x;
            return;
        }
        this.root = splay(this.root, x);
        if (x < this.root.key){
            Node n = new Node(x);
            n.left = this.root.left;
            n.right = this.root;
            this.root.left = null;
            this.root = n;
        }
        else if(x > this.root.key){
            Node n = new Node(x);
            n.right = this.root.right;
            n.left = this.root;
            this.root.right = null;
            this.root = n;
        }
    }
    public Node search(int x){
        this.root = splay(this.root, x);
        if(this.root.key == x)
        	return this.root;
        else
        	return null;
        
	}
    /* Splay busca el nodo que contiene la llave x, y lo lleva hasta la raíz n.
     * En caso de no existir, traerá al último nodo en el camino, hasta la raíz. */
    public Node splay(Node n, int x) {
        if(n == null)
        	return null;
        if(x < n.key){
            if(n.left == null){
                return n;
            }
            if(x < n.left.key){
                n.left.left = splay(n.left.left, x);
                n = zig(n);
            }
            else if(x > n.left.key){
                n.left.right = splay(n.left.right, x);
                if(n.left.right != null)
                    n.left = zag(n.left);
            }
            if(n.left == null)
            	return n;
            else
            	return zig(n);
        }
        else if(x > n.key){
            if(n.right == null){
                return n;
            }
            if (x < n.right.key){
                n.right.left  = splay(n.right.left, x);
                if (n.right.left != null)
                    n.right = zig(n.right);
            }
            else if (x > n.right.key){
                n.right.right = splay(n.right.right, x);
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
    public int preOrder(Node n, PrintWriter pw){
    	int p = 0;
    	if(n != null){
			pw.println(n.key);
			p = 1;
			if(n.left != null)
				p=p+preOrder(n.left, pw);
			if(n.right != null)
				p=p+preOrder(n.right, pw);
		}
		return p;
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
		for(int i = 0; i < N; i++)
			tree.insert(keys[i]);
		tf = System.currentTimeMillis();
		pw.println("Número insertados: "+tree.preOrder(tree.root, pw));
		pw.println("Tiempo Inserción: "+(tf-ti));
		int[] keysToSearch = new int[100*N];
		for(int i = 0; i < N; i++){
			keysToSearch[i] = tree.numRandom(0, max);
			//pw.println(""+keys[i]);
		}
		int count = 0;
		ti = System.currentTimeMillis();
		for(int i = 0; i < 100*N; i++){
			Node found = tree.search(keysToSearch[i]);
			if(found != null)
				count++;
		}
		tf = System.currentTimeMillis();
		pw.println("Tiempo Búsqueda: "+(tf-ti));
		pw.println("Coincidencias: "+count);
		pw.close();
	}
}