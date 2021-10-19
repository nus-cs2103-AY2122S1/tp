package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.MonthDay;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Person;
import seedu.address.model.util.PersonBirthdayComparator;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Person> birthdayReminders;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        this.birthdayReminders = generateBirthdayReminderList(addressBook);
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
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
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
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
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
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

    //=========== Birthday Reminder List Accessors ===========================================================
    private static FilteredList<Person> generateBirthdayReminderList(ReadOnlyAddressBook addressBook) {

        // Retrieves birthdays that have yet to pass
        CompletableFuture<Stream<Person>> beforeCurrentDay = CompletableFuture.supplyAsync(() ->
            addressBook
                .getPersonList()
                .stream()
                .filter(p -> p.getBirthday().isPresent() && !haveBirthdayAfterCurrentDay(p))
                .sorted(new PersonBirthdayComparator())
        );

        // Retrieves birthdays that have passed
        CompletableFuture<Stream<Person>> currentDayOnwards = CompletableFuture.supplyAsync(() ->
            addressBook
                .getPersonList()
                .stream()
                .filter(p -> p.getBirthday().isPresent() && haveBirthdayAfterCurrentDay(p))
                .sorted(new PersonBirthdayComparator())
        );
        Stream<Person> concatStream = Stream.concat(beforeCurrentDay.join(), currentDayOnwards.join());

        return new FilteredList<>(
                concatStream.collect(Collectors.toCollection(FXCollections::observableArrayList))
        );
    }

    private static boolean haveBirthdayAfterCurrentDay(Person p) {
        MonthDay currentMonthDay = MonthDay.now();
        Optional<Birthday> possibleBirthday = p.getBirthday();
        return possibleBirthday
            .map(birthday -> currentMonthDay.isAfter(MonthDay.from((birthday.birthdate))))
            .orElse(false);
    }

    @Override
    public ObservableList<Person> getBirthdayReminderList() {
        return birthdayReminders;
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

}
