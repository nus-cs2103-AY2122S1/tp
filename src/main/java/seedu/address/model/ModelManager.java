package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final Countdown countdown;
    private final Shortcut shortcut;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given addressBook, countdown and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyCountdown countdown,
                        ReadOnlyUserPrefs userPrefs, ReadOnlyShortcut shortcut) {
        super();
        requireAllNonNull(addressBook, countdown, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + ", countdown: " + countdown
                + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.countdown = new Countdown(countdown);
        this.shortcut = new Shortcut(shortcut);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
    }

    public ModelManager() {
        this(new AddressBook(), new Countdown(), new UserPrefs(), new Shortcut());
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
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public Path getCountdownFilePath() {
        return userPrefs.getCountdownFilePath();
    }

    @Override
    public Path getShortcutFilePath() {
        return userPrefs.getShortcutFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    @Override
    public void setCountdownFilePath(Path countdownFilePath) {
        requireNonNull(countdownFilePath);
        userPrefs.setCountdownFilePath(countdownFilePath);
    }

    @Override
    public void setShortcutFilePath(Path shortcutFilePath) {
        requireNonNull(shortcutFilePath);
        userPrefs.setShortcutFilePath(shortcutFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public int size() {
        return addressBook.getPersonsSize();
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public boolean hasPersonName(String name) {
        requireNonNull(name);
        return addressBook.hasPerson(Person.createTempFakePerson(name));
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public ObservableList<Tag> getUniqueTagList() {
        ObservableList<Person> contactList = this.addressBook.getPersonList();
        ArrayList<Tag> tagList = new ArrayList<>();
        for (Person contact : contactList) {
            Set<Tag> curContactTagSet = contact.getTags();
            for (Tag tag : curContactTagSet) {
                if (!tagList.contains(tag)) {
                    tagList.add(tag);
                }
            }
        }
        return FXCollections.observableList(tagList);
    }

    @Override
    public Hashtable<Tag, Integer> getUniqueTagTable() {
        ObservableList<Person> contactList = this.addressBook.getPersonList();
        Hashtable<Tag, Integer> uniqueTagSet = new Hashtable<>();
        for (Person contact : contactList) {
            Set<Tag> curContactTagSet = contact.getTags();
            for (Tag tag : curContactTagSet) {
                if (uniqueTagSet.containsKey(tag)) {
                    uniqueTagSet.merge(tag, 1, Integer::sum);
                } else {
                    uniqueTagSet.put(tag, 1);
                }
            }
        }
        return uniqueTagSet;
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
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
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }


    //=========== Countdown ================================================================================

    @Override
    public void setCountdown(ReadOnlyCountdown countdown) {
        this.countdown.resetData(countdown);
    }

    @Override
    public ReadOnlyCountdown getCountdown() {
        return countdown;
    }

    @Override
    public void setDate(LocalDate newDate) {
        requireAllNonNull(newDate);

        countdown.setDate(newDate);
    }

    @Override
    public LocalDate getWeddingDate() {
        return countdown.getDate();
    }

    //=========== Shortcut ================================================================================
    @Override
    public void setShortcut(ReadOnlyShortcut shortcut) {
        requireNonNull(shortcut);
        this.shortcut.resetData(shortcut);
    }

    @Override
    public ReadOnlyShortcut getShortcut() {
        return shortcut;
    }

    @Override
    public String removeShortcut(String keyword) {
        return shortcut.removeShortcut(keyword);
    }

    @Override
    public void addShortcut(String keyword, String commandString) {
        requireAllNonNull(keyword, commandString);
        shortcut.addShortcut(keyword, commandString);
    }

    @Override
    public String getShortcutFromKey(String keyword) {
        requireNonNull(keyword);
        return shortcut.getCommandFromKey(keyword);
    }

    @Override
    public String listShortcut() {
        return shortcut.toString();
    }

}
