package bearmaps.proj2c;

import java.util.*;

public class TrieMap<T>{
    private class Node {
        private final char c;
        private final Map<Character, Node> next;
        private boolean isEnd;
        private T item;

        public Node(char c) {
            this.c = c;
            this.next = new HashMap<>();
            this.isEnd = false;
            this.item = null;
        }
    }

    private final Node root;
    private int size;

    public TrieMap() {
        root = new Node(' ');
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(String key) {
        Node parent = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (!parent.next.containsKey(c)) {
                return false;
            }
            parent = parent.next.get(c);
        }
        return parent.isEnd;
    }

    public Node put(Node parent, String s, int i, T item) {
        if (parent == null) {
            return null;
        }
        if (s == null) {
            return null;
        }
        if (item == null) {
            return null;
        }
        if (s.length() == i) {
            if (!parent.isEnd) {
                size = size + 1;
                parent.isEnd = true;
                parent.item = item;
            }
            return parent;
        }
        char c = s.charAt(i);
        Node node = parent.next.get(c);;
        if (node == null) {
            node = new Node(c);
            parent.next.put(c, node);
        }
        int nextIdx = i + 1;
        return put(node, s, nextIdx, item);
    }

    public boolean put(String key, T item) {
        if (key == null) {
            return false;
        }
        if (item == null) {
            return false;
        }
        return put(root, key, 0, item) == null;
    }

    public T get(String key) {
        Node parent = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (!parent.next.containsKey(c)) {
                return null;
            }
            parent = parent.next.get(c);
        }
        if (!parent.isEnd) {
            return null;
        }
        return parent.item;
    }
    /**
     * Collect all strings from trie.
     * */
    public Set<String> keySet() {
        HashSet<String> result = new HashSet<>();
        for (char c: root.next.keySet()) {
            collectKeys(String.valueOf(c), result, root.next.get(c));
        }
        return result;
    }

    public void collectKeys(String s, Set<String> container, Node node) {
        if (s == null) {
            return;
        }
        if (container == null) {
            return;
        }
        if (node == null) {
            return;
        }
        if (node.isEnd) {
            container.add(s);
        }
        for (char c: node.next.keySet()) {
            collectKeys(s + c, container, node.next.get(c));
        }
    }

    public Set<String> keysWithPrefix(String prefix) {
        Node prefixRoot = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (!prefixRoot.next.containsKey(c)) {
                return null;
            }
            prefixRoot = prefixRoot.next.get(c);
        }
        HashSet<String> result = new HashSet<>();
        collectKeys(prefix, result, prefixRoot);
        return result;
    }
}
