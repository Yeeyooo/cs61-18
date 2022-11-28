import edu.princeton.cs.algs4.MinPQ;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BinaryTrie implements Serializable {

    /** root of trie. */
    private Node root;

    /** Huffman trie node. */
    private static class Node implements Comparable<Node> {
        private final char ch;

        private final int freq;

        private final Node left, right;

        Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        private boolean isLeaf() {
//            assert ((left == null) && (right == null) || ((left != null) && (right != null)));
            return (left == null) && (right == null);
        }

        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

    /**
     * Build a Huffman decoding trie according to the given frequency table.
     * @param frequencyTable symbol table which maps symbols of type V to their relative frequencies
     */
    public BinaryTrie(Map<Character, Integer> frequencyTable) {
        MinPQ<Node> pq = new MinPQ<>();
        for (Character key : frequencyTable.keySet()) {
            if (frequencyTable.get(key) > 0) {
                pq.insert(new Node(key, frequencyTable.get(key), null, null));
            }
        }

        while (pq.size() > 1) {
            Node left = pq.delMin();
            Node right = pq.delMin();
            Node parent = new Node('\0', left.freq + right.freq, left, right);
            pq.insert(parent);
        }

        root = pq.delMin();
    }

    /**
     * finds the longest prefix that matches the given querySequence
     * @param querySequence query sequence
     * @return a Match object that matches
     */
    public Match longestPrefixMatch(BitSequence querySequence) {
        Node node = root;
        for (int i = 0; i < querySequence.length(); i++) {
            if (node.isLeaf()) {
                return new Match(new BitSequence(querySequence.firstNBits(i)), node.ch);
            } else {
                if (querySequence.bitAt(i) == 0) {
                    node = node.left;
                } else {
                    node = node.right;
                }
            }
        }
        return new Match(querySequence, node.ch);
    }

    /**
     * Get the map that is the inverse of the trie.
     * @return the inverse of the coding trie
     */
    public Map<Character, BitSequence> buildLookupTable() {
        Map<Character, BitSequence> lookupTable = new HashMap<>();
        buildTable(lookupTable, root, "");
        return lookupTable;
    }

    /**
     * helper method.
     * @param lookupTable look up table to be built
     * @param x current Node
     * @param s current 0's and 1's sequence
     */
    private static void buildTable(Map<Character, BitSequence> lookupTable, Node x, String s) {
        if (!x.isLeaf()) {
            buildTable(lookupTable, x.left, s + '0');
            buildTable(lookupTable, x.right, s + '1');
        } else {
            lookupTable.put(x.ch, new BitSequence(s));
        }
    }
}
