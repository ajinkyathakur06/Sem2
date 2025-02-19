package polymorphism;

import java.util.Scanner;

public class PolymorphismDemo {

	public static void main(String[] args) {
		String chc;
		Printer printObj = new Printer();
		System.out.println("Enter YES if you want a color print else enter NO");
		Scanner sc = new Scanner(System.in);
		chc = sc.next();
		if(chc.equalsIgnoreCase("YES")) {
			printObj.printPaper(true);
		}
		else if(chc.equalsIgnoreCase("NO")){
			printObj.printPaper();
		}
		else 
			System.out.println("Input does not match");
	}

}
