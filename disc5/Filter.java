public class Filter<T> implements Predicate<T> {
    public boolean test(T x) {
        String xString = x.toString();
        return xString.length() > 3;
    }
}
