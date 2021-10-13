package seedu.address.model.tutorialclass;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.student.ClassCode;
import seedu.address.model.tag.Tag;

/**
 * Represents a tutorial class in the Classmate.
 * Guarantees: details are present and not null, fields are validated and immutable.
 *
 */
public class TutorialClass {

    // Class Fields
    private final ClassCode classCode;
    private final Schedule schedule;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * @param classCode ClassCode of Tutorial Class.
     * @param schedule Class Schedule.
     * @param tags Optional tags.
     */
    public TutorialClass(ClassCode classCode, Schedule schedule, Set<Tag> tags) {
        this.classCode = classCode;
        this.schedule = schedule;
        this.tags.addAll(tags);
    }

    public ClassCode getClassCode() {
        return classCode;
    }

    public Schedule getSchedule() {
        return schedule;
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
