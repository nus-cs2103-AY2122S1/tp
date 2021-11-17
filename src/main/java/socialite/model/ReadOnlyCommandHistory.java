package socialite.model;

import java.util.List;

/**
 * Unmodifiable view of a command history
 */
public interface ReadOnlyCommandHistory {
    /**
     * Returns an immutable history of the past commands.
     */
    List<String> getCommandHistory();
}
