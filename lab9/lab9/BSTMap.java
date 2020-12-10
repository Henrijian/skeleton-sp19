package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root; // root of BSTMap.

    private class Node {
        private K key; // key of this node.
        private V value; // value of this node.
        private Node left; // left subtree.
        private Node right; // right subtree.
        private int size; // number of nodes in subtree.

        public Node(K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    /** Initializes an empty BSTMap. */
    public BSTMap() {
        // Do nothing.
    }

    /** Remove all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        Node keyNode = getNode(root, key);
        return keyNode != null;
    }

    /* Get the node which key is key(argument), else return null. */
    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            return getNode(node.left, key);
        } else if (compare > 0) {
            return getNode(node.right, key);
        }
        return node;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        Node keyNode = getNode(root, key);
        if (keyNode == null) {
            return null;
        }
        return keyNode.value;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size(root);
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    /* Add node with key and value into tree. */
    private Node put(Node tree, K key, V value) {
        if (tree == null) {
            return new Node(key, value, 1);
        }
        int compare = key.compareTo(tree.key);
        if (compare < 0) {
            tree.left = put(tree.left, key, value);
        } else if (compare > 0) {
            tree.right = put(tree.right, key, value);
        }
        tree.size = size(tree.left) + size(tree.right) + 1;
        return tree;
    }

    /* Return the size of tree. */
    private int size(Node tree) {
        if (tree == null) {
            return 0;
        }
        return tree.size;
    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> ketSet = new HashSet<>();
        getKeySet(root, ketSet);
        return ketSet;
    }

    /* Retrieve a set of key of tree into keySet. */
    private void getKeySet(Node tree, Set<K> keySet) {
        if (tree == null) {
            return;
        }
        keySet.add(tree.key);
        getKeySet(tree.left, keySet);
        getKeySet(tree.right, keySet);
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        V value = get(key);
        root = remove(root, key, null);
        return value;
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        Node keyNode = getNode(root, key);
        if (keyNode == null) {
            return null;
        }
        if (!keyNode.value.equals(value)) {
            return null;
        }
        remove(root, key, value);
        return value;
    }

    /* Removes the node with key and value from tree. */
    private Node remove(Node tree, K key, V value) {
        if (tree == null) {
            return null;
        }
        int compare = key.compareTo(tree.key);
        if (compare < 0) {
            tree.left = remove(tree.left, key, value);
        } else if (compare > 0) {
            tree.right = remove(tree.right, key, value);
        } else {
            if (value != null && !tree.value.equals(value)) {
                return tree;
            }
            if (tree.left == null) {
                return tree.right;
            }
            if (tree.right == null) {
                return tree.left;
            }
            Node smallNode = getSmall(tree.right);
            smallNode.right = removeSmall(tree.right);
            smallNode.left = tree.left;
            tree = smallNode;
        }
        tree.size = size(tree.left) + size(tree.right) + 1;
        return tree;
    }

    /* Return true, if the map is empty, else false. */
    public boolean isEmpty() {
        return size() == 0;
    }

    /* Remove the node with the smallest key in root. */
    public void removeSmall() {
        if (isEmpty()) {
            throw new NoSuchElementException("Map underflow.");
        }
        removeSmall(root);
    }

    /* Remove the node with the smallest key in tree. */
    private Node removeSmall(Node tree) {
        if (tree.left == null) {
            return tree.right;
        }
        tree.left = removeSmall(tree.left);
        tree.size = size(tree.left) + size(tree.right) + 1;
        return tree;
    }

    /* Get the smallest key. */
    public K getSmall() {
        if (isEmpty()) {
            throw new NoSuchElementException("Map underflow.");
        }
        Node smallNode = getSmall(root);
        return smallNode.key;
    }

    /* Get the node with the smallest key in tree. */
    private Node getSmall(Node tree) {
        if (tree.left == null) {
            return tree;
        }
        return getSmall(tree.left);
    }

    private class KeyIterator implements Iterator<K> {
        private Node root;
        private Node curr;

        public KeyIterator(Node root) {
            root = root;
            curr = getSmall(root);
        }

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public K next() {
            Node next = curr;
            curr = getNext(root, next.key);
            return next.key;
        }
    }

    /* Returns the node with the smallest key in tree but greater than key. */
    private Node getNext(Node tree, K key) {
        if (tree == null) {
            return null;
        }
        int compare = key.compareTo(tree.key);
        if (compare < 0) {
            Node next = getNext(tree.left, key);
            if (next != null) {
                return next;
            } else {
                return tree;
            }
        }
        return getNext(tree.right, key);
    }

    @Override
    public Iterator<K> iterator() {
        return new KeyIterator(root);
    }
}
