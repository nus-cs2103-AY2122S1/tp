package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASSCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tutorialclass.TutorialClass;

public class AddClassCommand extends Command {

    public static final String COMMAND_WORD = "addc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a class to Classmate. "
            + "Parameters: "
            + PREFIX_CLASSCODE + "CLASSCODE "
            + PREFIX_SCHEDULE + "SCHEDULE "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CLASSCODE + "G06 "
            + PREFIX_SCHEDULE + "Tuesday 12:00pm to 2:00pm, Friday 12:00pm to 2:00pm "
            + PREFIX_TAG + "BestClass";

    public static final String MESSAGE_SUCCESS = "New class added: %1$s";
    public static final String MESSAGE_DUPLICATE_CLASS = "This class already exists in Classmate";

    private final TutorialClass toAdd;

    /**
     * Creates an AddClassCommand to add the specified {@code Student}
     */
    public AddClassCommand(TutorialClass tutorialClass) {
        requireNonNull(tutorialClass);
        toAdd = tutorialClass;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasTutorialClass(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLASS);
        }

        model.addTutorialClass(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddClassCommand // instanceof handles nulls
                && toAdd.equals(((AddClassCommand) other).toAdd));
    }
}
