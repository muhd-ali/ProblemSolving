import java.util.Stack;

class Solution {
  public static void main(String[] args) {
    var dividend = 10;
    var divisor = 10;
    var obj = new Solution();
    var output = obj.minAddToMakeValid1("())))(())))((((");
    System.out.println(output);
  }

  public int minAddToMakeValid(String str) {
    Stack<Character> stack = new Stack<Character>();
    char[] charArray = str.toCharArray();
    for (Character c : charArray) {
      if (stack.isEmpty()) {
        stack.push(c);
      } else {
        if (c == ')') {
          if (stack.peek() == '(') {
            stack.pop();
            continue;
          }
        }
        stack.push(c);
      }
    }
    return stack.size();
  }
  public int minAddToMakeValid1(String str) { // without stack
    int balanceLeft = 0, balanceRight = 0;
    char[] charArray = str.toCharArray();
    for (char c : charArray) {
      if (c == '(') {
        balanceLeft++;
      } else {
        if (balanceLeft>0) {
          balanceLeft--;
        } else {
          balanceRight++;
        }
      }
    }
    return balanceLeft + balanceRight;
  }
}
