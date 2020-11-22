package es.datastructur.synthesizer;

public interface BoundedQueue<T> extends Iterable<T> {
    /* return size of the buffer. */
    int capacity();

    /* return number of items currently in the buffer. */
    int fillCount();

    /* add item x to the end of the buffer. */
    void enqueue(T x);

    /* delete and return item x from the front of the buffer. */
    T dequeue();

    /* return item from the front of the buffer. (but do not delete) */
    T peek();

    /* is the buffer is empty? */
    default boolean isEmpty() {
        return fillCount() == 0;
    }

    /* is the buffer is full? */
    default boolean isFull() {
        return fillCount() == capacity();
    }
}
