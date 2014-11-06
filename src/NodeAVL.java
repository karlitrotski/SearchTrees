public class NodeAVL {
	int key;
	NodeAVL left;
	NodeAVL right;
	int FE;
	int h;
	NodeAVL parent;
	public NodeAVL(int key, NodeAVL parent){
		this.parent=parent;
		this.key=key;
		this.left=null;
		this.right=null;
		this.FE=0;
		this.h=0;
	}
	public NodeAVL(int key) {
		this.key=key;
		this.left=null;
		this.right=null;
		this.FE=0;
		this.h=0;
	}
	
	public void update(){
		int r=0;
		int l=0;
		if(this.left!=null){
			this.left.update();
			l=this.left.FE;
		}
		if(this.right!=null){
			this.right.update();
			r=this.right.FE;
		}
		this.FE=r-l;
	}
}