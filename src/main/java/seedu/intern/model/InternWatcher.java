package seedu.intern.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.intern.model.applicant.Applicant;
import seedu.intern.model.applicant.UniqueApplicantList;

/**
 * Wraps all data at the intern-watcher level
 * Duplicates are not allowed (by .isSameApplicant comparison)
 */
public class InternWatcher implements ReadOnlyInternWatcher {

    private final UniqueApplicantList applicants;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        applicants = new UniqueApplicantList();
    }

    public InternWatcher() {}

    /**
     * Creates an InternWatcher using the Applicants in the {@code toBeCopied}
     */
    public InternWatcher(ReadOnlyInternWatcher toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the applicant list with {@code Applicants}.
     * {@code Applicants} must not contain duplicate applicants.
     */
    public void setApplicants(List<Applicant> applicants) {
        this.applicants.setApplicants(applicants);
    }

    /**
     * Resets the existing data of this {@code InternWatcher} with {@code newData}.
     */
    public void resetData(ReadOnlyInternWatcher newData) {
        requireNonNull(newData);

        setApplicants(newData.getApplicantList());
    }

    //// applicant-level operations

    /**
     * Returns true if a applicant with the same identity as {@code applicant} exists in the intern watcher.
     */
    public boolean hasApplicant(Applicant applicant) {
        requireNonNull(applicant);
        return applicants.contains(applicant);
    }

    /**
     * Adds a applicant to the intern watcher.
     * The applicant must not already exist in the intern watcher.
     */
    public void addApplicant(Applicant p) {
        applicants.add(p);
    }

    /**
     * Replaces the given applicant {@code target} in the list with {@code editedApplicant}.
     * {@code target} must exist in the intern watcher.
     * The applicant identity of {@code editedApplicant} must not be the same as another existing applicant in the
     * intern watcher.
     */
    public void setApplicant(Applicant target, Applicant editedApplicant) {
        requireNonNull(editedApplicant);

        applicants.setApplicant(target, editedApplicant);
    }

    /**
     * Removes {@code key} from this {@code InternWatcher}.
     * {@code key} must exist in the intern watcher.
     */
    public void removeApplicant(Applicant key) {
        applicants.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return applicants.asUnmodifiableObservableList().size() + " applicants";
        // TODO: refine later
    }

    @Override
    public ObservableList<Applicant> getApplicantList() {
        return applicants.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InternWatcher // instanceof handles nulls
                && applicants.equals(((InternWatcher) other).applicants));
    }

    @Override
    public int hashCode() {
        return applicants.hashCode();
    }
}
