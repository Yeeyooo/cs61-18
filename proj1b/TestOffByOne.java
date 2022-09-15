import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    /** static instantiation.*/
    static CharacterComparator offByOne = new OffByOne();
    @Test
    public void testequalChars() {
        assertTrue(offByOne.equalChars('b', 'c'));
        assertFalse(offByOne.equalChars('d', 'n'));
        assertTrue(offByOne.equalChars('p', 'o'));
        assertFalse(offByOne.equalChars('r', 'x'));
        assertTrue(offByOne.equalChars('&','%'));
        assertFalse(offByOne.equalChars('a', 'A'));
    }

}
