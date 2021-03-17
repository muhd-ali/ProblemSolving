import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*

s1 = "abcdaf"
s2 = "zbcdf"

lcm(a,b) = max(lcm(a[1:], b), lcm(a, b[1:]))

*/


    
class Solution {
    static List<String> maxStreak(int work_hours, int day_hours, String schedule) {
        List<String> schedules = new ArrayList<>();
        int sum = sum(schedule);
        int hoursToComplete = work_hours - sum;

        return schedules;
    }
    
    public static void main(String[] args) {
        String[] list = {
            "YYY",
            "YYY",
            "YYY",
            "NYY",
            "YYY",
            "YYY",
            "YYY",
            "YYY",
            "YYY",
            "YYY",
            "YYY",
            "YYY",
            "NYY",
        };
        List<String> data = Arrays.asList(list);
        int res = maxStreak(3, data);
        System.out.println(res);
    }
}