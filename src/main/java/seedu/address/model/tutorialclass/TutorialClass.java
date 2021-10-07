package seedu.address.model.tutorialclass;


import seedu.address.model.tag.Tag;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a tutorial class in the Classmate.
 * Guarantees: details are present and not null, fields are validated and immutable.
 *
 */
public class TutorialClass {

    // Class Fields
    private final String classCode;
    private final Schedule schedule;
    private final Set<Tag> tags = new HashSet<>();

    public TutorialClass(String classCode, Schedule schedule, Set<Tag> tags) {
        this.classCode = classCode;
        this.schedule = schedule;
        this.tags.addAll(tags);
    }

    public String getClassCode() {
        return classCode;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public Set<Tag> getTags() {
        return tags;
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
