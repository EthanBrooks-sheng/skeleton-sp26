import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;

public class ArrayDeque61BTest {
    @Test
    public void addFirstAndAddLastTest() {
        Deque61B<Integer> expected = new ArrayDeque61B<>();
        assertThat(expected.toList()).isEmpty();

        expected.addFirst(1);  // [1]
        assertThat(expected.toList()).containsExactly(1).inOrder();
        expected.addLast(2);   // [1, 2]
        assertThat(expected.toList()).containsExactly(1, 2).inOrder();
        expected.addFirst(0);  // [0, 1, 2]
        expected.addLast(3);   // [0, 1, 2, 3]
        expected.addFirst(10); // [10, 0, 1, 2, 3]
        expected.addLast(15);  // [10, 0, 1, 2, 3, 15]
        expected.addLast(20);  // [10, 0, 1, 2, 3, 15, 20]
        assertThat(expected.toList()).containsExactly(10, 0, 1, 2, 3, 15, 20).inOrder();
        expected.addLast(25);  // [10, 0, 1, 2, 3, 15, 20, 25]
        assertThat(expected.toList()).containsExactly(10, 0, 1, 2, 3, 15, 20, 25).inOrder();
    }

    @Test
    public void getFirstAndGetLastTest() {
        Deque61B<Integer> expected = new ArrayDeque61B<>();
        assertThat(expected.getLast()).isNull();
        assertThat(expected.getFirst()).isNull();

        expected.addFirst(1); // [1]
        assertThat(expected.getFirst()).isEqualTo(1);
        expected.addLast(2);  // [1, 2]
        assertThat(expected.getLast()).isEqualTo(2);
        expected.addFirst(0); // [0, 1, 2]
        expected.addFirst(9); // [9, 0, 1, 2]
        expected.addFirst(8); // [8, 9, 0, 1, 2]
        expected.addFirst(7); // [7, 8, 9, 0, 1, 2]
        expected.addFirst(6); // [6, 7, 8, 9, 0, 1, 2]
        expected.addFirst(5); // [5, 6, 7, 8, 9, 0, 1, 2]
        assertThat(expected.getFirst()).isEqualTo(5);
        assertThat(expected.getLast()).isEqualTo(2);

    }

    @Test
    public void getTest() {
        Deque61B<Integer> expected = new ArrayDeque61B<>();
        assertThat(expected.get(0)).isNull();
        assertThat(expected.get(-1)).isNull();
        expected.addFirst(1);
        assertThat(expected.get(0)).isEqualTo(1);
        assertThat(expected.get(1)).isNull();
        expected.addLast(2);
        expected.addLast(3);
        expected.addLast(4);
        expected.addLast(5);
        expected.addFirst(0);
        expected.addFirst(9);
        assertThat(expected.get(0)).isEqualTo(9);
        assertThat(expected.get(1)).isEqualTo(0);
        assertThat(expected.get(6)).isEqualTo(5);

    }

    @Test
    public void isEmptyTest() {
        Deque61B<Integer> expected = new ArrayDeque61B<>();
        assertThat(expected.isEmpty()).isTrue();
        expected.addFirst(1);
        assertThat(expected.isEmpty()).isFalse();
    }

    @Test
    public void sizeTest() {
        Deque61B<Integer> expected = new ArrayDeque61B<>();
        assertThat(expected.size()).isEqualTo(0);
        expected.addFirst(1);
        assertThat(expected.size()).isEqualTo(1);
        expected.addLast(2);
        expected.addLast(3);
        assertThat(expected.size()).isEqualTo(3);
    }

    @Test
    public void removeFirstAndRemoveLastTest() {
        Deque61B<Integer> expected = new ArrayDeque61B<>();
        assertThat(expected.removeFirst()).isNull();
        assertThat(expected.removeLast()).isNull();

        expected.addFirst(1); // [1]
        expected.addLast(2);  // [1, 2]
        expected.addLast(3);  // [1, 2, 3]
        expected.addLast(4);  // [1, 2, 3, 4]
        expected.addLast(5);  // [1, 2, 3, 4, 5]
        assertThat(expected.removeFirst()).isEqualTo(1);
        expected.addFirst(2);
        assertThat(expected.removeLast()).isEqualTo(5);
        expected.addLast(10);
        assertThat(expected.toList()).containsExactly(2, 2, 3, 4, 10).inOrder();
    }

    @Test
    public void resizeTest() {
        Deque61B<Integer> expected = new ArrayDeque61B<>();
        for (int i = 0; i < 8; i++) {
            expected.addLast(i);
        }
        assertThat(expected.toList()).containsExactly(0, 1, 2, 3, 4, 5, 6, 7).inOrder();
        assertThat(expected.size()).isEqualTo(8);
        expected.addLast(8);
        assertThat(expected.toList()).containsExactly(0, 1, 2, 3, 4, 5, 6, 7, 8).inOrder();
        assertThat(expected.size()).isEqualTo(9);
    }

    @Test
    public void resizeDownTest() {
        Deque61B<Integer> expected = new ArrayDeque61B<>();
        for (int i = 0; i < 16; i++) {
            expected.addLast(i);
        }
        assertThat(expected.size()).isEqualTo(16);
        for (int i = 0; i < 13; i++) {
            expected.removeLast();
        }
        assertThat(expected.size()).isEqualTo(3);
        assertThat(expected.toList()).containsExactly(0, 1, 2).inOrder();
    }
}
