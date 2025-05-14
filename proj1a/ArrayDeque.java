public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int capacity;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
        capacity = 8;
    }

    public void resize(int x) {
        T[] temp = (T[]) new Object[x * 2];
        for (int i = 0; i < size; i++, nextFirst++) {
            temp[0] = items[(nextFirst + 1) % capacity];
        }
        items = temp;
        nextLast = nextFirst;
        nextFirst = capacity - 1;

    }

    public void addFirst(T item) {
        if (size == capacity) {
            resize(capacity);
        }
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1 + capacity) % capacity;
        size++;
    }

    public void addLast(T item) {
        if (size == 0) {
            resize(capacity);
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
            System.out.println(items[(curFirst + 1) % size] + " ");
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size--;
        T removedValue = items[(nextFirst + 1) % size];
        items[(nextFirst + 1) % size] = null;
        nextFirst = (nextFirst + 1) % size;
        return removedValue;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size--;
        T removedValue = items[(nextLast - 1 + size) % size];
        items[(nextLast - 1 + size) % size] = null;
        nextLast = (nextLast - 1 + size) % size;
        return removedValue;
    }
}
