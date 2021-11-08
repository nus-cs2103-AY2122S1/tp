package seedu.address.testutil;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyHrManager;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;
import seedu.address.model.position.Position;

/**
 * A default model stub that have all of the methods failing.
 */
public class ModelStub implements Model {

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getHrManagerCandidatesFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getHrManagerPositionsFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getHrManagerInterviewsFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setHrManagerCandidatesFilePath(Path hrManagerCandidatesFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setHrManagerPositionsFilePath(Path hrManagerPositionsFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setHrManagerInterviewsFilePath(Path hrManagerInterviewsFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addPerson(Person person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setHrManager(ReadOnlyHrManager newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyHrManager getHrManager() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasPerson(Person person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasPosition(Position position) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Position getPositionReference(Position position) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deletePosition(Position target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addPosition(Position position) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setPosition(Position target, Position editedPosition) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Position> getFilteredPositionList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredPositionList(Predicate<Position> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deletePerson(Person target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    public void deletePositionFromPerson(Position p) {
        throw new AssertionError("This method should not be called.");
    }

    public boolean isPositionClosed(Position p) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasInterview(Interview interview) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteInterview(Interview target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Person getPerson(Index index) {
        throw new AssertionError("This method should not be called.");
    }
    public void addInterview(Interview interview) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setInterview(Interview target, Interview editedInterview) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Interview> getFilteredInterviewList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredInterviewList(Predicate<Interview> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    public void deleteInterviewFromPerson(Interview i) {
        throw new AssertionError("This method should not be called.");
    }

    public void deletePersonFromInterview(Person p) {
        throw new AssertionError("This method should not be called.");
    }
}
