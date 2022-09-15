import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {


        static CharacterComparator offByN = new OffByN(4);

        @Test
    public void testequalChars() {
           assertTrue(offByN.equalChars('a','e'));
           assertTrue(offByN.equalChars('g','c'));
           assertFalse(offByN.equalChars('v','a'));
           assertTrue(offByN.equalChars('h','l'));
        }

}
