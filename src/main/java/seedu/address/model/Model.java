package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.application.Application;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Application> PREDICATE_SHOW_ALL_APPLICATIONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' Internship file path.
     */
    Path getInternshipFilePath();

    /**
     * Sets the user prefs' Internship file path.
     */
    void setInternshipFilePath(Path internshipFilePath);

    /**
     * Replaces Internship data with the data in {@code internship}.
     */
    void setInternship(ReadOnlyInternship internship);

    /** Returns the Internship */
    ReadOnlyInternship getInternship();

    /**
     * Returns true if a application with the same identity as {@code application} exists in the Internship.
     */
    boolean hasApplication(Application application);

    /**
     * Deletes the given application.
     * The application must exist in the Internship.
     */
    void deleteApplication(Application target);

    /**
     * Adds the given application.
     * {@code application} must not already exist in the Internship.
     */
    void addApplication(Application application);

    /**
     * Replaces the given application {@code target} with {@code editedApplication}.
     * {@code target} must exist in the Internship.
     * The application identity of {@code editedApplication} must not be the same as another
     * existing application in the Internship.
     */
    void setApplication(Application target, Application editedApplication);

    /** Returns an unmodifiable view of the filtered application list */
    ObservableList<Application> getFilteredApplicationList();

    /**
     * Updates the filter of the filtered application list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredApplicationList(Predicate<Application> predicate);

    /**
     * Commits the change made to Internship data to the versionedInternship
     * @param currentVersion the current version of Internship data as a result of change
     */
    void commitInternship(ReadOnlyInternship currentVersion);

    /**
     * Undo the most recent change made to Internship data
     */
    void undoInternship();

    /**
     * Redo the most recent change made to Internship data
     */
    void redoInternship();

    /**
     * Determines whether the undo action is allowed at the current state
     * @return whether the undo action is allowed
     */
    boolean canUndoInternship();

    /**
     * Determines whether the redo action is allowed at the current state
     * @return whether the redo action is allowed
     */
    boolean canRedoInternship();

    /**
     * Returns true if there is at least one application in the currently visible list whose
     * interview time list is not empty
     * @return true if there is at least one application in the currently visible list whose
     *      * interview time list is not empty, and false otherwise
     */
    boolean hasInterviewTimeInList();

}
