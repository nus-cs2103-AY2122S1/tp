package seedu.address.model.done;

/**
 * Represents an Applicant's status in RecruitIn; if an Applicant is considered Done, it means that
 * the recruiter has finished helping an Applicant out and will no longer need to contact the Applicant.
 */
public class Done {

    public static final String STATUS_DONE = "Done";
    public static final String STATUS_UNDONE = "Not Done";

    private String doneStatus;
    private boolean isDone;

    /**
     * Constructor for Done; every Applicant will initially be marked as Not Done.
     */
    public Done() {
        this.doneStatus = STATUS_UNDONE;
        this.isDone = false;
    }

    public String getDoneStatus() {
        return doneStatus;
    }

    public void setAsDone() {
        this.doneStatus = STATUS_DONE;
        this.isDone = true;
    }

    public void setAsUndone() {
        this.doneStatus = STATUS_UNDONE;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return doneStatus;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Done // instanceof handles nulls
                && (doneStatus.equals(((Done) other).doneStatus))
                && (isDone == ((Done) other).isDone)); // state check
    }

    @Override
    public int hashCode() {
        return doneStatus.hashCode();
    }
}
