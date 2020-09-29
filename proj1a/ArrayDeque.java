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
        nextLast = getNext(nextFirst);
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
    private int getPrevious(int index) throws IndexOutOfBoundsException {
        if (index < 0 || items.length <= index) {
            throw new IndexOutOfBoundsException("Given index must be in boundary.");
        }
        if (index == 0) {
            return items.length - 1;
        }
        return index - 1;
    }

    /** Get next index of the given index. */
    private int getNext(int index) throws IndexOutOfBoundsException {
        if (index < 0 || items.length <= index) {
            throw new IndexOutOfBoundsException("Given index must be in boundary.");
        }
        if (index == items.length - 1) {
            return 0;
        }
        return index + 1;
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
        int itemIdx = getNext(nextFirst);
        while (i != 0) {
            itemIdx = getNext(itemIdx);
            i--;
        }
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
        if (length <= 0) {
            items = (T[]) new Object[MINIMUM_ARRAY_SIZE];
            size = 0;
            nextFirst = (int) Math.random() * MINIMUM_ARRAY_SIZE;
            nextLast = getNext(nextFirst);
            return;
        }

        T[] newItems = (T[]) new Object[length];
        int newFirst = 0;
        int curIdx = getNext(nextFirst);
        int newIdx = newFirst;
        int tempSize = size;
        while (tempSize != 0 && newIdx < newItems.length) {
            newItems[newIdx] = items[curIdx];
            curIdx = getNext(curIdx);
            newIdx += 1;
            tempSize--;
        }
        items = newItems;
        if (length < size) {
            size = length;
        }
        nextFirst = getPrevious(newFirst);
        nextLast = getNext(nextFirst);
        tempSize = size;
        while (tempSize != 0) {
            nextLast = getNext(nextLast);
            tempSize--;
        }
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * EXPAND_FACTOR);
        }
        items[nextFirst] = item;
        nextFirst = getPrevious(nextFirst);
        size++;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * EXPAND_FACTOR);
        }
        items[nextLast] = item;
        nextLast = getNext(nextLast);
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
        int first = getNext(nextFirst);
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
        int last = getPrevious(nextLast);
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
