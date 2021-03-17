class Solution {
  public static void main(String[] args) {
    var obj = new Solution();
    var input = "the sky is     blue dream";
    var output = obj.reverseWords(input);
    System.out.println(output);
  }

  public String reverseWords(String s) {
    String [] words = s.trim().split("\\s+");
    String temp;
    int replacementIndex;
    for (int i=0; i<words.length/2; i++) {
      temp = words[i];
      replacementIndex = words.length - i - 1;
      words[i] = words[replacementIndex];
      words[replacementIndex] = temp;
    }
    return String.join(" ", words);
  }
}
