class Solution {
    private LinkedList<Character> minPossibleString(int A) {
        LinkedList<Character> list = new LinkedList<>();
        for(int i=0; i<A; i++) {
            list.add('a');
        }

        ListIterator<Character> iter = list.listIterator();
        while(iter.hasNext()) {
            iter.next();
            if (iter.hasNext()) {
                iter.next();
                if (iter.hasNext()) {
                    iter.add('b');
                }
            }
        }

        return list;
    }
    
    private int minPossibleStringUsedBs(int A) {
        return (A - 1) / 2;
    }
    
    private int addOneBIfNeeded(ListIterator<Character> iter, int remainingBs) {
        if (remainingBs > 0) {
            iter.add('b');
        }
        return remainingBs - 1;
    }
    
    private void fillRemainingBs(LinkedList<Character> list, int remainingBs) {
        ListIterator<Character> iter = list.listIterator();
        Character curr, next = null;
        while(iter.hasNext() && remainingBs > 0) {
            if (next == null) {
                remainingBs = addOneBIfNeeded(iter, remainingBs);
                remainingBs = addOneBIfNeeded(iter, remainingBs);
            }
            curr = iter.next();
            if (iter.hasNext()) {
                next = iter.next();
                iter.previous();
                if (next == 'a') {
                    if (curr == 'b') {
                        remainingBs = addOneBIfNeeded(iter, remainingBs);
                    } else {
                        remainingBs = addOneBIfNeeded(iter, remainingBs);
                        remainingBs = addOneBIfNeeded(iter, remainingBs);
                    }
                }
            } else {
                if (curr == 'a') {
                    remainingBs = addOneBIfNeeded(iter, remainingBs);
                    remainingBs = addOneBIfNeeded(iter, remainingBs);
                } else {
                    remainingBs = addOneBIfNeeded(iter, remainingBs);
                }
            }
        }
    }
    
    public String strWithout3a3b(int A, int B) {
        LinkedList<Character> list = minPossibleString(A);
        fillRemainingBs(list, B - minPossibleStringUsedBs(A));
        return list.stream().map(String::valueOf).collect(Collectors.joining());
    }
}