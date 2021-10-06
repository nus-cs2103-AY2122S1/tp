package seedu.siasa.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.siasa.commons.core.GuiSettings;
import seedu.siasa.model.person.Person;
import seedu.siasa.model.policy.Policy;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Policy> PREDICATE_SHOW_ALL_POLICIES = unused -> true;

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
     * Returns the user prefs' SIASA file path.
     */
    Path getSiasaFilePath();

    /**
     * Sets the user prefs' SIASA file path.
     */
    void setSiasaFilePath(Path siasaFilePathPath);

    /**
     * Replaces SIASA data with the data in {@code siasa}.
     */
    void setSiasa(ReadOnlySiasa siasa);

    /** Returns the AddressBook */
    ReadOnlySiasa getSiasa();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the SIASA.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the SIASA.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the SIASA.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the SIASA.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the SIASA.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns true if a policy with the same identity as {@code policy} exists in the SIASA.
     */
    boolean hasPolicy(Policy policy);

    /**
     * Deletes the given policy.
     * The policy must exist in the SIASA.
     */
    void deletePolicy(Policy target);

    /**
     * Adds the given policy.
     * {@code policy} must not already exist in the SIASA.
     */
    void addPolicy(Policy policy);

    /**
     * Replaces the given policy {@code target} with {@code editedPolicy}.
     * {@code target} must exist in the SIASA.
     * The policy identity of {@code editedPolicy} must not be the same as another existing policy in the SIASA.
     */
    void setPolicy(Policy target, Policy editedPolicy);

    /** Returns an unmodifiable view of the filtered policy list */
    ObservableList<Policy> getFilteredPolicyList();

    /**
     * Updates the filter of the filtered policy list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPolicyList(Predicate<Policy> predicate);
}
