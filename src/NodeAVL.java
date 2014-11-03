
public class NodeAVL {
	int key;
	NodeAVL left;
	NodeAVL right;
	int FE;
	NodeAVL parent;
	public NodeAVL(int key) {
		this.key=key;
		this.left=null;
		this.right=null;
		this.FE=0;
	}

	public static int numRandom(int min, int max){
		return ((int)(Math.random()*max-min+1));
	}
	public void insertar(int key){
		if(this.key<key){
			if(this.right==null){
				NodeAVL nuevito = new NodeAVL(key);
				nuevito.parent=this;
				this.right=nuevito;
				this.right.update();
			}
			else{
				this.right.insertar(key);
			}
		}
		else{
			if(this.left==null){
				NodeAVL nuevito = new NodeAVL(key);
				nuevito.parent=this;
				this.left=nuevito;
				this.left.update();
			}
			else{
				this.left.insertar(key);
			}
		}
	}
	public int calculateHeight(){
		// calcular altura
		int izq,der;
		if(this.left==null){
			izq=0;
		}
		else{
			izq= left.FE;
		}
		if(this.right==null){
			der=0;
		}
		else{
			der = right.FE;
		}
		return der-izq;
	}
	public void update(){
		// actualizar FE y tomar decisiones
		this.FE=calculateHeight();
		if(FE < -1){
			
		}
		else if(FE > 1){
			
		}
		else{
			
		}
		if(parent!=null){
			parent.update();
		}
		
		
	}
	public void rotarDer(){
		NodeAVL papa = this.parent;
		NodeAVL este = this;
		NodeAVL hijoIzq= this.left;
		NodeAVL hijoDer= this.right;
		// paso 1 - nodo nuevo de la derecha
		NodeAVL Nuevo = new NodeAVL(papa.key);
		Nuevo.right=this.parent.right;
		Nuevo.right.parent=Nuevo;
		Nuevo.left=hijoDer;
		Nuevo.left.parent=Nuevo;
		Nuevo.parent=papa;
		
		// paso 2 = correr los datos a la derecha
		
		papa.key=this.key;
		papa.left=this.left;
		papa.right=Nuevo;
		
		this.key=hijoIzq.key;
		this.left=hijoIzq.left;
		this.right=hijoIzq.right;
				
	}
	
	public void rotarIzq(){
		
	}
	
	public void main(String[] args){
		NodeAVL Arbol = new NodeAVL(0);
		Arbol.insertar(1);
		Arbol.insertar(2);
	}
}
