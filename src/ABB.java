import java.io.PrintWriter;
import java.io.FileWriter;
public class ABB{
	Node root;
	public ABB(){
		this.root = new Node(-1);
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
				n.left = new Node(x);
			else
				insert(x, n.left);
		}
		else if(x > n.key){
			if(n.right == null)
				n.right = new Node(x);
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
		PrintWriter pw = new PrintWriter(new FileWriter("abbtree_test.txt"));
		pw.println("ABB Tree Test");
		pw.println("");
		ABB tree = new ABB();
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
			tree.insert(keys[i], tree.root);
		tf = System.currentTimeMillis();
		pw.println("");
		tree.preOrder(tree.root, pw);
		pw.println("");
		pw.println("Tiempo Inserción: "+(tf-ti));
		int[] keysToSearch = new int[100*N];
		for(int i = 0; i < N; i++){
			keysToSearch[i] = tree.numRandom(0, max);
			//pw.println(""+keys[i]);
		}
		int count = 0;
		ti = System.currentTimeMillis();
		for(int i = 0; i < 100*N; i++){
			Node found = tree.search(keysToSearch[i], tree.root);
			if(found != null)
				count++;
		}
		tf = System.currentTimeMillis();
		pw.println("");
		pw.println("Tiempo Búsqueda: "+(tf-ti));
		pw.println("Coincidencias: "+count);
		pw.close();
	}
}