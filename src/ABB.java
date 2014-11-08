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
			pw = new PrintWriter(new FileWriter("abb_test"+k+".txt"));
			int N = (int)Math.pow(2, (10+2*k));
			ABB tree = new ABB();
			pw.println("ABB Test con N = "+N);
			pw.println("");
			int max = (int)(Math.pow(2, 22)-1);
			int[] keys = new int[N];
//			pw.println("Keys Array:");
//			pw.println("");
			for(int i = 0; i < N; i++){
				keys[i] = tree.numRandom(0, max);
//				pw.println(""+keys[i]);
			}
//			pw.println("");
//			pw.println("Tree:");
//			pw.println("");
			long ti, tf;
			ti = System.currentTimeMillis();
			for(int i = 0; i < N; i++)
				tree.insert(keys[i],tree.root);
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
				Node found = tree.search(keysToSearch[i],tree.root);
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
					Node found = tree.search(keysToSearch[i],tree.root);
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
					Node found = tree.search(keysToSearch[i],tree.root);
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