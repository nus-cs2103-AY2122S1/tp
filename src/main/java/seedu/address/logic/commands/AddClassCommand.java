package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Tuition.TuitionClass;
import seedu.address.model.person.Person;

import static java.util.Objects.requireNonNull;

public class AddClassCommand extends Command {
    public static final String COMMAND_WORD = "addclass";

    public static final String MESSAGE_SUCCESS = "New tuition class added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This time slot has already been taken in the address book";


    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private TuitionClass toAdd;

    /**
     * Creates an AddClassCommand to add the specified {@code TuitionClass}
     */
    public AddClassCommand(TuitionClass tuitionClass) throws CommandException{
        requireNonNull(tuitionClass);
        toAdd = tuitionClass;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        //Todo
//        if (model.hasTuitionClass(toAdd)) {
//            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
//        }
//        model.addTuitionClass(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}
