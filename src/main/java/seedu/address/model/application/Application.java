package seedu.address.model.application;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.model.applicant.Applicant;
import seedu.address.model.position.Position;

/**
 * An association class representing a job application.
 *
 * References both the job applicant and the applied position.
 * Contains relevant information on the application.
 *
 * If either the Applicant or Position is deleted, the Application should also be deleted.
 */
public class Application {
    private final Applicant applicant;
    private final Position position;
    private ApplicationStatus status;

    /**
     * Constructor for a job application.
     * Invoked whenever the add-applicant command is called.
      */
    public Application(Applicant applicant, Position position) {
        this.applicant = applicant;
        this.position = position;
        this.status = ApplicationStatus.PENDING;
    }

    /**
     * Marks the application as pending.
     */
    public void markAsPending() {
        this.status = ApplicationStatus.PENDING;
    }

    /**
     * Marks the application as accepted.
     */
    public void markAsAccepted() {
        this.status = ApplicationStatus.ACCEPTED;
    }

    /**
     * Marks the application as rejected.
     */
    public void markAsRejected() {
        this.status = ApplicationStatus.REJECTED;
    }

    /**
     * Returns the status of the /application.
     */
    public ApplicationStatus getStatus() {
        return status;
    }

    public Position getPosition() {
        return position;
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
        return applicant.equals(otherApplication.applicant)
                && position.equals(otherApplication.position)
                && status.equals(otherApplication.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicant, position, status);
    }

    @Override
    public String toString() {
        return "Application: {"
                + "Applicant: " + applicant.getName()
                + ", Position: " + position.getTitle()
                + ", Status: " + status
                + "}";
    }

    /**
     * The status of a job application.
     */
    public enum ApplicationStatus {
        PENDING, ACCEPTED, REJECTED;

        public static final String MESSAGE_CONSTRAINTS;

        static {
            Stream<String> messageConstraints = Stream.of(values())
                    .map(applicationStatus -> applicationStatus.toString().toLowerCase());
            MESSAGE_CONSTRAINTS = messageConstraints.collect(
                    Collectors.joining(", ", "Application status must be one of: ", "."));
        }

        /**
         * Parses the given string into an ApplicationStatus.
         * Case-insensitive. Input must match exactly with the enum name.
         */
        public static ApplicationStatus fromString(String inputString) {
            requireNonNull(inputString);
            return valueOf(inputString.toUpperCase());
        }

    }
}
