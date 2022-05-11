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
        E result = peek();
        E target = (E) arr[size];
        arr[size] = null;

        siftDown(1, target);

        return result;
    }

    private void siftDown(int idx, E target) {
        if(comparator != null) {
            siftDownComparator(idx, target, comparator);
        }
        else {
            siftDownComparable(idx, target);
        }
    }

    private void siftDownComparator(int idx, E target, Comparator<? super E> comp) {

        arr[idx] = null;	// 삭제 할 인덱스의 노드를 삭제
        size--;

        int parent = idx;	// 삭제노드부터 시작 할 부모를 가리키는 변수
        int child;	// 교환 될 자식을 가리키는 변수

        // 왼쪽 자식 노드의 인덱스가 요소의 개수보다 작을 때 까지 반복
        while((child = getLeftChild(parent)) <= size) {

            int right = getRightChild(parent);	// 오른쪽 자식 인덱스

            Object childVal = arr[child];	// 왼쪽 자식의 값 (교환 될 값)

            /*
             *  오른쪽 자식 인덱스가 size를 넘지 않으면서
             *  왼쪽 자식이 오른쪽 자식보다 큰 경우
             *  재배치 할 노드는 작은 자식과 비교해야하므로 child와 childVal을
             *  오른쪽 자식으로 바꿔준다.
             */
            if(right <= size && comp.compare((E) childVal, (E) arr[right]) > 0) {
                child = right;
                childVal = arr[child];
            }

            // 재배치 할 노드가 자식 노드보다 작을경우 반복문을 종료한다.
            if(comp.compare(target ,(E) childVal) <= 0){
                break;
            }

            /*
             *  현재 부모 인덱스에 자식 노드 값을 대체해주고
             *  부모 인덱스를 자식 인덱스로 교체
             */
            arr[parent] = childVal;
            parent = child;
        }

        // 최종적으로 재배치 되는 위치에 타겟이 된 값을 넣어준다.
        arr[parent] = target;

        /*
         *  용적의 사이즈가 최소 용적보다는 크면서 요소의 개수가 전체 용적의 1/4일경우
         *  용적을 반으로 줄임(단, 최소용적보단 커야함)
         */
        if(arr.length > DEFAULT_CAPACITY && size < arr.length / 4) {
            resize(Math.max(DEFAULT_CAPACITY, arr.length / 2));
        }

    }

    // Comparable을 이용한 sift-down
    private void siftDownComparable(int idx, E target) {

        Comparable<? super E> comp = (Comparable<? super E>) target;

        arr[idx] = null;
        size--;

        int parent = idx;
        int child;

        while((child = getLeftChild(parent)) <= size) {

            int right = getRightChild(parent);

            Object childVal = arr[child];

            if(right <= size && ((Comparable<? super E>)childVal).compareTo((E)arr[right]) > 0) {
                child = right;
                childVal = arr[child];
            }

            if(comp.compareTo((E) childVal) <= 0){
                break;
            }
            arr[parent] = childVal;
            parent = child;

        }
        arr[parent] = comp;

        if(arr.length > DEFAULT_CAPACITY && size < arr.length / 4) {
            resize(Math.max(DEFAULT_CAPACITY, arr.length / 2));
        }

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
