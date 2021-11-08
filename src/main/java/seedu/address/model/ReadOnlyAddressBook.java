package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.cca.Cca;
import seedu.address.model.person.Person;
import seedu.address.model.reminder.Reminder;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();
    /**
     * Returns an unmodifiable view of the CCA list.
     * This list will not contain any duplicate CCAs.
     */
    ObservableList<Cca> getCcaList();
    /**
     * Returns an unmodifiable view of the reminders list.
     * This list will not contain any duplicate reminders.
     */
    ObservableList<Reminder> getReminderList();

}
