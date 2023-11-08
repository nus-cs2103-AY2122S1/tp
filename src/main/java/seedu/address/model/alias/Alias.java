package seedu.address.model.alias;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents an Alias in SportsPA.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Alias {
    private final Shortcut shortcut;
    private final CommandWord commandWord;

    /**
     * Every field must be present and not null.
     */
    public Alias(Shortcut shortcut, CommandWord commandWord) {
        requireAllNonNull(shortcut, commandWord);
        this.shortcut = shortcut;
        this.commandWord = commandWord;
    }

    public Shortcut getShortcut() {
        return shortcut;
    }

    public CommandWord getCommandWord() {
        return commandWord;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Alias)) {
            return false;
        }

        Alias otherAlias = (Alias) other;
        return otherAlias.getShortcut().equals(getShortcut())
                && otherAlias.getCommandWord().equals(getCommandWord());
    }
}
