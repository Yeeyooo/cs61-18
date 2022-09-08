//import javax.lang.model.type.ArrayType;

public class ArrayDeque<T> {

    private T[] items;

    /**The size of the Deque.*/
    private int size;

    /**The length of the array.*/
    private int length;
    private int headsentinel;
    private int tailsentinel;
    public ArrayDeque() {
        items = (T[])new Object[8];
        size = 0;
        headsentinel = 4;
        tailsentinel = 4;
        length = 8;
    }
    public int advance(int index,int module) {
        index %= module;
        if(index == module-1) {
            return 0;
        }
        return index+1;

    }
    public int back(int index) {
        if(index == 0) {
            return length - 1;
        }
        return index-1;

    }


    public void grow() {
        T[] newarray = (T[])new Object[length*2];
        int ptr1 = headsentinel;
        int ptr2 = length;
        while(ptr1 != tailsentinel) {
            newarray[ptr2] = items[ptr1];
            ptr1 = advance(ptr1,length);
            ptr2 = advance(ptr2,length*2);
        }
        items = newarray;
        headsentinel = length;
        tailsentinel = ptr2;
        length*=2;

    }

    public void shrink() {
        T[] newarray = (T[])new Object[length/2];
        int ptr1 = headsentinel;
        int ptr2 = length/4;
        while(ptr1 != tailsentinel) {
            newarray[ptr2] = items[ptr1];
            ptr1 = advance(ptr1,length);
            ptr2 = advance(ptr2,length/2);
        }
        items = newarray;
        headsentinel = length/4;
        tailsentinel = ptr2;
        length /= 2;

    }
    public void addFirst(T item) {
        if(size == length) {
            grow();
        }
        if(size == 0) {
            items[headsentinel] = item;
            size += 1;
        }
        else {
            headsentinel = back(headsentinel);
            items[headsentinel] = item;
            size+=1;
        }

    }
    public void addLast(T item) {
        if(size == length) {
            grow();
        }
        if(size == 0) {
            items[tailsentinel] = item;
            size += 1;
        }
        else {
            tailsentinel = advance(tailsentinel,length);
            items[tailsentinel] = item;
            size += 1;
        }

    }
    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
    public void printDeque() {
        if(size == 0)
            return;
        int iter = headsentinel;
        while(iter != tailsentinel) {
            System.out.print(items[iter]+" ");
            iter = advance(iter,length);
        }
        System.out.print(items[iter]+" ");
    }
    public T removeFirst() {
        if(size == 0) {
            return null;
        }
        if((length/size)>4) {
            shrink();
        }
        T result = items[headsentinel];
        headsentinel = advance(headsentinel,length);
        size -= 1;
        return result;
    }
    public T removeLast() {
        if(size == 0)
            return null;
        if((length/size)>4) {
            shrink();
        }
        T result = items[tailsentinel];
        tailsentinel = back(tailsentinel);
        size -= 1;
        return result;
    }
    public T get(int index) {
        if(size==0||(index<0||index>items.length-1))
            return null;
        int iter = headsentinel;
        while(index > 0) {
            iter = advance(iter,length);
        }
        return items[iter];
//        if((tailsentinel<=headsentinel&&index>=tailsentinel&&index<=headsentinel)||
//                (headsentinel>=tailsentinel&&index>=headsentinel&&index<=tailsentinel)) {
//            return items[index];
//        }
//        else {
//            return null;
//        }
    }
    public static void main(String[] args){
        ArrayDeque<Integer> L = new ArrayDeque<>();
        L.addFirst(1);
        L.addLast(2);
        L.addLast(3);
        L.addFirst(40);
        L.printDeque();
    }
}
