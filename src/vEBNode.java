
public class vEBNode {
	int min;
	int max;
	int U;
	vEBNode summary;
	vEBNode[] cluster;
	public vEBNode(int U){
		min = higherSquareRoot();
		max = -1;	
		this.U = U;
		
		/* Allocate the summary and cluster children. */
		initializeChildren(U);
	}
	public void initializeChildren(int U){
		if(U <= 2){ // base size
			summary = null;
			cluster = null;
		}
		else{
			int childUnivereSize = higherSquareRoot();
			summary = new vEBNode(childUnivereSize);
			cluster = new vEBNode[childUnivereSize];
			for(int i = 0; i < childUnivereSize; i++){
				cluster[i] = new vEBNode(childUnivereSize);
			}
		}
	}
	
	/*
	 * Returns the value of the most significant bits of x.
	 */
	public int higherSquareRoot(){
		return (int)Math.pow(2, Math.ceil((Math.log10(U) / Math.log10(2)) / 2));
	}
}
