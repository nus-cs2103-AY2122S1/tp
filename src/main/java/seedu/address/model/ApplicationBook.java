package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.application.Application;
import seedu.address.model.application.UniqueApplicationList;

public class ApplicationBook implements ReadOnlyApplicationBook {

    private final UniqueApplicationList applications = new UniqueApplicationList();

    public ApplicationBook() {}

    /**
     * Creates an ApplicationBook using the Applications in the {@code toBeCopied}
     */
    public ApplicationBook(ReadOnlyApplicationBook toBeCopied) {
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
     * Resets the existing data of this {@code ApplicationList} with {@code newData}.
     */
    public void resetData(ReadOnlyApplicationBook newData) {
        requireNonNull(newData);
        setApplications(newData.getApplicationList());
    }

    //// application-level operations

    /**
     * Adds an application to the application book.
     * The application must not already exist in the application book.
     */
    public void addApplication(Application a) {
        applications.add(a);
    }

    @Override
    public ObservableList<Application> getApplicationList() {
        return applications.asUnmodifiableObservableList();
    }

}
