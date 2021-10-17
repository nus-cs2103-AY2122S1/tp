package seedu.notor.testutil;

import seedu.notor.commons.core.trie.Trie;

public class TypicalTries {
    public static Trie zeroItem = new Trie();
    public static Trie oneItem = Trie.createTrie("aaaaa");
    public static Trie twoItem = Trie.createTrie("aaaaa", "bbbbb");
    public static Trie fourItem = Trie.createTrie("aaaaa", "aaabb", "aabbb", "bbbbb");

    private TypicalTries() {}
}
