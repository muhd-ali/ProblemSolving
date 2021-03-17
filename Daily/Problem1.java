/**
 * Problem1
 * Implement regular expression matching with the following special characters:
    •	. (period) which matches any single character
    •	* (asterisk) which matches zero or more of the preceding element
 * That is, implement a function that takes in a string and a valid regular expression and         returns whether or not the string matches the regular expression.
 * For example, given the regular expression "ra." and the string "ray", your function should return true. The same regular expression on the string "raymond" should return false.
 * Given the regular expression ".*at" and the string "chat", your function should return true. The same regular expression on the string "chats" should return false.
 */

public class Problem1 {

    public static void main(String[] args) {
        String inputStr, regex;
        boolean output;
        boolean areAllTestsPassed = true;

        regex = "r*a";
        System.out.println(String.format("Testing regex '%s'", regex));
        
        inputStr = "ra";
        output = match(regex, inputStr);
        if (output != true) {
            areAllTestsPassed = false;
            System.out.println(String.format("'%s' = (%b, true)", inputStr, output));
        }

        inputStr = "a";
        output = match(regex, inputStr);
        if (output != true) {
            areAllTestsPassed = false;
            System.out.println(String.format("'%s' = (%b, true)", inputStr, output));
        }

        inputStr = "rrra";
        output = match(regex, inputStr);
        if (output != true) {
            areAllTestsPassed = false;
            System.out.println(String.format("'%s' = (%b, true)", inputStr, output));
        }

        inputStr = "raa";
        output = match(regex, inputStr);
        if (output != false) {
            areAllTestsPassed = false;
            System.out.println(String.format("'%s' = (%b, false)", inputStr, output));
        }

        inputStr = "aa";
        output = match(regex, inputStr);
        if (output != false) {
            areAllTestsPassed = false;
            System.out.println(String.format("'%s' = (%b, false)", inputStr, output));
        }

        inputStr = "rrraa";
        output = match(regex, inputStr);
        if (output != false) {
            areAllTestsPassed = false;
            System.out.println(String.format("'%s' = (%b, false)", inputStr, output));
        }

        regex = "r*a*";
        System.out.println(String.format("Testing regex '%s'", regex));
        
        inputStr = "ra";
        output = match(regex, inputStr);
        if (output != true) {
            areAllTestsPassed = false;
            System.out.println(String.format("'%s' = (%b, true)", inputStr, output));
        }

        inputStr = "a";
        output = match(regex, inputStr);
        if (output != true) {
            areAllTestsPassed = false;
            System.out.println(String.format("'%s' = (%b, true)", inputStr, output));
        }

        inputStr = "rrra";
        output = match(regex, inputStr);
        if (output != true) {
            areAllTestsPassed = false;
            System.out.println(String.format("'%s' = (%b, true)", inputStr, output));
        }

        inputStr = "raa";
        output = match(regex, inputStr);
        if (output != true) {
            areAllTestsPassed = false;
            System.out.println(String.format("'%s' = (%b, true)", inputStr, output));
        }

        inputStr = "aa";
        output = match(regex, inputStr);
        if (output != true) {
            areAllTestsPassed = false;
            System.out.println(String.format("'%s' = (%b, true)", inputStr, output));
        }

        inputStr = "rrraa";
        output = match(regex, inputStr);
        if (output != true) {
            areAllTestsPassed = false;
            System.out.println(String.format("'%s' = (%b, true)", inputStr, output));
        }
        
        inputStr = "raaz";
        output = match(regex, inputStr);
        if (output != false) {
            areAllTestsPassed = false;
            System.out.println(String.format("'%s' = (%b, false)", inputStr, output));
        }

        inputStr = "aaz";
        output = match(regex, inputStr);
        if (output != false) {
            areAllTestsPassed = false;
            System.out.println(String.format("'%s' = (%b, false)", inputStr, output));
        }

        inputStr = "rrraaz";
        output = match(regex, inputStr);
        if (output != false) {
            areAllTestsPassed = false;
            System.out.println(String.format("'%s' = (%b, false)", inputStr, output));
        }

        if (areAllTestsPassed) {
            System.out.println("all tests passed!");
        }
    }

    static boolean match(String regex, String str) {
        return match_helper(0, 0, regex, str);
    }
    
    static boolean match_helper(int idx_r, int idx_s, String regex, String str) {
        if (idx_r >= regex.length() || idx_s >= str.length()) {
            if (idx_r >= regex.length() && idx_s >= str.length()) {
                return true;
            } else {
                return false;
            }
        }
        
        char currChar_r = regex.charAt(idx_r);
        boolean isFollowedByStar = false;
        if (idx_r + 1 < regex.length() && regex.charAt(idx_r + 1) == '*') {
            isFollowedByStar = true;
        }

        char currChar_s = str.charAt(idx_s);

        if (isFollowedByStar) {
            while(idx_s < str.length() && doCharactersMatch(currChar_r, str.charAt(idx_s))) {
                idx_s++;
            }
            idx_r += 2;
        } else {
            if (doCharactersMatch(currChar_r, currChar_s)) {
                idx_r++;
                idx_s++;
            } else {
                return false;
            }
        }

        return match_helper(idx_r, idx_s, regex, str);
    }

    static boolean doCharactersMatch(char chr_r, char chr_s) {
        return chr_r == '.' || chr_r == chr_s;
    }
    
}