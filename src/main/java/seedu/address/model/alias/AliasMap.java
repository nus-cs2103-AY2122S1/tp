package seedu.address.model.alias;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

/**
 * A Serializable class that contains the alias mappings.
 */
public class AliasMap implements Serializable {

    private final HashMap<Shortcut, CommandWord> mappings;

    /**
     * Initialises {@code AliasMap} object with no mappings.
     */
    public AliasMap() {
        mappings = new HashMap<>();
    }

    /**
     * Adds an alias into {@code AliasMap}.
     *
     * @param alias the alias to add into the mappings.
     */
    public void add(Alias alias) {
        mappings.put(alias.getShortcut(), alias.getCommandWord());
    }

    /**
     * Removes the specified alias from {@code AliasMap}.
     *
     * @param shortcut the shortcut to remove.
     * @return CommandWord associated with {@code shortcut}, null the alias does not exist.
     */
    public CommandWord remove(Shortcut shortcut) {
        return mappings.remove(shortcut);
    }

    /**
     * Replaces input with the original command if alias was defined for given input.
     *
     * @param input the shortcut to search for.
     * @return the command if shortcut exists, input if otherwise.
     */
    public String convertAliasIfPresent(String input) {
        if (!Shortcut.isValidShortcut(input)) {
            return input;
        }
        CommandWord output = mappings.get(new Shortcut(input));
        if (Objects.isNull(output)) {
            return input;
        }
        return output.getCommandWord();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof AliasMap)) {
            return false;
        }

        AliasMap o = (AliasMap) other;

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

    @Override
    public int hashCode() {
        return Objects.hash(mappings);
    }
}
