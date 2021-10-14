package seedu.notor.commons.core.trie;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import seedu.notor.commons.core.LogsCenter;
import seedu.notor.commons.exceptions.IllegalValueException;

/**
 * Implementation of a Trie data structure for command and tag autocompletion.
 */
public class Trie {
    private static final String NO_VALUE_MESSAGE_FORMAT = "Message %s is not stored in this Trie!";
    private static final Logger logger = LogsCenter.getLogger(Trie.class);

    private final Map<String, Integer> messageCounts = new HashMap<>();
    private final TrieNode root = TrieNode.create();

    /**
     * Sets up TrieNode class with the proper ordering for checking for children.
     */
    public static void setup() {
        logger.info("Setting up Tries...");
        TrieNode.setup();
    }

    /**
     * Adds a message to the Trie.
     *
     * @param message Message to be added to the Trie.
     */
    public void add(String message) {
        if (messageCounts.containsKey(message)) {
            messageCounts.put(message, messageCounts.get(message) + 1);
        } else {
            messageCounts.put(message, 1);
        }
        root.add(message);
    }

    /**
     * Removes a message from the Trie.
     *
     * @param message Message to be removed from the Trie.
     * @throws IllegalValueException If the Trie does not contain the message.
     */
    public void remove(String message) throws IllegalValueException {
        if (!messageCounts.containsKey(message)) {
            throw new IllegalValueException(String.format(NO_VALUE_MESSAGE_FORMAT, message));
        }
        if (messageCounts.get(message) > 1) {
            messageCounts.put(message, messageCounts.get(message) - 1);
            return;
        }
        root.remove(message);
        messageCounts.remove(message);
    }

    /**
     * Searches for a message in the Trie.
     *
     * @param query Message to search for in the Trie.
     * @return String starting with the query message.
     */
    public String find(String query) {
        return root.find(query);
    }

    /**
     * Gets first item in the Trie by order.
     *
     * @return First item in the Trie by order.
     */
    public String getFirst() {
        return root.getFirst();
    }

    @Override
    public String toString() {
        return messageCounts.keySet().toString();
    }
}
