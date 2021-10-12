package seedu.programmer.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_CLASS_ID;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

import java.util.Optional;

import seedu.programmer.commons.core.Messages;
import seedu.programmer.commons.util.CollectionUtil;
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

    /**
     * Stores the details to query the students with. Each non-empty field value will be used to query
     * for the student from the list;
     */
    public static class QueryStudentDescriptor {
        private String name;
        private String studentId;
        private String classId;

        public QueryStudentDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public QueryStudentDescriptor(ViewCommand.QueryStudentDescriptor toCopy) {
            setName(toCopy.name);
            setStudentId(toCopy.studentId);
            setClassId(toCopy.classId);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldToBeQueried() {
            return CollectionUtil.isAnyNonNull(name, studentId, classId);
        }

        public void setName(String name) {
            this.name = name;
        }

        public Optional<String> getName() {
            return Optional.ofNullable(name);
        }

        public void setStudentId(String studentId) {
            this.studentId = studentId;
        }

        public Optional<String> getStudentId() {
            return Optional.ofNullable(studentId);
        }

        public void setClassId(String classId) {
            this.classId = classId;
        }

        public Optional<String> getClassId() {
            return Optional.ofNullable(classId);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof ViewCommand.QueryStudentDescriptor)) {
                return false;
            }

            // state check
            ViewCommand.QueryStudentDescriptor e = (ViewCommand.QueryStudentDescriptor) other;

            return getName().equals(e.getName())
                    && getStudentId().equals(e.getStudentId())
                    && getClassId().equals(e.getClassId());
        }

    }
}
