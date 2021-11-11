package seedu.intern.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.intern.commons.core.GuiSettings;
import seedu.intern.logic.commands.exceptions.CommandException;
import seedu.intern.model.applicant.Applicant;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Applicant> PREDICATE_SHOW_ALL_APPLICANTS = unused -> true;

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
     * Returns the user prefs' intern watcher file path.
     */
    Path getInternWatcherFilePath();

    /**
     * Sets the user prefs' intern watcher file path.
     */
    void setInternWatcherFilePath(Path internWatcherFilePath);

    /**
     * Replaces intern watcher data with the data in {@code InternWatcher}.
     */
    void setInternWatcher(ReadOnlyInternWatcher internWatcher);

    /** Returns the InternWatcher */
    ReadOnlyInternWatcher getInternWatcher();

    /**
     * Returns true if a applicant with the same identity as {@code applicant} exists in the
     * intern watcher.
     */
    boolean hasApplicant(Applicant applicant);

    /**
     * Displays given applicant
     */
    void displayApplicant(Applicant applicant, boolean isToggle);

    /**
     * Deletes the given applicant.
     * The applicant must exist in the intern watcher.
     */
    void deleteApplicant(Applicant target);

    /**
     * Adds the given applicant.
     * {@code applicant} must not already exist in the intern watcher.
     */
    void addApplicant(Applicant applicant);

    /**
     * Replaces the given applicant {@code target} with {@code editedApplicant}.
     * {@code target} must exist in the intern watcher.
     * The applicant identity of {@code editedApplicant} must not be the same as another existing applicant in the
     * intern watcher.
     */
    void setApplicant(Applicant target, Applicant editedApplicant);

    /** Returns an unmodifiable view of the filtered applicant list */
    ObservableList<Applicant> getFilteredApplicantList();

    /** Returns an unmodifiable view of the applicant to display */
    Applicant getApplicant();

    /**
     * Updates the selected applicant.
     */
    void updateApplicant(Applicant newApplicant);

    /**
     * Updates the filter of the filtered applicant list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredApplicantList(Predicate<Applicant> predicate);

    /**
     * Saves the current InternWatcher state to its history.
     */
    void commitInternWatcher(String commitMessage);

    /**
     * Restores the InternWatcher to a previous state from its history.
     * @return Command that was undone.
     */
    String undoInternWatcher() throws CommandException;

    /**
     * Restores the InternWatcher to a previously undone state from its history.
     * @return Command that was redone.
     */
    String redoInternWatcher() throws CommandException;

    /**
     * Returns true if the model has a previous state to revert to.
     * @return if an undo action is possible.
     */
    boolean isUndoAvailable();

    /**
     * Returns true if the model has a previous undone state to restore to.
     * @return if a redo action is possible.
     */
    boolean isRedoAvailable();

    /**
     * Returns the toggle flag
     */
    boolean getIsToggle();
}
