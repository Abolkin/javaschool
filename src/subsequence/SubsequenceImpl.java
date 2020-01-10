package subsequence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SubsequenceImpl implements Subsequence {

    @Override
    public boolean find(List<String> first, List<String> second) {
        Iterator<String> firstIterator = first.iterator();
        Iterator<String> secondIterator = second.iterator();
        List<String> third = new ArrayList<>();
        String fNext = firstIterator.next();
        String sNext = secondIterator.next();
        while (firstIterator.hasNext()) {
            while (secondIterator.hasNext()) {
                if (sNext.equals(fNext)) {
                    fNext = firstIterator.next();
                } else {
                    sNext = secondIterator.next();
                }
                if (sNext.equals(fNext)) {
                    third.add(fNext);
                }
            }
        }
        return third.equals(first);
    }
}
