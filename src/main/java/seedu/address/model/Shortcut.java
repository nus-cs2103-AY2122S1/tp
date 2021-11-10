package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents shortcuts stored by the user
 */
public class Shortcut implements ReadOnlyShortcut {

    private HashMap<String, String> shortcutMap;

    public Shortcut() {
        shortcutMap = new HashMap<>();
    }

    /**
     * Creates a Shortcut using the data in the {@code toBeCopied}
     */
    public Shortcut(ReadOnlyShortcut toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Resets the existing data of this {@code Shortcut} with {@code newData}.
     */
    public void resetData(ReadOnlyShortcut newData) {
        requireNonNull(newData);

        setShortcutMap(newData.getShortcutMap());
    }

    /**
     * Replaces the contents of the shortcut map with {@code shorcutMap}.
     */
    public void setShortcutMap(HashMap<String, String> shortcutMap) {
        this.shortcutMap = shortcutMap;
    }

    /**
     * Adds a shortcut to the shortcut map with a {@code keyword} and {@code commandString} key-pair value.
     *
     * @param keyword Key to call a command
     * @param commandString Command to be called
     */
    public void addShortcut(String keyword, String commandString) {
        shortcutMap.put(keyword, commandString);
    }

    /**
     * Fetches the command by a key.
     *
     * @param keyword Key to call a command
     * @return Command to be called
     */
    public String getCommandFromKey(String keyword) {
        return shortcutMap.get(keyword);
    }

    public String removeShortcut(String keyword) {
        return shortcutMap.remove(keyword);
    }

    /**
     * Returns an unmodifiable view of the shortcut map.
     */
    @Override
    public HashMap<String, String> getShortcutMap() {
        return shortcutMap;
    }

    @Override
    public String toString() {
        String output = "Here are the shortcuts you have: \n";
        for (Map.Entry<String, String> entry : shortcutMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            output += "KEY " + key + " - COMMAND " + value + "\n";
        }
        return output;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Shortcut // instanceof handles nulls
                && shortcutMap.equals(((Shortcut) other).shortcutMap));
    }
}
