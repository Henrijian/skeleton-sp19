public class ArrayDeque<T> {
    private final int EXPAND_FACTOR = 2;
    private final double USAGE_FACTOR = 0.25;
    private final int SHRINK_FACTOR = 2;
    private final int MINIMUM_ARRAY_SIZE = 8;

    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;


    /**
     * invariant:
     * 1. array holds the array of added items.
     * 2. size equals the number of item added in list.
     * 3. the last item is at size - 1 position in array.
     * 4. the first item is at 0 position in array.
     */
    public ArrayDeque() {
        items = (T[]) new Object[MINIMUM_ARRAY_SIZE];
        size = 0;
        nextFirst = (int) Math.random() * MINIMUM_ARRAY_SIZE;
        nextLast = getNext(nextFirst, 0, items.length - 1);
    }

//    public ArrayDeque(T... items) {
//        this();
//        int newSize = items.length;
//        while (newSize < items.length) {
//            newSize = newSize * EXPAND_FACTOR;
//        }
//        resize(newSize);
//        for (int i = 0; i < items.length; i++) {
//            addLast(items[i]);
//        }
//    }

//    /** Creates a deep copy of other. */
//    public ArrayDeque(ArrayDeque other) {
//        this();
//        for (int i = 0; i < other.size(); i++) {
//            addLast((T) other.get(i));
//        }
//    }

    /** Get previous index of the given index. */
    private int getPrevious(int index, int start, int end) throws IndexOutOfBoundsException {
        if (index < start || end < index) {
            throw new IndexOutOfBoundsException("Given index must be in boundary.");
        }
        if (index == 0) {
            return end;
        }
        return index - 1;
    }

    private int getPrevious(int index, int start, int end, int distance)
            throws IndexOutOfBoundsException {
        int curIdx = index;
        while (distance > 0) {
            curIdx = getPrevious(curIdx, start, end);
            distance--;
        }
        return curIdx;
    }

    /** Get next index of the given index. */
    private int getNext(int index, int start, int end) throws IndexOutOfBoundsException {
        if (index < start || end < index) {
            throw new IndexOutOfBoundsException("Given index must be in boundary.");
        }
        if (index == end) {
            return start;
        }
        return index + 1;
    }

    private int getNext(int index, int distance, int start, int end)
            throws IndexOutOfBoundsException {
        int curIdx = index;
        while (distance > 0) {
            curIdx = getNext(curIdx, start, end);
            distance--;
        }
        return curIdx;
    }

    /** Return the number of added items in deque. */
    public int size() {
        return size;
    }

    /** Get the ith item from deque, if the item does not exist, return null. */
    public T get(int i) {
        if (i < 0 || size <= i) {
            return null;
        }
        int itemIdx = getNext(nextFirst, i + 1, 0, items.length - 1);
        return items[itemIdx];
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
    private void resize(int length) {
        if (length < 0) {
            length = 0;
        }
        T[] temp = (T[]) new Object[length];
        int first = getNext(nextFirst, 0, items.length - 1);
        int last = getPrevious(nextLast, 0, items.length - 1);
        nextFirst = (int) Math.random() * temp.length;
        if (size < temp.length) {
            nextLast = getNext(nextFirst, size + 1, 0, temp.length - 1);
        } else {
            nextLast = nextFirst;
            size = items.length;
        }
        if (first < last) {
            System.arraycopy(items, first, temp, getNext(nextFirst, 0, items.length - 1), size);
        } else {
            int pos = getNext(nextFirst, 0, temp.length - 1);
            System.arraycopy(items, first, temp, pos, items.length - first);
            pos = getNext(nextFirst, items.length - first + 1, 0, temp.length - 1);
            System.arraycopy(items, 0, temp, pos, last + 1);
        }
        items = temp;
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * EXPAND_FACTOR);
        }
        items[nextFirst] = item;
        nextFirst = getPrevious(nextFirst, 0, items.length - 1);
        size++;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * EXPAND_FACTOR);
        }
        items[nextLast] = item;
        nextLast = getNext(nextLast, 0, items.length - 1);
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
                System.out.printf("%s ", get(i));
            } else {
                System.out.printf("%s", get(i));
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
        int first = getNext(nextFirst, 0, items.length - 1);
        T removedItem = items[first];
        nextFirst = first;
        items[first] = null;
        size--;
        double usage = (double) size / (double) items.length;
        if (usage < USAGE_FACTOR && items.length > MINIMUM_ARRAY_SIZE) {
            resize(items.length / SHRINK_FACTOR);
        }
        return removedItem;
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int last = getPrevious(nextLast, 0, items.length - 1);
        T removedItem = items[last];
        nextLast = last;
        items[last] = null;
        size--;
        double usage = (double) size / (double) items.length;
        if (usage < USAGE_FACTOR && items.length > MINIMUM_ARRAY_SIZE) {
            resize(items.length / SHRINK_FACTOR);
        }
        return removedItem;
    }
}
