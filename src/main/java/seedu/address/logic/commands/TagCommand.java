package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;

public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_SUCCESS = "Displayed all tags! \n"
            + "You can go back to list view by typing \"list\" or any other valid command.";

    @Override
    public CommandResult execute() throws CommandException {
        requireNonNull(model);
        model.getFilteredTagList();
        return new CommandResult(MESSAGE_SUCCESS, false, false, true, false, false);
    }
}
