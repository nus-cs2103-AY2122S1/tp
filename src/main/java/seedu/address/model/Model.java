package seedu.address.model;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Hashtable;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

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
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' countdown file path.
     */
    Path getCountdownFilePath();

    /**
     * Returns the user prefs' shortcut file path.
     */
    Path getShortcutFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Sets the user prefs' countdown file path.
     */
    void setCountdownFilePath(Path countdownFilePath);

    /**
     * Sets the user prefs' shortcut file path
     */
    void setShortcutFilePath(Path shortcuteFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Replaces countdown data with the data in {@code countdown}.
     */
    void setCountdown(ReadOnlyCountdown countdown);

    /**
     * Replaces shortcut data with the data in {@code shortcut}.
     */
    void setShortcut(ReadOnlyShortcut shortcut);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /** Returns the Countdown */
    ReadOnlyCountdown getCountdown();

    /** Returns the Shortcut */
    ReadOnlyShortcut getShortcut();

    /** Adds a shortcut */
    void addShortcut(String keyword, String commandString);

    /** Removes a shortcut */
    String removeShortcut(String keyword);

    /** Get shortcut from Key */
    String getShortcutFromKey(String keyword);

    /** Lists all shortcuts */
    String listShortcut();

    /**
     * Returns the size of the address book
     */
    int size();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);


    /**
     * Returns true if a person with the same name as {@code name} exists in the address book.
     */
    boolean hasPersonName(String name);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Replaces the contents of the date with {@code weddingDate}.
     */
    void setDate(LocalDate newDate);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns an unmodifiable view of the wedding date.
     */
    LocalDate getWeddingDate();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns a HashSet of the unique Tags in the model, with the number of contacts labelled with
     * each respective tags as the value.
     */
    Hashtable<Tag, Integer> getUniqueTagTable();

    /**
     * Returns an observable list of the unique Tags in the model
     */
    ObservableList<Tag> getUniqueTagList();
}
