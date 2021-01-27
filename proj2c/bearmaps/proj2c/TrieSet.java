package bearmaps.proj2c;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrieSet {
    private class Node {
        private final char c;
        private final Map<Character, Node> next;
        private boolean isEnd;

        public Node(char c) {
            this.c = c;
            this.next = new HashMap<>();
            this.isEnd = false;
        }
    }

    private final Node root;
    private int size;

    public TrieSet() {
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

    public Node add(Node parent, String s, int i) {
        if (parent == null) {
            return null;
        }
        if (s == null) {
            return null;
        }
        if (s.length() <= i) {
            if (!parent.isEnd) {
                size = size + 1;
                parent.isEnd = true;
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
        return add(node, s, nextIdx);
    }

    public boolean add(String key) {
        if (key == null) {
            return false;
        }
        return add(root, key, 0) == null;
    }

    /**
     * Collect all strings from trie.
     * */
    public List<String> collect() {
        ArrayList<String> result = new ArrayList<>();
        for (char c: root.next.keySet()) {
            collectHelp(String.valueOf(c), result, root.next.get(c));
        }
        return result;
    }

    public void collectHelp(String s, List<String> container, Node node) {
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
            collectHelp(s + c, container, node.next.get(c));
        }
    }

    public List<String> keysWithPrefix(String prefix) {
        Node prefixRoot = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (!prefixRoot.next.containsKey(c)) {
                return null;
            }
            prefixRoot = prefixRoot.next.get(c);
        }
        ArrayList<String> result = new ArrayList<>();
        collectHelp(prefix, result, prefixRoot);
        return result;
    }
}
