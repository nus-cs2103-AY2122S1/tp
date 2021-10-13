package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.UniqueApplicantList;

/**
 * Wraps all applicant data at ApplicantBook level
 * Duplicates are not allowed (by .isSameApplicant comparison)
 */
public class ApplicantBook implements ReadOnlyApplicantBook {

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

    public ApplicantBook() {}

    /**
     * Creates an ApplicantBook using the Applicants in the {@code toBeCopied}
     */
    public ApplicantBook(ReadOnlyApplicantBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the applicant list with {@code applicants}.
     * {@code applicants} must not contain duplicate applicants.
     */
    public void setApplicants(List<Applicant> applicants) {
        this.applicants.setApplicants(applicants);
    }

    /**
     * Resets the existing data of this {@code ApplicantBook} with {@code newData}.
     */
    public void resetData(ReadOnlyApplicantBook newData) {
        requireNonNull(newData);
        setApplicants(newData.getApplicantList());
    }

    //// position-level operations
    /**
     * Returns true if an applicant with the same identity as {@code applicant} exists in the applicant book.
     */
    public boolean hasApplicant(Applicant applicant) {
        requireNonNull(applicant);
        return applicants.contains(applicant);
    }

    /**
     * Adds an applicant to the applicant book.
     * The applicant must not already exist in the applicant book.
     */
    public void addApplicant(Applicant a) {
        applicants.add(a);
    }

    /**
     * Replaces the given applicant {@code target} in the list with {@code editedApplicant}.
     * {@code target} must exist in the applicant book.
     * The applicant identity of {@code editedApplicant} must not be the same as
     * another existing applicant in the applicant book.
     */
    public void setApplicant(Applicant target, Applicant editedApplicant) {
        requireNonNull(editedApplicant);
        applicants.setApplicant(target, editedApplicant);
    }

    /**
     * Removes {@code key} from this {@code ApplicantBook}.
     * {@code key} must exist in the applicant book.
     */
    public void removeApplicant(Applicant key) {
        applicants.remove(key);
    }

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
                || (other instanceof ApplicantBook // instanceof handles nulls
                && applicants.equals(((ApplicantBook) other).applicants));
    }

}
