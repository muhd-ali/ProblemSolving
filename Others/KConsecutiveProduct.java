import java.util.Arrays;

class KConsecutiveProduct {
    int[] solve(int[] nums, int k) {
        int size = nums.length - k + 1;
        if (size < 1) return null;
        int[] result = new int[nums.length - k + 1];
        for (int j = k - 1; j < nums.length; j++) {
            int prod = 1, lo = j - k + 1;
            for (int i = lo; i <= j; i++){
                prod *= nums[i];
            }
            result[lo] = prod;
        }
        return result;
    }

    public static void main(String[] args) {
        KConsecutiveProduct solver = new KConsecutiveProduct();
        int[] result = solver.solve(
            new int[]{1,2,3},
            1
        );
        System.out.println(Arrays.toString(result));
    }
}