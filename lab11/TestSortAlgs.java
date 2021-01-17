import edu.princeton.cs.algs4.Queue;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.junit.Assert;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        Queue<String> unsortedQueue = new Queue<>();
        unsortedQueue.enqueue("Henri");
        unsortedQueue.enqueue("Jenny");
        unsortedQueue.enqueue("Cindy");
        unsortedQueue.enqueue("Josh");
        unsortedQueue.enqueue("Apple");
        Queue<String> sortedQueue = QuickSort.quickSort(unsortedQueue);

        Assert.assertTrue(isSorted(sortedQueue));
    }

    @Test
    public void testMergeSort() {
        Queue<String> unsortedQueue = new Queue<>();
        unsortedQueue.enqueue("Henri");
        unsortedQueue.enqueue("Jenny");
        unsortedQueue.enqueue("Cindy");
        unsortedQueue.enqueue("Josh");
        unsortedQueue.enqueue("Apple");
        Queue<String> sortedQueue = MergeSort.mergeSort(unsortedQueue);

        Assert.assertTrue(isSorted(sortedQueue));
    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
