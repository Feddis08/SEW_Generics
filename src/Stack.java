/**
 * Generic stack implementation based on an array.
 *
 * @param <T> element type stored in the stack
 */
public class Stack<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private final int maxSize;
    private int top;
    private Object[] items;

    /**
     * Creates an empty stack with a default capacity.
     */
    public Stack() {
        this.maxSize = DEFAULT_CAPACITY;
        this.top = 0;
        this.items = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Creates a stack with all attribute values provided from outside.
     *
     * @param maxSize maximum number of elements the stack can contain
     * @param top current element count (next free index)
     * @param items backing object array
     */
    public Stack(int maxSize, int top, Object[] items) {
        if (maxSize < 0) {
            throw new IllegalArgumentException("maxSize must not be negative.");
        }
        if (top < 0 || top > maxSize) {
            throw new IllegalArgumentException("top must be between 0 and maxSize.");
        }
        if (items == null || items.length < maxSize) {
            throw new IllegalArgumentException("items array must have at least maxSize elements.");
        }

        this.maxSize = maxSize;
        this.top = top;
        this.items = items;
    }

    /**
     * Pushes an element on top of the stack.
     *
     * @param element element to push
     * @throws StackFullException when the stack is already full
     */
    public void push(T element) throws StackFullException {
        if (top == maxSize) {
            throw new StackFullException("Stack overflow: cannot push to a full stack.");
        }

        items[top] = element;
        top++;
    }

    /**
     * Removes and returns the top element from the stack.
     *
     * @return top element
     * @throws StackEmptyException when the stack is empty
     */
    @SuppressWarnings("unchecked")
    public T pop() throws StackEmptyException {
        if (top == 0) {
            throw new StackEmptyException("Stack underflow: cannot pop from an empty stack.");
        }

        top--;
        T value = (T) items[top];
        items[top] = null;
        return value;
    }

    /**
     * Returns the top element without removing it from the stack.
     *
     * @return top element
     * @throws StackEmptyException when the stack is empty
     */
    @SuppressWarnings("unchecked")
    public T peek() throws StackEmptyException {
        if (top == 0) {
            throw new StackEmptyException("Stack is empty: cannot peek.");
        }

        return (T) items[top - 1];
    }

    /**
     * Lists all elements from bottom to top separated by semicolons.
     *
     * @return stack content as a semicolon-separated string
     */
    public String list() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < top; i++) {
            if (i > 0) {
                builder.append(';');
            }
            builder.append(items[i]);
        }

        return builder.toString();
    }

    /**
     * Returns the number of currently stored elements.
     *
     * @return current stack size
     */
    public int size() {
        return top;
    }
}
