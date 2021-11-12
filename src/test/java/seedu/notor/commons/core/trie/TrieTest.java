package seedu.notor.commons.core.trie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.notor.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.notor.commons.exceptions.IllegalValueException;
import seedu.notor.testutil.TypicalTries;

/**
 * Implementation of a Trie data structure for command and tag autocompletion.
 */
public class TrieTest {
    private final Map<String, Integer> testMap = new HashMap<>();

    private void setup() {
        Trie.setup();
    }

    private void setupOneElement() {
        setup();
        testMap.put("aaaaa", 1);
    }

    private void setupTwoElements() {
        setup();
        setupOneElement();
        testMap.put("bbbbb", 1);
    }

    private void setupOneElementTwoCount() {
        setup();
        testMap.put("aaaaa", 2);
    }

    @Test
    public void addItem() {
        setupOneElement();
        Trie testTrie = TypicalTries.getTypicalTries().get(0);
        testTrie.add("aaaaa");
        assertEquals(testTrie.toString(), testMap.keySet().toString());
    }

    @Test
    public void deleteItem_singleItemTrie_success() throws IllegalValueException {
        setupOneElement();
        testMap.remove("aaaaa");
        Trie testTrie = TypicalTries.getTypicalTries().get(1);
        testTrie.remove("aaaaa");
        assertEquals(testTrie.toString(), testMap.keySet().toString());
    }

    @Test
    public void deleteItem_singleItemTrie_fail() {
        Trie testTrie = TypicalTries.getTypicalTries().get(0);
        assertThrows(IllegalValueException.class, () -> testTrie.remove("abc"));
    }

    @Test
    public void findItem_twoItemTrie_success() {
        setup();
        Trie testTrie = TypicalTries.getTypicalTries().get(2);
        assertEquals("aaaaa", testTrie.findSingle("aa"));
        assertEquals("bbbbb", testTrie.findSingle("b"));
        assertEquals("bbbbb", testTrie.findSingle("bbbbb"));
    }

    @Test
    public void findAllItems_fourItemTrie_success() {
        setup();
        Trie testTrie = TypicalTries.getTypicalTries().get(3);
        List<String> expectedSuccess = new ArrayList<>();
        expectedSuccess.add("aaaaa");
        expectedSuccess.add("aaabb");
        assertEquals(expectedSuccess, testTrie.findAllMatches("aaa"));
        // Try searching with wider bounds.
        expectedSuccess.add("aabbb");
        assertEquals(expectedSuccess, testTrie.findAllMatches("aa"));
    }

    @Test
    public void findAllItem_fourItemTrie_fail() {
        setup();
        Trie testTrie = TypicalTries.getTypicalTries().get(3);
        List<String> expectedFail = new ArrayList<>();
        assertEquals(expectedFail, testTrie.findAllMatches("abc"));
    }
}
