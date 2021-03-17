import java.util.Arrays;
import java.lang.Math;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
  static final int INT_MAX = 2147483647;
  static final int INT_MIN = -2147483648;
  static final String INT_MAX_STRING = Integer.toString(INT_MAX);
  static final String INT_MIN_STRING = Integer.toString(INT_MIN);
  final String pattern = "^(\\s*)((-|\\+)?)(0*)(\\d+)(.*)";

  public static void main(String[] args) {
    var input = "2147483649";
    var obj = new Solution();
    var output = obj.myAtoi(input);
    System.out.println(output);
  }

  static Boolean numberInStringIsLessThan(String number, String testNum) {
    // both are positive
    for (int i=0; i<number.length(); i++) {
      int number_dig = Character.getNumericValue(number.charAt(i));
      int testNum_dig = Character.getNumericValue(testNum.charAt(i));
      if (number_dig<testNum_dig) {
        return true;
      }
    }
    return false;
  }

  static Boolean numberInStringIsGreaterThan(String number, String testNum) {
    // both are negative
    return numberInStringIsLessThan(
      number.substring(0, number.length()),
      testNum.substring(0, testNum.length())
    );
  }

  String checkForBounds(String number) {
    if (number.charAt(0) == '-') {
      if ((number.length() > INT_MIN_STRING.length()) ||
        ((number.length() == INT_MIN_STRING.length()) &&
        !numberInStringIsGreaterThan(number, INT_MIN_STRING))) {
        return INT_MIN_STRING;
      }
    } else {
      if ((number.length() > INT_MAX_STRING.length()) ||
        ((number.length() == INT_MAX_STRING.length()) &&
        !numberInStringIsLessThan(number, INT_MAX_STRING))) {
          return INT_MAX_STRING;
        }
    }
    return number;
  }

  int convertToNumber(String number) {
    if (number.equals(INT_MIN_STRING)) {
      return INT_MIN;
    } else if (number.equals(INT_MAX_STRING)) {
      return INT_MAX;
    }
    int number_int = 0;
    int start = (number.charAt(0) == '-') ? 1 : 0;
    int length = number.length();
    for (int i=start; i<length; i++) {
      number_int += Character.getNumericValue(number.charAt(i)) * Math.pow(10, length-1-i);
    }
    return start==0 ? number_int : -number_int;
  }

  public int myAtoi(String str) {
    Pattern p = Pattern.compile(pattern);
    Matcher m = p.matcher(str);
    if (m.matches()) {
      int start = m.start(5);
      int end = m.end(5);
      String number = str.substring(start, end);
      if (m.group(2).equals("-")) {
        number = "-"+number;
      }
      number = this.checkForBounds(number);
      return this.convertToNumber(number);
    }
    return 0;
  }
}
