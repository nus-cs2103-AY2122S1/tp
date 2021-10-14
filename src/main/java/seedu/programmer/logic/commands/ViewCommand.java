package seedu.programmer.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_CLASS_ID;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

import seedu.programmer.commons.core.Messages;
import seedu.programmer.model.Model;
import seedu.programmer.model.student.StudentDetailContainsQueryPredicate;

/**
 * Finds and lists all students in ProgrammerError whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all students whose names contain any of "
            + "the specified arguments (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: [" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_STUDENT_ID + "STUDENTID] "
            + "[" + PREFIX_CLASS_ID + "CLASSID] "
            + "Example: " + COMMAND_WORD + " -n alice -cid B01 -sid A1234567X";

    private final StudentDetailContainsQueryPredicate predicate;

    /**
     * Initializes a a {@code ViewCommand} object with the {@code predicate} to test which students to be filtered.
     * @param predicate that filters the list of students to be displayed.
     */
    public ViewCommand(StudentDetailContainsQueryPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && predicate.equals(((ViewCommand) other).predicate)); // state check
    }
}
