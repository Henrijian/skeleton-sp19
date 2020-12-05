import java.util.Random;

/**
 * Created by hug.
 */
public class ExperimentHelper {

    /** Returns the internal path length for an optimum binary search tree of
     *  size N. Examples:
     *  N = 1, OIPL: 0
     *  N = 2, OIPL: 1
     *  N = 3, OIPL: 2
     *  N = 4, OIPL: 4
     *  N = 5, OIPL: 6
     *  N = 6, OIPL: 8
     *  N = 7, OIPL: 10
     *  N = 8, OIPL: 13
     */
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

    /** Returns the average depth for nodes in an optimal BST of
     *  size N.
     *  Examples:
     *  N = 1, OAD: 0
     *  N = 5, OAD: 1.2
     *  N = 8, OAD: 1.625
     * @return
     */
    public static double optimalAverageDepth(int N) {
        return (double) optimalIPL(N) / N;
    }

    public static void randomlyDeleteAndInsert(BST<Integer> bst, int operationCount, boolean deleteRandom) {
        for (int operationIdx = 0; operationIdx < operationCount; operationIdx++) {
            int deleteKey = bst.getRandomKey();
            if (deleteRandom) {
                bst.deleteTakingRandom(deleteKey);
            } else {
                bst.deleteTakingSuccessor(deleteKey);
            }

            Random r = new Random();
            int insertKey;
            do {
                insertKey = r.nextInt(Integer.MAX_VALUE);
            } while (bst.contains(insertKey));
            bst.add(insertKey);
        }
    }
}
