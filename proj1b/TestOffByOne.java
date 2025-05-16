import org.junit.Test;

import static org.junit.Assert.*;

public class TestOffByOne {
    /*
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
     */
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testOffByOne() {
        assertTrue(offByOne.equalChars('f', 'e'));
        assertTrue(offByOne.equalChars('e', 'f'));
        assertFalse(offByOne.equalChars('E', 'f'));
        assertFalse(offByOne.equalChars('F', 'e'));
        assertTrue(offByOne.equalChars('E', 'F'));
    }

}
