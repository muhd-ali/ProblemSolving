import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    class IndexPair {
        int qArrayIndex, otherArrayIndex;

        IndexPair(int qArrayIndex, int otherArrayIndex) {
            this.qArrayIndex = qArrayIndex;
            this.otherArrayIndex = otherArrayIndex;
        }

        @Override
        public String toString() {
            return String.format("i=%d, j=%d", qArrayIndex, otherArrayIndex);
        }

        String sumString(int[] qArray, int[] otherArray) {
            int num1 = qArray[qArrayIndex];
            int num2 = otherArray[otherArrayIndex];
            int sum = num1 + num2;
            return String.format("1:%d + 2:%d = %d", num1, num2, sum);
        }

        int[] orderedPair(int[] arr1, int[] arr2, int[] qArray) {
            int first = qArrayIndex, second = otherArrayIndex;
            if (arr1 != qArray) {
                first = otherArrayIndex;
                second = qArrayIndex;
            }
            return new int[]{arr1[first], arr2[second]};
        }
    }

    PriorityQueue<IndexPair> createMinHeap(int[] qArray, int[] otherArray) {
        if (qArray.length == 0) {
            return null;
        }
        PriorityQueue<IndexPair> queue = new PriorityQueue<>(
            qArray.length,
            Comparator.comparing(
                e -> {
                    return qArray[e.qArrayIndex] + otherArray[e.otherArrayIndex];
                }
            )
        );
        for (int i = 0; i < qArray.length; i++) {
            queue.add(new IndexPair(i, 0));
        }
        return queue;
    }

    List<int[]> kSmallestPairs(int[] arr1, int[] arr2, int k) {
        int[] qArray = arr1.length < arr2.length ? arr1 : arr2;
        int[] otherArray = qArray == arr1 ? arr2 : arr1;
        List<int[]> list = new ArrayList<>();
        PriorityQueue<IndexPair> queue = createMinHeap(qArray, otherArray);
        if (queue == null) {
            return list;
        }
        for (int i = 0; i < k; i++) {
            IndexPair min = queue.poll();
            if (min == null) {
                break;
            }
            if (min.otherArrayIndex < otherArray.length - 1) {
                queue.add(
                    new IndexPair(min.qArrayIndex, min.otherArrayIndex + 1)
                );
            }
            list.add(min.orderedPair(arr1, arr2, qArray));
        }

        // for (int[] p : list) {
        //     System.out.println(Arrays.toString(p));
        // }
        // System.err.println("");
        
        return list;
    }


    public static void main(String[] args) {
        Solution k = new Solution();
        k.kSmallestPairs(
            new int[] {1, 7, 11},
            new int[] {2, 4, 6},
            3
        );

        k.kSmallestPairs(
            new int[] {1,1,2},
            new int[] {1,2,3},
            2
        );

        k.kSmallestPairs(
            new int[] {1,2},
            new int[] {3},
            3
        );

        k.kSmallestPairs(
            new int[] {},
            new int[] {},
            3
        );
    }
}