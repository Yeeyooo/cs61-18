/** Second part of project1a.
 * Array implementation.
 * @author yeahooooo.
 */
public class ArrayDeque<T> {
    /** Main data structure for this implementation.*/
    private T[] items;

    /** The size of the Deque.*/
    private int size;

    /**The length of the array.*/
    private int length;
    /** the first node in the array.*/
    private int headsentinel;
    /** the last node int the array.*/
    private int tailsentinel;

    /** Constructor.*/
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        headsentinel = 4;
        tailsentinel = 4;
        length = 8;
    }
    /** advance the certain pointer.
     * @param index certain index
     * @param module module.
     * @return the advance result
     */
    private int advance(int index, int module) {
        index %= module;
        if (index == module - 1) {
            return 0;
        }
        return index + 1;

    }

    /** decrement the certain pointer.
     * @param index  index
     * @return  the back result
     */
    private int back(int index) {
        if (index == 0) {
            return length - 1;
        }
        return index - 1;

    }

    /**
     *  Make the length of the array larger.
     */
    private void grow() {
        T[] newarray = (T[]) new Object[length * 2];
        int ptr1 = headsentinel;
        int ptr2 = length;
        while (ptr1 != tailsentinel) {
            newarray[ptr2] = items[ptr1];
            ptr1 = advance(ptr1, length);
            ptr2 = advance(ptr2, length * 2);
        }
        items = newarray;
        headsentinel = length;
        tailsentinel = ptr2;
        length *= 2;

    }
    /** reduce the length of the array.*/
    private void shrink() {
        T[] newarray = (T[]) new Object[length / 2];
        int ptr1 = headsentinel;
        int ptr2 = length / 4;
        while (ptr1 != tailsentinel) {
            newarray[ptr2] = items[ptr1];
            ptr1 = advance(ptr1, length);
            ptr2 = advance(ptr2, length / 2);
        }
        items = newarray;
        headsentinel = length / 4;
        tailsentinel = ptr2;
        length /= 2;

    }

    /**
     * add the item to the head of the array.
     * @param item item to be inserted
     */
    public void addFirst(T item) {
        if (size == length) {
            grow();
        }
        headsentinel = back(headsentinel);
        items[headsentinel] = item;
        size += 1;

    }

    /**
     * add the item to the tail of the array.
     * @param item item to be inserted
     */
    public void addLast(T item) {
        if (size == length) {
            grow();
        }
        items[tailsentinel] = item;
        tailsentinel = advance(tailsentinel, length);
        size += 1;

    }

    /**
     * decide if the Deque is empty.
     * @return true if the Deque is empty,else false.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /** return the size of the deque.
     * @return  the size of the deque
     */
    public int size() {
        return size;
    }

    /** print the whole deque.*/
    public void printDeque() {
        if (size == 0) {
            return;
        }
        int iter = headsentinel;
        while (iter != tailsentinel) {
            System.out.print(items[iter] + " ");
            iter = advance(iter, length);
        }
    }

    /**
     * remove the first node in the deque.
     * @return  the remove item.
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if ((length / size) >= 4 && length >= 16) {
            shrink();
        }
        T result = items[headsentinel];
        headsentinel = advance(headsentinel, length);
        size -= 1;
        return result;
    }

    /**
     *  remove the last node in the deque.
     * @return the removed item.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if ((length / size) > 4 && length >=16) {
            shrink();
        }
        tailsentinel = back(tailsentinel);
        size -= 1;
        return items[tailsentinel];
    }

    /**
     * get the ith item.
     * @param index index
     * @return the item we want
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int iter = headsentinel;
        for(int i = 0;i < index; i++) {
            iter = advance(iter, length);
        }
        return items[iter];
    }

}
