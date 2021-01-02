package bearmaps;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    @Override
    public void add(T item, double priority) {
        return;
    }

    @Override
    public boolean contains(T item) {
        return false;
    }

    @Override
    public T getSmallest() {
        return null;
    }

    @Override
    public T removeSmallest() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void changePriority(T item, double priority) {
        return;
    }
}
