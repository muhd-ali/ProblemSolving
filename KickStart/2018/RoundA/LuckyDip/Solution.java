import java.util.ArrayList;
import java.util.HashMap;

public class Solution {
    HashMap<Integer, Integer> createValuesCountDict(int[] itemValues) {
        HashMap<Integer, Integer> valuesCountDict = new HashMap<>();
        for (int value : itemValues) {
            Integer count = valuesCountDict.get(value), newCount = 1;
            if (count != null) {
                newCount += 1;
            }
            valuesCountDict.put(value, newCount);
        }
        return valuesCountDict;
    }

    public class ValueCountPair {
        Integer value;
        Integer count;
        ValueCountPair(int value, int count) {
            this.value = value;
            this.count = count;
        }
        
        @Override
        public String toString() {
            return String.format("(value: %d, count: %d)", this.value, this.count);
        }
    }

    ArrayList<ValueCountPair> createValuesCountSortedPairs(HashMap<Integer, Integer> valuesCountDict) {
        ArrayList<ValueCountPair> list = new ArrayList<>();
        for (int key : valuesCountDict.keySet()) {
            list.add(new ValueCountPair(key, valuesCountDict.get(key)));
        }
        list.sort((e1, e2) -> e1.value.compareTo(e2.value));
        return list;
    }

    public class Distribution {
        ArrayList<ValueCountPair> valuesCountSortedPairs;
        int totalCount, uniqueMedianIndex;
        double expectedForGoodChoice;

        Distribution(ArrayList<ValueCountPair> valuesCountSortedPairs) {
            this.valuesCountSortedPairs = valuesCountSortedPairs;
            processData();
        }

        @Override
        public String toString() {
            String str = "[";
            for (int i = 0; i < valuesCountSortedPairs.size(); i++) {
                str += valuesCountSortedPairs.get(i).toString() + ", ";
            }
            str += "]";
            return str;
        }

        private void setTotalCount() {
            totalCount = 0;
            for (ValueCountPair pair : valuesCountSortedPairs) {
                totalCount += pair.count;
            }
        }

        private void setUniqueMedianIndex() {
            uniqueMedianIndex = valuesCountSortedPairs.size() / 2;
        }
        
        private void processData() {
            setTotalCount();
            setUniqueMedianIndex();
            setExpectedForGoodChoice();

        }

        private void setExpectedForGoodChoice() {
            expectedForGoodChoice = averageForRange(getGoodPartitionIndex());
        }

        public int uniqueCount() {
            return valuesCountSortedPairs.size();
        }

        double averageForRange(int start, int end) {
            int startFixed = Math.min((Math.max(0, start)), valuesCountSortedPairs.size() - 1);
            int endFixed = Math.max((Math.min(valuesCountSortedPairs.size() - 1, end)), 0);

            double sum = 0;
            ValueCountPair pair;
            for (int i = startFixed; i <= endFixed; i++) {
                pair = valuesCountSortedPairs.get(i);
                sum += pair.value * pair.count;
            }
            return sum / totalCount;
        }

        double averageForRange(int start) {
            return averageForRange(start, valuesCountSortedPairs.size() - 1);
        }

        int getGoodPartitionIndex() {
            return uniqueMedianIndex;
        }

        double getTotalNumberOfBadChoices() {
            double count = 0;
            for (int i = 0; i < getGoodPartitionIndex(); i++) {
                count += valuesCountSortedPairs.get(i).count;
            }
            return count;
        }
    }

    Distribution createDistribution(int[] itemValues) {
        HashMap<Integer, Integer> valuesCountDict = createValuesCountDict(itemValues);
        ArrayList<ValueCountPair> valuesCountSortedPairs = createValuesCountSortedPairs(valuesCountDict);
        Distribution dist = new Distribution(valuesCountSortedPairs);
        return dist;
    }

    double solveCase_rec(Distribution dist, int numberOfReplacements) {
        if (numberOfReplacements == 0) {
            double avg = dist.averageForRange(0);
            System.out.println("avg: " + avg);
            return avg;
        } else {
            double coeff = dist.getTotalNumberOfBadChoices() / dist.totalCount;
            double expectedForBadChoice = coeff * solveCase_rec(dist, numberOfReplacements - 1);
            System.out.println("coeff: " + coeff);
            System.out.println("expectedForBadChoice: " + expectedForBadChoice);
            return dist.expectedForGoodChoice + expectedForBadChoice;
        }
    }

    double solveCase(int[] itemValues, int maxNumberOfReplacements) {
        Distribution dist = createDistribution(itemValues);
        return solveCase_rec(dist, maxNumberOfReplacements);
    }

    public static void main(String[] args) {
        // Scanner scanIn = new Scanner(System.in);
        Solution sol = new Solution();
        double result = sol.solveCase(new int[] {1, 2, 3, 4}, 0);
        System.out.println(result);
    }    
}