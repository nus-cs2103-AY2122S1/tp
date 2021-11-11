package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.application.Application;
import seedu.address.model.application.UniqueApplicationList;

/**
 * Wraps all data at the Internship level
 * Duplicates are not allowed (by .isSameApplication comparison)
 */
public class Internship implements ReadOnlyInternship {

    private final UniqueApplicationList applications;

    {
        applications = new UniqueApplicationList();
    }

    public Internship() {}

    /**
     * Creates an Internship using the Applications in the InterSHIP data storage
     */
    public Internship(ReadOnlyInternship toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the application list with {@code applications}.
     * {@code applications} must not contain duplicate applications.
     */
    public void setApplications(List<Application> applications) {
        this.applications.setApplications(applications);
    }

    /**
     * Resets the existing data of this {@code Internship} with {@code newData}.
     */
    public void resetData(ReadOnlyInternship newData) {
        requireNonNull(newData);

        setApplications(newData.getApplicationList());
    }

    //// application-level operations

    /**
     * Returns true if an application with the same identity as {@code application} exists in the Internship.
     */
    public boolean hasApplication(Application application) {
        requireNonNull(application);
        return applications.contains(application);
    }

    /**
     * Adds an application to the Internship.
     * The application must not already exist in the Internship.
     */
    public void addApplication(Application p) {
        applications.add(p);
    }

    /**
     * Replaces the given application {@code target} in the list with {@code editedApplication}.
     * {@code target} must exist in the Internship.
     * The application identity of {@code editedApplication} must not be the same as another existing
     * application in the Internship.
     */
    public void setApplication(Application target, Application editedApplication) {
        requireNonNull(editedApplication);

        applications.setApplication(target, editedApplication);
    }

    /**
     * Removes {@code key} from this {@code Internship}.
     * {@code key} must exist in the Internship.
     */
    public void removeApplication(Application key) {
        applications.remove(key);
    }

    //// util methods

    /**
     * Prints the Application list in a more reader-friendly format
     * Used for testing purpose only
     * @return the Application list as displayed a more reader-friendly format
     */
    @Override
    public String toString() {
        StringBuilder applicationList = new StringBuilder();
        for (Application internship: applications) {
            applicationList.append(internship).append("\n");
        }
        return applicationList.toString();
    }

    @Override
    public ObservableList<Application> getApplicationList() {
        return applications.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Internship // instanceof handles nulls
                && applications.equals(((Internship) other).applications));
    }

    @Override
    public int hashCode() {
        return applications.hashCode();
    }
}
