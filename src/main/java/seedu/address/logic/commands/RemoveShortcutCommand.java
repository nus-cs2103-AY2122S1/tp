package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.ShortcutCommand.SHORTCUT_NOT_FOUND;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Removes a shortcut to the shortcut list stored.
 */
public class RemoveShortcutCommand extends Command {

    public static final String COMMAND_WORD = "removesc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes a shortcut with a specified call"
            + "Parameters: KEYWORDS c/[Command to be executed]\n"
            + "Example: " + COMMAND_WORD + " a";

    public static final String SHORTCUT_REMOVE_SUCCESS = "Removed %s: %s";

    private final String keyword;
    private UndoCommand commandToUndo;

    /**
     * Creates new {@code RemoveShortcutCommand}
     * @param keyword Key to call the command
     */
    public RemoveShortcutCommand(String keyword) {
        requireNonNull(keyword);
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        commandToUndo = new UndoCommand();
        commandToUndo.setPrevCommand(this);
        String commandString = model.removeShortcut(keyword);
        if (commandString == null) {
            commandToUndo.setPrevCommand(null);
            throw new CommandException(String.format(SHORTCUT_NOT_FOUND, keyword));
        }
        return new CommandResult(String.format(SHORTCUT_REMOVE_SUCCESS, keyword, commandString));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || ((other instanceof RemoveShortcutCommand)
                && keyword.equals(((RemoveShortcutCommand) other).keyword)); // stateCheck
    }
}
