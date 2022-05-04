import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class Heap<E> {
    private static final boolean DEBUG = false;
    private static final int DEFAULT_CAPACITY = 10;

    private Object[] arr;
    private int size;
    private final Comparator<? super E> comparator;

    public Heap() {
        this(null);
    }

    public Heap(Comparator<? super E> comparator) {
        this.arr = new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.comparator = comparator;
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

    private void resize(int capacity) {
//        Object[] newArr = new Object[capacity];
//
//        // 새 배열에 기존에 있던 배열의 요소 모두 복사
//        for (int i = 1; i <= size; i++) {
//            newArr[i] = arr[i];
//        }

        Object[] newArr = new Object[capacity];
        System.arraycopy(arr, 1, newArr, 1, size);

        this.arr = newArr;
    }

    public void add(E value) {
        if (arr.length == size + 1) {
            resize(arr.length * 2);
        }

        // 벼일 index 1 부터 채워 나간다. (getParent(), getRightChild(), getLeftChild() 적용 위해)
        siftUp(size + 1, value);
        size++;
    }

    private void siftUp(int idx, E value) {
        if (comparator != null) {
            siftUpComparator(idx, value, comparator);
        } else {
            siftUpComparable(idx, value);
        }
    }

    private void siftUpComparable(int idx, E value) {
        Comparable comp = (Comparable) value;

        while(idx > 1) {
            int parent = getParent(idx);
            Object parentVal = arr[parent];
            Log("ind = " + idx + ", val = " + comp + ", parent = " + parent + ", parentVal = " + parentVal);

            // 입력 값이 같거나 크면 그대로 현재(마지막) index 에 저장
            if (comp.compareTo(parentVal) >= 0) {
                break;
            }

            // 입력 값이 작으면 부모값을 현재 index 에 저장.
            Log("set idx " + idx + ", parentVal = " + parentVal);
            arr[idx] = parentVal;
            idx = parent;
        }
        Log("set idx " + idx + ", val = " + comp);
        arr[idx] = comp;
    }

    private void siftUpComparator(int idx, E value, Comparator<? super E> comparator) {

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
        if (arr[1] == null) {
            throw new NoSuchElementException();
        }

        return (E) arr[1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Object[] toArray() {
        Object[] newArr = new Object[size];
        System.arraycopy(arr, 1, newArr, 0, size);
        return newArr;
//        return Arrays.copyOf(arr, size + 1);
    }

    private void Log(String log) {
        if (DEBUG) System.out.println(log);
    }
}
