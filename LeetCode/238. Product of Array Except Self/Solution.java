import java.util.Arrays;

class Solution {
  public static void main(String[] args) {
    System.out.println("coljava ");
    var input = new int[] {1,2,3,4};
    var obj = new Solution();
    var output = obj.productExceptSelf(input);
    System.out.println(Arrays.toString(output));
  }

  public int[] productExceptSelf(int[] nums) {
    int[] prods = new int[nums.length];
    for (int i=0; i<prods.length; i++) {
      prods[i] = 1;
    }

    int prod = 1;
    for (int i=0; i<prods.length; i++) {
      prods[i] *= prod;
      prod *= nums[i];
    }

    prod = 1;
    for (int i=prods.length-1; i>=0; i--) {
      prods[i] *= prod;
      prod *= nums[i];
    }
    return prods;
  }
}
