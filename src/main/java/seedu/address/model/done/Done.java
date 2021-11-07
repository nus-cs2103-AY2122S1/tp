package seedu.address.model.done;

import static java.util.Objects.requireNonNull;

/**
 * Represents an Applicant's status in RecruitIn; if an Applicant is considered Done, it means that
 * the recruiter has finished helping an Applicant out and will no longer need to contact the Applicant.
 */
public class Done {

    public static final String STATUS_DONE = "Done";
    public static final String STATUS_UNDONE = "Not Done";
    public static final Done DONE = new Done(STATUS_DONE);
    public static final Done UNDONE = new Done(STATUS_UNDONE);

    public static final String FIND_MESSAGE_CONSTRAINTS = "You can only search for Done or Not Done";

    private String doneStatus;

    /**
     * Constructor for Done; every Applicant will initially be marked as Not Done.
     */
    public Done() {
        this.doneStatus = STATUS_UNDONE;
    }

    /**
     * Secondary constructor for Done.
     */
    public Done(String doneStatus) {
        requireNonNull(doneStatus);
        assert (doneStatus.equalsIgnoreCase(STATUS_DONE) || doneStatus.equalsIgnoreCase(STATUS_UNDONE));
        this.doneStatus = doneStatus;
    }

    public String getDoneStatus() {
        return doneStatus;
    }

    /**
     * Sets the doneStatus of a Done to "Done".
     */
    public void setAsDone() {
        this.doneStatus = STATUS_DONE;
    }

    /**
     * Sets the doneStatus of a Done to "Not Done".
     */
    public void setAsUndone() {
        this.doneStatus = STATUS_UNDONE;
    }

    @Override
    public String toString() {
        return doneStatus;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Done // instanceof handles nulls
                && (doneStatus.equals(((Done) other).doneStatus))); // state check
    }

    @Override
    public int hashCode() {
        return doneStatus.hashCode();
    }
}
