package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;

public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_SUCCESS = "Displayed all tags! \n"
            + "To view student list, type: \"list\".\n"
            + "To view schedule, type: \"schedule\".";

    @Override
    public CommandResult execute() throws CommandException {
        requireNonNull(model);
        model.getObservableTagList();
        return new CommandResult(MESSAGE_SUCCESS, false, false, true, false, false);
    }
}
