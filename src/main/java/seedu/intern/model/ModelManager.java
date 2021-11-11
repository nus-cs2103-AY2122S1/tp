package seedu.intern.model;

import static java.util.Objects.requireNonNull;
import static seedu.intern.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.intern.commons.core.GuiSettings;
import seedu.intern.commons.core.LogsCenter;
import seedu.intern.logic.commands.exceptions.CommandException;
import seedu.intern.model.applicant.Applicant;

/**
 * Represents the in-memory model of the intern book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private final VersionedInternWatcher internWatcher;
    private final UserPrefs userPrefs;
    private final FilteredList<Applicant> filteredApplicants;
    private Applicant applicant;
    private boolean isToggle;

    /**
     * Initializes a ModelManager with the given internWatcher and userPrefs.
     */
    public ModelManager(ReadOnlyInternWatcher internWatcher, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(internWatcher, userPrefs);

        logger.fine("Initializing with intern book: " + internWatcher + " and user prefs " + userPrefs);

        this.internWatcher = new VersionedInternWatcher(internWatcher);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredApplicants = new FilteredList<>(this.internWatcher.getApplicantList());
    }

    public ModelManager() {
        this(new InternWatcher(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getInternWatcherFilePath() {
        return userPrefs.getInternWatcherFilePath();
    }

    @Override
    public void setInternWatcherFilePath(Path internWatcherFilePath) {
        requireNonNull(internWatcherFilePath);
        userPrefs.setInternWatcherFilePath(internWatcherFilePath);
    }

    //=========== InternWatcher ================================================================================

    @Override
    public void setInternWatcher(ReadOnlyInternWatcher internWatcher) {
        this.internWatcher.resetData(internWatcher);
    }

    @Override
    public ReadOnlyInternWatcher getInternWatcher() {
        return internWatcher;
    }

    @Override
    public boolean hasApplicant(Applicant applicant) {
        requireNonNull(applicant);
        return internWatcher.hasApplicant(applicant);
    }

    @Override
    public void deleteApplicant(Applicant target) {
        internWatcher.removeApplicant(target);
    }

    @Override
    public void addApplicant(Applicant applicant) {
        internWatcher.addApplicant(applicant);
        updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
    }

    @Override
    public void setApplicant(Applicant target, Applicant editedApplicant) {
        requireAllNonNull(target, editedApplicant);

        internWatcher.setApplicant(target, editedApplicant);
    }

    //=========== Filtered Applicant List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Applicant} backed by the internal list of
     * {@code versionedInternWatcher}
     */
    @Override
    public ObservableList<Applicant> getFilteredApplicantList() {
        return filteredApplicants;
    }

    @Override
    public void updateFilteredApplicantList(Predicate<Applicant> predicate) {
        requireNonNull(predicate);
        filteredApplicants.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return internWatcher.equals(other.internWatcher)
                && userPrefs.equals(other.userPrefs)
                && filteredApplicants.equals(other.filteredApplicants);
    }

    //=========== Undo/Redo ============================================================================

    @Override
    public void commitInternWatcher(String commitMessage) {
        internWatcher.commitState(commitMessage);
    }

    @Override
    public String undoInternWatcher() throws CommandException {
        return internWatcher.undo();
    }

    @Override
    public String redoInternWatcher() throws CommandException {
        return internWatcher.redo();
    }

    @Override
    public boolean isUndoAvailable() {
        return internWatcher.canUndo();
    }

    @Override
    public boolean isRedoAvailable() {
        return internWatcher.canRedo();
    }

    //=========== View ============================================================================
    @Override
    public void displayApplicant(Applicant applicant, boolean isToggle) {
        updateApplicant(applicant);
        updateGetIsToggle(isToggle);
    }

    @Override
    public boolean getIsToggle() {
        return isToggle;
    }

    private void updateGetIsToggle(boolean isToggle) {
        this.isToggle = isToggle;
    }

    @Override
    public Applicant getApplicant() {
        return applicant;
    }

    @Override
    public void updateApplicant(Applicant newApplicant) {
        applicant = newApplicant;
    }

}
