package seedu.address.model.application;

import java.util.Objects;

import seedu.address.model.position.Position;

/**
 * Represents an application to a specific job position.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Application {
    private final Position position;
    private final ApplicationStatus status;

    /**
     * Constructor for a job application.
     * Invoked whenever the add-applicant command is called.
      */
    public Application(Position position) {
        this(position, ApplicationStatus.PENDING);
    }

    /**
     * Internal constructor for a job application which specifies an application status.
     */
    public Application(Position position, ApplicationStatus applicationStatus) {
        this.position = position;
        this.status = applicationStatus;
    }

    /**
     * Returns a new Application with the status updated as specified.
     */
    public Application markAs(ApplicationStatus applicationStatus) {
        return new Application(position, applicationStatus);
    }

    /**
     * Returns a description of the application and its status.
     */
    public String getDescription() {
        return position.getTitle() + "; Status: " + status;
    }

    public Position getPosition() {
        return position;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Application)) {
            return false;
        }

        Application otherApplication = (Application) other;
        return position.equals(otherApplication.position)
                && status.equals(otherApplication.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, status);
    }

    @Override
    public String toString() {
        return "Application: {"
                + ", Position: " + position.getTitle()
                + ", Status: " + status
                + "}";
    }

    /**
     * The status of a job application.
     */
    public enum ApplicationStatus { PENDING, ACCEPTED, REJECTED }
}
