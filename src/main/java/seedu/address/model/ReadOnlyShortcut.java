package seedu.address.model;

import java.util.HashMap;

/**
 * Unmodifiable view of a shortcut
 */
public interface ReadOnlyShortcut {

    /**
     * Returns an unmodifiable view of the shortcut map
     */
    HashMap<String, String> getShortcutMap();
}
