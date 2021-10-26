package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSESSMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Assessment;
import seedu.address.model.student.AssessmentStatistics;
import seedu.address.model.student.ID;
import seedu.address.model.student.IdContainsKeywordsPredicate;
import seedu.address.model.student.Name;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentStatistics;

/**
 * Shows information of a student or an assessment.
 */
public class ShowCommand extends Command {

    public static final String COMMAND_WORD = "show";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows performance analysis of a student or an assessment. "
            + "Parameters: "
            + "( INDEX | "
            + PREFIX_NAME + "<student_name> | "
            + PREFIX_ID + "<student_id> | "
            + PREFIX_ASSESSMENT + "<assessment_name>)";

    public static final String MESSAGE_SUCCESS = "Info requested successfully";
    public static final String MESSAGE_NONEXISTENT_STUDENT = "This student does not exist.";
    public static final String MESSAGE_NONEXISTENT_ASSESSMENT = "This assessment does not exist.";
    public static final String MESSAGE_DUPLICATE_STUDENT_NAME =
            "This student needs to be specified using ID due to duplicate naming.";

    private Index index;
    private Name name;
    private ID id;
    private Assessment assessment;

    /**
     * Constructor for a {@code ShowCommand}.
     */
    public ShowCommand(Index index, Name name, ID id, Assessment assessment) {
        setIndex(index);
        setName(name);
        setId(id);
        setAssessment(assessment);
    }

    public ShowCommand(Index index) {
        this(index, null, null, null);
    }

    public ShowCommand(Name name) {
        this(null, name, null, null);
    }

    public ShowCommand(ID id) {
        this(null, null, id, null);
    }

    public ShowCommand(Assessment assessment) {
        this(null, null, null, assessment);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        return getIndex().isPresent() 
                ? showStudentByIndex(model)
                : getAssessment().isPresent() 
                ? showAssessment(model)
                : showStudentByPrefixes(model);
    }

    /**
     * Executes command when a {@code Student} info is requested by index.
     */
    private CommandResult showStudentByIndex(Model model) throws CommandException {
        List<Student> students = model.getFilteredStudentList();

        if (index.getZeroBased() >= students.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student matchedStudent = students.get(index.getZeroBased());

        Info info = new Info(matchedStudent);
        StudentStatistics statistics = new StudentStatistics(matchedStudent);
        return new CommandResult(MESSAGE_SUCCESS, info, statistics.toLineChart());
    }

    /**
     * Executes command when a {@code Student} info is requested by name or ID.
     */
    private CommandResult showStudentByPrefixes(Model model) throws CommandException {
        Predicate<Student> predicate = createStudentPredicate();
        assert predicate != null;

        model.updateFilteredStudentList(predicate);
        List<Student> matchedStudents = model.getFilteredStudentList();

        if (matchedStudents.size() == 0) {
            throw new CommandException(MESSAGE_NONEXISTENT_STUDENT);
        }

        if (matchedStudents.size() > 1) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT_NAME);
        }

        Student matchedStudent = matchedStudents.get(0);

        Info info = new Info(matchedStudent);
        StudentStatistics statistics = new StudentStatistics(matchedStudent);
        return new CommandResult(MESSAGE_SUCCESS, info, statistics.toLineChart());
    }

    /**
     * Executes command when a {@code Student} info is requested.
     */
    private CommandResult showAssessment(Model model) throws CommandException {
        assert getAssessment().isPresent();
        Assessment matchedAssessment = model.getAssessment(assessment);

        if (matchedAssessment == null) {
            throw new CommandException(MESSAGE_NONEXISTENT_ASSESSMENT);
        }

        Info info = new Info(matchedAssessment);
        AssessmentStatistics statistics = new AssessmentStatistics(matchedAssessment);
        return new CommandResult(MESSAGE_SUCCESS, info, statistics.toHistogram());
    }

    /**
     * Creates a {@code Predicate} checking if a student has a matched name or ID.
     */
    private Predicate<Student> createStudentPredicate() {
        return getName().isPresent()
                ? new NameContainsKeywordsPredicate(Arrays.asList(name.toString().split("\\s+")))
                : getId().isPresent()
                ? new IdContainsKeywordsPredicate(Collections.singletonList(id.toString()))
                : null;
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
        boolean isIndexEquals = Objects.equals(index, toCompare.index);
        boolean isNameEquals = Objects.equals(name, toCompare.name);
        boolean isIdEquals = Objects.equals(id, toCompare.id);
        boolean isAssessmentEquals = Objects.equals(assessment, toCompare.assessment);

        return isIndexEquals && isNameEquals && isIdEquals && isAssessmentEquals;
    }

    public void setIndex(Index index) {
        this.index = index;
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

    public Optional<Index> getIndex() {
        return Optional.ofNullable(index);
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
        private Index index;
        private Student student;
        private Assessment assessment;

        public Info(Index index) {
            setIndex(index);
        }

        public Info(Student student) {
            setStudent(student);
        }

        public Info(Assessment assessment) {
            setAssessment(assessment);
        }

        public void setIndex(Index index) {
            this.index = index;
        }

        public void setStudent(Student student) {
            this.student = student;
        }

        public void setAssessment(Assessment assessment) {
            this.assessment = assessment;
        }

        public Optional<Index> getIndex() {
            return Optional.ofNullable(index);
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

            return getIndex().equals(e.getIndex())
                    && getStudent().equals(e.getStudent())
                    && getAssessment().equals(e.getAssessment());
        }
    }
}
