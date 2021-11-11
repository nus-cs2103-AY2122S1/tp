package seedu.address.model.application;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Represents an Application in InternSHIP.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Application {

    // Identity fields
    private final Company company;
    private final Position position;
    private final Deadline deadline;

    // Default fields
    private final Completion completion;
    private final Status status;
    private final Priority priority;

    // Optional fields
    private final Set<Requirement> requirements = new HashSet<>();
    private final Set<InterviewDateAndTime> interviewDateAndTime = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Application(Company company, Position position, Deadline deadline, Completion completion, Status status,
                       Priority priority, Set<Requirement> requirements,
                       Set<InterviewDateAndTime> interviewDateAndTime) {
        requireAllNonNull(company, position, deadline, completion, requirements);
        this.company = company;
        this.position = position;
        this.deadline = deadline;
        this.completion = completion;
        this.status = status;
        this.priority = priority;
        this.requirements.addAll(requirements);
        this.interviewDateAndTime.addAll(interviewDateAndTime);
    }

    /**
     * Returns the name of the company applied to in the {@code Application}.
     */
    public Company getCompany() {
        return company;
    }

    /**
     * Returns the position applied for in the {@code Application}.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Returns the application deadline of the {@code Application}.
     */
    public Deadline getDeadline() {
        return deadline;
    }

    /**
     * Returns the completion status of the {@code Application}.
     */
    public Completion getCompletion() {
        return this.completion;
    }

    /**
     * Returns the status (Pending/ Accepted/ Rejected) of the {@code Application}.
     */
    public Status getStatus() {
        return this.status;
    }

    /**
     * Returns the priority (Low/ Medium/ High) of the {@code Application}.
     */
    public Priority getPriority() {
        return this.priority;
    }

    /**
     * Returns an immutable requirement set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Requirement> getRequirements() {
        return Collections.unmodifiableSet(requirements);
    }

    /**
     * Returns an immutable interview date and time set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<InterviewDateAndTime> getInterviewDateAndTime() {
        return Collections.unmodifiableSet(interviewDateAndTime);
    }

    public boolean hasInterviewTime() {
        return !interviewDateAndTime.isEmpty();
    }

    public boolean isAccepted() {
        return status.value.equalsIgnoreCase("accepted");
    }

    public boolean isRejected() {
        return status.value.equalsIgnoreCase("rejected");
    }

    public boolean isCompleted() {
        return completion.value.equalsIgnoreCase("completed");
    }

    /**
     * Returns true if both applications have the same name and position.
     * This defines a weaker notion of equality between two applications.
     */
    public boolean isSameApplication(Application otherApplication) {
        if (otherApplication == this) {
            return true;
        }

        return otherApplication != null
                && otherApplication.getCompany().equals(getCompany())
                && otherApplication.getPosition().equals(getPosition());
    }

    /**
     * Returns true if both applications have the same identity and data fields.
     * This defines a stronger notion of equality between two applications.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Application)) {
            return false;
        }

        Application otherApplication = (Application) other;
        return otherApplication.getCompany().equals(getCompany())
                && otherApplication.getPosition().equals(getPosition())
                && otherApplication.getDeadline().equals(getDeadline())
                && otherApplication.getCompletion().equals(getCompletion())
                && otherApplication.getStatus().equals(getStatus())
                && otherApplication.getPriority().equals(getPriority())
                && otherApplication.getRequirements().equals(getRequirements())
                && otherApplication.getInterviewDateAndTime().equals(getInterviewDateAndTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(company, position, deadline, completion, status, requirements, interviewDateAndTime);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("Company: ");
        builder.append(getCompany())
                .append("; Position: ")
                .append(getPosition())
                .append("; Deadline: ")
                .append(getDeadline().toFormattedString())
                .append("; Completion: ")
                .append(getCompletion())
                .append("; Decision: ")
                .append(getStatus())
                .append("; Priority: ")
                .append(getPriority());

        Set<Requirement> requirements = getRequirements();
        if (!requirements.isEmpty()) {
            builder.append("; Requirements: ");
            requirements.forEach(builder::append);
        }

        Set<InterviewDateAndTime> interviewDateAndTimes = getInterviewDateAndTime();
        if (!interviewDateAndTimes.isEmpty()) {
            List<String> stringList = new ArrayList<>();
            for (Iterator<InterviewDateAndTime> it = interviewDateAndTimes.iterator(); it.hasNext(); ) {
                stringList.add("[" + it.next().toFormattedString() + "]");
            }
            builder.append("; Interview Date and Time: ");
            stringList.forEach(builder::append);
        }

        return builder.toString();
    }

}
