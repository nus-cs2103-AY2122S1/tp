package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.modulelesson.ModuleLesson;
import seedu.address.model.modulelesson.UniqueModuleLessonList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at contHACKS level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class Conthacks implements ReadOnlyConthacks {

    private final UniquePersonList persons;
    private final UniqueModuleLessonList moduleLessons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        moduleLessons = new UniqueModuleLessonList();
    }

    public Conthacks() {}

    /**
     * Creates an Conthacks using the Persons in the {@code toBeCopied}
     */
    public Conthacks(ReadOnlyConthacks toBeCopied) {
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
     * Replaces the contents of the lesson list with {@code lessons}.
     * {@code lessons} must not contain duplicate lesson;
     */
    public void setModuleLessons(List<ModuleLesson> lessons) {
        moduleLessons.setModuleLessons(lessons);
    }

    /**
     * Resets the existing data of this {@code Conthacks} with {@code newData}.
     */
    public void resetData(ReadOnlyConthacks newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setModuleLessons(newData.getModuleLessonList());
    }

    /**
     * Sorts the contacts in contHACKS by alphabetical order.
     */
    public void sortConthacks() {
        persons.sortList();
        moduleLessons.sortList();
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in contHACKS.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns true if a lesson with the same identity as {@code moduleLesson} exists in contHACKS.
     */
    public boolean hasLesson(ModuleLesson moduleLesson) {
        requireNonNull(moduleLesson);
        return moduleLessons.contains(moduleLesson);
    }

    /**
     * Returns true if a lesson that clashes with {@code moduleLesson} exists in contHACKS.
     */
    public boolean hasLessonClashingWith(ModuleLesson moduleLesson) {
        requireNonNull(moduleLesson);
        return moduleLessons.containsLessonClashingWith(moduleLesson);
    }

    /**
     * Adds a person to contHACKS.
     * The person must not already exist in contHACKS.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Adds a lesson to contHACKS.
     * The lesson must not already exist in contHACKS.
     */
    public void addLesson(ModuleLesson m) {
        moduleLessons.add(m);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in contHACKS.
     * The person identity of {@code editedPerson} must not be the same as another existing person in contHACKS.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Replaces the given lesson {@code target} in the list with {@code editedLesson}.
     * {@code target} must exist in contHACKS.
     * The lesson information of {@code editedLesson} must not be the same as another
     * existing lesson in contHACKS.
     */
    public void setModuleLesson(ModuleLesson target, ModuleLesson editedLesson) {
        requireNonNull(editedLesson);

        moduleLessons.setModuleLesson(target, editedLesson);
    }

    /**
     * Removes {@code key} from this {@code Conthacks}.
     * {@code key} must exist in contHACKS.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    /**
     * Removes {@code key} from this {@code Conthacks}.
     * {@code key} must exist in contHACKS.
     */
    public void removeLesson(ModuleLesson key) {
        moduleLessons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<ModuleLesson> getModuleLessonList() {
        return moduleLessons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Conthacks // instanceof handles nulls
                && persons.equals(((Conthacks) other).persons))
                && moduleLessons.equals(((Conthacks) other).moduleLessons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(persons, moduleLessons);
    }

    public void clearPersonList() {
        setPersons(new ArrayList<>());
    }

    public void clearLessonList() {
        setModuleLessons(new ArrayList<>());
    }
}
