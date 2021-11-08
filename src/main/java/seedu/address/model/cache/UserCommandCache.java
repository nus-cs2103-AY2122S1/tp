package seedu.address.model.cache;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores the current command cache.
 * Uses the Singleton design framework since there should only be 1 cache running at all time.
 */
public class UserCommandCache {

    private static UserCommandCache instance;
    private List<String> commandCache;
    private int index;

    /** Package private constructor of the cache */
    private UserCommandCache() {
        commandCache = new ArrayList<>();
        index = 0;
    }

    /** Method to get the instance. Creates one if it does not exist */
    public static UserCommandCache getInstance() {
        if (instance == null) {
            instance = new UserCommandCache();
        }
        return instance;
    }

    /**
     * Add a command to current cache. Reset index to the end of cache.
     * Deletes the first half if the size of cache reaches 50;
     * @param command Command to add to cache
     */
    public void addCommand(String command) {
        // Check input not null
        requireNonNull(command);

        // Check cache is not null;
        requireNonNull(commandCache);

        if (commandCache.size() >= 50) {
            List<String> temp = new ArrayList<>();
            for (int i = 25; i < commandCache.size(); i++) {
                temp.add(commandCache.get(i));
            }
            commandCache = temp;
        }

        commandCache.add(command);
        index = commandCache.size();
    }

    /**
     * Get the "previous" command entered with respect to current pointer. If there is no last command, returns
     * the current command instead.
     * @return Last entered command String
     */
    public String getBefore() {
        requireNonNull(commandCache);
        index = Math.max(0, index - 1);
        if (commandCache.size() == 0) {
            return "";
        }
        String lastCommand = commandCache.get(index);
        return lastCommand;
    }

    /**
     * Get the "next" command entered with respect to current pointer. If there is no next command, returns
     * empty string instead.
     * @return Last entered command String
     */
    public String getAfter() {
        requireNonNull(commandCache);
        if (commandCache.size() == 0) {
            return "";
        }
        index = Math.min(commandCache.size(), index + 1);
        String lastCommand;
        if (index == commandCache.size()) {
            lastCommand = "";
        } else {
            lastCommand = commandCache.get(index);
        }
        return lastCommand;
    }

    /**
     * Reset the current cache.
     * Used for testing.
     */
    void reset() {
        commandCache = new ArrayList<>();
        index = 0;
    }
}
