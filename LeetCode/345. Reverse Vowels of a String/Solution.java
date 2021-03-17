public class Solution {
    public static String reverseVowels(String s) {
        int i = 0, j = s.length() - 1;
        boolean foundFromLeft = false, foundFromRight = false;
        char temp;

        char[] chrs = s.toCharArray();
        while (i < j) {
            if (isVowel(chrs[i]))
                foundFromLeft = true;
            else
                i++;

            if (isVowel(chrs[j]))
                foundFromRight = true;
            else
                j--;

            if (foundFromLeft && foundFromRight) {
                temp = chrs[i];
                chrs[i] = chrs[j];
                chrs[j] = temp;

                foundFromLeft = false;
                foundFromRight = false;
                i++;
                j--;
            }
        }

        return new String(chrs);
    }

    public static boolean isVowel(char chr) {
        char[] vowels = "aeiouAEIOU".toCharArray();
        for (char elt : vowels) {
            if (chr == elt)
                return true;
        }
        return false;
    }
}
