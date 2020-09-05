/**
 * Tests pairwise force between bodies
 */
public class TestBody {
    /**
     * Tests pairwise force between two bodies
     */
    public static void main(String[] args) {
        checkPairwiseForce();
    }

    /**
     * Checks whether or not two Doubles are equal and prints the result.
     *
     * @param actual Double received
     * @param expected Expected Double
     * @param label Label for the 'test' case
     * @param eps Tolerance for the double comparison
     */
    private static void checkEquals(double actual, double expected, String label, double eps) {
        if (Double.isNaN(actual) || Double.isInfinite(actual)) {
            System.out.printf("FAIL: %s: Expected %e and you gave %e%n", label, expected, actual);
        } else if (Math.abs(expected - actual) <= eps * Math.max(expected, actual)) {
            System.out.printf("PASS: %s: Expected %e and you gave %e%n", label, expected, actual);
        } else {
            System.out.printf("FAIL: %s: Expected %e and you gave %e%n", label, expected, actual);
        }
    }

    /**
     * Checks pairwise force between two bodies
     */
    private static void checkPairwiseForce() {
        System.out.println("Checking pairwise force...");
        Body b1 = new Body(1.0, 1.0, 0.0, 0.0, 5, "");
        Body b2 = new Body(2.0, 2.0, 0.0, 0.0, 10, "");

        checkEquals(b1.calcForceExertedBy(b2), 1.6675e-9, "checkPairwiseForce", 0.01);
        checkEquals(b2.calcForceExertedBy(b1), 1.6675e-9, "checkPairwiseForce", 0.01);
    }
}
