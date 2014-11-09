import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

	
public class VEBTree {

	private vEBNode root;
	
	public vEBNode search (int x, vEBNode r){
		
		// implementar buscar (?)
		if(x==r.min || x==r.max){
			return r;
		}
		else if (r.U==2){
			return null;
		}
		
		else{
			int raiz = r.higherSquareRoot();
			int high=(int) Math.ceil(x/raiz);
			int low=x%raiz;
			return search(low,r.cluster[high]);
		}
		
		
	}
	public vEBNode insert(int x,vEBNode r){
		
		if(r.min>r.max){ // vacío
			r.min=x;
			r.max=x;
			return r;
		}
		
		else if(r.min == r.max){ // hay un solo elemento
			if(x<r.min){
				// ver donde se ubicaría el actual r.min. luego dejar r.min = x;
				// insertar al auxiliar el número de children que corresponde al nuevo
				r.min=x;
			}
			else if(x>r.max){
				// ver donde se ubicaría el actual r.max. luego dejar r.max = x;
				// insertar al auxiliar el número de children que corresponde al nuevo
				r.max=x;
			}
			return r;
		}
		else if(x<r.min){
			//swapear los nodos? (x,r.min);
			//se deja el x como r.min, y el r.min hay que insertarlo
			int aux = r.min;
			r.min=x;
			x=aux;
		}
		
		else if(x>r.max){
			//cosas
			r.max=x; // hay que insertarlo igual
		}
		// está dentro de los boundaries de los hijos
		int raiz = r.higherSquareRoot();
		int i = (int) Math.ceil(x/raiz);
		// al parecer insertaremos el modulo desde el floor. juej
		insert(x%raiz,r.cluster[i]);
		if(r.cluster[i].min == r.cluster[i].max){
			insert(i,r.summary);
		}
			// más cosas
		
		return r;
	}
	public int numRandom(int min, int max){
		return ((int)(Math.random()*max-min+1));
	}
	
	static public void main(String[] args) throws IOException{
		
//		VEBTree tree = new VEBTree();
//		tree.root=new vEBNode((int)Math.pow(2, 20));
//		tree.insert(50, tree.root);
//		if(tree.search(50,tree.root)==null)
//			System.out.println("no"+null);
//		else System.out.println("buena");
//		if(tree.search(48,tree.root)==null)
//			System.out.println("no"+null);
//		else System.out.println("buena");
	

		
		
		PrintWriter pw;
		int runs = 3; // deberian ir seis!
		for(int k = 0; k < runs; k++){
			pw = new PrintWriter(new FileWriter("VEB Test "+k+".txt"));
			VEBTree tree = new VEBTree();
			int N = (int)Math.pow(2, (10+2*k));
			int max = (int)(Math.pow(2, 22)-1);
			tree.root=new vEBNode(max+1);
			pw.println("VEBTest con N = "+N);
			pw.println("");
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
//			System.out.println("lower = "+tree.root.lowerSquareRoot());
//			System.out.println("higher = "+tree.root.higherSquareRoot());
//			tree.insert(1606105,tree.root);
			for(int i = 0; i < N; i++){
//				System.out.println(keys[i]);
				tree.insert(keys[i],tree.root);
			}
			//tree.preOrder(tree.root, pw);
			tf = System.currentTimeMillis();
//			pw.println("Número insertados: "+tree.count(tree.root));
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
				vEBNode found = tree.search(keysToSearch[i],tree.root);
				if(found != null)
					count++;
			}
			tf = System.currentTimeMillis();
			pw.println("Tiempo Búsqueda: "+(tf-ti));
			pw.println("Coincidencias: "+count);
			pw.println("");
			pw.println("Experimento Tipo 2: Búsqueda con probabilidad");
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
					vEBNode found = tree.search(keysToSearch[i],tree.root);
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
					vEBNode found = tree.search(keysToSearch[i],tree.root);
					if(found != null)
						count++;
				}
				tf = System.currentTimeMillis();
				pw.println("cons = "+cons[i]);
				pw.println("Tiempo Búsqueda: "+(tf-ti));
				pw.println("Coincidencias: "+count);
				pw.println("");
		    }
			pw.close();
		}
	}
}
