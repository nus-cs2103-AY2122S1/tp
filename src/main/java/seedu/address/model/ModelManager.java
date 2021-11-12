package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Entry;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

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
    public boolean hasClashingLesson(Lesson lesson) {
        requireNonNull(lesson);
        return addressBook.hasClashingLesson(lesson);
    }

    @Override
    public boolean hasClashingLesson(Lesson lesson, Lesson lessonToIgnore) {
        requireNonNull(lesson);
        return addressBook.hasClashingLesson(lesson, lessonToIgnore);
    }

    @Override
    public Set<String> getClashingLessonsString(Lesson lesson) {
        requireNonNull(lesson);
        return addressBook.getClashingLessonsString(lesson);
    }

    @Override
    public Set<String> getClashingLessonsString(Lesson lesson, Lesson lessonToIgnore) {
        requireNonNull(lesson);
        return addressBook.getClashingLessonsString(lesson, lessonToIgnore);
    }

    @Override
    public ObservableList<Person> getUnfilteredPersonList() {
        return addressBook.getPersonList();
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
    public void addPersonAtIndex(Person person, Index index) {
        addressBook.addPerson(index, person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public Calendar getCalendar() {
        return addressBook.getCalendar();
    }

    @Override
    public ObservableList<Entry<Lesson>> getUpcomingLessons() {
        return addressBook.getUpcomingLessons();
    }

    @Override
    public void updateUpcomingLessons() {
        addressBook.updateUpcomingLessons();
    }

    @Override
    public ObservableList<Tag> getObservableTagList() {
        return addressBook.getTagList();
    }

    @Override
    public ObservableMap<Tag, Integer> getTagCounter() {
        return addressBook.getTagCounter();
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
    public boolean hasPersonFilteredList(Person person) {
        requireNonNull(person);
        return filteredPersons.contains(person);
    }

    //=========== Last Updated Accessors =============================================================

    /**
     * Returns an immutable last updated date of the address book.
     *
     * @return An immutable {@code LastUpdatedDate}.
     */
    @Override
    public LastUpdatedDate getLastUpdatedDate() {
        return addressBook.getLastUpdatedDate();
    }

    /**
     * Sets the lastUpdatedDate field in {@code AddressBook} to the local date time when
     * the application was initially launched.
     */
    @Override
    public void setLastUpdatedDate() {
        addressBook.setLastUpdatedDate(new LastUpdatedDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressBook, userPrefs, filteredPersons);
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
