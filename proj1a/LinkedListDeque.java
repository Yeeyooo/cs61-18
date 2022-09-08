/**first part of the project1A.
 * Deque implemented by Linked List.
 * @param <T> generic type.
 * @author yeahooooo.
 */
public class LinkedListDeque<T> {
    /**inner class Node.*/
    private class Node {
        /**the item stored on this node.*/
        private T item;
        /**the Node before this Node.*/
        private Node prev;
        /**the Node after this Node.*/
        private Node next;

        /**constructor for Node.
         * @param n  the value of the item.
         * @param pprev the value of the prev pointer.
         * @param nnext  the value of the next pointer.
         */
        public Node(T n, Node pprev, Node nnext) {
            item = n;
            prev = pprev;
            next = nnext;
        }

        /**
         * Constructor.
         * @param pprev  the value of the prev pointer.
         * @param nnext  the value of the next pointer.
         */
        public Node(Node pprev, Node nnext) {
            prev = pprev;
            next = nnext;
        }

    }
    /** sentinel node.*/
    private Node sentinel;
    /** the size of the deque.*/
    private int size;


    /** Constructor.*/
    public LinkedListDeque() {
        sentinel = new Node(null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /**
     * judge if the deque is empty.
     * @return true if the deque is empty, else false.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * return the size of the deque.
     * @return size
     */
    public int size() {
        return size;
    }

    /**
     * add a item to the beginning of the deque.
     * @param item the value of the item to be inserted.
     */
    public void addFirst(T item) {
        Node newnode = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = newnode;
        sentinel.next = newnode;
        size += 1;
    }

    /**
     * add a item to the end of the deque.
     * @param item  the value of the item to be inserted.
     */
    public void addLast(T item) {
        Node newnode = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = newnode;
        sentinel.prev = newnode;
        size += 1;
    }

    /**
     * remove the first node of the deque.
     * @return  the value of the removed item.
     */
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
     * remove the last node of the deque.
     * @return the value of the removed item.
     */
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
     * get a certain item.
     * @param index index
     * @return the value of the item we want.
     */
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

    /**
     * helper method.
     * @param begin the beginning of the search.
     * @param index index.
     * @return the value of the item we want.
     */
    private T getRecursiveHelper(Node begin, int index) {
        if (index == 0) {
            return begin.item;
        }
        return getRecursiveHelper(begin.next, index - 1);
    }

    /**
     * recursively get implementation.
     * @param index index
     * @return the value of the item we want.
     */
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);

    }

    /** print the whole deque.*/
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
