package pkg_coding_club;

import java.util.Arrays;
public class DemoImplementation {
	public static void main(String[] args) {
        // Example 1: Sum of Even and Odd Numbers
        int[] array = {11, 20, 35, 40, 53, 60};
        int[] sums = SumEvenOdd.sumEvenOdd(array);
        System.out.println("Array: " + Arrays.toString(array));
        System.out.println("Sum of Even Numbers: " + sums[0]);
        System.out.println("Sum of Odd Numbers: " + sums[1]);

        // Example 2: Second Largest Element
        int[] nums = {10, 5, 8, 20, 15};
        int secondLargestElement = SecondLargest.secondLargest(nums);
        System.out.println("Second Largest Element: " + secondLargestElement);

        // Example 3: Letter Positions
        String word = "IMCC";
        String positions = LetterPositions.letterPositions(word);
        System.out.println("Input Word: " + word);
        System.out.println("Output: " + positions);
    }
}