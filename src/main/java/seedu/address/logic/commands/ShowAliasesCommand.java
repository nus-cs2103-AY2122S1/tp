package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Lists all aliases to the user.
 */
public class ShowAliasesCommand extends Command {

    public static final String COMMAND_WORD = "aliases";
    public static final String MESSAGE_SUCCESS = "List of Aliases:\n%1$s";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        return new CommandResult(String.format(MESSAGE_SUCCESS, model.getAliases()));
    }
}
