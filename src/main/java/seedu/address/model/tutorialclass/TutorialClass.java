package seedu.address.model.tutorialclass;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.model.student.ClassCode;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.model.tutorialgroup.UniqueTutorialGroupList;

/**
 * Represents a tutorial class in the Classmate.
 * Guarantees: details are present and not null, fields are validated and immutable.
 *
 */
public class TutorialClass {

    // Class Fields
    private final ClassCode classCode;
    private final Schedule schedule;
    private UniqueTutorialGroupList tutorialGroups;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * @param classCode ClassCode of Tutorial Class.
     * @param schedule Class Schedule.
     * @param tags Optional tags.
     */
    public TutorialClass(ClassCode classCode, Schedule schedule, Set<Tag> tags) {
        this.classCode = classCode;
        this.schedule = schedule;
        this.tutorialGroups = new UniqueTutorialGroupList();
        this.tags.addAll(tags);
    }

    /**
     * Constructor that is called when initializing the Tutorial class from storage.
     *
     * @param classCode ClassCode of Tutorial Class.
     * @param schedule Class Schedule.
     * @param tutorialGroups List of Tutorial Groups
     * @param tags Optional tags.
     */
    public TutorialClass(ClassCode classCode, Schedule schedule, List<TutorialGroup> tutorialGroups, Set<Tag> tags) {
        this.classCode = classCode;
        this.schedule = schedule;
        this.tutorialGroups = new UniqueTutorialGroupList();
        for (TutorialGroup tutorialGroup : tutorialGroups) {
            this.tutorialGroups.add(tutorialGroup);
        }
        this.tags.addAll(tags);
    }

    /**
     * Creates a tutorial class for checking.
     * @param classCode ClassCode of TutorialClass for checking.
     * @return The TutorialClass with a ClassCode.
     */
    public static TutorialClass createTestTutorialClass(ClassCode classCode) {
        return new TutorialClass(classCode, new Schedule("Tuesday 12:00pm to 2:00pm, Friday 12:00pm to 2:00pm"),
                new HashSet<Tag>());
    }

    public ClassCode getClassCode() {
        return classCode;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public UniqueTutorialGroupList getTutorialGroups() {
        return tutorialGroups;
    }

    /**
     * Converts the UniqueTutorialGroupList to List to be used for storage
     *
     * @return List of tutorial groups
     */
    public ObservableList<TutorialGroup> getTutorialGroupsAsList() {
        return tutorialGroups.asUnmodifiableObservableList();
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both tutorial classes have the same name.
     * This defines a weaker notion of equality between two tutorial classes.
     */
    public boolean isSameTutorialClass(TutorialClass otherClass) {
        if (otherClass == this) {
            return true;
        }

        return otherClass != null
                && otherClass.getClassCode().equals(getClassCode());

    }

    /**
     * Returns true if a student with the same identity as {@code student} exists in the ClassMATE.
     */
    public boolean contains(TutorialGroup tutorialGroup) {
        requireNonNull(tutorialGroup);
        return tutorialGroups.contains(tutorialGroup);
    }

    /**
     * Adds a student to the ClassMATE.
     * The student must not already exist in the ClassMATE.
     */
    public void addTutorialGroup(TutorialGroup tutorialGroup) {
        tutorialGroups.add(tutorialGroup);
    }

    /**
     * Sorts the tutorial groups in ClassMATE.
     */
    public void sortTutorialGroups() {
        tutorialGroups.sort();
    }

    /**
     * Removes {@code key} from this {@code Classmate}.
     * {@code key} must exist in the ClassMATE.
     */
    public void removeTutorialGroup(TutorialGroup key) {
        tutorialGroups.remove(key);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TutorialClass)) {
            return false;
        }

        TutorialClass otherClass = (TutorialClass) other;
        return otherClass.getSchedule().equals(getSchedule())
                && otherClass.getClassCode().equals(getClassCode())
                && otherClass.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        return Objects.hash(classCode, schedule, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getClassCode())
                .append("; schedule: ")
                .append(getSchedule());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
