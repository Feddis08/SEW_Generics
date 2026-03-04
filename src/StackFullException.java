/**
 * Exception that is thrown when an element is pushed to a full stack.
 */
public class StackFullException extends Exception {
    /**
     * Creates the exception with a custom message.
     *
     * @param message explanation of the error
     */
    public StackFullException(String message) {
        super(message);
    }
}
