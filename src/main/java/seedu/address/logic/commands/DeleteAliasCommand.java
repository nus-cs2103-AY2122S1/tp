package seedu.address.logic.commands;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.alias.CommandWord;
import seedu.address.model.alias.Shortcut;

/**
 * Deletes user-defined alias from SportsPA user preferences.
 */
public class DeleteAliasCommand extends Command {
    public static final String COMMAND_WORD = "unalias";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes specified alias (case-sensitive).\n"
            + "Parameters: "
            + "SHORTCUT\n"
            + "Example: " + COMMAND_WORD + " lf";
    public static final String MESSAGE_ALIAS_DOES_NOT_EXIST = "The given alias '%1$s' does not exist.";
    public static final String MESSAGE_SUCCESS = "Alias for %1$s defined as %2$s successfully removed.";

    private final Shortcut shortcut;

    /**
     * Creates {@code DeleteAlias} object.
     */
    public DeleteAliasCommand(Shortcut shortcut) {
        requireNonNull(shortcut);
        this.shortcut = shortcut;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        CommandWord commandWord = model.removeAlias(shortcut);
        if (isNull(commandWord)) {
            throw new CommandException(String.format(MESSAGE_ALIAS_DOES_NOT_EXIST, shortcut));
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, commandWord, shortcut));
    }

    @Override
    public boolean equals(Object obj) {
        return (obj == this)
                || (obj instanceof DeleteAliasCommand
                && shortcut.equals(((DeleteAliasCommand) obj).shortcut));
    }
}
