package seedu.address.model.done;

/**
 * Represents an Applicant's status in RecruitIn; if an Applicant is considered Done, it means that
 * the recruiter has finished helping an Applicant out and will no longer need to contact the Applicant.
 */
public class Done {

    public static final String DONE_MESSAGE = "This applicant is Done";
    public static final String UNDONE_MESSAGE = "This applicant is not yet Done";

    private Boolean isDone;

    /**
     * Constructor for Done; every Applicant will initially be marked as not Done.
     */
    public Done() {
        this.isDone = false;
    }

    public Boolean applicantIsDone() {
        return isDone;
    }

    public void setOppositeDoneStatus() {
        isDone = !isDone;
    }

    @Override
    public String toString() {
        if (isDone) {
            return DONE_MESSAGE;
        } else {
            return UNDONE_MESSAGE;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.person.done.Done // instanceof handles nulls
                && (isDone.equals(((seedu.address.model.person.done.Done) other).isDone))); // state check
    }

    @Override
    public int hashCode() {
        return isDone.hashCode();
    }
}
