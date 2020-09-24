public class ArrayDeque<T> {
    private T[] array;
    private int size;
    private final int EXPAND_FACTOR = 2;
    private final double USAGE_FACTOR = 0.25;
    private final int SHRINK_FACTOR = 2;
    private final int MINIMUM_ARRAY_SIZE = 8;

    /**
     * invariant:
     * 1. array holds the array of added items.
     * 2. size equals the number of item added in list.
     * 3. the last item is at size - 1 position in array.
     * 4. the first item is at 0 position in array.
     */
    public ArrayDeque() {
        array = (T[]) new Object[8];
        size = 0;
    }

//    public ArrayDeque(T... items) {
//        this();
//        while (array.length < items.length) {
//            array = (T[]) new Object[array.length * EXPAND_FACTOR];
//        }
//        for (int i = 0; i < items.length; i++) {
//            array[i] = items[i];
//            size++;
//        }
//    }

    /** Creates a deep copy of other. */
//    public ArrayDeque(ArrayDeque other) {
//        this();
//        for (int i = 0; i < other.size(); i++) {
//            addLast((T) other.get(i));
//        }
//    }

    /** Return the number of added items in deque. */
    public int size() {
        return size;
    }

    /** Get the ith item from deque, if the item does not exist, return null. */
    public T get(int i) {
        if (i < 0 || size <= i) {
            return null;
        }
        return array[i];
    }

    /** Check if the items in deque are equal to compare. */
    private boolean equals(ArrayDeque<T> compare) {
        if (size != compare.size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            T myItem = this.get(i);
            T compareItem = compare.get(i);
            if (!myItem.equals(compareItem)) {
                return false;
            }
        }
        return true;
    }

    /** Resize array to the given length, if length is negative value,
     * resize array to empty array. */
    private void resize(int length, int oldPos, int newPos) {
        if (length < 0) {
            length = 0;
        }
        T[] temp = (T[]) new Object[length];
        System.arraycopy(array, oldPos, temp, newPos, size);
        array = temp;
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        if (size == array.length) {
            resize(size * EXPAND_FACTOR, 0, 1);
        }
        array[0] = item;
        size++;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        if (size == array.length) {
            resize(size * EXPAND_FACTOR, 0, 0);
        }
        array[size] = item;
        size++;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line. */
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            if (i != size - 1) {
                System.out.printf("%s ", array[i]);
            } else {
                System.out.printf("%s", array[i]);
            }
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T removedItem = array[0];
        System.arraycopy(array, 1, array, 0, size - 1);
        size--;
        double usage = (double) size / (double) array.length;
        if (usage < USAGE_FACTOR && array.length > MINIMUM_ARRAY_SIZE) {
            resize(array.length / SHRINK_FACTOR, 0, 0);
        }
        return removedItem;
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T removedItem = array[size - 1];
        array[size - 1] = null;
        size--;
        double usage = (double) size / (double) array.length;
        if (usage < USAGE_FACTOR && array.length > MINIMUM_ARRAY_SIZE) {
            resize(array.length / SHRINK_FACTOR, 0, 0);
        }
        return removedItem;
    }
}
