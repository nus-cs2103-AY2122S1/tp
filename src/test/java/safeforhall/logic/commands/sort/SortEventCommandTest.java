package safeforhall.logic.commands.sort;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static safeforhall.logic.commands.CommandTestUtil.assertCommandSuccess;
import static safeforhall.logic.commands.sort.SortEventCommand.ASCENDING;
import static safeforhall.logic.commands.sort.SortEventCommand.DESCENDING;
import static safeforhall.testutil.Assert.assertThrows;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.model.Model;
import safeforhall.model.ModelManager;
import safeforhall.model.UserPrefs;
import safeforhall.model.event.Event;
import safeforhall.model.event.EventName;
import safeforhall.model.event.Venue;
import safeforhall.model.person.Faculty;
import safeforhall.model.person.Name;
import safeforhall.testutil.TypicalEvents;

public class SortEventCommandTest {

    @Test
    public void constructor_nullField_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortEventCommand(null, ASCENDING));
    }

    @Test
    public void constructor_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortEventCommand(EventName.FIELD, null));
    }

    @Test
    public void getComparator_invalidField_throwsCommandException() {
        SortEventCommand invalidFieldCommand = new SortEventCommand("z", ASCENDING);
        assertThrows(CommandException.class, SortEventCommand.ALLOWED_FIELDS, () ->
                invalidFieldCommand.getComparator());
    }

    @Test
    public void getComparator_invalidOrder_throwsCommandException() {
        SortEventCommand invalidFieldCommand = new SortEventCommand(EventName.FIELD, "c");
        assertThrows(CommandException.class, SortEventCommand.ALLOWED_ORDER, () ->
                invalidFieldCommand.getComparator());
    }

    @Test
    public void execute_listIsNotSorted_showsSortedList() {
        Model model = new ModelManager(TypicalEvents.getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(TypicalEvents.getTypicalAddressBook(), new UserPrefs());
        Comparator<Event> eventNameComparator = Comparator.comparing(Event::getEventName);
        expectedModel.updateSortedEventList(eventNameComparator);

        assertCommandSuccess(new SortEventCommand(EventName.FIELD, ASCENDING), model,
                SortEventCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsSorted_showsSameList() {
        Model model = new ModelManager(TypicalEvents.getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(TypicalEvents.getTypicalAddressBook(), new UserPrefs());
        Comparator<Event> eventNameComparator = Comparator.comparing(Event::getEventName);
        expectedModel.updateSortedEventList(eventNameComparator);
        model.updateSortedEventList(eventNameComparator);

        assertCommandSuccess(new SortEventCommand(Name.FIELD, ASCENDING), model,
                SortEventCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        SortEventCommand sortVenueAscendingCommand = new SortEventCommand(Venue.FIELD, ASCENDING);
        SortEventCommand sortVenueDescendingCommand = new SortEventCommand(Venue.FIELD, DESCENDING);
        SortEventCommand sortFacultyDescendingCommand = new SortEventCommand(Faculty.FIELD, DESCENDING);

        // same object -> returns true
        assertTrue(sortVenueAscendingCommand.equals(sortVenueAscendingCommand));

        // same values -> returns true
        SortEventCommand sortVenueAscendingCommandCopy = new SortEventCommand(Venue.FIELD, ASCENDING);
        assertTrue(sortVenueAscendingCommand.equals(sortVenueAscendingCommandCopy));

        // different types -> returns false
        assertFalse(sortVenueAscendingCommand.equals(1));

        // null -> returns false
        assertFalse(sortVenueAscendingCommand.equals(null));

        // different order -> returns false
        assertFalse(sortVenueAscendingCommand.equals(sortVenueDescendingCommand));

        // different field -> returns false
        assertFalse(sortVenueAscendingCommand.equals(sortFacultyDescendingCommand));
    }

}
