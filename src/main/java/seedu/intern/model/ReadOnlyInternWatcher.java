package seedu.intern.model;

import javafx.collections.ObservableList;
import seedu.intern.model.applicant.Applicant;

/**
 * Unmodifiable view of an intern book
 */
public interface ReadOnlyInternWatcher {

    /**
     * Returns an unmodifiable view of the applicants list.
     * This list will not contain any duplicate applicants.
     */
    ObservableList<Applicant> getApplicantList();

}
