package safeforhall.logic.commands.find;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static safeforhall.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import safeforhall.commons.core.Messages;
import safeforhall.logic.commands.find.FindEventCommand.FindCompositePredicate;
import safeforhall.model.Model;
import safeforhall.model.ModelManager;
import safeforhall.model.UserPrefs;
import safeforhall.model.event.Capacity;
import safeforhall.model.event.EventDate;
import safeforhall.model.event.EventName;
import safeforhall.model.event.Venue;
import safeforhall.testutil.TypicalEvents;

/**
 * Contains integration tests (interaction with the Model) for {@code FindEventCommand}.
 */
public class FindEventCommandTest {
    private Model model = new ModelManager(TypicalEvents.getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalEvents.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        FindCompositePredicate firstPredicate = new FindCompositePredicate();
        firstPredicate.setEventName(new EventName("Training"));
        firstPredicate.setEventDate(new EventDate("01-03-2021"));
        firstPredicate.setVenue(new Venue("Field"));
        firstPredicate.setCapacity(new Capacity("5"));

        FindCompositePredicate secondPredicate = new FindCompositePredicate();

        FindEventCommand findFirstCommand = new FindEventCommand(firstPredicate);
        FindEventCommand findSecondCommand = new FindEventCommand(secondPredicate);

        assertFalse(firstPredicate.equals(1));

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindEventCommand findFirstCommandCopy = new FindEventCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different Event -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noEventFound() {
        String expectedMessage = String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, 0);
        FindCompositePredicate predicate = preparePredicate("null", null, null, null);
        FindEventCommand command = new FindEventCommand(predicate);
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEventList());
    }

    @Test
    public void execute_multipleKeywords_multipleEventsFound() {
        String expectedMessage = String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, 3);
        FindCompositePredicate predicate = preparePredicate("basketball powerlifting band", null, null, null);
        FindEventCommand command = new FindEventCommand(predicate);
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalEvents.BASKETBALL, TypicalEvents.POWERLIFTING, TypicalEvents.BAND),
                model.getFilteredEventList());
    }

    @Test
    public void execute_multipleKeywords_multipleEventsFound2() {
        String expectedMessage = String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, 2);
        FindCompositePredicate predicate = preparePredicate("swim road relay", null, null, null);
        FindEventCommand command = new FindEventCommand(predicate);
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalEvents.SWIM, TypicalEvents.ROAD_RELAY),
                model.getFilteredEventList());
    }

    @Test
    public void execute_multipleKeywords_multipleEventsFound3() {
        String expectedMessage = String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, 3);
        FindCompositePredicate predicate = preparePredicate("swim band volleyball", null, null, null);
        FindEventCommand command = new FindEventCommand(predicate);
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalEvents.VOLLEYBALL, TypicalEvents.SWIM, TypicalEvents.BAND),
                model.getFilteredEventList());
    }

    @Test
    public void execute_missingFieldWithPrefix_fail() {
        try {
            String expectedMessage = String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, 3);
            FindCompositePredicate predicate = preparePredicate("", null, null, null);
            FindEventCommand command = new FindEventCommand(predicate);
            expectedModel.updateFilteredEventList(predicate);
            assertCommandSuccess(command, model, expectedMessage, expectedModel);
            assertEquals(Arrays.asList(TypicalEvents.SWIM, TypicalEvents.BAND, TypicalEvents.VOLLEYBALL),
                    model.getFilteredEventList());
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(1, 1);
        }
    }

    /**
     * Parses {@code userInput} into a {@code FindCompositePredicate}.
     */
    private FindCompositePredicate preparePredicate(String eventName, String eventDate, String venue, String capacity) {
        FindCompositePredicate f = new FindCompositePredicate();

        if (eventName != null) {
            f.setEventName(new EventName(eventName));
        }
        if (eventDate != null) {
            f.setEventDate(new EventDate(eventDate));
        }
        if (venue != null) {
            f.setVenue(new Venue(venue));
        }
        if (capacity != null) {
            f.setCapacity(new Capacity(capacity));
        }

        return f;
    }
}
