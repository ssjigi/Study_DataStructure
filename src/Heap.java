import java.util.Comparator;

public class Heap<E> {
    public Heap() {

    }

    public Heap(int capacity) {
    }

    public Heap(Comparator<? super E> comparator) {

    }

    public Heap(int capacity, Comparator<? super E> comparator) {

    }

    private int getParent(int index) {
        return index / 2;
    }

    private int getLeftChild(int index) {
        return index * 2;
    }

    private int getRightChild(int index) {
        return (index * 2) + 1;
    }

    private void resize() {

    }

    public void add(E value) {

    }

    private void siftUp() {

    }

    public E remove() {
        return null;
    }

    private void siftDown(int index, E target) {

    }

    public int size() {
        return 0;
    }

    public E peek() {
        return null;
    }

    public boolean isEmpty() {
        return false;
    }

    public Object[] toArray() {
        return null;
    }
}
