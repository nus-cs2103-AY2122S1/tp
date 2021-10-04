package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tuition.TuitionClass;

public class AddClassCommand extends Command {
    public static final String COMMAND_WORD = "addclass";

    public static final String MESSAGE_SUCCESS = "New tuition class added: %1$s";
    public static final String MESSAGE_DUPLICATE_CLASS = "This time slot has already been taken in the address book";


    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add tuition class given name, limit, sessions, timeslot, and student \n"
            + "Parameters: NAME LIMIT COUNTER TIMESLOT STUDENT\n"
            + "Example: " + COMMAND_WORD + " n/Physics l/10 c/4 ts/Mon 4pm, s/";

    private TuitionClass toAdd;

    /**
     * Creates an AddClassCommand to add the specified {@code TuitionClass}
     */
    public AddClassCommand(TuitionClass tuitionClass) {
        requireNonNull(tuitionClass);
        toAdd = tuitionClass;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasTuition(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLASS);
        }
        model.addTuition(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}
