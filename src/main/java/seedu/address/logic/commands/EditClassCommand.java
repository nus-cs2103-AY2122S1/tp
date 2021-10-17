package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LIMIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class EditClassCommand extends Command {
    public static final String COMMAND_WORD = "editclass";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits details of the class identified by the index numbers used in the Classes list.\n"
            + "Parameters: CLASS_INDEX "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_TIMESLOT + "TIMESLOT] "
            + "[" + PREFIX_LIMIT + "LIMIT] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Physics"
            + PREFIX_TIMESLOT + "Mon 11:00-11:30"
            + PREFIX_LIMIT + "20";

    public static final String MESSAGE_EDIT_CLASS_SUCCESS = "Edited Class: %1$s";
    public static final String MESSAGE_NO_CLASS_CHANGES = "Student details are already up to date.";
    public static final String MESSAGE_DUPLICATE_CLASS = "This class already exists in the address book.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        return null;
    }


}
