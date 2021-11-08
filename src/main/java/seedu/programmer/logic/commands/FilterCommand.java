package seedu.programmer.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.commons.core.Messages.MESSAGE_STUDENTS_FILTERED;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_CLASS_ID;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

import seedu.programmer.model.Model;
import seedu.programmer.model.student.StudentDetailContainsQueryPredicate;

/**
 * Finds and lists all students in ProgrammerError whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all students whose fields contain any of "
            + "the specified arguments (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: [" + PREFIX_NAME + "<NAME>] "
            + "[" + PREFIX_STUDENT_ID + "<STUDENT_ID>] "
            + "[" + PREFIX_CLASS_ID + "<CLASS_ID>] "
            + "[" + PREFIX_EMAIL + "<EMAIL>]\n"
            + "Example: " + COMMAND_WORD + " -cid B01";

    private final StudentDetailContainsQueryPredicate predicate;

    /**
     * Initializes a a {@code FilterCommand} object with the {@code predicate} to test which students to be filtered.
     * @param predicate that filters the list of students to be displayed.
     */
    public FilterCommand(StudentDetailContainsQueryPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);
        return new CommandResult(
                String.format(MESSAGE_STUDENTS_FILTERED, model.getFilteredStudentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterCommand // instanceof handles nulls
                && predicate.equals(((FilterCommand) other).predicate)); // state check
    }
}
