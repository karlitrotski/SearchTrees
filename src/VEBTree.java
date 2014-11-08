	
public class VEBTree {

	private vEBNode root;
	
	public vEBNode search (int x, vEBNode r){
		
		// implementar buscar (?)
		
		if(x>=r.min && x<=r.max){
			//buscar en los hijos
		}
		
		return null;
		
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
		int i = (int) Math.floor(x/raiz);
		// al parecer insertaremos el modulo desde el floor. juej
		insert(x%raiz,r.cluster[i]);
		if(r.cluster[i].min == r.cluster[i].max){
			insert(i,r.summary);
		}
			// más cosas
		
		return r;
	}
	
	static public void main(String[] args){
		VEBTree tree = new VEBTree();
		int M = (int) Math.pow(2, 20);
		tree.root=new vEBNode(M);
		
		tree.insert(0, tree.root);
		tree.insert(10, tree.root);
		tree.insert(100, tree.root);
		tree.insert(1000, tree.root);
		tree.insert(10000, tree.root);
		tree.insert(100000, tree.root);
		tree.insert(1000000, tree.root);
		// tirate un breakpoint en la linea anterior y debugea, ahi vai a ver los cosos juej
		
		
	}
}
