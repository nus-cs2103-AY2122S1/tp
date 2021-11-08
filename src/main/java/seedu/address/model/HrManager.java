package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.interview.Interview;
import seedu.address.model.interview.UniqueInterviewList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.position.Position;
import seedu.address.model.position.UniquePositionList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class HrManager implements ReadOnlyHrManager {

    private final UniquePersonList persons;

    private final UniquePositionList positions;

    private final UniqueInterviewList interviews;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */

    {
        persons = new UniquePersonList();
        positions = new UniquePositionList();
        interviews = new UniqueInterviewList();
    }

    public HrManager() {
    }

    /**
     * Creates an HrManager using the persons, positions and interviews in the {@code toBeCopied}
     */
    public HrManager(ReadOnlyHrManager toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the position list with {@code positions}.
     * {@code positions} must not contain duplicate positions.
     */
    public void setPositions(List<Position> positions) {
        this.positions.setPositions(positions);
    }

    /**
     * Replaces the contents of the interview list with {@code interviews}.
     * {@code interviews} must not contain duplicate interviews.
     */
    public void setInterviews(List<Interview> interviews) {
        this.interviews.setInterviews(interviews);
    }

    /**
     * Resets the existing data of this {@code HrManager} with {@code newData}.
     */
    public void resetData(ReadOnlyHrManager newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setPositions(newData.getPositionList());
        setInterviews(newData.getInterviewList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the HR Manager.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the HR Manager.
     * The person must not already exist in the HR Manager.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the HR Manager.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the HR Manager.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code HrManager}.
     * {@code key} must exist in the HR Manager.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// position-level operations

    /**
     * Returns true if a position with the same identity as {@code position} exists in the HR Manager.
     */
    public boolean hasPosition(Position position) {
        requireNonNull(position);
        return positions.contains(position);
    }

    public Position getPosition(Position position) {
        return positions.getPosition(position);
    }

    /**
     * Adds a position to the HR Manager.
     * The position must not already exist in the HR Manager.
     */
    public void addPosition(Position p) {
        positions.add(p);
    }

    /**
     * Replaces the given position {@code target} in the list with {@code editedPosition}.
     * {@code target} must exist in the HR Manager.
     * The position identity of {@code editedPosition} must not be the same as another existing position in the HR
     * Manager.
     */
    public void setPosition(Position target, Position editedPosition) {
        requireNonNull(editedPosition);

        positions.setPosition(target, editedPosition);
    }

    /**
     * Removes {@code key} from this {@code HrManager}.
     * {@code key} must exist in the HR Manager.
     */
    public void removePosition(Position key) {
        positions.remove(key);
    }

    /**
     * Deletes a position from every candidate
     *
     * @param p The position to be deleted
     */
    public void deletePositionFromPerson(Position p) {
        for (Person person : persons) {
            if (person.appliedForPosition(p)) {
                person.deletePosition(p);
            }
        }
    }

    public boolean isPositionClosed(Position toCheck) {
        return positions.positionIsClosed(toCheck);
    }

    //// interview-level operations

    /**
     * Returns true if an interview with the same identity as {@code interview} exists in the HR Manager.
     */
    public boolean hasInterview(Interview interview) {
        requireNonNull(interview);
        return interviews.contains(interview);
    }

    /**
     * Adds an interview to the HR Manager.
     * The interview must not already exist in the HR Manager.
     */
    public void addInterview(Interview interview) {
        interviews.add(interview);
    }

    /**
     * Replaces the given interview {@code target} in the list with {@code editedInterview}.
     * {@code target} must exist in the HR Manager.
     * The interview identity of {@code editedInterview} must not be the same as another existing interview in the
     * HR Manager.
     */
    public void setInterview(Interview target, Interview editedInterview) {
        requireNonNull(editedInterview);

        interviews.setInterview(target, editedInterview);
    }

    /**
     * Removes {@code key} from this {@code HrManager}.
     * {@code key} must exist in the HR Manager.
     */
    public void removeInterview(Interview key) {
        interviews.remove(key);
    }

    /**
     * Deletes Interview from a Person's interviewList.
     */
    public void deleteInterviewFromPerson(Interview interview) {
        for (Person person : persons) {
            if (person.hasInterview(interview)) {
                person.deleteInterview(interview);
            }
        }
    }

    /**
     * Deletes Person from an Interview's personList.
     */
    public void deletePersonFromInterview(Person person) {
        for (Interview interview : interviews) {
            if (interview.hasCandidate(person)) {
                interview.deleteCandidate(person);
            }
        }
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons\n"
                + positions.asUnmodifiableObservableList().size() + " positions\n"
                + interviews.asUnmodifiableObservableList().size() + " interviews";
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Position> getPositionList() {
        return positions.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Interview> getInterviewList() {
        return interviews.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HrManager // instanceof handles nulls
                && persons.equals(((HrManager) other).persons))
                && positions.equals(((HrManager) other).positions)
                && interviews.equals(((HrManager) other).interviews);
    }

    @Override
    public int hashCode() {
        return persons.hashCode() ^ positions.hashCode() ^ interviews.hashCode();
    }

    public Person getPerson(Index index) {
        return persons.getPerson(index);
    }
}
