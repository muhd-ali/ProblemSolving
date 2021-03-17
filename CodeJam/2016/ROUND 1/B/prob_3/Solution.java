import java.util.*;

class Solution {
    public static String separater = "====================================================";
	private static final void myDebug(String str) {
		System.out.println("----------------------->(<**" + str + "**>)");
		System.out.println("\n");
	}

    public static int processList(List<Pair<String,String>> list) {
        int count1 = 0;
        for (int i=0; i<list.size(); i++) {
            for (int j=0; j<list.size(); j++) {
                if (i != j) {
                    if (list.get(i).getElementA().equals(list.get(j).getElementA()) && !list.get(i).getElementA().equals("-1")) {
                        count1++;
                        list.get(j).elt1 = "-1";
                    }
                }
            }
            list.get(i).elt1 = "-1";
        }

        int count2 = 0;
        for (int i=0; i<list.size(); i++) {
            for (int j=0; j<list.size(); j++) {
                if (i != j) {
                    if (list.get(i).getElementB().equals(list.get(j).getElementB()) && !list.get(i).getElementB().equals("-1")) {
                        count2++;
                        list.get(j).elt2 = "-1";
                    }
                }
            }
            list.get(i).elt2 = "-1";
        }

        return count1 * count2;
    }
}
