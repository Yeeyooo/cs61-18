package synthesizer;
import java.util.Iterator;

/** ArrayRingBuffer.
 * @author yeahooooo.
 * @param <T> generic type.
 */
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /** Index for the next dequeue or peek. */
    private int first;
    /** Index for the next enqueue. */
    private int last;
    /** Array for storing the buffer data. */
    private T[] rb;

    /**Create a new ArrayRingBuffer with the given capacity.
     * @param capacity the capacity of arrayringbuffer
     */
    public ArrayRingBuffer(int capacity) {
        this.first = 0;
        this.last = 0;
        this.fillCount = 0;
        this.capacity = capacity;
        rb = (T[]) new Object[capacity];

    }

    /** advance the pointer.
     * @param pointer the pointer to be advanced.
     * @return the new pointer.
     */
    private int advance(int pointer) {
        if (pointer == this.capacity - 1) {
            pointer = 0;
        } else {
            pointer += 1;
        }
        return pointer;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last = advance(last);
        fillCount += 1;

    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T head = rb[first];
        fillCount -= 1;
        first = advance(first);
        return head;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    /** to support iteration.*/
    @Override
    public Iterator<T> iterator() {
        return new Helpiterator();
    }

    /** private helper method.*/
    private class Helpiterator implements Iterator<T> {
        /** pos.*/
        private int pos;
        /** constructor.*/
        Helpiterator() {
            pos = 0;
        }

        /** hasNext.
         * @return return a boolean value.
         */
        public boolean hasNext() {
            return pos < capacity;
        }

        /** next.
         * @return return the next item and increment the pos.
         */
        public T next() {
            T item = rb[pos];
            pos += 1;
            return item;
        }

    }

}
