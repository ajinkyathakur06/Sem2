package polymorphism;

public class Printer {
	//private String prtNM;
	//private String prtID;
	//private double price;
	
	public void printPaper() {
		System.out.println("This will print black and white copies");
	}

	
	
	public void printPaper(boolean ch) {
		System.out.println("This will print color prints");
	}
}
