import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T>{

    private int nextFirst, nextLast, size, length;
    T[] items;

    public ArrayDeque61B() {
        size = 0;
        length = 8;
        items = (T []) new Object[length];
        nextFirst = 3;
        nextLast = 4;
    }

    /**
     * Add {@code x} to the front of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addFirst(T x) {
        items[nextFirst] = x;
        size++;
        nextFirst--;
        if (nextFirst < 0)
            nextFirst = length - 1;
    }

    /**
     * Add {@code x} to the back of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addLast(T x) {
        items[nextLast] = x;
        size++;
        nextLast = (nextLast + 1) % length;
    }

    /**
     * Returns a List copy of the deque. Does not alter the deque.
     *
     * @return a new list copy of the deque.
     */
    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        if (size == 0) {
            return List.of();
        }
        int index = nextLast;
        int tempLength = length;
        while (tempLength > 0) {
            if (items[index] != null) {
                returnList.add(items[index]);
            }
            index = (index + 1) % length;
            tempLength--;
        }
        return returnList;
    }

    /**
     * Returns if the deque is empty. Does not alter the deque.
     *
     * @return {@code true} if the deque has no elements, {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * Returns the size of the deque. Does not alter the deque.
     *
     * @return the number of items in the deque.
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     * Return the element at the front of the deque, if it exists.
     *
     * @return element, otherwise {@code null}.
     */
    @Override
    public T getFirst() {
        return null;
    }

    /**
     * Return the element at the back of the deque, if it exists.
     *
     * @return element, otherwise {@code null}.
     */
    @Override
    public T getLast() {
        return null;
    }

    /**
     * Remove and return the element at the front of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeFirst() {
        return null;
    }

    /**
     * Remove and return the element at the back of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeLast() {
        return null;
    }

    /**
     * The Deque61B abstract data type does not typically have a get method,
     * but we've included this extra operation to provide you with some
     * extra programming practice. Gets the element, iteratively. Returns
     * null if index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T get(int index) {
        return null;
    }

    /**
     * This method technically shouldn't be in the interface, but it's here
     * to make testing nice. Gets an element, recursively. Returns null if
     * index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T getRecursive(int index) {
        return null;
    }

    public static void main(String[] args) {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addFirst(1);  // [1]
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(9);
        deque.addLast(5);
        deque.addLast(6);
        deque.addLast(7);
        deque.addFirst(8);
    }
}
