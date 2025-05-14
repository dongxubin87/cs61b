public class LinkedListDeque<T> {

    private class Node {
        private T val;
        private Node pre;
        private Node next;

        Node() {
            val = null;
            pre = this;
            next = this;
        }

        Node(T i, Node n, Node p) {
            val = i;
            next = n;
            pre = p;
        }

    }

    private int size;
    private Node sentinel;

    public LinkedListDeque() {
        sentinel = new Node();
        size = 0;
    }

    public LinkedListDeque(T x) {
        sentinel = new Node();
        sentinel.next = new Node(x, sentinel, sentinel);
        sentinel.pre = sentinel.next;
        size = 1;
    }

    public void addFirst(T x) {
        Node temp = sentinel.next;
        sentinel.next = new Node(x, temp, sentinel);
        temp.pre = sentinel.next;
        size++;
    }

    public void addLast(T x) {
        Node temp = sentinel.pre;
        sentinel.pre = new Node(x, sentinel, temp);
        temp.next = sentinel.pre;
        size++;
    }

    public boolean isEmpty() {
        return sentinel.next == sentinel;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (isEmpty()) {
            System.out.println("");
            return;
        }
        Node cur = sentinel.next;
        while (cur != sentinel) {
            System.out.print(cur.next.val + " ");
            cur = cur.next;
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node removalFirst = sentinel.next;
        sentinel.next = removalFirst.next;
        removalFirst.next.pre = sentinel;
        size--;
        return removalFirst.val;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node removalLast = sentinel.pre;
        sentinel.pre = removalLast.pre;
        removalLast.pre.next = sentinel;
        size--;
        return removalLast.val;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node cur = sentinel;
        for (int i = 0; i <= index; i++) {
            cur = cur.next;
        }

        return cur.val;
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return recursion(sentinel.next, index).val;
    }

    private Node recursion(Node n, int i) {
        if (i == 0) {
            return n;
        }
        return recursion(n.next, i - 1);
    }
}
