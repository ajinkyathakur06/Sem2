package pkginheritance;

public class ThisExample {

	int i,j;
	
	public ThisExample(){
		/*
		 * i=0; j=0;
		 */
		this(10);
	}
	
	public ThisExample(int val) {
		i=j=val;
		//this(val,val)
	}
	
	public ThisExample(int v1, int v2) {
		i=v1;
		j=v2;
	}
	
	public void showValues() {
		this.showValues("both");
	}
	
	public void showValues(String s) {
		System.out.println("i is "+i);
		System.out.println("j is "+j);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThisExample thsExObj = new ThisExample();
		ThisExample thsExObj1 = new ThisExample();
		ThisExample thsExObj2 = new ThisExample();
		thsExObj.showValues();
		thsExObj1.showValues();
		thsExObj2.showValues();
	}

}
