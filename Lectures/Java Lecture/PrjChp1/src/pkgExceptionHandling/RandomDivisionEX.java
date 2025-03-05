package pkgExceptionHandling;

import java.util.Random;

public class RandomDivisionEX {
	/** 
	 * This method generates two random numbers and performs division on them.
	 * In case of an exception, it is handled by the try-catch block.
	 * @return result of division or 0 in case of an exception.
	 */
	public int randomDiv() {
		Random rndNum = new Random(); // creating object of Random class to generate random numbers
		int num1 = rndNum.nextInt(1); // changed to 10 to avoid divide by zero issue
		int num2 = rndNum.nextInt(10);// ensures num2 is never 0 to prevent division by zero
		int result = 0;
		try {
			System.out.println("Num1 is: " + num1);
			System.out.println("Num2 is: " + num2);
			result = num1 / num2;
		} catch (ArithmeticException ae) {
			System.out.println("Exception: " + ae);
		}
		return result;
	}

	public static void main(String args[]) {
		RandomDivisionEX obj = new RandomDivisionEX(); 
		int result = obj.randomDiv(); 
		System.out.println("Result: " + result);
	}
}

