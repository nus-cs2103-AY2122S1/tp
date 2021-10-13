package seedu.notor.commons.core.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implentation of TrieNode data structure to create Tries.
 */
public class TrieNode {
    private static final List<Character> ORDER = new ArrayList<>();

    private final Map<Character, TrieNode> children = new HashMap<>();
    private final String value;
    private boolean isWord;

    private TrieNode(Character character, String previousValue, boolean isWord) {
        if (character != null) {
            this.value = previousValue + character;
        } else {
            this.value = previousValue;
        }
        this.isWord = isWord;
    }

    protected static void setup() {
        ORDER.add(' ');
        for (char c = '0'; c <= '9'; c++) {
            ORDER.add(c);
        }
        for (char c = 'a'; c <= 'z'; c++) {
            ORDER.add(c);
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            ORDER.add(c);
        }
    }

    protected static TrieNode create() {
        return new TrieNode(null, "", false);
    }

    protected void add(String message) {
        if (message.equals("")) {
            isWord = true;
            return;
        }
        Character nextChar = message.charAt(0);
        String nextWord = message.length() >= 2 ? message.substring(1) : "";
        TrieNode nextNode;
        if (children.containsKey(nextChar)) {
            nextNode = children.get(nextChar);
        } else {
            nextNode = new TrieNode(nextChar, value, false);
        }
        nextNode.add(nextWord);
        children.remove(nextChar);
        children.put(nextChar, nextNode);
    }

    protected boolean remove(String message) {
        if (message.length() == 0) {
            isWord = false;
            return true;
        }
        Character nextChar = message.charAt(0);
        String nextWord = message.length() >= 2 ? message.substring(1) : "";
        if (children.get(nextChar).remove(nextWord)) {
            if (children.size() == 1) {
                children.remove(nextChar);
                return true;
            }
        }
        return false;
    }

    protected String getFirst() {
        if (isWord) {
            return value;
        }
        for (Character character : ORDER) {
            if (children.containsKey(character)) {
                if (children.get(character).getFirst() != null) {
                    return children.get(character).getFirst();
                }
            }
        }
        return null;
    }

    protected String find(String query) {
        if (query.equals("")) {
            return getFirst();
        }
        Character nextChar = query.charAt(0);
        String nextWord = query.length() >= 2 ? query.substring(1) : "";
        if (!children.containsKey(nextChar)) {
            return null;
        }
        return children.get(nextChar).find(nextWord);
    }

    @Override
    public String toString() {
        if (isWord) {
            return value + ", ";
        }
        StringBuilder result = new StringBuilder();
        for (TrieNode child : children.values()) {
            result.append(child.toString());
        }
        return result.toString();
    }
}
