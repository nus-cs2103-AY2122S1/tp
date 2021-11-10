package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Adds a shortcut to the shortcut list stored.
 */
public class ListShortcutCommand extends Command {

    public static final String COMMAND_WORD = "listsc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all your preset shortcuts \n"
            + "Example: " + COMMAND_WORD + " a";


    /**
     * Creates new {@code ListShortcutCommand}
     */
    public ListShortcutCommand() {

    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(model.listShortcut());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListShortcutCommand);
    }
}
