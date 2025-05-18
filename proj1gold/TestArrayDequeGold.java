import static org.junit.Assert.*;

import org.junit.Test;

public class TestArrayDequeGold {

    StudentArrayDeque<Integer> testStudentArrayDeque = new StudentArrayDeque<>();
    ArrayDequeSolution<Integer> testArrayDequeSolution = new ArrayDequeSolution<>();

    @Test
    public void testDeque() {
        for (int i = 0; i < 1000; i++) {
            int testNum = StdRandom.uniform(4);
            Integer randomNum = StdRandom.uniform(10) + 1;
            test(testNum, randomNum);
        }
    }

    public void test(int x, Integer element) {
        Integer actual, expected;
        String msg = "";
        switch (x) {
            case 0:
                testStudentArrayDeque.addFirst(element);
                testArrayDequeSolution.addFirst(element);
                actual = testStudentArrayDeque.get(0);
                expected = testStudentArrayDeque.get(0);
                msg += "addFirst(" + element + ")\n";
                assertEquals(msg, expected, actual);
                break;
            case 1:
                testStudentArrayDeque.addLast(element);
                testArrayDequeSolution.addLast(element);
                actual = testStudentArrayDeque.get(testStudentArrayDeque.size() - 1);
                expected = testStudentArrayDeque.get(testArrayDequeSolution.size() - 1);
                msg += "addLast(" + element + ")\n";
                assertEquals(msg, expected, actual);
                break;
            case 2:
                if (!testStudentArrayDeque.isEmpty() && !testArrayDequeSolution.isEmpty()) {
                    actual = testStudentArrayDeque.removeFirst();
                    expected = testArrayDequeSolution.removeFirst();
                    msg += "removeFirst()\n";
                    assertEquals(msg, expected, actual);
                }
                break;
            case 3:
                if (!testStudentArrayDeque.isEmpty() && !testArrayDequeSolution.isEmpty()) {
                    actual = testStudentArrayDeque.removeLast();
                    expected = testArrayDequeSolution.removeLast();
                    msg += "removeLast()\n";
                    assertEquals(msg, expected, actual);
                }
                break;
        }


    }

}
