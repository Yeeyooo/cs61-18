/**
 * check if a string is a palindrome.
 * @author yeahooooo
 */
public class Palindrome {

    /**
     * convert a string to a deque.
     * @param word the string to be handled.
     * @return return a deque whose item come from the string.
     */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> result = new LinkedListDeque<>();
        if (word.length() == 0) {
            return null;
        }
        char first = word.charAt(0);
        result.addFirst(first);
        for (int j = 1; j < word.length(); j++) {
            char next = word.charAt(j);
            result.addLast(next);
        }
        return result;

    }

    /**
     * check is a string is a palindrome.
     * @param word string to be handled.
     * @return return value.
     */
    public boolean isPalindrome(String word) {
        Deque<Character> tmp = wordToDeque(word);
        int len = word.length();
        if (len == 0 || len == 1) {
            return true;
        }
        while (len > 1) {
            char head = tmp.removeFirst();
            char rear = tmp.removeLast();
            if (head != rear) {
                return false;
            }
            len -= 2;
        }
        return true;

    }

    /**
     * overload version.
     * @param word string to be handled.
     * @param cc a comparator.
     * @return return value.
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> result = wordToDeque(word);
        int len = word.length();
        if (len == 0 || len == 1) {
            return true;
        }
        while (len > 1) {
            char head = result.removeFirst();
            char rear = result.removeLast();
            if (!cc.equalChars(head, rear)) {
                return false;
            }
            len -= 2;
        }
        return true;

    }


}
