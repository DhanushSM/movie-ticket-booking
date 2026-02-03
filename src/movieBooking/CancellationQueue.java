package movieBooking;

import java.util.LinkedList;
import java.util.Queue;

public class CancellationQueue<T> {
    private Queue<T> queue;

    public CancellationQueue() {
        this.queue = new LinkedList<>();
    }

    public void enqueue(T item) {
        queue.add(item);
    }

    public T dequeue() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
