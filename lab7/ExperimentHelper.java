public class ExperimentHelper {

    /** Return the optimal internal path length(IPL),
     * which means the total length of path of each nodes in tree. */
    public static int optimalIPL(int N) {
        int height = (int) Math.floor(Math.log10(N) / Math.log10(2));
        if (height <= 0) {
            return 0;
        }
        int shorterBushyTreeNodeCount = (int) Math.pow(2, height) - 1;
        int leafCount = N - shorterBushyTreeNodeCount;
        int leafPathLength = leafCount * height;
        return leafPathLength + optimalIPL(N - leafCount);
    }

    /** Return the optimal average depth, meaning the average depth of bushy tree. */
    public static double optimalAverageDepth(int N) {
        return (double) optimalIPL(N) / N;
    }

    public static void main(String[] argv) {
        for (int i = 1; i <= 1024; i *= 2) {
            System.out.printf("node: %d, optimalIPL: %d, optimalAverageDepth: %f\n", i, optimalIPL(i), optimalAverageDepth(i));
        }
    }
}
