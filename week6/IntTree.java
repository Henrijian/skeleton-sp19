class IntTree {
    public final int data;
    public IntTree left;
    public IntTree right;

    public IntTree (int data, IntTree left, IntTree right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    /** Assuming that T and L are binary search trees each with a single
     * sentinel tree node, and that all left children in L aside from
     * the sentinel are empty (L is "right-leaning"), returns (the
     * sentinel of) a binary search tree containing the original elements
     * of T and L. The operation is destructive, and creates no
     * new nodes. In the worst case, it takes time linear in the
     * total number of items in T and L. */
    public static IntTree mergeRight (IntTree T, IntTree L) {
        IntTree rootT = T.left;
        IntTree rootL = L.left;
        while (rootL != null) {
            put(rootT, rootL);
            IntTree temp = rootL.right;
            rootL.left = null;
            rootL.right = null;
            rootL = temp;
        }
        return T;
    }

    public static IntTree put(IntTree tree1, IntTree tree2) {
        if (tree1 == null) {
            return tree2;
        }
        if (tree1.data < tree2.data) {
            tree1.right = put(tree1.right, tree2);
        } else if (tree1.data > tree2.data) {
            tree1.left = put(tree1.left, tree2);
        }
        return tree1;
    }

    public static IntTree add(IntTree node, int value) {
        if (node == null) {
            return new IntTree(value, null, null);
        }
        if (node.data < value) {
            node.right = add(node.right, value);
        } else if (node.data > value) {
            node.left = add(node.left, value);
        }
        return node;
    }

    public static void main(String[] argv) {
        IntTree root = new IntTree(12, null, null);
        add(root, 3);
        add(root, 10);
        add(root, 8);
        add(root, 16);
        add(root, 14);
        add(root, 27);
        IntTree sentinelRoot = new IntTree(0, root, null);

        IntTree right = new IntTree(1, null, null);
        add(right, 11);
        add(right, 26);
        IntTree sentinelRight = new IntTree(0, right, null);

        mergeRight(sentinelRoot, sentinelRight);
    }
}