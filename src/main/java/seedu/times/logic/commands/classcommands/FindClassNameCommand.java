package seedu.times.logic.commands.classcommands;

import static java.util.Objects.requireNonNull;

import seedu.times.commons.core.Messages;
import seedu.times.logic.commands.Command;
import seedu.times.logic.commands.CommandResult;
import seedu.times.logic.commands.exceptions.CommandException;
import seedu.times.model.Model;
import seedu.times.model.tuitionclass.predicates.ClassNameContainsKeywordsPredicate;
import seedu.times.ui.TabName;

/**
 * Finds a tuition class in Timestable by its class name.
 */
public class FindClassNameCommand extends Command {

    public static final String COMMAND_WORD = "findclassname";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all classes whose class name contains all of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: CLASS_NAME [, [CLASS_NAME]...]\n"
            + "Example: " + COMMAND_WORD + " sec4 physics";

    private final ClassNameContainsKeywordsPredicate predicate;

    public FindClassNameCommand(ClassNameContainsKeywordsPredicate predicate) {
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof FindClassNameCommand)) {
            return false;
        }

        return ((FindClassNameCommand) o).predicate.equals(predicate);
    }
}
