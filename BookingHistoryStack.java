package movieBooking;
import java.util.LinkedList;

public class BookingHistoryStack<T> {
    private LinkedList<T> stack;

    public BookingHistoryStack() {
        stack = new LinkedList<>();
    }

    public void push(T item) {
        stack.push(item);
    }

    public T pop() {
        return stack.pop();
    }

    public T peek() {
        return stack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public String toString() {
        return stack.toString();
    }
}

