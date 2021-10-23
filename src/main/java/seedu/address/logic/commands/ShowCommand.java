package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSESSMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Collections;
import java.util.Optional;
import java.util.function.Predicate;
import javafx.collections.ObservableList;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Assessment;
import seedu.address.model.student.ID;
import seedu.address.model.student.IdContainsKeywordsPredicate;
import seedu.address.model.student.Name;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.Student;

/**
 * Shows information of a student or an assessment.
 */
public class ShowCommand extends Command {

    public static final String COMMAND_WORD = "show";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows performance analysis of a student or an assessment. "
            + "Parameters: "
            + "(" + PREFIX_NAME + "<student_name> | "
            + PREFIX_ID + "<student_id> | "
            + PREFIX_ASSESSMENT + "<assessment_name>)";

    public static final String MESSAGE_SUCCESS = "Info requested successfully";
    public static final String MESSAGE_NONEXISTENT_STUDENT = "This student does not exist.";
    public static final String MESSAGE_NONEXISTENT_ASSESSMENT = "This assessment does not exist.";
    public static final String MESSAGE_DUPLICATE_STUDENT_NAME =
            "This student needs to be specified using ID due to duplicate naming.";

    private Name name;
    private ID id;
    private Assessment assessment;

    public ShowCommand(Name name, ID id, Assessment assessment) {
        setName(name);
        setId(id);
        setAssessment(assessment);
    }

    public ShowCommand(Name name) {
        this(name, null, null);
    }

    public ShowCommand(ID id) {
        this(null, id, null);
    }

    public ShowCommand(Assessment assessment) {
        this(null, null, assessment);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        return getAssessment().isEmpty() ? showStudent(model) : showAssessment(model);
    }

    /**
     * Executes command when a {@code Student} info is requested.
     */
    private CommandResult showStudent(Model model) throws CommandException {
        Predicate<Student> predicate = createStudentPredicate();
        assert predicate != null;

        model.updateFilteredStudentList(predicate);
        ObservableList<Student> matchedStudents = model.getFilteredStudentList();

        if (matchedStudents.size() == 0) {
            throw new CommandException(MESSAGE_NONEXISTENT_STUDENT);
        }

        if (matchedStudents.size() > 1) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT_NAME);
        }

        Student matchedStudent = matchedStudents.get(0);
        Info info = new Info(matchedStudent);
        return new CommandResult(MESSAGE_SUCCESS, info);
    }

    /**
     * Executes command when a {@code Student} info is requested.
     */
    private CommandResult showAssessment(Model model) throws CommandException {
        assert getAssessment().isPresent();

        Assessment matchedAssessment = model.getAssessment(assessment);
        assert matchedAssessment != null;

        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Creates a {@code Predicate} checking if a student has a matched name or ID.
     */
    private Predicate<Student> createStudentPredicate() {
        return name != null ? new NameContainsKeywordsPredicate(Collections.singletonList(name.toString()))
                : id != null ? new IdContainsKeywordsPredicate(Collections.singletonList(id.toString())) : null;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ShowCommand)) {
            return false;
        }

        // state check
        ShowCommand toCompare = (ShowCommand) other;
        boolean isNameEquals = (name != null && name.equals(toCompare.name)) || name == toCompare.name;
        boolean isIdEquals = (id != null && id.equals(toCompare.id)) || id == toCompare.id;
        boolean isAssessmentEquals = (assessment != null && assessment.equals(toCompare.assessment))
                || assessment == toCompare.assessment;

        return isNameEquals && isIdEquals && isAssessmentEquals;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public void setAssessment(Assessment assessment) {
        this.assessment = assessment;
    }

    public Optional<Name> getName() {
        return Optional.ofNullable(name);
    }

    public Optional<ID> getId() {
        return Optional.ofNullable(id);
    }

    public Optional<Assessment> getAssessment() {
        return Optional.ofNullable(assessment);
    }

    /**
     * Stores info of a student or an assessment.
     */
    public static class Info {
        private Student student;
        private Assessment assessment;

        public Info(Student student) {
            setStudent(student);
        }

        public Info(Assessment assessment) {
            setAssessment(assessment);
        }

        public void setStudent(Student student) {
            this.student = student;
        }

        public void setAssessment(Assessment assessment) {
            this.assessment = assessment;
        }

        public Optional<Student> getStudent() {
            return Optional.ofNullable(student);
        }

        public Optional<Assessment> getAssessment() {
            return Optional.ofNullable(assessment);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof Info)) {
                return false;
            }

            // state check
            Info e = (Info) other;

            return getStudent().equals(e.getStudent())
                    && getAssessment().equals(e.getAssessment());
        }
    }
}
