package seedu.address.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * A Serializable class that contains the command word aliases.
 */
public class Aliases implements Serializable {

    private final HashMap<String, String> mappings;

    /**
     * Initialises {@code Aliases} object with no mappings.
     */
    public Aliases() {
        mappings = new HashMap<>();
    }

    /**
     * Adds the given alias into {@code Aliases}.
     */
    public void add(String alias, String commandWord) {
        mappings.put(alias, commandWord);
    }

    /**
     * Removes the given alias from {@code Aliases}.
     *
     * @param alias the alias to remove.
     * @return commandWord associated with {@code alias}, null if does not exist.
     */
    public String remove(String alias) {
        return mappings.remove(alias);
    }

    /**
     * Replaces input with the original command if alias was defined for given input.
     */
    public String convertAliasIfPresent(String input) {
        return mappings.getOrDefault(input, input);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Aliases)) { //this handles null as well.
            return false;
        }

        Aliases o = (Aliases) other;

        return mappings.equals(o.mappings);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        mappings.entrySet().forEach(entry -> {
            sb.append(entry.getKey() + ": " + entry.getValue() + "\n");
        });
        return sb.toString();
    }
}
