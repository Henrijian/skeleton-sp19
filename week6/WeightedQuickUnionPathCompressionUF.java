import java.security.InvalidParameterException;

public class WeightedQuickUnionPathCompressionUF {
    private int[] items;
    private int[] sizes;

    /**
     * itemCount: the number of items.
     */
    public WeightedQuickUnionPathCompressionUF(int itemCount) {
        if (itemCount < 0) {
            throw new InvalidParameterException("itemCount must be greater or equal to 0.");
        }
        items = new int[itemCount];
        sizes = new int[itemCount];
        for (int i = 0; i < itemCount; i++) {
            items[i] = i;
            sizes[i] = 1;
        }
    }

    private void validateItem(int item) {
        final int lowerBound = 0;
        final int upperBound = items.length;
        if (item < lowerBound || item >= upperBound) {
            throw new InvalidParameterException(String.format("item must be between %d and %d.", lowerBound, upperBound));
        }
    }

    /** Find the root of item. */
    private int findRoot(int item) {
        validateItem(item);
        int root = item;
        while (root != items[root]) {
            root = items[root];
        }
        while (item != root) {
            int newItem = items[item];
            items[item] = root;
            sizes[item] = 1;
            item = newItem;
        }
        return root;
    }

    /** Union item1 and item2. */
    public void union(int item1, int item2) {
        int root1 = findRoot(item1);
        int root2 = findRoot(item2);
        if (root1 == root2) {
            return;
        }
        if (sizes[root1] < sizes[root2]) {
            items[root1] = root2;
            sizes[root2] += sizes[root1];
        } else {
            items[root2] = root1;
            sizes[root1] += sizes[root2];
        }
    }

    /** Return true if item1 and item2 is connected, else return false. */
    public boolean connected(int item1, int item2) {
        return findRoot(item1) == findRoot(item2);
    }
}
