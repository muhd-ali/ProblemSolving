import java.util.ArrayList;
import java.util.List;

class Result {
    static boolean areAllPresent(int m, String attendence) {
        char[] allPresent = new char[m];
        for (int i = 0; i < m; i++) {
            allPresent[i] = 'Y';
        }
        String allPresentString = new String(allPresent);
        return allPresentString.equals(attendence);
    }
    
    public static int maxStreak(int m, List<String> data) {
        int maxStreakCount = 0, countStartIndex = 0;
        boolean isCountStarted = false;
        for (int i = 0; i < data.size(); i++) {
            if (areAllPresent(m, data.get(i))) {
                if (!isCountStarted) {
                    countStartIndex = i;
                    isCountStarted = true;
                }
            } else {
                if (isCountStarted) {
                    int days = (i-1) - countStartIndex + 1;
                    if (days > maxStreakCount) {
                        maxStreakCount = days;
                    }
                    isCountStarted = false;
                }
            }        
        }
        if (isCountStarted) {
            int days = (data.size()-1) - countStartIndex + 1;
            if (days > maxStreakCount) {
                maxStreakCount = days;
            }
            isCountStarted = false;
        }
        return maxStreakCount;
    }
    
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("YNYY");
        list.add("YYYY");
        list.add("YYYY");
        list.add("YYNY");
        list.add("NYYN");
        list.add("YYYY");
        list.add("YYYY");
        list.add("YYYY");
        list.add("YYYY");
        list.add("YYYY");
        list.add("YYYY");
        int res = maxStreak(4, list);
        System.out.println("="+res);
    }
}