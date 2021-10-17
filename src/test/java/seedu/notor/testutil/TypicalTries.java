package seedu.notor.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.notor.commons.core.trie.Trie;

public class TypicalTries {
    public static final Trie zeroItem = new Trie();
    public static final Trie oneItem = Trie.createTrie("aaaaa");
    public static final Trie twoItem = Trie.createTrie("aaaaa", "bbbbb");
    public static final Trie fourItem = Trie.createTrie("aaaaa", "aaabb", "aabbb", "bbbbb");

    private TypicalTries() {}

    public static List<Trie> getTypicalTries() {
        return new ArrayList<>(Arrays.asList(zeroItem, oneItem, twoItem, fourItem));
    }
}
