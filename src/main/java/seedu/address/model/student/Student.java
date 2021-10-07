package seedu.address.model.student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.group.Description;
import seedu.address.model.group.Group;
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
    private final Group group;

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, TelegramHandle telegramHandle, Email email, Group group) {
        requireAllNonNull(name, telegramHandle, email, group);
        this.name = name;
        this.telegramHandle = telegramHandle;
        this.email = email;
        this.group = group;
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

    public Group getGroup() {
        return group;
    }

    // TODO zhi hao pls check if can remove thx :)
    public GroupName getGroupName() {
        return group.getGroupName();
    }

    public Description getGroupDescription() {
        return group.getDescription();
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
                && otherStudent.getGroupName().equals(getGroupName());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, telegramHandle, email, group);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Telegram Handle: ")
                .append(getTelegramHandle())
                .append("; Email: ")
                .append(getEmail())
                .append("; GroupName")
                .append(getGroupName());

        return builder.toString();
    }

}
