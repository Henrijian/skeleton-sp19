import java.util.Iterator;

public class Test implements Iterator<Integer> {
    @Override
    public boolean hasNext () {
        return false;
    }

    @Override
    public Integer next () {
        return null;
    }
}
