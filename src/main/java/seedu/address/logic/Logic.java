package seedu.address.logic;

import java.nio.file.Path;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Entry;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.LastUpdatedDate;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the AddressBook.
     *
     * @see seedu.address.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the observable list of tags */
    ObservableList<Tag> getObservableTagList();

    /** Returns an unmodifiable view of the tag counter. */
    ObservableMap<Tag, Integer> getTagCounter();

    /** Returns an unmodifiable view of the observable list of lessons belonging to the student. */
    ObservableList<Lesson> getLessonList(Person student);

    /** Returns an unmodifiable view of an empty observable list of lessons. */
    ObservableList<Lesson> getEmptyLessonList();

    /** Returns the CalendarFX calendar */
    Calendar getCalendar();

    /** Returns an unmodifiable view of the list of upcoming lessons within two days. */
    ObservableList<Entry<Lesson>> getUpcomingLessons();

    /** Updates the list of upcoming lists within two days. */
    void updateUpcomingLessons();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the last time when the system was updated.
     */
    LastUpdatedDate getLastUpdatedDate();
}
