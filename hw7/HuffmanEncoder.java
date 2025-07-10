import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanEncoder {
    public static Map<Character, Integer> buildFrequencyTable(char[] inputSymbols) {
        Map<Character, Integer> fTable = new HashMap<>();
        for (char ch : inputSymbols) {
            fTable.put(ch, fTable.getOrDefault(ch, 0) + 1);
        }
        return fTable;
    }

    public static void main(String[] args) {
        String file = args[0];
        char[] chars = FileUtils.readFile(file);
        Map<Character, Integer> frequencyTable = buildFrequencyTable(chars);
        BinaryTrie binaryTrie = new BinaryTrie(frequencyTable);
        ObjectWriter ow = new ObjectWriter(file + ".huf");
        ow.writeObject(binaryTrie);
        ow.writeObject(chars.length);
        Map<Character, BitSequence> lookupTable = binaryTrie.buildLookupTable();
        List<BitSequence> bitsequences = new ArrayList<>();
        for (char ch : chars) {
            bitsequences.add(lookupTable.get(ch));
        }
        BitSequence allBits = BitSequence.assemble(bitsequences);
        ow.writeObject(allBits);
    }
}
