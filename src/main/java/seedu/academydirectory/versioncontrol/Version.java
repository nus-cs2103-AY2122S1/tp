package seedu.academydirectory.versioncontrol;

import java.util.List;

/**
 * API of the Version component
 */
public interface Version {
    /**
     * Commits a change and tie the change with a given message
     * @param message Message attached to the Commit for a readable explanation
     */
    boolean commit(String message);

    List<String> retrieveHistory();
}
