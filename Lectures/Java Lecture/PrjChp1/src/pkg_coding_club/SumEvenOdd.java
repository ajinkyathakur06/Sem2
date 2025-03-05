package pkg_coding_club;

public class SumEvenOdd {
    public static int[] sumEvenOdd(int[] arr) {
        int sumEven = 0;
        int sumOdd = 0;

        for (int num : arr) {
            if (num % 2 == 0) {
                sumEven += num;
            } else {
                sumOdd += num;
            }
        }

        return new int[]{sumEven, sumOdd};
    }
}