import java.io.FileWriter;
import java.io.PrintWriter;

public class AVL {
	private NodeAVL root;
	public AVL(){
		this.root = null;
	}
	public NodeAVL simpleLeftRotation(NodeAVL k2){
		NodeAVL k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.h = Math.max( height( k2.left ), height( k2.right ) ) + 1;
        k1.h = Math.max( height( k1.left ), height(k2) ) + 1;
        return k1;
	}
	public NodeAVL simpleRightRotation(NodeAVL k1){
        NodeAVL k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.h = Math.max( height( k1.left ), height( k1.right ) ) + 1;
        k2.h = Math.max( height( k2.right ), height(k1) ) + 1;
        return k2;
	}
	public NodeAVL doubleLeftRotation(NodeAVL k3){
		k3.left=simpleRightRotation(k3.left);
		return simpleLeftRotation(k3); 
		
	}
	public NodeAVL doubleRightRotation(NodeAVL k1){
		k1.right = simpleLeftRotation(k1.right);
		return simpleRightRotation(k1);
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
	public NodeAVL insert(int x,NodeAVL r){
		if(r==null){
			r = new NodeAVL(x);
			return r;
		}
		else{
			if(x<r.key){
				r.left=insert(x,r.left);
				if(height(r.right)-height(r.left)==-2){
					if(x-r.left.key<0){
						r=simpleLeftRotation(r);
					}
					else{
						r=doubleLeftRotation(r);
					}
				}
			}
			else if(x>r.key){
				r.right=insert(x,r.right);
				if(height(r.left)-height(r.right)==-2){
					if(x-r.right.key>0){
						r=simpleRightRotation(r);
					}
					else{
						r=doubleRightRotation(r);
					}
				}
			}
            r.h = Math.max( height( r.left ), height( r.right ) ) + 1;
			return r;
		}
		
	}
    public void preOrder(NodeAVL n, PrintWriter pw){
		if(n != null){
			pw.println(n.key);
			if(n.left != null)
				preOrder(n.left, pw);
			if(n.right != null)
				preOrder(n.right, pw);
		}
	}
	public static int numRandom(int min, int max){
		return ((int)(Math.random()*max-min+1));
	}
	
	public int height(NodeAVL r){
        return r == null ? -1 : r.h;
	}
	static public void main(String[] args) throws Exception{
		PrintWriter pw = new PrintWriter(new FileWriter("avl_test.txt"));
		pw.println("AVL Test");
		pw.println("");
		AVL r = new AVL();
		int N = (int)Math.pow(2, 18);
		int max = (int)(Math.pow(2, 22)-1);

		for(int i = 0; i < N; i++){
			r.root=r.insert(numRandom(1,max),r.root);
		}
		r.preOrder(r.root,pw);
		//System.out.println(max);
		pw.close();
	}

}
