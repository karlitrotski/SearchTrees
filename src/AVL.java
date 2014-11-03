import java.io.FileWriter;
import java.io.PrintWriter;

public class AVL {
	Node root;
	public AVL(){
		this.root = new Node(-1, null);
	}
	public void simpleLeftRotation(){
		
	}
	public void simpleRightRotation(){
		
	}
	public void doubleLeftRotation(){
		
	}
	public void doubleRightRotation(){
		
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
	public static void preOrder(NodeAVL r, PrintWriter pw){
		if(r != null){
			pw.println(r.key);
			if(r.left != null)
				preOrder(r.left, pw);
			if(r.right != null)
				preOrder(r.right, pw);
		}
	}
	public static int numRandom(int min, int max){
		return ((int)(Math.random()*max-min+1));
	}
	static public void main(String[] args) throws Exception{
		PrintWriter pw = new PrintWriter(new FileWriter("avl_test.txt"));
		pw.println("AVL Test");
		pw.println("");
		NodeAVL r = new NodeAVL(0);
		int N = (int)Math.pow(2, 18);
		int max = (int)(Math.pow(2, 22)-1);
		for(int i = 0; i < N; i++){
			r.insertar(numRandom(1,max));
		}
		preOrder(r, pw);
		//System.out.println(max);
		pw.close();
	}

}
