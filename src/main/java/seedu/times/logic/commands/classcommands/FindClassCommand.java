package seedu.times.logic.commands.classcommands;

import static java.util.Objects.requireNonNull;

import seedu.times.commons.core.Messages;
import seedu.times.logic.commands.Command;
import seedu.times.logic.commands.CommandResult;
import seedu.times.logic.commands.exceptions.CommandException;
import seedu.times.model.Model;
import seedu.times.model.tuitionclass.ClassTiming;
import seedu.times.model.tuitionclass.predicates.ClassTimingContainsKeywordsPredicate;
import seedu.times.ui.TabName;

/**
 * Finds a tuition class in Timestable by its class timing.
 */
public class FindClassCommand extends Command {

    public static final String COMMAND_WORD = "findclass";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all classes whose class timing contains all of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Valid keywords are limited to:\n"
            + "    1) 3 letter abbreviation for day of the week\n"
            + "    2) time expressed in HH:MM-HH:MM format.\n"
            + "(" + ClassTiming.MESSAGE_CONSTRAINTS + ")\n"
            + "Parameters: CLASS_TIMING\n"
            + "Example: " + COMMAND_WORD + " Mon 11:30-12:30";

    private final ClassTimingContainsKeywordsPredicate predicate;

    /**
     * Constructs a new FindClassCommand.
     *
     * @param predicate The predicate to filter the classes by.
     */
    public FindClassCommand(ClassTimingContainsKeywordsPredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredClassList(predicate);

        updateView(TabName.CLASSES);
        hideTuitionClassStudentList();

        return new CommandResult(
                String.format(Messages.MESSAGE_CLASSES_LISTED_OVERVIEW, model.getFilteredTuitionClassList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindClassCommand // instanceof handles nulls
                && predicate.equals(((FindClassCommand) other).predicate)); // state check
    }
}
