import static org.junit.Assert.*;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/** Performs some basic linked list tests. */
public class LinkedListDequeTest {
	
	/* Utility method for printing out empty checks. */
	public static boolean checkEmpty(boolean expected, boolean actual) {
		if (expected != actual) {
			System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Utility method for printing out empty checks. */
	public static boolean checkSize(int expected, int actual) {
		if (expected != actual) {
			System.out.println("size() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Prints a nice message based on whether a test passed. 
	 * The \n means newline. */
	public static void printTestStatus(boolean passed) {
		if (passed) {
			System.out.println("Test passed!\n");
		} else {
			System.out.println("Test failed!\n");
		}
	}

	/** Adds a few things to the list, checking isEmpty() and size() are correct, 
	  * finally printing the results. 
	  *
	  * && is the "and" operation. */
	public static void addIsEmptySizeTest() {
		System.out.println("Running add/isEmpty/Size test.");
		System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
		/*
		LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst("front");
		
		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
		passed = checkSize(1, lld1.size()) && passed;
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.addLast("middle");
		passed = checkSize(2, lld1.size()) && passed;

		lld1.addLast("back");
		passed = checkSize(3, lld1.size()) && passed;

		System.out.println("Printing out deque: ");
		lld1.printDeque();

		printTestStatus(passed);
		*/
	}

	/** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
	public static void addRemoveTest() {

		System.out.println("Running add/remove test.");

		System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
		/*
		LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty 
		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty 
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.removeFirst();
		// should be empty 
		passed = checkEmpty(true, lld1.isEmpty()) && passed;

		printTestStatus(passed);
		*/
	}

	@Test
	public void testSize() {
		LinkedListDeque<Integer> input = new LinkedListDeque<Integer>(1, 2, 3, 4, 5);
		int actual = input.size();
		int expected = 5;
		assertEquals(expected, actual);
	}

	@Test
	public void testEquals() {
		LinkedListDeque<Integer> input = new LinkedListDeque<Integer>(5, 10 ,15);
		LinkedListDeque<Integer> actual = input;
		LinkedListDeque<Integer> expected = new LinkedListDeque<Integer>(5, 10, 15);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void testAddFirst() {
		LinkedListDeque<Integer> input1 = new LinkedListDeque<Integer>(1, 2, 3, 4, 5);
		input1.addFirst(0);
		LinkedListDeque<Integer>actual1 = input1;
		LinkedListDeque<Integer> expected1 = new LinkedListDeque<Integer>(0, 1, 2, 3, 4, 5);
		assertTrue(expected1.equals(actual1));

		LinkedListDeque<Integer> input2 = new LinkedListDeque<Integer>(8, 10);
		input2.addFirst(6);
		input2.addFirst(4);
		input2.addFirst(2);
		LinkedListDeque<Integer>actual2 = input2;
		LinkedListDeque<Integer> expected2 = new LinkedListDeque<Integer>(2, 4, 6, 8, 10);
		assertTrue(expected2.equals(actual2));
	}

	@Test
	public void testAddLast() {
		LinkedListDeque<Integer> input1 = new LinkedListDeque<Integer>(1, 2, 3, 4, 5);
		input1.addLast(6);
		LinkedListDeque<Integer>actual1 = input1;
		LinkedListDeque<Integer> expected1 = new LinkedListDeque<Integer>(1, 2, 3, 4, 5, 6);
		assertTrue(expected1.equals(actual1));

		LinkedListDeque<Integer> input2 = new LinkedListDeque<Integer>(2, 4);
		input2.addLast(6);
		input2.addLast(8);
		input2.addLast(10);
		LinkedListDeque<Integer>actual2 = input2;
		LinkedListDeque<Integer> expected2 = new LinkedListDeque<Integer>(2, 4, 6, 8, 10);
		assertTrue(expected2.equals(actual2));
	}

	@Test
	public void testIsEmpty() {
		LinkedListDeque<Integer> input1 = new LinkedListDeque<Integer>();
		assertTrue(input1.isEmpty());

		LinkedListDeque<Integer> input2 = new LinkedListDeque<Integer>(1, 2, 3);
		assertFalse(input2.isEmpty());
	}

	@Test
	public void testPrintDeque() {
		ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent1));
		PrintStream originalOut = System.out;
		try {
			outContent1.reset();
			LinkedListDeque<Integer> input1 = new LinkedListDeque<Integer>(1, 2, 3, 4, 5);
			input1.printDeque();
			assertEquals("1 2 3 4 5\n", outContent1.toString());

			outContent1.reset();
			LinkedListDeque<String> input2 = new LinkedListDeque<String>("My", "name", "is", "Henri");
			input2.printDeque();
			assertEquals("My name is Henri\n", outContent1.toString());
		} finally {
			System.setOut(originalOut);
		}
	}

	@Test
	public void testRemoveFirst() {
		LinkedListDeque<Integer> input1 = new LinkedListDeque<Integer>(1, 2, 3, 4, 5);
		Integer actual1 = input1.removeFirst();
		Integer expected1 = 1;
		assertEquals(expected1, actual1);
		assertEquals(4, input1.size());

		LinkedListDeque<Integer> input2 = new LinkedListDeque<Integer>();
		Integer actual2 = input2.removeFirst();
		Integer expected2 = null;
		assertEquals(expected2, actual2);
		assertEquals(0, input2.size());
	}

	@Test
	public void testRemoveLast() {
		LinkedListDeque<Integer> input1 = new LinkedListDeque<Integer>(1, 2, 3, 4, 5);
		Integer actual1 = input1.removeLast();
		Integer expected1 = 5;
		assertEquals(expected1, actual1);
		assertEquals(4, input1.size());

		LinkedListDeque<Integer> input2 = new LinkedListDeque<Integer>();
		Integer actual2 = input2.removeLast();
		Integer expected2 = null;
		assertEquals(expected2, actual2);
		assertEquals(0, input2.size());
	}

	@Test
	public void testGet() {
		LinkedListDeque<Integer> input1 = new LinkedListDeque<Integer>(1, 2, 3, 4, 5);
		Integer actual1 = input1.get(2);
		Integer expected1 = 3;
		assertEquals(expected1, actual1);

		LinkedListDeque<Integer> input2 = new LinkedListDeque<Integer>(2, 4, 6, 8);
		Integer actual2 = input2.get(4);
		Integer expected2 = null;
		assertEquals(expected2, actual2);
	}

	@Test
	public void testLinkedListDeque() {
		LinkedListDeque<Integer> input1 = new LinkedListDeque<Integer>(1, 2, 3, 4, 5);
		LinkedListDeque<Integer> actual1 = new LinkedListDeque<Integer>(input1);
		LinkedListDeque<Integer> expected1 = new LinkedListDeque<Integer>(1, 2, 3, 4, 5);
		assertTrue(expected1.equals(actual1));
	}

//	public static void main(String[] args) {
//		System.out.println("Running tests.\n");
//		addIsEmptySizeTest();
//		addRemoveTest();
//	}
} 