package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.assessment.UniqueAssessmentList;
import seedu.address.model.group.GroupName;

/**
 * Represents a Student in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Identity fields
    private final Name name;
    private final TelegramHandle telegramHandle;
    private final Email email;

    // Data fields
    private final Note note;
    private final GroupName groupName;
    private final UniqueAssessmentList assessments;
    private final FilteredList<Assessment> filteredAssessments;

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, TelegramHandle telegramHandle, Email email, Note note, GroupName groupName) {
        requireAllNonNull(name, telegramHandle, email, groupName);
        this.name = name;
        this.telegramHandle = telegramHandle;
        this.note = note;
        this.email = email;
        this.groupName = groupName;
        this.assessments = new UniqueAssessmentList();
        this.filteredAssessments = new FilteredList<>(this.getAssessmentList());
    }

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, TelegramHandle telegramHandle, Email email, Note note, GroupName groupName,
                   UniqueAssessmentList assessments) {
        requireAllNonNull(name, telegramHandle, email, groupName, note, assessments);
        this.name = name;
        this.telegramHandle = telegramHandle;
        this.note = note;
        this.email = email;
        this.groupName = groupName;
        this.assessments = assessments;
        this.filteredAssessments = new FilteredList<>(this.getAssessmentList());
    }

    public Name getName() {
        return name;
    }

    public TelegramHandle getTelegramHandle() {
        return telegramHandle;
    }

    public Email getEmail() {
        return email;
    }

    public GroupName getGroupName() {
        return groupName;
    }

    public boolean hasAssessment(Assessment assessment) {
        return assessments.contains(assessment);
    }

    public void addAssessment(Assessment assessment) {
        assessments.add(assessment);
    }

    /**
     * Removes {@code key} from this {@code Student}.
     * {@code key} must exist in the student's assessment list.
     */
    public void deleteAssessment(Assessment key) {
        assessments.remove(key);
    }

    public ObservableList<Assessment> getAssessmentList() {
        return assessments.asUnmodifiableObservableList();
    }

    public ObservableList<Assessment> getFilteredAssessmentList() {
        return filteredAssessments;
    }

    public UniqueAssessmentList getUniqueAssessmentList() {
        return assessments;
    }

    /**
     * Updates the filter of the filtered assessment list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    public void updateFilteredAssessmentList(Predicate<Assessment> predicate) {
        requireNonNull(predicate);
        filteredAssessments.setPredicate(predicate);
    }

    /**
     * Return the note in students.
     * @return Note object
     */
    public Note getNote() {
        return this.note;
    }

    /**
     * Checks if student is weak based on recent assessment.
     * @return true if student is weak
     */
    public boolean isWeak() {
        if (!assessments.asUnmodifiableObservableList().isEmpty()) {
            int index = assessments.asUnmodifiableObservableList().size() - 1;
            return getAssessmentList().get(index).getScore().isFail();
        }
        return false;
    }

    /**
     * Returns true if both students have the same name.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getName().equals(getName());
    }

    /**
     * Returns true if both students have the same identity and data fields.
     * This defines a stronger notion of equality between two students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return otherStudent.getName().equals(getName())
                && otherStudent.getTelegramHandle().equals(getTelegramHandle())
                && otherStudent.getEmail().equals(getEmail())
                && otherStudent.getGroupName().equals(getGroupName())
                && otherStudent.getAssessmentList().equals(getAssessmentList());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, telegramHandle, email, groupName);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Telegram Handle: ")
                .append(getTelegramHandle())
                .append("; Email: ")
                .append(getEmail())
                .append("; Group: ")
                .append(getGroupName());

        return builder.toString();
    }

}
