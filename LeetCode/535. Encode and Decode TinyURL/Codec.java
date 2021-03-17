import java.util.HashMap;

public class Codec {
  HashMap<String, String> dict = new HashMap<String, String>();

  public static void main(String[] args) {
    var obj = new Codec();
    System.out.println(obj.encode("https://leetcode.com/problems/design-tinyurl"));
    System.out.println(obj.decode("-1163677885"));
  }



  public String encode(String longUrl) {
    String hash = Integer.toString(longUrl.hashCode());
    dict.put(hash, longUrl);
    return hash;
  }

  public String decode(String shortUrl) {
    return dict.get(shortUrl);
  }
}
