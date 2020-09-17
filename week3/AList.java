/* Invariants:
 addLast: The next item we want to add, will go into position size
 getLast: The item we want to return is in position size - 1
 size: The number of items in the list should be size.
*/
public class AList {
    private int[] items;
    private int size;

    /** Creates an empty list. */
    public AList() {
        items = new int[100];
        size = 0;
    }

    /** Resize the AList to the specified capacity. */
    private void resize(int capacity) {
        int[] new_items = new int[capacity];
        System.arraycopy(items, 0, new_items, 0, size);
        items = new_items;
    }

    /** Inserts X into the front of the list. */
    public void addFirst(int x) {
        if (size == items.length) {
            resize(size * 2);
        }
        System.arraycopy(items, 0, items, 1, size);
        items[0] = x;
        size = size + 1;
    }

    /** Return the item from the front of the list. */
    public int getFirst() {
        return items[0];
    }

    /** Deletes item from front of the list and
     * return deleted item. */
    public int removeFirst(){
        int first = items[0];
        System.arraycopy(items, 1, items, 0, size - 1);
        size -= 1;
        if (size < items.length / 4) {
            resize(items.length / 2);
        }
        return first;
    }

    /** Inserts X into the back of the list. */
    public void addLast(int x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[size] = x;
        size = size + 1;
    }

    /** Returns the item from the back of the list. */
    public int getLast() {
        return items[size - 1];
    }

    /** Deletes item from back of the list and
     * returns deleted item. */
    public int removeLast() {
        int last = items[size - 1];
        size -= 1;
        if (size < items.length / 4) {
            resize(items.length / 2);
        }
        return last;
    }

    /** Gets the ith item in the list (0 is the front). */
    public int get(int i) {
        return items[i];
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        AList L = new AList();
        L.addFirst(1);
        L.addFirst(2);
        System.out.printf("removed first: %d\n", L.removeFirst());
        System.out.printf("first item: %d\n", L.getFirst());
        L.addLast(2);
        L.addLast(3);
        System.out.printf("remove last: %d\n", L.removeLast());
        System.out.printf("last item: %d\n", L.getLast());
        for (int i = 0; i < L.size; i++) {
            System.out.printf("item %d: %d\n", i, L.get(i));
        }
        System.out.printf("size: %d\n", L.size);
    }
}
