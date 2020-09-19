public class Sort {
    /** Sort the list x in destructive way. */
    public static void sort(String[] x) {
        sortStart(x, 0);
    }

    /** Find the smallest index in x from position index start(included). */
    public static int findSmallest(String[] x, int start) {
        int smallestIndex = start;
        for (int i = start; i < x.length; i++) {
            int compare = x[i].compareTo(x[smallestIndex]);
            if (compare < 0) {
                smallestIndex = i;
            }
        }
        return smallestIndex;
    }

    /** Swap the item of indices a and b in x. */
    public static void swap(String[] x, int a, int b) {
        String temp = x[a];
        x[a] = x[b];
        x[b] = temp;
    }

    /** Sort the array x from position start(included). */
    public static void sortStart(String[] x, int start) {
        if (start == x.length) {
            return;
        }
        int smallestIndex = findSmallest(x, start);
        swap(x, smallestIndex, start);
        sortStart(x, start + 1);
    }

    int intField;
    String strField;
    double doubleField;
    public Sort(int intField, String strField, double doubleField) {
        this.intField = intField;
        this.strField = strField;
        this.doubleField = doubleField;
    }
}
