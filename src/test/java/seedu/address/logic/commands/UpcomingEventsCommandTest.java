package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_UPCOMING_EVENT_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.ALICE;
import static seedu.address.testutil.TypicalEvents.REBECCA;
import static seedu.address.testutil.TypicalEvents.getTypicalSchedule;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Date;
import seedu.address.model.event.EventContainsCurrentTimePredicate;
import seedu.address.model.event.EventContainsCurrentTimePredicateTest;

/**
 * Contains integration tests (interaction with the Model) and unit tests for UpcomingEventsCommand.
 */
public class UpcomingEventsCommandTest {
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSchedule(), new UserPrefs());
        model.addEvent(REBECCA);
        expectedModel = new ModelManager(model.getSchedule(), new UserPrefs());
    }

    @Test
    public void execute_upcomingEvents() {
        String expectedMessage = String.format(MESSAGE_UPCOMING_EVENT_LISTED_OVERVIEW, 2);
        EventContainsCurrentTimePredicateTest.EventContainsCurrentTimePredicateStub predicate =
                new EventContainsCurrentTimePredicateTest.EventContainsCurrentTimePredicateStub();
        UpcomingEventsCommand command = new UpcomingEventsCommand(predicate);
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, REBECCA), model.getFilteredEventList());
    }
    @Test
    public void equals() {
        UpcomingEventsCommand firstCommand = new UpcomingEventsCommand();
        EventContainsCurrentTimePredicate firstPredicate =
                new EventContainsCurrentTimePredicate(LocalTime.parse("0730", timeFormatter),
                        new Date("2020-01-01"));
        EventContainsCurrentTimePredicate secondPredicate =
                new EventContainsCurrentTimePredicate(LocalTime.parse("0830", timeFormatter),
                        new Date("2020-01-02"));
        UpcomingEventsCommand thirdCommand = new UpcomingEventsCommand(firstPredicate);
        UpcomingEventsCommand fourthCommand = new UpcomingEventsCommand(secondPredicate);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different dates and time -> returns false
        assertFalse(thirdCommand.equals(fourthCommand));
    }
}
