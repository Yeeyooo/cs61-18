/** Array Deque.
 * @author yeahooooo
 * @param <T> generic type
 */
public class ArrayDeque<T> implements Deque<T> {

    /** array of items.*/
    private T[] items;

    /**The size of the Deque.*/
    private int size;

    /**The length of the array.*/
    private int length;

    /** headsentinel.*/
    private int headsentinel;

    /** tailsentinel.*/
    private int tailsentinel;

    /** Constructor.*/
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        headsentinel = 4;
        tailsentinel = 4;
        length = 8;
    }

    /**
     * advance the pointer.
     * @param index index.
     * @param module module.
     * @return return the new index.
     */
    public int advance(int index, int module) {
        index %= module;
        if (index == module - 1) {
            return 0;
        }
        return index + 1;

    }

    /**
     * back the pointer.
     * @param index index
     * @return return new pointer.
     */
    public int back(int index) {
        if (index == 0) {
            return length - 1;
        }
        return index - 1;

    }


    /** make the size of deque larger.*/
    public void grow() {
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

    /** make the size of the deque smaller.*/
    public void shrink() {
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

    /** add first item.
     * @param item the item to be added.
     */
    @Override
    public void addFirst(T item) {
        if (size == length) {
            grow();
        }
        if (size == 0) {
            items[headsentinel] = item;
            size += 1;
        } else {
            headsentinel = back(headsentinel);
            items[headsentinel] = item;
            size += 1;
        }

    }

    /**
     * add last item.
     * @param item the item to be added.
     */
    @Override
    public void addLast(T item) {
        if (size == length) {
            grow();
        }
        if (size == 0) {
            items[tailsentinel] = item;
            size += 1;
        } else {
            tailsentinel = advance(tailsentinel, length);
            items[tailsentinel] = item;
            size += 1;
        }

    }

    /**
     * check if the deque is empty.
     * @return return value.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * get the size of the deque.
     * @return the size.
     */
    @Override
    public int size() {
        return size;
    }

    /** print the whole deque.*/
    @Override
    public void printDeque() {
        if (size == 0) {
            return;
        }
        int iter = headsentinel;
        while (iter != tailsentinel) {
            System.out.print(items[iter] + " ");
            iter = advance(iter, length);
        }
        System.out.print(items[iter] + " ");

    }

    /** remove the first item.
     * @return removed item.
     */
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if ((length / size) > 4) {
            shrink();
        }
        T result = items[headsentinel];
        headsentinel = advance(headsentinel, length);
        size -= 1;
        return result;

    }

    /** remove the last item.
     * @return removed item.
     */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if ((length / size) > 4) {
            shrink();
        }
        T result = items[tailsentinel];
        tailsentinel = back(tailsentinel);
        size -= 1;
        return result;
    }

    /** get the specific item.
     * @param index the index of the item.
     * @return the item we want to get.
     */
    @Override
    public T get(int index) {
        if (size == 0 || (index < 0 || index > items.length - 1)) {
            return null;
        }
        int iter = headsentinel;
        while (index > 0) {
            iter = advance(iter, length);
        }
        return items[iter];

    }
}

