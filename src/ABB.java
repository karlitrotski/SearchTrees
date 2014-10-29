import java.io.PrintWriter;
import java.io.FileWriter;
public class ABB{
	NodeABB root;
	public ABB(){
		this.root = new NodeABB(-1);
	}
	public NodeABB search(int x, NodeABB n){
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
	public void insert(int x, NodeABB n){
		if(n.key == -1)
			n.key = x;
		else if(x < n.key){
			if(n.left == null)
				n.left = new NodeABB(x);
			else
				insert(x, n.left);
		}
		else if(x > n.key){
			if(n.right == null)
				n.right = new NodeABB(x);
			else
				insert(x, n.right);
		}
		return;
	}
	public void preOrder(NodeABB n, PrintWriter pw){
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
		PrintWriter pw = new PrintWriter(new FileWriter("test.txt"));
		pw.println("ABB Test");
		pw.println("");
		ABB tree = new ABB();
		int N = (int)Math.pow(2, 10);
		int max = (int)(Math.pow(2, 22)-1);
		for(int i = 0; i < N; i++){
			tree.insert(tree.numRandom(0, max), tree.root);
		}
		tree.preOrder(tree.root, pw);
		//System.out.println(max);
		pw.close();
	}
}