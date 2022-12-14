package synthesizer;
import java.util.Iterator;
public interface BoundedQueue<T> extends Iterable<T> {
    /** return size of the buffer.*/
    int capacity();
    /** return number of items currently in the buffer.*/
    int fillCount();
    /** add item to the end.*/
    void enqueue(T x);
    /** delete and return item from the front.*/
    T dequeue();
    /** return (but do not delete) item from the front.*/
    T peek();

    /** iterator().*/
    Iterator<T> iterator();

    /** is the buffer empty. (fillCount equals zero)?*/
    default boolean isEmpty() {
        int fillcount = fillCount();
        return fillcount == 0;
    }
    /** is the buffer full. (fillCount is same as capacity)? */
    default boolean isFull() {
        return capacity() == fillCount();
    }

}
