import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

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
    private ArrayList<Item> [] buckets;

    private static final int DEFAULT_SIZE = 16;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    public MyHashMap(int initialSize, double loadFactor) {
        this.LOAD_FACTOR_BOUND = loadFactor;
        this.keys = new HashSet<>();
        this.buckets = (ArrayList<Item>[]) new ArrayList[initialSize];
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
        ArrayList<Item> sepChain = buckets[hashIdx];
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
        ArrayList<Item>[] oldBuckets = buckets;
        buckets = (ArrayList<Item>[]) new ArrayList[size];
        for (ArrayList<Item> sepChain : oldBuckets) {
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
        ArrayList<Item> sepChain = buckets[hashIdx];
        if (sepChain == null) {
            sepChain = new ArrayList<>();
            buckets[hashIdx] = sepChain;
        }
        for (Item item: sepChain) {
            if (item.key.equals(k)) {
                item.value = v;
                return;
            }
        }
        Item newItem = new Item(k, v);
        sepChain.add(newItem);
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
        throw new UnsupportedOperationException();
    }

    @Override
    public Value remove(Key k, Value v) {
        throw new UnsupportedOperationException();
    }

    public Iterator<Key> iterator() {
        return keys.iterator();
    }
}
