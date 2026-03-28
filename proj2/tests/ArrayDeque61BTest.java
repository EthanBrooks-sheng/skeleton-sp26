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
}
