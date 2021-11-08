package seedu.address.logic.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import seedu.address.logic.commands.Command;

/**
 * Stores mapping of aliases to their respective commands.
 * Each key may only be associated with a single value.
 * Keys are unique, but the values may contain duplicates, i.e. different keys mapping to the same value
 */
public class CommandAliases {

    /** Aliases mapped to their respective commands**/
    private static final Map<String, Command> commandAliasMap = new HashMap<>();

    /**
     * Associates the specified command with {@code alias} key in this map.
     * If the map previously contained a mapping for the key, the old value will be replaced.
     *
     * @param alias   Alias key with which the specified command is to be associated
     * @param command Command value to be associated with the specified alias key
     */
    public static void put(String alias, Command command) {
        commandAliasMap.put(alias, command);
    }

    /**
     * Returns the value of {@code alias}.
     */
    public static Optional<Command> getValue(String alias) {
        return Optional.ofNullable(commandAliasMap.get(alias));
    }

    public static Map<String, Command> getMap() {
        return commandAliasMap;
    }
}
