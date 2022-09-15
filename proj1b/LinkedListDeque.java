/**first part of the project1A
 * Deque implemented by Linked List
 * @param <T>
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

        /**constructor for Node.*/
        public Node(T n, Node pprev, Node nnext) {
            item = n;
            prev = pprev;
            next = nnext;
        }

        /** Constructor for Node.*/
        public Node(Node pprev, Node nnext) {
            prev = pprev;
            next = nnext;
        }

    }
    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null,null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }
    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public int size() {
        return size;
    }
    @Override
    public void addFirst(T item) {
        Node newnode = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = newnode;
        sentinel.next = newnode;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        Node newnode=new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = newnode;
        sentinel.prev = newnode;
        size += 1;
    }
    @Override
    public T removeFirst() {
        if(size == 0) {
            return null;
        }
        T result = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return result;
    }

    @Override
    public T removeLast() {
        if(size == 0) {
            return null;
        }
        T result = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return result;
    }
    @Override
    public T get(int index) {
        if(index < 0 || index > size-1 || this.size() == 0) {
            return null;
        }
        Node temp = sentinel;
        for(int i = 0; i <= index; i++) {
            temp = temp.next;
        }
        return temp.item;

    }

    public T getRecursiveHelper(Node begin, int index) {
        if(index == 0) {
            return begin.item;
        }
        return getRecursiveHelper(begin.next,index-1);
    }

    public T getRecursive(int index) {
        if(index>=size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);

    }

    @Override
    public void printDeque() {
        if(size == 0) {
            return;
        }
        Node iter = sentinel.next;
        while(iter != sentinel) {
            System.out.print(iter.item+" ");
            iter = iter.next;
        }

    }
}
