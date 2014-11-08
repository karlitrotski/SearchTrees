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
    public void preOrder(Node n, PrintWriter pw){
    	if(n != null){
			pw.println(n.key);
			if(n.left != null)
				preOrder(n.left, pw);
			if(n.right != null)
				preOrder(n.right, pw);
		}
	}
    public int count(Node n){
    	int p = 0;
    	if(n != null){
			p = 1;
			if(n.left != null)
				p=p+count(n.left);
			if(n.right != null)
				p=p+count(n.right);
		}
		return p;
	}
	public int numRandom(int min, int max){
		return ((int)(Math.random()*max-min+1));
	}
    static public void main(String[] args) throws Exception{
		PrintWriter pw;
		int runs = 6;
		for(int k = 0; k < runs; k++){
			pw = new PrintWriter(new FileWriter("splaytree_test"+k+".txt"));
			SplayTree tree = new SplayTree();
			int N = (int)Math.pow(2, (10+2*k));
			pw.println("Splay Tree con N = "+N);
			pw.println("");
			int max = (int)(Math.pow(2, 22)-1);
			int[] keys = new int[N];
			//pw.println("Keys Array:");
			//pw.println("");
			for(int i = 0; i < N; i++){
				keys[i] = tree.numRandom(0, max);
				//pw.println(""+keys[i]);
			}
			//pw.println("");
			//pw.println("Tree:");
			//pw.println("");
			long ti, tf;
			ti = System.currentTimeMillis();
			for(int i = 0; i < N; i++)
				tree.insert(keys[i]);
			//tree.preOrder(tree.root, pw);
			tf = System.currentTimeMillis();
			pw.println("Número insertados: "+tree.count(tree.root));
			pw.println("Tiempo Inserción: "+(tf-ti));
			pw.println("");
			int[] keysToSearch = new int[100*N];
			for(int i = 0; i < 100*N; i++){
				keysToSearch[i] = tree.numRandom(0, max);
				//pw.println(""+keys[i]);
			}
			pw.println("Experimento Tipo 1: Búsqueda sin probabilidad");
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
			pw.println("");
			/*pw.println("Experimento Tipo 2: Búsqueda con probabilidad");
			double[] cons = {1.2, 1.5, 2.0};
			for(int i = 0; i < cons.length; i++){
				double[] nonNormalP = new double[N];
				double sumNonNormalP = 0;
				for(int j = 0; j < N; j++){
					nonNormalP[j] = 1/Math.pow(j+1, cons[i]);
					sumNonNormalP += nonNormalP[j];
				}
				// constante de normalización
				double c = 1/sumNonNormalP;
				keysToSearch = new int[100*N];
				for(int j = 0; j < keysToSearch.length; j++){
					double accumP = 0;
					double randomN = Math.random();
					for(int l = 0; l < N; l++){
						double probToBeSearched = c * nonNormalP[l];
						accumP += probToBeSearched;
						if(accumP >= randomN) {
							keysToSearch[j] = keys[l];
							break;
						}
					}
				}
				count = 0;
				ti = System.currentTimeMillis();
				for(int j = 0; j < 100*N; j++){
					Node found = tree.search(keysToSearch[i]);
					if(found != null)
						count++;
				}
				tf = System.currentTimeMillis();
				pw.println("cons = "+cons[i]);
				pw.println("Tiempo Búsqueda: "+(tf-ti));
				pw.println("Coincidencias: "+count);
				pw.println("");
			}
			pw.println("Experimento Tipo 3: Búsqueda con probabilidad");
		    for(int i = 0; i < cons.length; i++){
		        double[] nonNormalP = new double[N];
		        double sumNonNormalP = 0;
		        for(int j = 0; j < N; j++) {
		          nonNormalP[j] = 1/Math.pow(cons[i], j + 1);
		          sumNonNormalP += nonNormalP[j];
		        }
		        // constante de normalización
		        double c = 1/sumNonNormalP;
		        keysToSearch = new int[100*N];
		        for(int j = 0; j < keysToSearch.length; j++){
		        	double accumP = 0;
		        	double randomN = Math.random();
		        	for(int l = 0; l < N; l++){
		        		double probToBeSearched = c*nonNormalP[l];
		        		accumP += probToBeSearched;
		        		if(accumP >= randomN){
		        			keysToSearch[j] = keys[l];
		        			break;
		        		}
		        	}
		        }
		        count = 0;
				ti = System.currentTimeMillis();
				for(int j = 0; j < 100*N; j++){
					Node found = tree.search(keysToSearch[i]);
					if(found != null)
						count++;
				}
				tf = System.currentTimeMillis();
				pw.println("cons = "+cons[i]);
				pw.println("Tiempo Búsqueda: "+(tf-ti));
				pw.println("Coincidencias: "+count);
				pw.println("");
		    }*/
			pw.close();
		}
	}
}