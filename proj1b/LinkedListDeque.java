/**first part of the project1A.
 * Deque implemented by Linked List
 * @param <T>
 * @author yeahooooo
 */
public class LinkedListDeque<T> implements Deque<T> {
    /**inner class Node.*/
    public class Node {
        /**the item stored on this node.*/
        private T item;
        /**the Node before this Node.*/
        private Node prev;
        /**the Node after this Node.*/
        private Node next;

        /**constructor for Node.
         * @param n n
         * @param pprev pprev
         * @param nnext nnext
         */
        public Node(T n, Node pprev, Node nnext) {
            item = n;
            prev = pprev;
            next = nnext;
        }

        /** Constructor for Node.
         * @param pprev  pprev
         * @param nnext nnext
         */
        public Node(Node pprev, Node nnext) {
            prev = pprev;
            next = nnext;
        }

    }

    /** sentinel node.*/
    private Node sentinel;
    /** size of the deque.*/
    private int size;

    /** constructor.*/
    public LinkedListDeque() {
        sentinel = new Node(null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /**
     *  whether the deque is empty.
     * @return return value.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * size.
     * @return the size of the deque.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * add the first item.
     * @param item the item to be added.
     */
    @Override
    public void addFirst(T item) {
        Node newnode = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = newnode;
        sentinel.next = newnode;
        size += 1;
    }

    /**
     * add the last item.
     * @param item the item to be added.
     */
    @Override
    public void addLast(T item) {
        Node newnode = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = newnode;
        sentinel.prev = newnode;
        size += 1;
    }

    /**
     * remove.
     * @return removed item.
     */
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T result = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return result;
    }

    /**
     * remove.
     * @return removed item
     */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T result = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return result;
    }

    /**
     * get.
     * @param index the index of the item.
     * @return return
     */
    @Override
    public T get(int index) {
        if (index < 0 || index > size - 1 || this.size() == 0) {
            return null;
        }
        Node temp = sentinel;
        for (int i = 0; i <= index; i++) {
            temp = temp.next;
        }
        return temp.item;

    }

    /** recursive.
     * @param begin begin
     * @param index index
     * @return return
     */
    public T getRecursiveHelper(Node begin, int index) {
        if (index == 0) {
            return begin.item;
        }
        return getRecursiveHelper(begin.next, index - 1);
    }

    /**
     * recursive.
     * @param index index
     * @return return
     */
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);

    }

    @Override
    public void printDeque() {
        if (size == 0) {
            return;
        }
        Node iter = sentinel.next;
        while (iter != sentinel) {
            System.out.print(iter.item + " ");
            iter = iter.next;
        }

    }
}
