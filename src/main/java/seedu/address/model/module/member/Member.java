package seedu.address.model.module.member;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.module.Module;
import seedu.address.model.module.Name;
import seedu.address.model.module.member.position.Position;
import seedu.address.model.module.task.TaskList;

/**
 * Represents a Member in the Ailurus.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Member extends Module {

    // Identity fields
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Position> positions = new HashSet<>();
    private final TaskList taskList = new TaskList();

    /**
     * Every field must be present and not null.
     */
    public Member(Name name, Phone phone, Email email, Address address, Set<Position> positions) {
        super(name);
        requireAllNonNull(phone, positions);
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.positions.addAll(positions);
    }

    /**
     * Constructs a member with tasks.
     */
    public Member(Name name, Phone phone, Email email, Address address, Set<Position> positions, TaskList taskList) {
        super(name);
        requireAllNonNull(phone, positions, taskList);
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.positions.addAll(positions);
        this.taskList.setTasks(taskList);
    }

    public Phone getPhone() {
        return phone;
    }

    public Optional<Email> getEmail() {
        return Optional.ofNullable(email);
    }

    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }

    /**
     * Returns an immutable position set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Position> getPositions() {
        return Collections.unmodifiableSet(positions);
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList.setTasks(taskList);
    }

    /**
     * Returns true if both members have the same name.
     * This defines a weaker notion of equality between two members.
     */
    @Override
    public boolean isSameType(Module otherMember) {
        if (otherMember == this) {
            return true;
        } else if (otherMember instanceof Member) {
            return otherMember.getName().equals(getName());
        }
        return false;
    }

    /**
     * Returns true if both members have the same identity and data fields.
     * This defines a stronger notion of equality between two members.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Member)) {
            return false;
        }

        Member otherMember = (Member) other;
        return otherMember.getName().equals(getName())
                && otherMember.getPhone().equals(getPhone())
                && otherMember.getEmail().equals(getEmail())
                && otherMember.getAddress().equals(getAddress())
                && otherMember.getPositions().equals(getPositions());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), phone, email, address, positions);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone());

        if (getEmail().isPresent()) {
            builder.append("; Email: ").append(getEmail().get());
        }

        if (getAddress().isPresent()) {
            builder.append("; Address: ").append(getAddress().get());
        }

        Set<Position> positions = getPositions();
        if (!positions.isEmpty()) {
            builder.append("; Positions: ");
            positions.forEach(builder::append);
        }
        if (!taskList.isEmpty()) {
            builder.append("; Tasks: ");
            taskList.iterator().forEachRemaining(builder::append);
        }
        return builder.toString();
    }

}
