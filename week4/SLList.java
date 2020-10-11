public class SLList {
    private class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int item, IntNode next) {
            this.item = item;
            this.next = next;
        }
    }

    private IntNode first;
    private int size;

    public SLList() {
        first = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean equals(SLList L) {
        if (size() != L.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if (get(i) != L.get(i)) {
                return false;
            }
        }
        return true;
    }

    public void addFirst(int x) {
        first = new IntNode(x, first);
        size += 1;
    }

    public void addLast(int x) {
        if (size == 0) {
            first = new IntNode(x, null);
            size += 1;
            return;
        }
        IntNode p = first;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x, null);
        size += 1;
    }

    public int getFirst() {
        return first.item;
    }

    public int get(int position) {
        int i = 0;
        IntNode p = first;
        while (i != position && p != null) {
            p = p.next;
            i += 1;
        }
        return p.item;
    }

    /** Insert the item at the position, position is an non-negetive integer. */
    public void insert(int item, int position) {
        if (first == null || position == 0) {
            addFirst(item);
            return;
        }

        IntNode prevNode = first;
        while (position > 1 && prevNode.next != null) {
            position -= 1;
            prevNode = prevNode.next;
        }
        IntNode newNode = new IntNode(item, prevNode.next);
        prevNode.next = newNode;
        size += 1;
    }

    /** Reverse list in iterative way, without using new method. */
    public void reverse() {
        if (first == null) {
            return;
        }
        IntNode prevNode = null;
        IntNode currNode = first;
        while (currNode.next != null) {
            IntNode nextNode = currNode.next;
            currNode.next = prevNode;
            prevNode = currNode;
            currNode = nextNode;
        }
        currNode.next = prevNode;
        first = currNode;
    }

    /** Reverse list from IntNode lst. */
    private IntNode reverse(IntNode lst) {
        if (lst == null || lst.next == null) {
            return lst;
        } else {
            IntNode endOfReversed = lst.next;
            IntNode startOfReversed = reverse(lst.next);
            endOfReversed.next = lst;
            lst.next = null;
            return startOfReversed;
        }
    }

    /** Reverse list in recursive way, without using new method. */
    public void reverseRecursive() {
        first = reverse(first);
    }
}