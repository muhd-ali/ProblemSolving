class Solution {
    int distinctElementsCount(int size, int[] elements) {
        int[] counted = new int[size];
        int count, flag;
        counted[0] = elements[0];
        count = 1; // one element is counted
        for (int i = 0; i < size; i++) {
            flag = 0;
            for (int j = 0; j < count; j++) {
                if (elements[i] == counted[j]) {
                    flag = 1;
                }
            }
            if (flag == 1) {
                count++;
                counted[count - 1] = elements[i];
            }
        }
        return count;
    }
}