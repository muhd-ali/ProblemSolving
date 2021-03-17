import java.util.HashMap;

class Solution {
    int lengthForIndex(int index, char[] stringArray) {
        HashMap<Character, Boolean> dict = new HashMap<>();
        int count = 0;
        for (int i = index; i < stringArray.length; i++) {
            if (!dict.containsKey(stringArray[i])) {
                dict.put(stringArray[i], true);
                count++;
            } else {
                break;
            }
        }
        return count;
    }
    
    public int lengthOfLongestSubstring(String s) {
        char[] stringArray = s.toCharArray();
        int maxLengthSubstring = 0;
        for (int i = 0; i < stringArray.length ; i++) {
            int current = lengthForIndex(i, stringArray);
            if (current > maxLengthSubstring) {
                maxLengthSubstring = current;
            }
        }
        return maxLengthSubstring;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "pwwkew";
        int i = sol.lengthOfLongestSubstring(s);
        System.out.println(i);
    }
}