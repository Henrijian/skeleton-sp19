import java.util.Iterator;
public class OHIterator implements Iterator<OHRequest> {
    OHRequest curr;

    public OHIterator(OHRequest queue) {
        curr = queue;
    }

    public boolean isGood(String description) {
        return description != null && description.length() > 5;
    }

    @Override
    public boolean hasNext() {
        while (curr != null && !isGood(curr.description)) {
            curr = curr.next;
        }
        return curr != null;
    }
    @Override
    public OHRequest next() {
        OHRequest returnedOHRequest = curr;
        curr = curr.next;
        return returnedOHRequest;
    }
}
