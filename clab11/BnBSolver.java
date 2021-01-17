import java.util.ArrayList;
import java.util.List;

/**
 * BnBSolver for the Bears and Beds problem. Each Bear can only be compared to Bed objects and each Bed
 * can only be compared to Bear objects. There is a one-to-one mapping between Bears and Beds, i.e.
 * each Bear has a unique size and has exactly one corresponding Bed with the same size.
 * Given a list of Bears and a list of Beds, create lists of the same Bears and Beds where the ith Bear is the same
 * size as the ith Bed.
 */
public class BnBSolver {
    private class BnBPair extends Pair<List<Bear>, List<Bed>> {
        public List<Bear> bears() {
            return first();
        }

        public List<Bed> beds() {
            return second();
        }

        public BnBPair (List<Bear> bears, List<Bed> beds) {
            super(bears, beds);
        }
    }

    private final List<Bear> sortedBears;

    private final List<Bed> sortedBeds;

    public BnBSolver(List<Bear> bears, List<Bed> beds) {
        BnBPair unsortedBnBPair = new BnBPair(bears, beds);
        BnBPair sortedBnBPair = quickSortBnB(unsortedBnBPair);
        sortedBears = sortedBnBPair.bears();
        sortedBeds = sortedBnBPair.beds();
    }

    private Bed getRandomBed(List<Bed> beds) {
        if (beds == null) {
            throw new IllegalArgumentException("beds cannot be null in getRandomBear.");
        }
        if (beds.size() == 0) {
            throw new IllegalArgumentException("beds cannot be empty in getRandomBear.");
        }
        int randomIdx = (int) (Math.random() * beds.size());
        return beds.get(randomIdx);
    }

    private Bear getRandomBear(List<Bear> bears) {
        if (bears == null) {
            throw new IllegalArgumentException("bears cannot be null in getRandomBear.");
        }
        if (bears.size() == 0) {
            throw new IllegalArgumentException("bears cannot be empty in getRandomBear.");
        }
        int randomIdx = (int) (Math.random() * bears.size());
        return bears.get(randomIdx);
    }

    private void partitionBeds(List<Bed> unsorted, Bear pivot, List<Bed> less, List<Bed> equal,
                               List<Bed> greater) {
        for (Bed bed: unsorted) {
            int compare = bed.compareTo(pivot);
            if (compare < 0) {
                less.add(bed);
            } else if (compare > 0) {
                greater.add(bed);
            } else {
                equal.add(bed);
            }
        }
    }

    private void partitionBears(List<Bear> unsorted, Bed pivot, List<Bear> less, List<Bear> equal,
                                List<Bear> greater) {
        for (Bear bear: unsorted) {
            int compare = bear.compareTo(pivot);
            if (compare < 0) {
                less.add(bear);
            } else if (compare > 0) {
                greater.add(bear);
            } else {
                equal.add(bear);
            }
        }
    }

    private BnBPair concatenate(BnBPair pair1, BnBPair pair2) {
        List<Bear> bears = pair1.bears();
        bears.addAll(pair2.bears());
        List<Bed> beds = pair1.beds();
        beds.addAll(pair2.beds());
        return new BnBPair(bears, beds);
    }

    private BnBPair quickSortBnB(BnBPair pair) {
        if (pair.bears().size() != pair.beds().size()) {
            throw new IllegalArgumentException("The size of pair's bears and beds are not equal.");
        }
        int pairSize = pair.bears().size();
        if (pairSize <= 1) {
            return pair;
        }
        List<Bear> bears = pair.bears();
        List<Bed> beds = pair.beds();
        Bed pivotBed = getRandomBed(beds);
        List<Bear> lessBears = new ArrayList<>();
        List<Bear> equalBears = new ArrayList<>();
        List<Bear> greaterBears = new ArrayList<>();
        partitionBears(bears, pivotBed, lessBears, equalBears, greaterBears);
        List<Bed> lessBeds = new ArrayList<>();
        List<Bed> equalBeds = new ArrayList<>();
        List<Bed> greaterBeds = new ArrayList<>();
        for (Bear pivotBear: equalBears) {
            partitionBeds(beds, pivotBear, lessBeds, equalBeds, greaterBeds);
        }
        BnBPair lessPair = new BnBPair(lessBears, lessBeds);
        BnBPair sortedLessPair = quickSortBnB(lessPair);
        BnBPair equalPair = new BnBPair(equalBears, equalBeds);
        BnBPair greaterPair = new BnBPair(greaterBears, greaterBeds);
        BnBPair sortedGreaterPair = quickSortBnB(greaterPair);
        BnBPair result = concatenate(sortedLessPair, equalPair);
        result = concatenate(result, sortedGreaterPair);
        return result;
    }

    /**
     * Returns List of Bears such that the ith Bear is the same size as the ith Bed of solvedBeds().
     */
    public List<Bear> solvedBears() {
        return sortedBears;
    }

    /**
     * Returns List of Beds such that the ith Bear is the same size as the ith Bear of solvedBears().
     */
    public List<Bed> solvedBeds() {
        return sortedBeds;
    }
}
