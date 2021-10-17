package seedu.tuitione.logic.commands;

import seedu.tuitione.commons.core.Messages;
import seedu.tuitione.model.Model;
import seedu.tuitione.model.student.IsSpecifiedGrade;

import static java.util.Objects.requireNonNull;

public class FilterCommand extends Command{

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters out students and lessons if they are"
            + " of the specified grade.\n"
            + "Parameters: GRADE \n"
            + "Example: " + COMMAND_WORD + " S2";

    private final IsSpecifiedGrade predicate;

    public FilterCommand(IsSpecifiedGrade predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);
        String output = model.getFilteredStudentList().size() == 0 || model.getFilteredStudentList().size() == 1
                ? String.format(Messages.MESSAGE_SINGULAR_STUDENT_LISTED_OVERVIEW,
                model.getFilteredStudentList().size())
                : String.format(Messages.MESSAGE_PLURAL_STUDENT_LISTED_OVERVIEW,
                model.getFilteredStudentList().size());
        return new CommandResult(output);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterCommand // instanceof handles nulls
                && predicate.equals(((FilterCommand) other).predicate)); // state check
    }
}
