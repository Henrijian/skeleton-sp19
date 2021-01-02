package bearmaps;

import java.util.*;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private class ItemInfo {
        public double priority;
        public int heapIdx;

        public ItemInfo(double priority, int heapIdx) {
            this.priority = priority;
            this.heapIdx = heapIdx;
        }
    }

    private ArrayList<T> minHeap;
    private HashMap<T, ItemInfo> itemInfo;

    public ArrayHeapMinPQ() {
        this.minHeap = new ArrayList<>();
        this.itemInfo = new HashMap<>();
    }

    private int parent(int heapIdx) {
        validateHeapIdx(heapIdx);
        return (heapIdx + 1) / 2 - 1;
    }

    private int leftChild(int heapIdx) {
        validateHeapIdx(heapIdx);
        return heapIdx * 2 + 1;
    }

    private int rightChild(int heapIdx) {
        validateHeapIdx(heapIdx);
        return heapIdx * 2 + 2;
    }

    private void swim(int heapIdx) {
        if (minHeap.size() == 0) {
            return;
        }
        validateHeapIdx(heapIdx);
        int parentIdx = parent(heapIdx);
        if (parentIdx < 0) {
            return;
        }
        T parentItem = minHeap.get(parentIdx);
        ItemInfo parentInfo = itemInfo.get(parentItem);
        T myItem = minHeap.get(heapIdx);
        ItemInfo myInfo = itemInfo.get(myItem);
        if (myInfo.priority < parentInfo.priority) {
            swap(heapIdx, parentIdx);
            swim(parentIdx);
        }
    }

    private void sink(int heapIdx) {
        if (minHeap.size() == 0) {
            return;
        }
        validateHeapIdx(heapIdx);
        T myItem = minHeap.get(heapIdx);
        ItemInfo myInfo = itemInfo.get(myItem);

        int leftChildIdx = leftChild(heapIdx);
        double leftChildPriority = Double.MAX_VALUE;
        if (isValidHeapIdx(leftChildIdx)) {
            T leftChildItem = minHeap.get(leftChildIdx);
            leftChildPriority = itemInfo.get(leftChildItem).priority;
        }

        int rightChildIdx = rightChild(heapIdx);
        double rightChildPriority = Double.MAX_VALUE;
        if (isValidHeapIdx(rightChildIdx)) {
            T rightChildItem = minHeap.get(rightChildIdx);
            rightChildPriority = itemInfo.get(rightChildItem).priority;
        }

        double minChildPriority = Double.min(leftChildPriority, rightChildPriority);
        if (myInfo.priority <= minChildPriority) {
            return;
        }

        if (leftChildPriority < rightChildPriority) {
            swap(heapIdx, leftChildIdx);
            sink(leftChildIdx);
        } else {
            swap(heapIdx, rightChildIdx);
            sink(rightChildIdx);
        }
    }

    private void swap(int heapIdxA, int heapIdxB) {
        validateHeapIdx(heapIdxA);
        validateHeapIdx(heapIdxB);

        T itemA = minHeap.get(heapIdxA);
        ItemInfo infoA = itemInfo.get(itemA);
        infoA.heapIdx = heapIdxB;
        T itemB = minHeap.get(heapIdxB);
        ItemInfo infoB = itemInfo.get(itemB);
        infoB.heapIdx = heapIdxA;
        Collections.swap(minHeap, heapIdxA, heapIdxB);
    }

    private void validateHeapIdx(int heapIdx) {
        if (!isValidHeapIdx(heapIdx)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isValidHeapIdx(int heapIdx) {
        return (0 <= heapIdx && heapIdx < minHeap.size());
    }

    @Override
    public void add(T item, double priority) {
        if (item == null || itemInfo.containsKey(item)) {
            throw new IllegalArgumentException();
        }
        minHeap.add(item);
        itemInfo.put(item, new ItemInfo(priority, minHeap.size() - 1));
        swim(minHeap.size() - 1);
    }

    @Override
    public boolean contains(T item) {
        return itemInfo.containsKey(item);
    }

    @Override
    public T getSmallest() {
        if (minHeap.size() == 0) {
            throw new NoSuchElementException();
        }
        return minHeap.get(0);
    }

    @Override
    public T removeSmallest() {
        if (minHeap.size() == 0) {
            throw new NoSuchElementException();
        }
        swap(0, minHeap.size() - 1);
        T smallestItem = minHeap.remove(minHeap.size() - 1);
        itemInfo.remove(smallestItem);
        sink(0);
        return smallestItem;
    }

    @Override
    public int size() {
        return minHeap.size();
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!itemInfo.containsKey(item)) {
            throw new NoSuchElementException();
        }
        ItemInfo myInfo = itemInfo.get(item);
        myInfo.priority = priority;
        swim(myInfo.heapIdx);
        sink(myInfo.heapIdx);
    }
}
