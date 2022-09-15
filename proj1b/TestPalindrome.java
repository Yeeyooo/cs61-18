import org.junit.Test;
import static org.junit.Assert.*;

/** testPalidrome.
 * @author yeahooooo
 */
public class TestPalindrome {
    /** static instantiation.*/
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
    public void testisPalindrome() {
        assertFalse(palindrome.isPalindrome("Dog"));
        assertTrue(palindrome.isPalindrome("abccba"));
        assertTrue(palindrome.isPalindrome("A"));
        assertFalse(palindrome.isPalindrome("Abbbccccccbbba"));
        assertTrue(palindrome.isPalindrome(""));
        CharacterComparator tmp = new OffByOne();
        assertTrue(palindrome.isPalindrome("bfopopgc", tmp));
        assertFalse(palindrome.isPalindrome("aaaaa", tmp));
        assertTrue(palindrome.isPalindrome("", tmp));
        assertTrue(palindrome.isPalindrome("A", tmp));


    }
}
