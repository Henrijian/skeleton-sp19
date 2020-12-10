package lab9;

import java.util.*;

public class MyHashMap<Key, Value> implements Map61B<Key, Value> {
    private class Item {
        Key key;
        Value value;
        Item(Key k, Value v) {
            this.key = k;
            this.value = v;
        }
    }

    private final double LOAD_FACTOR_BOUND;
    private final int ENLARGE_FACTOR = 2;
    private final HashSet<Key> keys;
    private LinkedList<Item>[] buckets;

    private static final int DEFAULT_SIZE = 16;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    public MyHashMap(int initialSize, double loadFactor) {
        if (initialSize < 0 || loadFactor <= 0) {
            throw new IllegalArgumentException();
        }
        this.LOAD_FACTOR_BOUND = loadFactor;
        this.keys = new HashSet<>();
        this.buckets = (LinkedList<Item>[]) new LinkedList[initialSize];
    }

    public MyHashMap(int initialSize) {
        this(initialSize, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap() {
        this(DEFAULT_SIZE, DEFAULT_LOAD_FACTOR);
    }

    private int hashIdx(int hashCode) {
        return (hashCode & 0x7FFFFFFF) % buckets.length;
    }

    @Override
    public void clear() {
        keys.clear();
        Arrays.fill(buckets, null);
    }

    @Override
    public boolean containsKey(Key k) {
        return keys.contains(k);
    }

    @Override
    public Value get(Key k) {
        int hashIdx = hashIdx(k.hashCode());
        LinkedList<Item> sepChain = buckets[hashIdx];
        if (sepChain == null) {
            return null;
        }
        for (Item item: sepChain) {
            if (item.key.equals(k)) {
                return item.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return keys.size();
    }

    /** Resize array of buckets to specified number. */
    private void resize(int size) {
        LinkedList<Item>[] oldBuckets = buckets;
        buckets = (LinkedList<Item>[]) new LinkedList[size];
        for (LinkedList<Item> sepChain : oldBuckets) {
            if (sepChain == null) {
                continue;
            }
            for (Item item : sepChain) {
                add(item.key, item.value);
            }
        }
    }

    /** Add key and value to buckets. */
    private void add(Key k, Value v) {
        int hashIdx = hashIdx(k.hashCode());
        LinkedList<Item> sepChain = buckets[hashIdx];
        if (sepChain == null) {
            sepChain = new LinkedList<>();
            buckets[hashIdx] = sepChain;
        }
        for (Item item: sepChain) {
            if (item.key.equals(k)) {
                item.value = v;
                return;
            }
        }
        Item newItem = new Item(k, v);
        sepChain.addFirst(newItem);
    }

    @Override
    public void put(Key k, Value v) {
        keys.add(k);
        double loadFactor = (double) keys.size() / buckets.length;
        if (loadFactor >= LOAD_FACTOR_BOUND) {
            resize(buckets.length * ENLARGE_FACTOR);
        }
        add(k, v);
    }

    @Override
    public Set<Key> keySet() {
        return keys;
    }

    @Override
    public Value remove(Key k) {
        return remove(k, null);
    }

    @Override
    public Value remove(Key k, Value v) {
        int hashIdx = hashIdx(k.hashCode());
        LinkedList<Item> sepChain = buckets[hashIdx];
        if (sepChain == null) {
            return null;
        }
        Value removedValue = null;
        for (Item item: sepChain) {
            if (!item.key.equals(k)) {
                continue;
            }
            if (item.value.equals(v) || v == null) {
                removedValue = item.value;
                sepChain.remove(item);
                break;
            }
        }
        if (removedValue != null) {
            keys.remove(k);
            double loadFactorBound = (double) buckets.length / ENLARGE_FACTOR * LOAD_FACTOR_BOUND / 2;
            double loadFactor = (double) keys.size() / buckets.length;
            if (loadFactor <= loadFactorBound) {
                resize(buckets.length / ENLARGE_FACTOR);
            }
        }
        return removedValue;
    }

    public Iterator<Key> iterator() {
        return keys.iterator();
    }
}
