package bearmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArrayHeapMinPQTest {
    private class ItemNode {
        public final int item;
        public double priority;

        public ItemNode (int item, double priority) {
            this.item = item;
            this.priority = priority;
        }
    }

    private class ItemNodeComparator implements Comparator<ItemNode> {
        @Override
        public int compare(ItemNode node1, ItemNode node2) {
            if (node1.priority > node2.priority) {
                return 1;
            } else if (node1.priority < node2.priority) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    @Test
    public void containsTest() {
        Random random = new Random();
        ArrayHeapMinPQ<Integer> arrayHeapMinPQ = new ArrayHeapMinPQ<>();
        HashSet<Integer> including = new HashSet<>();
        for (int i = 0; i < 100000; i++) {
            Integer item = random.nextInt(Integer.MAX_VALUE);
            while (including.contains(item)) {
                item = random.nextInt(Integer.MAX_VALUE);
            }
            double priority = random.nextDouble();
            including.add(item);
            arrayHeapMinPQ.add(item, priority);
        }
        for (int item: including) {
            assertTrue(arrayHeapMinPQ.contains(item));
        }
        for (int i = 0; i < 100; i++) {
            Integer item = random.nextInt(Integer.MAX_VALUE);
            while (including.contains(item)) {
                item = random.nextInt(Integer.MAX_VALUE);
            }
            assertTrue(!arrayHeapMinPQ.contains(item));
        }
        assertTrue(!arrayHeapMinPQ.contains(null));
    }

    @Test
    public void smallestTest () {
        Random random = new Random();
        ArrayHeapMinPQ<Integer> arrayHeapMinPQ = new ArrayHeapMinPQ<>();
        HashSet<Integer> including = new HashSet<>();
        ArrayList<ItemNode> itemNodes = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            Integer item = random.nextInt(Integer.MAX_VALUE);
            while (including.contains(item)) {
                item = random.nextInt(Integer.MAX_VALUE);
            }
            double priority = random.nextDouble();
            including.add(item);
            ItemNode itemNode = new ItemNode(item, priority);
            itemNodes.add(itemNode);
            arrayHeapMinPQ.add(item, priority);
        }
        assertEquals(itemNodes.size(), arrayHeapMinPQ.size());
        ItemNodeComparator comparator = new ItemNodeComparator();
        itemNodes.sort(comparator);
        while (arrayHeapMinPQ.size() != 0) {
            int smallest = arrayHeapMinPQ.getSmallest();
            ItemNode smallestNode = itemNodes.get(0);
            assertEquals(smallestNode.item, smallest);
            smallest = arrayHeapMinPQ.removeSmallest();
            assertEquals(smallestNode.item, smallest);
            itemNodes.remove(0);
        }
    }

    @Test
    public void sizeTest() {
        Random random = new Random();
        ArrayHeapMinPQ<Integer> arrayHeapMinPQ = new ArrayHeapMinPQ<>();
        HashSet<Integer> including = new HashSet<>();
        int size = 100000;
        for (int i = 0; i < size; i++) {
            Integer item = random.nextInt(Integer.MAX_VALUE);
            while (including.contains(item)) {
                item = random.nextInt(Integer.MAX_VALUE);
            }
            double priority = random.nextDouble();
            including.add(item);
            arrayHeapMinPQ.add(item, priority);
        }
        assertEquals(size, arrayHeapMinPQ.size());
    }

    @Test
    public void changePriorityTest() {
        Random random = new Random();
        ArrayHeapMinPQ<Integer> arrayHeapMinPQ = new ArrayHeapMinPQ<>();
        HashSet<Integer> including = new HashSet<>();
        ArrayList<ItemNode> itemNodes = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            Integer item = random.nextInt(Integer.MAX_VALUE);
            while (including.contains(item)) {
                item = random.nextInt(Integer.MAX_VALUE);
            }
            double priority = random.nextDouble();
            including.add(item);
            ItemNode itemNode = new ItemNode(item, priority);
            itemNodes.add(itemNode);
            arrayHeapMinPQ.add(item, 0);
        }
        assertEquals(itemNodes.size(), arrayHeapMinPQ.size());
        for (int i = 0; i < itemNodes.size(); i++) {
            ItemNode itemNode = itemNodes.get(i);
            arrayHeapMinPQ.changePriority(itemNode.item, itemNode.priority);
        }
        ItemNodeComparator comparator = new ItemNodeComparator();
        itemNodes.sort(comparator);
        while (arrayHeapMinPQ.size() != 0) {
            int smallest = arrayHeapMinPQ.getSmallest();
            ItemNode smallestNode = itemNodes.get(0);
            assertEquals(smallestNode.item, smallest);
            smallest = arrayHeapMinPQ.removeSmallest();
            assertEquals(smallestNode.item, smallest);
            itemNodes.remove(0);
        }
    }
}
