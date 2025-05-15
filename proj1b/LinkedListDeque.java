/**
 * I choose to use circular sentinel,
 * which is the teacher's recommended way
 */
public class LinkedListDeque<T> implements Deque<T> {

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

    // circular sentinel
    public LinkedListDeque() {
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.pre = sentinel.next;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        Node temp = sentinel.next;
        sentinel.next = new Node(x, temp, sentinel);
        temp.pre = sentinel.next;
        size++;
    }

    @Override
    public void addLast(T x) {
        Node temp = sentinel.pre;
        sentinel.pre = new Node(x, sentinel, temp);
        temp.next = sentinel.pre;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.next == sentinel;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        if (isEmpty()) {
            System.out.println("");
            return;
        }
        Node cur = sentinel.next; // don't change the Deque, so we need cur
        while (cur != sentinel) {
            System.out.print(cur.next.val + " ");
            cur = cur.next;
        }
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node removalFirst = sentinel.next;
        sentinel.next = removalFirst.next; // update sentinel.next
        removalFirst.next.pre = sentinel;
        size--; // update size
        return removalFirst.val;
    }

    @Override
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

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) { // check index
            return null;
        }
        Node cur = sentinel; // do not change the link, so we need a temp
        for (int i = 0; i <= index; i++) {
            cur = cur.next;
        }

        return cur.val;
    }

    // here I created a helper method recursionHelper
    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return recursionHelper(sentinel.next, index).val;
    }

    private Node recursionHelper(Node n, int i) {
        if (i == 0) {
            return n;
        }
        return recursionHelper(n.next, i - 1);
    }
}
