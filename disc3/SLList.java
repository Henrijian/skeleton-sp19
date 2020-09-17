@SuppressWarnings("ALL")
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

    public void addFirst(int x) {
        first = new IntNode(x, first);
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

    public static void main(String[] args) {
        SLList L = new SLList();
        L.addFirst(10);
        L.addFirst(8);
        L.addFirst(6);
        L.addFirst(4);
        L.addFirst(2);
        L.insert(9, 4);
        L.insert(7, 3);
        L.insert(5, 2);
        L.insert(3, 1);
        L.insert(1, 0);
        for (int i = 0; i < L.size; i++) {
            System.out.printf("item %d: %d\n", i, L.get(i));
        }
        L.reverse();
        for (int i = 0; i < L.size; i++) {
            System.out.printf("item %d: %d\n", i, L.get(i));
        }
        L.reverseRecursive();
        for (int i = 0; i < L.size; i++) {
            System.out.printf("item %d: %d\n", i, L.get(i));
        }
    }
}