package nustracker.logic.commands;

import static nustracker.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import nustracker.model.Model;
import nustracker.model.ModelManager;

class EventsCommandTest {

    private final Model model = new ModelManager();
    private final Model expectedModel = new ModelManager();

    @Test
    void execute_events_success() {
        CommandResult expectedCommandResult = new CommandResult(EventsCommand.MESSAGE_SHOW_EVENTS_SUCCESS,
                false, false, false, false, false, false, true);
        assertCommandSuccess(new EventsCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    void equals() {
        EventsCommand eventsCommand = new EventsCommand();

        assertTrue(eventsCommand.equals(eventsCommand));
        assertTrue(eventsCommand.equals(new EventsCommand()));

        assertFalse(eventsCommand.equals(null));
        assertFalse(eventsCommand.equals(new StudentsCommand()));
    }
}
