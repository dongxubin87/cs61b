public class ArrayDeque<T> {
    private T[] items;
    private int size; // the number 0f elements
    private int nextFirst;
    private int nextLast;
    private int capacity; // the length of ArrayDeque

    // Initialize ArrayDeque
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0; // it can be any number(0~7)
        nextLast = 1; //  nextFirst + 1
        capacity = 8; // default length of array
    }

    /**
     * I made some mistakes here,
     * oldArray = {e,f,g,null,null,a,b,c}
     * then we get newArray = {a,b,c,e,f,g,null,null,null,...}
     */
    private void resize(int x) {
        T[] temp = (T[]) new Object[x];
        for (int i = 0; i < size; i++, nextFirst++) {
            temp[i] = items[(nextFirst + 1) % capacity];
        }
        capacity = x;
        items = temp;
        nextLast = size;
        nextFirst = capacity - 1;

    }

    public void addFirst(T item) {
        if (size == capacity) {
            resize(capacity * 2); // when needed, double capacity
        }
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1 + capacity) % capacity;
        size++;
    }

    public void addLast(T item) {
        if (size == capacity) {
            resize(capacity * 2);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % capacity;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (isEmpty()) {
            System.out.println("");
        }
        int curFirst = nextFirst;
        for (int i = 0; i < size; i++, curFirst++) {
            System.out.print(items[(curFirst + 1) % capacity] + " ");
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        T removedValue = items[(nextFirst + 1) % capacity];
        items[(nextFirst + 1) % capacity] = null;
        nextFirst = (nextFirst + 1) % capacity;

        size--;
        if ((double) size / capacity <= 0.25 && capacity >= 16) {
            resize(capacity / 2); // here I half the capacity
        }
        return removedValue;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        T removedValue = items[(nextLast - 1 + capacity) % capacity];
        items[(nextLast - 1 + capacity) % capacity] = null;
        nextLast = (nextLast - 1 + capacity) % capacity;

        size--;
        if ((double) size / capacity <= 0.25 && capacity >= 16) {
            resize(capacity / 2);
        }
        return removedValue;
    }

    public T get(int index) {
        if (isEmpty() || index < 0 || index >= size) {
            return null;
        }
        return items[(nextFirst + 1 + capacity + index) % capacity];
    }
}
