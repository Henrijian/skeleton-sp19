import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.*;

public class ArraySet<T> implements Iterable<T> {

    private T[] items;
    private int size;

    public ArraySet() {
        items = (T[])new Object[100];
        size = 0;
    }

//    @Test
    public void containsTest() {
        ArraySet<String> s = new ArraySet<>();
        s.add("henri");
        assertTrue(s.contains("henri"));
    }

    /* Returns true if this map contains a mapping for the specified key.
     */
    public boolean contains(T x) {
        for (int i = 0; i < size; i += 1) {
            if (items[i] == null) {
                if (x == null) {
                    return true;
                }
            }
            else if (items[i].equals(x)) {
                return true;
            }
        }
        return false;
    }

//    @Test
    public void addTest() {
        ArraySet<String> s = new ArraySet<>();
        s.add("test");
        assertTrue(s.contains("test"));
    }

    /* Associates the specified value with the specified key in this map.
       Throws an IllegalArgumentException if the key is null. */
    public void add(T x) {
        if (contains(x)) {
            return;
        }
        items[size] = x;
        size += 1;
    }

//    @Test
    public void sizeTest() {
        ArraySet<String> s = new ArraySet<>();
        s.add("1");
        s.add("2");
        s.add("3");
        s.add("4");
        s.add("5");
        s.add("5");
        assertEquals(5, s.size());
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    private class ArraySetIterator implements Iterator<T> {
        private int curPos;

        public ArraySetIterator() {
            curPos = 0;
        }

        @Override
        public boolean hasNext(){
            return curPos < size;
        }

        @Override
        public T next() {
            T nextItem = items[curPos];
            curPos += 1;
            return nextItem;
        }
    }

    @Override
    public String toString() {
        StringBuilder returnSB = new StringBuilder("{");
        for (int i = 0; i < size - 1; i += 1) {
            if (items[i] == null) {
                returnSB.append("null");
            } else {
                returnSB.append(items[i].toString());
            }
            returnSB.append(", ");
        }
        if (size > 0) {
            returnSB.append(items[size - 1].toString());
        }
        returnSB.append("}");
        return returnSB.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (this.getClass() != other.getClass()) {
            return false;
        }
        ArraySet<T> o = (ArraySet<T>) other;
        if (this.size() != o.size()) {
            return false;
        }
        for (T item: this) {
            if (!o.contains(item)) {
                return false;
            }
        }
        return true;
    }

    public static <itemType> ArraySet<itemType> of(itemType... items) {
        ArraySet<itemType> returnSet = new ArraySet<>();
        for (itemType item: items) {
            returnSet.add(item);
        }
        return returnSet;
    }

    public static void main(String[] args) {
        ArraySet<String> s = new ArraySet<>();
        s.add(null);
        s.add("horse");
        s.add("fish");
        s.add("house");
        s.add("fish");
        System.out.println(s.contains("horse"));
        System.out.println(s.size());

        Iterator<String> seer = s.iterator();
        while (seer.hasNext()) {
            System.out.println(seer.next());
        }

        for (String i: s) {
            System.out.println(i);
        }

        System.out.println(s);

        ArraySet<String> s2 = new ArraySet<>();
        s2.add("fish");
        s2.add("house");
        s2.add("fish");
        s2.add("horse");
        s2.add(null);
        System.out.println(s.equals(s2));
        System.out.println(s.equals(s));
        System.out.println(s.equals(null));

        ArraySet<String> s3 = ArraySet.of("Hi", "I'm", "Henri");
        System.out.println(s3);
    }

    /* Also to do:
    1. Make ArraySet implement the Iterable<T> interface.
    2. Implement a toString method.
    3. Implement an equals() method.
    */
}