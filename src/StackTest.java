/**
 * Runnable test class for the generic stack implementation.
 */
public class StackTest {
    /**
     * Creates a new test runner.
     */
    public StackTest() {
    }

    /**
     * Entry point that executes all stack test scenarios.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Run the tests for Integer stacks first.
        testNumberStack();

        // Then run the tests for String stacks.
        testTextStack();

        // If the program reaches this line, all checks passed.
        System.out.println("All stack tests passed.");
    }

    private static void testNumberStack() {
        // Create a numeric stack with room for three values.
        Stack<Integer> numberStack = new Stack<>(3, 0, new Object[3]);

        try {
            // Push three values and check the list output.
            numberStack.push(10);
            numberStack.push(20);
            numberStack.push(30);
            assertEquals("10;20;30", numberStack.list(), "List after three pushes is wrong.");

            // Verify peek returns the top value but keeps the stack unchanged.
            assertEquals(30, numberStack.peek(), "Peek should return 30.");
            assertEquals(3, numberStack.size(), "Peek should not remove elements.");

            // Verify LIFO order with two pops.
            assertEquals(30, numberStack.pop(), "First pop should return 30.");
            assertEquals(20, numberStack.pop(), "Second pop should return 20.");
            assertEquals("10", numberStack.list(), "List after two pops is wrong.");

            // Fill to capacity again and verify overflow is raised.
            numberStack.push(99);
            numberStack.push(77);

            boolean overflowThrown = false;
            try {
                numberStack.push(55);
            } catch (StackFullException e) {
                overflowThrown = true;
            }
            assertTrue(overflowThrown, "Expected StackFullException when pushing into a full stack.");

            // Empty the stack completely.
            numberStack.pop();
            numberStack.pop();
            numberStack.pop();

            // Verify underflow when popping from an empty stack.
            boolean underflowThrown = false;
            try {
                numberStack.pop();
            } catch (StackEmptyException e) {
                underflowThrown = true;
            }
            assertTrue(underflowThrown, "Expected StackEmptyException when popping from empty stack.");

        } catch (StackFullException | StackEmptyException e) {
            throw new RuntimeException("Unexpected exception in number stack test: " + e.getMessage(), e);
        }
    }

    private static void testTextStack() {
        // Use the no-arg constructor to test default-capacity behavior.
        Stack<String> textStack = new Stack<>();

        try {
            // Push text values and verify the serialized list.
            textStack.push("alpha");
            textStack.push("beta");
            textStack.push("gamma");
            assertEquals("alpha;beta;gamma", textStack.list(), "Text list output is wrong.");

            // Check top value and then pop values in reverse insertion order.
            assertEquals("gamma", textStack.peek(), "Peek should return gamma.");
            assertEquals("gamma", textStack.pop(), "First pop should return gamma.");
            assertEquals("beta", textStack.pop(), "Second pop should return beta.");
            assertEquals("alpha", textStack.pop(), "Third pop should return alpha.");

            // Verify empty list representation after removing all elements.
            assertEquals("", textStack.list(), "List should be empty after all pops.");

            // Verify underflow on peek for empty stack.
            boolean emptyPeekThrown = false;
            try {
                textStack.peek();
            } catch (StackEmptyException e) {
                emptyPeekThrown = true;
            }
            assertTrue(emptyPeekThrown, "Expected StackEmptyException on peek with empty stack.");

        } catch (StackFullException | StackEmptyException e) {
            throw new RuntimeException("Unexpected exception in text stack test: " + e.getMessage(), e);
        }
    }

    private static void assertEquals(Object expected, Object actual, String message) {
        if (expected == null && actual == null) {
            return;
        }
        if (expected != null && expected.equals(actual)) {
            return;
        }
        throw new AssertionError(message + " Expected: " + expected + ", actual: " + actual);
    }

    private static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
