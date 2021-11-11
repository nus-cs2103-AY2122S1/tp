package nustracker.logic.commands;

import static java.util.Objects.requireNonNull;
import static nustracker.commons.core.Messages.MESSAGE_STUDENT_LIST_NOT_SHOWN;

import nustracker.commons.core.Messages;
import nustracker.logic.commands.exceptions.CommandException;
import nustracker.model.Model;
import nustracker.model.student.YearContainsKeywordsPredicate;
import nustracker.ui.MainWindow.CurrentlyShownList;

/**
 * Filters and lists all students in address book whose year contains any of the argument keywords.
 */
public class FilterYearCommand extends FilterCommand {

    private final YearContainsKeywordsPredicate predicate;

    public FilterYearCommand(YearContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model,
                                 CurrentlyShownList currentlyShownList) throws CommandException {
        requireNonNull(model);

        if (currentlyShownList != CurrentlyShownList.STUDENTS_LIST) {
            throw new CommandException(MESSAGE_STUDENT_LIST_NOT_SHOWN);
        }

        model.updateFilteredStudentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterYearCommand // instanceof handles nulls
                && predicate.equals(((FilterYearCommand) other).predicate)); // state check
    }
}
