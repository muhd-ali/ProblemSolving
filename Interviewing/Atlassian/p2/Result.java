import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Result {

    public static List<String> findSchedules(int workHours, int dayHours, String pattern) {
        return findSchedules(workHours, dayHours, pattern.toCharArray(), 0);
    }

    public static List<String> findSchedules(int workHours, int dayHours, char[] pattern, int level) {
        List<String> list = new ArrayList<>();
        if (pattern[level] == '?') {
            for (int i = 0; i < dayHours + 1; i++) {
                final String charToAppend = String.valueOf(i);
                if (level + 1 < pattern.length) {
                    List<String> innerList = findSchedules(workHours - i, dayHours, pattern, level + 1);
                    list.addAll(
                        innerList
                        .stream()
                        .map(s -> charToAppend + s)
                        .collect(Collectors.toList())
                    );
                } else {
                    list.add(charToAppend);
                }
            }
        } else {
            int hours = pattern[level] - '0';
            final String charToAppend = String.valueOf(hours);
            if (level + 1 < pattern.length) {
                List<String> innerList = findSchedules(workHours - hours, dayHours, pattern, level + 1);
                list.addAll(
                    innerList
                    .stream()
                    .map(s -> charToAppend + s)
                    .collect(Collectors.toList())
                );
            } else {
                list.add(charToAppend);
            }
        }
        return list.stream().filter(s -> {
            char[] chrs = s.toCharArray();
            int sum = 0;
            for (char c : chrs) {
                sum += c - '0';
            }
            if (sum == workHours) {
                return true;
            } else {
                return false;
            }
        }).collect(Collectors.toList());
    }


    
    public static void main(String[] args) {
        List<String> list = findSchedules(48, 8, "???8??8");
        for (String l : list) {
            System.out.println(l);
        }
    }
}