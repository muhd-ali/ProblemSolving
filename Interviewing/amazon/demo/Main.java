import java.util.Arrays;

class Main {
    private static void testP1() {
        int[] input, result;
        input = new int[] { 1, 0, 0, 0, 0, 1, 0, 0 };
        System.out.println("input: " + Arrays.toString(input));
        result = Colony.cellCompete(input, 1);
        System.out.println("result: " + Arrays.toString(result) + "\n");
        
        input = new int[] { 1, 1, 1, 0, 1, 1, 1, 1 };
        System.out.println("input: " + Arrays.toString(input));
        result = Colony.cellCompete(input, 2);
        System.out.println("result: " + Arrays.toString(result) + "\n");
    }

    private static void testP2() {
        int[] input;
        int result;

        input = new int[] { 2, 3, 4, 5, 6 };
        System.out.println("input: " + Arrays.toString(input));
        result = GCD.generalizedGCD(input);
        System.out.println("result: " + result + "\n");

        input = new int[] { 2, 4, 6, 8, 10 };
        System.out.println("input: " + Arrays.toString(input));
        result = GCD.generalizedGCD(input);
        System.out.println("result: " + result + "\n");

        input = new int[] { 9, 18 };
        System.out.println("input: " + Arrays.toString(input));
        result = GCD.generalizedGCD(input);
        System.out.println("result: " + result + "\n");
    }
    
    public static void main(String[] args) {
        testP2();
    }
}