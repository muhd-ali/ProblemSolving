import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class Tracker {
  public static void main(String[] args) {
    List<Integer> list = List.of(1,3);

    var tracker = new Tracker();
    System.out.println(tracker.nextServerNumber(list));
    System.out.println(tracker.allocate("abc"));
    System.out.println(tracker.allocate("abc"));
    System.out.println(tracker.allocate("abc"));
    System.out.println(tracker.allocate("abc"));
    tracker.deallocate("abc1");
    System.out.println(tracker.allocate("abc"));
  }

  public Integer nextServerNumber(List<Integer> list) {
    HashMap<Integer, Boolean> usedIndeces = new HashMap<Integer, Boolean>();
    int max = 0;
    for (Integer i : list) {
      usedIndeces.put(i, true);
      if (i > max) {
        max = i;
      }
    }

    Optional<Boolean> optUsed;
    for (int i = 1; i <= max; i++) {
      optUsed = Optional.ofNullable(usedIndeces.get(i));
      if (!optUsed.isPresent()) {
        return i;
      }
    }
    return max+1;
  }

  HashMap<String, List<Integer>> store = new HashMap<String, List<Integer>>();

  public String allocate(String hostType) {
    Optional<List<Integer>> optUsed = Optional.ofNullable(store.get(hostType));
    int id;
    if (optUsed.isPresent()) {
      id = this.nextServerNumber(optUsed.get());
    } else {
      ArrayList<Integer> list = new ArrayList<>();
      id = 1;
      store.put(hostType, list);
    }
    store.get(hostType).add(id);
    return hostType + id;
  }


  public void deallocate(String hostName) {
    Pattern p = Pattern.compile("([^\\d]*)(\\d+)$");
    Matcher m = p.matcher(hostName);
    if (m.matches()) {
      String hostType = m.group(1);
      String strID = m.group(2);
      int assignedID = Integer.parseInt(strID);
      Optional<List<Integer>> optUsed = Optional.ofNullable(store.get(hostType));
      if (optUsed.isPresent()) {
        optUsed.get().removeIf(s -> s.equals(assignedID));
      }
    }
  }
}
