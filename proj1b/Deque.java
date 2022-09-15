/**
 * The interface of Deque.
 * @param <T> generic type.
 * @author yeahooooo
 */
public interface Deque<T> {
    /** add the first item.
     * @param item the item to be added.
     */
    void addFirst(T item);

    /** add the last item.
     * @param item the item to be added.
     */
    void addLast(T item);

    /** check if the deque is empty.
     * @return return value.
     */
    boolean isEmpty();

    /** return the size of the deque.
     * @return return the size.
     */
    int size();
    /**print the whole Deque.*/
    void printDeque();

    /** remove the first item in the deque.
     * @return the removed item.
     */
    T removeFirst();

    /** remove the last item in the deque.
     * @return the removed item.
     */
    T removeLast();
    /** get ith item in the deque.
     * @param index the index of the item.
     * @return the item we want to get.
     */
    T get(int index);

}
