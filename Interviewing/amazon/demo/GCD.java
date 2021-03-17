import java.util.Arrays;

// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
// CLASS BEGINS, THIS CLASS IS REQUIRED
public class GCD {
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public static int generalizedGCD(int arr[]) {
        int minArrVal = Arrays.stream(arr).min().getAsInt();
        
        int gcd = 1;
        for (int i = 2; i <= minArrVal; i++) {
            if (isArrayDivisibleBy(arr, i)) {
                gcd = i;
            }
        }
        return gcd;
    }
    // METHOD SIGNATURE ENDS

    private static boolean isArrayDivisibleBy(int[] arr, int i) {
        for (int elt : arr) {
            if (elt % i != 0) {
                return false;
            }
        }
        return true;
    }
}