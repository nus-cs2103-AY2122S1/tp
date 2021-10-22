package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.applicant.Applicant;

/**
 * Unmodifiable view of an applicant book
 */
public interface ReadOnlyApplicantBook {

    /**
     * Returns an unmodifiable view of the applicant list.
     * This list will not contain any duplicate applicants.
     */
    ObservableList<Applicant> getApplicantList();

}
