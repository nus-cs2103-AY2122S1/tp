package seedu.notor.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.notor.commons.core.trie.Trie;

public class TypicalTries {
    public static final Trie ZERO_ITEM = new Trie();
    public static final Trie ONE_ITEM = Trie.createTrie("aaaaa");
    public static final Trie TWO_ITEM = Trie.createTrie("aaaaa", "bbbbb");
    public static final Trie FOUR_ITEM = Trie.createTrie("aaaaa", "aaabb", "aabbb", "bbbbb");

    private TypicalTries() {}

    public static List<Trie> getTypicalTries() {
        return new ArrayList<>(Arrays.asList(ZERO_ITEM, ONE_ITEM, TWO_ITEM, FOUR_ITEM));
    }
}
