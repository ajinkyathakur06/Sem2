/**
 * 
 */
package pkgInterfaceExample;

/**
 * 
 */
public class OuterClass {
	int x,y;
	
	public OuterClass(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	public void printValues() {
		System.out.println("x is : "+x);
		System.out.println("y is : "+y);
	}
	
	public interface innerInterface {
        void innerM1();
        void printValues();
    }
} 
