/**
 * Exception that is thrown when an element is read from an empty stack.
 */
public class StackEmptyException extends Exception {
    /**
     * Creates the exception with a custom message.
     *
     * @param message explanation of the error
     */
    public StackEmptyException(String message) {
        super(message);
    }
}
