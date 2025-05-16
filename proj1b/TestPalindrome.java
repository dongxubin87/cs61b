import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class TestPalindrome {
    /*// You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
     */
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        // test empty
        String a = "";
        assertTrue(palindrome.isPalindrome(a));
        // test 1 char
        String b = "x";
        assertTrue(palindrome.isPalindrome(b));
        // test correct case
        String c = "cs61b16sc";
        assertTrue(palindrome.isPalindrome(c));
        // test wrong case
        String d = "abcdefg";
        assertFalse(palindrome.isPalindrome(d));

        OffByOne obo = new OffByOne();

        assertTrue(obo.equalChars('a', 'b'));
        assertTrue(obo.equalChars('r', 'q'));

        assertFalse(obo.equalChars('a', 'e'));
        assertFalse(obo.equalChars('a', 'a'));
        assertFalse(obo.equalChars('z', 'a'));
    }
}
