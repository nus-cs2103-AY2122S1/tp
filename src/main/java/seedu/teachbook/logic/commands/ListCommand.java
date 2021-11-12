package seedu.teachbook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.teachbook.commons.core.index.DefaultIndices.INDEX_LIST_ALL;
import static seedu.teachbook.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import seedu.teachbook.logic.commands.exceptions.CommandException;
import seedu.teachbook.model.Model;
import seedu.teachbook.model.student.StudentIsAbsentPredicate;

/**
 * Lists all students in the current student list by clearing any filter.
 * {@code ListCommand} can also list all students in the entire TeachBook or
 * list all students in the current student list whose {@code Attendance} status is absent.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ":\n"
            + "list - shows the original list that contains all the students.\n"
            + "list all - clears any currently selected class and shows students from all classes.\n"
            + "list absent - shows all absent students.";

    public static final String MESSAGE_SUCCESS = "Listed all students";
    public static final String MESSAGE_SUCCESS_LIST_ALL = "Currently displaying all students from all classes";
    public static final String MESSAGE_SUCCESS_LIST_ABSENT = "Listed all absent students";
    public static final String MESSAGE_ALREADY_LIST_ALL = "Already displaying students from all classes\n"
            + "Use \"" + COMMAND_WORD + "\" to display the full list.";

    private final boolean isAll;
    private final boolean isAbsent;

    /**
     * Creates a {@code ListCommand} to list students in the entire TeachBook or
     * list all absent students in the current student list. If both parameters are {@code false},
     * the constructed {@code ListCommand} will list all students in the current student list.
     * The two parameters should not both equal to {@code true}.
     *
     * @param isAll if {@code true}, the constructed {@code ListCommand} will list students in the entire TeachBook.
     * @param isAbsent if {@code true}, the constructed {@code ListCommand} will list all absent students
     *                 in the current student list.
     */
    public ListCommand(boolean isAll, boolean isAbsent) {
        assert !(isAll && isAbsent);
        this.isAll = isAll;
        this.isAbsent = isAbsent;
    }

    /**
     * Creates a {@code ListCommand} that can list all students in the current student list.
     */
    public ListCommand() {
        this(false, false);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (isAll) {
            if (model.isListAll()) {
                throw new CommandException(MESSAGE_ALREADY_LIST_ALL);
            }
            model.updateCurrentlySelectedClass(INDEX_LIST_ALL);
            model.commitTeachBook();
            return new CommandResult(MESSAGE_SUCCESS_LIST_ALL, false, false, true, true);
        }

        if (isAbsent) {
            model.updateFilteredStudentList(new StudentIsAbsentPredicate());
            model.commitTeachBook();
            return new CommandResult(MESSAGE_SUCCESS_LIST_ABSENT);
        }

        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        model.commitTeachBook();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListCommand // instanceof handles nulls
                && isAll == ((ListCommand) other).isAll) // state check
                && isAbsent == ((ListCommand) other).isAbsent;
    }
}
