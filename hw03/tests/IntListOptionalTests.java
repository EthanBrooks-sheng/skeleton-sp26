import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Arrays;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IntListOptionalTests {
    // TBD
    @Test
    @Order(3)
    public void testSum() {
        // 测试 sum 函数是否可以将列表所有值进行求和
        // 检查只有 1 个元素和 3 个元素的列表
        int sum = 0;
        IntList L = new IntList(0, null);
        assertThat(L.sum()).isEqualTo(sum);
        sum = 7;
        L.addLast(3);
        L.addLast(4);
        assertThat(L.sum()).isEqualTo(sum);
    }

    @Test
    @Order(1)
    public void testAddLast() {
        int size = 2;
        IntList L = new IntList(0, null);
        L.addLast(1);
        assertThat(L.size()).isEqualTo(size);
        assertThat(L.get(0)).isEqualTo(0);
        assertThat(L.get(1)).isEqualTo(1);
    }

    @Test
    @Order(2)
    public void testAddFirst() {
        int size = 3;
        IntList L = new IntList(0, null);
        L.addFirst(1);
        L.addFirst(2);
        assertThat(L.size()).isEqualTo(size);
        assertThat(L.get(0)).isEqualTo(2);
        assertThat(L.get(1)).isEqualTo(1);
        assertThat(L.get(2)).isEqualTo(0);
    }
}
