import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j <= Math.min(i+k, nums.length - 1); j++) {
                long diff = nums[i];
                diff -= nums[j];
                diff = Math.abs(diff);
                if (diff <= t) {
                    return true;
                }
            }
        }
        return false;
	}

	public class Tuple<X, Y> {
		public final X x;
		public final Y y;

		public Tuple(X x, Y y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return String.format("(%d, %d)", this.x, this.y);
		}
	}
	
    public boolean containsNearbyAlmostDuplicateFunctional_help(LinkedList<Tuple<Integer, Integer>> nums, int k, int t) {
        System.out.print("Left with: [");
        for (Tuple<Integer, Integer> tuple : nums) {
            System.out.print(tuple + ", ");
        }
        System.out.println("]");
        if (nums.size() > 1) {
            long diff1 = nums.getLast().y, diff2 = nums.getFirst().y;
			if (
				(diff1 - diff2 <= t) &&
				(Math.abs(nums.getLast().x - nums.getFirst().x) <= k)
			) {
				// System.out.println(nums.get);
				return true;
			} else if (nums.size() > 2) {
				Tuple<Integer, Integer> first = nums.pollFirst();
                Tuple<Integer, Integer> last = nums.pollLast();
                diff1 -= first.y; diff2 = last.y - diff2;
				if (diff1 < diff2) {
	                nums.addFirst(first);
				} else {
					nums.addLast(last);	
                }
                
				return containsNearbyAlmostDuplicateFunctional_help(nums, k, t);
			}
		}
        return false;
    }

    public boolean containsNearbyAlmostDuplicateFunctional(int[] nums, int k, int t) {
        LinkedList<Tuple<Integer, Integer>> list = IntStream
			.range(0, nums.length)
			.parallel()
			.mapToObj(i -> new Tuple<>(i, nums[i]))
			.sorted((t1, t2) -> t1.y.compareTo(t2.y))
			.collect(Collectors.toCollection(() -> new LinkedList<Tuple<Integer, Integer>>()));

        // listOfTuples.forEach(tuple -> System.out.println(String.format("(%d, %d)", tuple.x, tuple.y)));
        return containsNearbyAlmostDuplicateFunctional_help(list, k, t);
    }
    
    public static void main(String[] args) {
        int[] nums;
        int k, t;
        boolean result;        
        
        nums = new int[] { 1, 2, 3, 1 };
        k = 3;
        t = 0;
        System.out.println("nums: " + Arrays.toString(nums) + ", k=" + k + ", t=" + t);
        result = new Solution().containsNearbyAlmostDuplicateFunctional(nums, k, t);
        System.out.println(result + "\n");

        nums = new int[] { 1, 0, 1, 1 };
        k = 1;
        t = 2;
        System.out.println("nums: " + Arrays.toString(nums) + ", k=" + k + ", t=" + t);
        result = new Solution().containsNearbyAlmostDuplicateFunctional(nums, k, t);
        System.out.println(result + "\n");

        nums = new int[] { 1, 5, 9, 1, 5, 9 };
        k = 2;
        t = 3;
        System.out.println("nums: " + Arrays.toString(nums) + ", k=" + k + ", t=" + t);
        result = new Solution().containsNearbyAlmostDuplicateFunctional(nums, k, t);
        System.out.println(result + "\n");

        nums = new int[] { -1, 2147483647 };
        k = 1;
        t = 2147483647;
        System.out.println("nums: " + Arrays.toString(nums) + ", k=" + k + ", t=" + t);
        result = new Solution().containsNearbyAlmostDuplicateFunctional(nums, k, t);
        System.out.println(result + "\n");

        nums = new int[] { 13, 7, 5, 3, 1 };
        k = 2;
        t = 2;
        System.out.println("nums: " + Arrays.toString(nums) + ", k=" + k + ", t=" + t);
        result = new Solution().containsNearbyAlmostDuplicateFunctional(nums, k, t);
        System.out.println(result + "\n");
    }
}