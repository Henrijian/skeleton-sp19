public class LinkedListDeque<Item> implements Deque<Item> {
    private class Node {
        private Item item;
        private Node next;
        private Node prev;

        private Node(Item item, Node next, Node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }

        private Node(Item item) {
            this(item, null, null);
        }
    }

    private final Node sentinel;
    private int size;

    /**
     * invariant:
     * 1. the next field of sentinel points to first item in list.
     * 2. the prev field of  first item in list points to sentinel.
     * 3. the prev field of sentinel points to last item in list.
     * 4. the next field of last item in list points to sentinel.
     * 5. size equals the number of item added in list.
     */
    public LinkedListDeque() {
        sentinel = new Node(null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @SafeVarargs
    public LinkedListDeque(Item... items) {
        this();
        Node p = sentinel;
        for (Item item : items) {
            Node newItem = new Node(item);
            newItem.prev = p;
            p.next = newItem;
            newItem.next = sentinel;
            sentinel.prev = newItem;
            p = newItem;
            size++;
        }
    }

    public LinkedListDeque(LinkedListDeque<Item> other) {
        this();
        for (int i = 0; i < other.size(); i++) {
            addLast(other.get(i));
        }
    }

    /** Return the number of the added items in list. */
    @Override
    public int size() {
        return size;
    }

    /** Check if the items in list are equal to compare. */
    private boolean equals(LinkedListDeque<Item> other) {
        if (this.size() != other.size()) {
            return false;
        }
        for (int i = 0; i < this.size(); i++) {
            Item myItem = this.get(i);
            Item otherItem = other.get(i);
            if (!myItem.equals(otherItem)) {
                return false;
            }
        }
        return true;
    }

    /** Add item to the front of the list. */
    @Override
    public void addFirst(Item item) {
        Node newItem = new Node(item, sentinel.next, sentinel);
        sentinel.next.prev = newItem;
        sentinel.next = newItem;
        size++;
    }

    /** Add item to the end of the list. */
    @Override
    public void addLast(Item item) {
        Node newItem = new Node(item, sentinel, sentinel.prev);
        sentinel.prev.next = newItem;
        sentinel.prev = newItem;
        size++;
    }

    /** Prints the items in the list from first to last, separated by a space.
     * Once all the items have been printed, print out a new line. */
    @Override
    public void printDeque() {
        Node p = sentinel.next;
        while (p != sentinel) {
            if (p.next != sentinel) {
                System.out.printf("%s ", p.item);
            } else {
                System.out.printf("%s", p.item);
            }
            p = p.next;
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the list.
     * If no such item exists, returns null. */
    @Override
    public Item removeFirst() {
        if (size == 0) {
            return null;
        }
        Node removedItem = sentinel.next;
        sentinel.next = removedItem.next;
        removedItem.next.prev = sentinel;
        size--;
        return removedItem.item;
    }

    /** Removes and returns the item at the back of the lst.
     * If no such item exists, returns null. */
    @Override
    public Item removeLast() {
        if (size == 0) {
            return null;
        }
        Node removedItem = sentinel.prev;
        sentinel.prev = removedItem.prev;
        removedItem.prev.next = sentinel;
        size--;
        return removedItem.item;
    }

    /** Get the ith item in list(0 based), if no such item exists return null. */
    @Override
    public Item get(int i) {
        if (i < 0 || i >= size) {
            return null;
        }
        Node p = sentinel.next;
        while (i > 0) {
            p = p.next;
            i--;
        }
        return p.item;
    }

    /** Get the node farther than p distance.  */
    private Node getNodeAfter(Node p, int distance) {
        if (p == null || distance == 0) {
            return p;
        }
        return getNodeAfter(p.next, distance - 1);
    }

    /** Same as get, but uses recursion. */
    public Item getRecursive(int index) {
        Node p = getNodeAfter(sentinel.next, index);
        if (p == null) {
            return null;
        }
        return p.item;
    }
}
