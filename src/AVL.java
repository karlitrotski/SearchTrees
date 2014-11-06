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
	
	public NodeAVL search(int x, NodeAVL n){
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
    public int preOrder(NodeAVL n, PrintWriter pw){
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
	
	public int height(NodeAVL r){
        return r == null ? -1 : r.h;
	}
	static public void main(String[] args) throws Exception{
		PrintWriter pw = new PrintWriter(new FileWriter("avl_test.txt"));
		pw.println("AVL Test");
		pw.println("");
		AVL tree = new AVL();
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
			tree.root=tree.insert(keys[i],tree.root);
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
			NodeAVL found = tree.search(keysToSearch[i],tree.root);
			if(found != null)
				count++;
		}
		tf = System.currentTimeMillis();
		pw.println("Tiempo Búsqueda: "+(tf-ti));
		pw.println("Coincidencias: "+count);
		pw.close();
	}

}
