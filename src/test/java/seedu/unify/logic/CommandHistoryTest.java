package seedu.unify.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.unify.logic.commands.exceptions.CommandException;

public class CommandHistoryTest {
    private CommandHistory history;

    @BeforeEach
    public void setUp() {
        history = CommandHistory.getInstance();
    }

    @Test
    public void addCommandToHistory() throws CommandException {
        final String firstValidCommand = "list";
        final String secondValidCommand = "clear";
        final String thirdValidCommand = "delete 1";


        // three commands stored in the history
        history.addCommandToHistory(firstValidCommand);
        history.addCommandToHistory(secondValidCommand);
        history.addCommandToHistory(thirdValidCommand);

        // checking if added correctly by going back
        assertEquals(thirdValidCommand, history.retrievePreviousCommand());
        assertEquals(secondValidCommand, history.retrievePreviousCommand());

        // checking if added correctly by going forward
        assertEquals(secondValidCommand, history.retrieveNextCommand());
        assertEquals(thirdValidCommand, history.retrieveNextCommand());
    }
}
