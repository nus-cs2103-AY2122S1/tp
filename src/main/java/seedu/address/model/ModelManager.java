package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;
import seedu.address.model.position.Position;

/**
 * Represents the in-memory model of the HR Manager data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final HrManager hrManager;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Position> filteredPositions;
    private final FilteredList<Interview> filteredInterviews;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyHrManager hrManager, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(hrManager, userPrefs);

        logger.fine("Initializing with HR Manager: " + hrManager + " and user prefs " + userPrefs);

        this.hrManager = new HrManager(hrManager);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.hrManager.getPersonList());
        filteredPositions = new FilteredList<>(this.hrManager.getPositionList());
        filteredInterviews = new FilteredList<>(this.hrManager.getInterviewList());
    }

    public ModelManager() {
        this(new HrManager(), new UserPrefs());
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
    public Path getHrManagerCandidatesFilePath() {
        return userPrefs.getHrManagerCandidatesFilePath();
    }

    @Override
    public Path getHrManagerPositionsFilePath() {
        return userPrefs.getHrManagerPositionsFilePath();
    }

    @Override
    public Path getHrManagerInterviewsFilePath() {
        return userPrefs.getHrManagerInterviewsFilePath();
    }

    @Override
    public void setHrManagerCandidatesFilePath(Path hrManagerCandidatesFilePath) {
        requireNonNull(hrManagerCandidatesFilePath);
        userPrefs.setHrManagerCandidatesFilePath(hrManagerCandidatesFilePath);
    }

    @Override
    public void setHrManagerPositionsFilePath(Path hrManagerPositionsFilePath) {
        requireNonNull(hrManagerPositionsFilePath);
        userPrefs.setHrManagerPositionsFilePath(hrManagerPositionsFilePath);
    }

    @Override
    public void setHrManagerInterviewsFilePath(Path hrManagerInterviewsFilePath) {
        requireNonNull(hrManagerInterviewsFilePath);
        userPrefs.setHrManagerInterviewsFilePath(hrManagerInterviewsFilePath);
    }

    //=========== HrManager ================================================================================

    @Override
    public void setHrManager(ReadOnlyHrManager hrManager) {
        this.hrManager.resetData(hrManager);
    }

    @Override
    public ReadOnlyHrManager getHrManager() {
        return hrManager;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return hrManager.hasPerson(person);
    }

    @Override
    public Person getPerson(Index index) {
        return hrManager.getPerson(index);
    }

    @Override
    public void deletePerson(Person target) {
        hrManager.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        hrManager.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        hrManager.setPerson(target, editedPerson);
    }

    @Override
    public boolean hasPosition(Position position) {
        requireNonNull(position);
        return hrManager.hasPosition(position);
    }

    @Override
    public Position getPositionReference(Position position) {
        return hrManager.getPosition(position);
    }

    @Override
    public void deletePosition(Position target) {
        hrManager.removePosition(target);
    }

    @Override
    public void addPosition(Position position) {
        hrManager.addPosition(position);
        updateFilteredPositionList(PREDICATE_SHOW_ALL_POSITIONS);
    }

    @Override
    public void setPosition(Position target, Position editedPosition) {
        requireAllNonNull(target, editedPosition);
        hrManager.setPosition(target, editedPosition);
    }

    public void deletePositionFromPerson(Position p) {
        hrManager.deletePositionFromPerson(p);
    }

    public boolean isPositionClosed(Position toCheck) {
        return hrManager.isPositionClosed(toCheck);
    }

    @Override
    public boolean hasInterview(Interview interview) {
        requireNonNull(interview);
        return hrManager.hasInterview(interview);
    }

    @Override
    public void deleteInterview(Interview target) {
        hrManager.removeInterview(target);
    }

    @Override
    public void addInterview(Interview interview) {
        hrManager.addInterview(interview);
        updateFilteredInterviewList(PREDICATE_SHOW_ALL_INTERVIEWS);
    }

    @Override
    public void setInterview(Interview target, Interview editedInterview) {
        requireAllNonNull(target, editedInterview);
        hrManager.setInterview(target, editedInterview);
    }

    public void deleteInterviewFromPerson(Interview interview) {
        hrManager.deleteInterviewFromPerson(interview);
    }

    public void deletePersonFromInterview(Person person) {
        hrManager.deletePersonFromInterview(person);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedHrManager}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Position} backed by the internal list of
     * {@code versionedHrManager}
     */
    @Override
    public ObservableList<Position> getFilteredPositionList() {
        return filteredPositions;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Interview} backed by the internal list of
     * {@code versionedHrManager}
     */
    @Override
    public ObservableList<Interview> getFilteredInterviewList() {
        return filteredInterviews;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public void updateFilteredPositionList(Predicate<Position> predicate) {
        requireNonNull(predicate);
        filteredPositions.setPredicate(predicate);
    }

    @Override
    public void updateFilteredInterviewList(Predicate<Interview> predicate) {
        requireNonNull(predicate);
        filteredInterviews.setPredicate(predicate);
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
        return hrManager.equals(other.hrManager)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && filteredPositions.equals(other.filteredPositions)
                && filteredInterviews.equals(other.filteredInterviews);
    }

}
