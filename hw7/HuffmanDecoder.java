import java.util.ArrayList;
import java.util.List;

public class HuffmanDecoder {
    /**
     * main decoder process.
     * @param args command line argument
     */
    public static void main(String[] args) {
        ObjectReader or = new ObjectReader(args[0]);
        BinaryTrie trie = (BinaryTrie) or.readObject();
        int length = (Integer) or.readObject();
        BitSequence hugeBits = (BitSequence) or.readObject();

        List<Character> symbolList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            Match match = trie.longestPrefixMatch(hugeBits);
            BitSequence sequence = match.getSequence();
            symbolList.add(match.getSymbol());
            hugeBits = hugeBits.allButFirstNBits(sequence.length());
        }

        char[] decodedArray = new char[symbolList.size()];
        for (int i = 0; i < symbolList.size(); i++) {
            decodedArray[i] = symbolList.get(i);
        }

        FileUtils.writeCharArray(args[1], decodedArray);
    }
}
