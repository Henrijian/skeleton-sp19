package synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Array for storing the buffer data. */
    private T[] rb;
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) (new Object[capacity]);
        first = 0;
        last = 0;
        fillCount = 0;
    }

    /** return size of the buffer. */
    @Override
    public int capacity() {
        return rb.length;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last += 1;
        if (last >= rb.length) {
            last = 0;
        }
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T dequeuedItem = rb[first];
        first += 1;
        if (first >= rb.length) {
            first = 0;
        }
        fillCount -= 1;
        return dequeuedItem;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T dequeuedItem = rb[first];
        return dequeuedItem;
    }

    private class MyIterator implements Iterator<T> {
        private int curr;

        public MyIterator() {
            curr = first;
        }

        @Override
        public boolean hasNext() {
            return curr != last;
        }

        @Override
        public T next() {
            T currItem = rb[curr];
            curr += 1;
            if (curr >= rb.length) {
                curr = 0;
            }
            return currItem;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o.getClass() == this.getClass()) {
            return false;
        }
        ArrayRingBuffer<T> other = (ArrayRingBuffer<T>) o;
        if (other.fillCount() != this.fillCount()) {
            return false;
        }
        Iterator<T> otherIterator = other.iterator();
        Iterator<T> myIterator = this.iterator();
        for (int i = 0; i < this.fillCount(); i++) {
            T otherItem = otherIterator.next();
            T myItem = myIterator.next();
            if (!otherItem.equals(myItem)) {
                return false;
            }
        }
        return true;
    }
}