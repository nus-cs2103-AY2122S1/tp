package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSESSMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.student.Assessment;
import seedu.address.model.student.Group;
import seedu.address.model.student.ID;
import seedu.address.model.student.Name;
import seedu.address.model.student.Score;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;

/**
 * Allocates an existing student to an existing group.
 */
public class AddScoreCommand extends Command {

    public static final String COMMAND_WORD = "add score";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds score of an assessment for a student. "
            + "Parameters: "
            + PREFIX_ASSESSMENT + "<assessment_name> "
            + "(" + PREFIX_NAME + "<student_name> | "
            + PREFIX_ID + "<student_id>) "
            + PREFIX_SCORE + "<score_in_percentage>";

    public static final String MESSAGE_SUCCESS = "New score added: %1$s";
    public static final String MESSAGE_NONEXISTENT_ASSESSMENT = "This assessment does not exist.";
    public static final String MESSAGE_NONEXISTENT_STUDENT = "This student does not exist.";
    public static final String MESSAGE_DUPLICATE_STUDENT_NAME =
            "Score needs to be added through ID for this student due to duplicate naming.";
    public static final String MESSAGE_EXISTENT_SCORE = "This assessment is already graded.";

    private final ScoreDescriptor scoreDescriptor;

    /**
     * @param scoreDescriptor details of the score
     */
    public AddScoreCommand(ScoreDescriptor scoreDescriptor) {
        requireNonNull(scoreDescriptor);
        this.scoreDescriptor = new ScoreDescriptor(scoreDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ReadOnlyAddressBook addressBook = model.getAddressBook();
        List<Assessment> assessmentList = addressBook.getAssessmentList();
        List<Student> studentList = addressBook.getStudentList();

        assert scoreDescriptor.getScore().isPresent();
        assert scoreDescriptor.getAssessment().isPresent();
        if (!assessmentList.contains(scoreDescriptor.getAssessment().get())) {
            throw new CommandException(MESSAGE_NONEXISTENT_ASSESSMENT);
        }

        Assessment assessmentToEdit = getToEditAssessment(assessmentList, scoreDescriptor);
        List<Student> studentsToEdit = getToEditStudents(studentList, scoreDescriptor);

        if (studentsToEdit.isEmpty()) {
            throw new CommandException(MESSAGE_NONEXISTENT_STUDENT);
        }

        if (studentsToEdit.size() > 1) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT_NAME);
        }

        Student studentToEdit = studentsToEdit.get(0);

        if (assessmentToEdit.isGraded(studentToEdit.getId())) {
            throw new CommandException(MESSAGE_EXISTENT_SCORE);
        }

        Student editedStudent = createEditedStudents(studentToEdit, scoreDescriptor);

        assessmentToEdit.setScore(editedStudent.getId(), scoreDescriptor.getScore().get());
        model.setStudent(studentToEdit, editedStudent);
        return new CommandResult(String.format(MESSAGE_SUCCESS, editedStudent));
    }

    /**
     * Gets and returns a map of {@code Assessment} with matching info
     * as specified in the {@code scoreDescriptor}.
     */
    public static Assessment getToEditAssessment(List<Assessment> assessments, ScoreDescriptor scoreDescriptor) {
        List<Assessment> assessmentToEdit = assessments.stream()
                .filter(scoreDescriptor.isToEditAssessment())
                .collect(Collectors.toList());
        assert assessmentToEdit.size() == 1;
        return assessmentToEdit.get(0);
    }

    /**
     * Get and returns a list of {@code Student} with matching info
     * as specified in the {@code scoreDescriptor}.
     */
    public static List<Student> getToEditStudents(List<Student> students, ScoreDescriptor scoreDescriptor) {
        List<Student> scoreStudents = students.stream()
                .filter(scoreDescriptor.isToEditStudent())
                .collect(Collectors.toList());
        return Collections.unmodifiableList(scoreStudents);
    }

    /**
     * Creates and returns a {@code Student} with the details of {@code preScoreStudent}
     * being added score as specified in {@code scoreDescriptor}.
     */
    public static Student createEditedStudents(Student toEditStudent, ScoreDescriptor scoreDescriptor) {
        assert toEditStudent != null;
        assert scoreDescriptor.getScore().isPresent();
        assert scoreDescriptor.getAssessment().isPresent();

        Name name = toEditStudent.getName();
        ID id = toEditStudent.getId();
        List<Group> groups = toEditStudent.getGroups();
        Map<Assessment, Score> scores = toEditStudent.getScores();
        Set<Tag> tags = toEditStudent.getTags();

        Assessment assessmentToEdit = scoreDescriptor.getAssessment().get();
        Score scoreToEdit = scoreDescriptor.getScore().get();
        assert !scores.containsKey(assessmentToEdit);
        // TODO: need to double check whether
        //  assessment does not exit or is inputted with null score

        Map<Assessment, Score> editedScores = new HashMap<>(scores);
        editedScores.put(assessmentToEdit, scoreToEdit);

        return new Student(name, id, groups, editedScores, tags);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddScoreCommand // instanceof handles nulls
                && scoreDescriptor.equals(((AddScoreCommand) other).scoreDescriptor));
    }

    /**
     * Stores the details to edit the student with. Each non-empty field value will replace the
     * corresponding field value of the student.
     */
    public static class ScoreDescriptor {
        private Name name;
        private ID id;
        private Assessment assessment;
        private Score score;

        public ScoreDescriptor() {}

        /**
         * Copy constructor.
         */
        public ScoreDescriptor(ScoreDescriptor toCopy) {
            setName(toCopy.name);
            setId(toCopy.id);
            setAssessment(toCopy.assessment);
            setScore(toCopy.score);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setId(ID id) {
            this.id = id;
        }

        public Optional<ID> getId() {
            return Optional.ofNullable(id);
        }

        public void setAssessment(Assessment assessment) {
            this.assessment = assessment;
        }

        public Optional<Assessment> getAssessment() {
            return Optional.ofNullable(assessment);
        }

        public void setScore(Score score) {
            this.score = score;
        }

        public Optional<Score> getScore() {
            return Optional.ofNullable(score);
        }

        /**
         * Returns a {@code Predicate} checking if an assessment have a matched name.
         */
        public Predicate<Assessment> isToEditAssessment() {
            return toCheck -> toCheck.equals(assessment);
        }

        /**
         * Returns a {@code Predicate} checking if a student have a matched name or ID.
         */
        public Predicate<Student> isToEditStudent() {
            return toCheck -> toCheck.getName().equals(name)
                    || toCheck.getId().equals(id);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof ScoreDescriptor)) {
                return false;
            }

            // state check
            ScoreDescriptor e = (ScoreDescriptor) other;

            return getName().equals(e.getName())
                    && getId().equals(e.getId())
                    && getAssessment().equals(e.getAssessment())
                    && getScore().equals(e.getScore());
        }
    }
}
