package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int[] bucketItemCountArray = new int[M];
        for (Oomage o: oomages) {
            int hashIndex = (o.hashCode() & 0x7FFFFFFF) % M;
            bucketItemCountArray[hashIndex] += 1;
        }
        double countLowerBound = oomages.size() / (double) 50;
        double countUpperBound = oomages.size() / 2.5;
        for (int itemCount: bucketItemCountArray) {
            if (itemCount < countLowerBound || countUpperBound < itemCount) {
                return false;
            }
        }
        return true;
    }
}
