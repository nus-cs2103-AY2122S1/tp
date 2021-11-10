package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.Model;

/**
 * Adds a shortcut to the shortcut list stored.
 */
public class AddShortcutCommand extends Command {

    public static final String COMMAND_WORD = "addsc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a shortcut with a specified call and function"
            + "which can be called by sc to recreate the command\n"
            + "Parameters: KEYWORDS c/[Command to be executed]\n"
            + "Example: " + COMMAND_WORD + " a c/find alice";

    private final String keyword;
    private final String commandString;
    private UndoCommand commandToUndo;

    /**
     * Creates new {@code AddShortcutCommand}
     * @param keyword Key to call the command
     * @param commandString Command to be called
     */
    public AddShortcutCommand(String keyword, String commandString) {
        requireAllNonNull(keyword, commandString);
        commandToUndo = new UndoCommand();
        commandToUndo.setPrevCommand(this);
        this.keyword = keyword;
        this.commandString = commandString;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.addShortcut(keyword, commandString);
        return new CommandResult("Added " + keyword + ": " + commandString);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddShortcutCommand // instanceof handles nulls
                && keyword.equals(((AddShortcutCommand) other).keyword)
                && commandString.equals(((AddShortcutCommand) other).commandString)); // state check
    }
}
