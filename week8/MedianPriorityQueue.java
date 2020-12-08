import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.MinPQ;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class MedianPriorityQueue<Item> {
    /** divide item into two part in descending order. */
    private final MaxPQ<Item> leftPQ; // left part of items.
    private final MinPQ<Item> rightPQ; // right part of items.
    private final Comparator<Item> comparator;

    public MedianPriorityQueue(Comparator<Item> init_comparator) {
        leftPQ = new MaxPQ<>(init_comparator);
        rightPQ = new MinPQ<>(init_comparator);
        comparator = init_comparator;
    }

    public int size() {
        return leftPQ.size() + rightPQ.size();
    }

    public boolean empty() {
        return size() == 0;
    }

    public void insert(Item x) {
        if (empty()) {
            rightPQ.insert(x);
            return;
        }
        Item median = median();
        int compare = comparator.compare(x, median);
        if (compare < 0) {
            leftPQ.insert(x);
            if (leftPQ.size() > rightPQ.size()) {
                rightPQ.insert(leftPQ.delMax());
            }
        } else {
            rightPQ.insert(x);
            if (rightPQ.size() > leftPQ.size()) {
                leftPQ.insert(rightPQ.delMin());
            }
        }
    }

    public Item median() {
        if (empty()) {
            return null;
        }
        if (leftPQ.size() > rightPQ.size()) {
            return leftPQ.max();
        } else {
            return rightPQ.min();
        }
    }

    public Item deleteMedian() {
        if (empty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        if (leftPQ.size() > rightPQ.size()) {
            return leftPQ.delMax();
        } else {
            return rightPQ.delMin();
        }
    }
}
