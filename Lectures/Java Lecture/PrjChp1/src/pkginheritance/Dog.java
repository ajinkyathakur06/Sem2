package pkginheritance;
public class Dog extends Mammal{
	private double tailLength;
	private boolean isTrained;

	public boolean isIstrained() {
		return isTrained;
	}
	public void getTrained() {	
	}
	public static void main(String[] args) {
	}
	
	public void details() {
		System.out.println("Printing tail length from the constructor "+this.tailLength);
		System.out.println("Printing isTrained from the constructor "+this.isTrained);
		/* details("hello"); */
	}
	
	public Dog(double tailLength,boolean isTrained) {
		this.tailLength = tailLength;
		this.isTrained = isTrained;
		System.out.println("Printing tail length from the constructor "+this.tailLength);
		System.out.println("Printing isTrained from the constructor "+this.isTrained);
	}

	
}
