package pkgInterfaceExample;

public class DemoInnerInterface {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OuterClass objout = new OuterClass(1,2);
		objout.printValues();
		
		OuterB outobj = new OuterB(10,20);
		outobj.printValues();
		outobj.innerM1();
		
		//OuterClass.innerInterface innObj = new OuterClass(8,8);
		
		OuterClass.innerInterface innObj1 = new OuterB(8,8);
		//innObj.innerM1();
		innObj1.printValues();
		
	}

}
