public class Array {
    /** Insert item at position in arr non-destructively,
     * if position is out of bound to the arr, add it to the last. */
    public static int[] insert(int[] arr, int item, int position) {
        if (position < 0) {
            return arr;
        }

        int[] result = new int[arr.length + 1];
        if (position >= arr.length) {
            System.arraycopy(arr, 0, result, 0, arr.length);
            result[arr.length] = item;
        } else {
            System.arraycopy(arr, 0, result, 0, position);
            result[position] = item;
            System.arraycopy(arr, position, result, position + 1, arr.length - position);
        }
        return result;
    }

    /** Reverse arr in destructive way. */
    public static void reverse(int[] arr) {
        int midPos = arr.length / 2;
        for (int i = 0; i < midPos; i++) {
            int temp = arr[i];
            int oppositePos = arr.length - 1 - i;
            arr[i] = arr[oppositePos];
            arr[oppositePos] = temp;
        }
    }

    /** Copy the item at index i with the amount of itself.
     * e.g. [3, 2, 1] => [3, 3, 3, 2, 2, 1] */
    public static int[] replicate(int[] arr) {
        int newSize = 0;
        for (int x: arr) {
            newSize += x;
        }
        int[] result = new int[newSize];
        int curPos = 0;
        for (int x: arr) {
            for (int i = 0; i < x; i++) {
                result[curPos] = x;
                curPos++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = new int[0];
        for (int i = 0; i < 5; i++) {
            arr = insert(arr, i + 1, 0);
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("item %d: %d\n", i, arr[i]);
        }
        reverse(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("item %d: %d\n", i, arr[i]);
        }
        int[] arr2 = replicate(arr);
        for (int i = 0; i < arr2.length; i++) {
            System.out.printf("item %d: %d\n", i, arr2[i]);
        }
        return;
    }
}
