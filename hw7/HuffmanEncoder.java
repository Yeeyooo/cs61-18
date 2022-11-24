import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanEncoder {
    /**
     * Map characters to their counts.
     * @param inputSymbols input char array
     * @return Map that maps characters to their counts in given array
     */
    public static Map<Character, Integer> buildFrequencyTable(char[] inputSymbols) {
        Map<Character, Integer> frequencyTable = new HashMap<>();
        for (char ch : inputSymbols) {
            if (!frequencyTable.containsKey(ch)) {
                frequencyTable.put(ch, 1);
            }
            else {
                frequencyTable.computeIfPresent(ch, (key, oldValue) -> oldValue + 1);
            }
        }
        return frequencyTable;
    }

    /**
     * main encode process.
     * @param args command line argument
     */
    public static void main(String[] args) {
        char[] inputSymbols = FileUtils.readFile(args[0]);
        Map<Character, Integer> frequencyTable = buildFrequencyTable(inputSymbols);
        BinaryTrie trie = new BinaryTrie(frequencyTable);

        ObjectWriter ow = new ObjectWriter(args[0] + ".huf");
        ow.writeObject(trie);
        ow.writeObject(inputSymbols.length);

        Map<Character, BitSequence> lookupTable = trie.buildLookupTable();

        List<BitSequence> bitsList = new ArrayList<>();
        for (char ch : inputSymbols) {
            bitsList.add(lookupTable.get(ch));
        }
        BitSequence hugeSequence = BitSequence.assemble(bitsList);
        ow.writeObject(hugeSequence);
    }
}
