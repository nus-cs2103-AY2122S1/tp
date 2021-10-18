package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CommandInputTest {
    private static final String EMPTY_COMMAND = "";
    private static final String COMMAND_ONE = "command one";
    private static final String COMMAND_TWO = "command two";
    private static final String COMMAND_THREE = "command three";

    private final CommandInput commandInput = new CommandInput();

    @Test
    public void value_default_isEmptyString() {
        assertEquals(EMPTY_COMMAND, commandInput.value());
    }

    @Test
    public void value_edited_isCorrect() {
        String editedCommand = COMMAND_ONE;
        commandInput.set(editedCommand);
        assertEquals(editedCommand, commandInput.value());
    }

    @Test
    public void save_resetsValueToEmptyString() {
        String editedCommand = COMMAND_ONE;
        commandInput.set(editedCommand);
        commandInput.save();
        assertEquals(EMPTY_COMMAND, commandInput.value());
    }

    @Test
    public void value_saved_isCorrect() {
        commandInput.set(COMMAND_ONE);
        commandInput.save();
        commandInput.set(COMMAND_TWO);
        commandInput.save();
        commandInput.set(COMMAND_THREE);
        assertEquals(COMMAND_TWO, commandInput.next());
        assertEquals(COMMAND_ONE, commandInput.next());
        assertEquals(COMMAND_TWO, commandInput.previous());
        assertEquals(COMMAND_THREE, commandInput.previous());
    }

    @Test
    public void value_editedHistoricalCommand_isCorrect() {
        commandInput.set(COMMAND_ONE);
        commandInput.save();
        commandInput.next();
        commandInput.set(COMMAND_TWO);
        assertEquals(COMMAND_TWO, commandInput.value());
    }

    @Test
    public void save_editedHistoricalCommand_isNotSaved() {
        commandInput.set(COMMAND_ONE);
        commandInput.save();
        commandInput.next();
        commandInput.set(COMMAND_TWO);
        commandInput.save();
        assertEquals(EMPTY_COMMAND, commandInput.value());
        assertEquals(COMMAND_TWO, commandInput.next());
        assertEquals(COMMAND_ONE, commandInput.next());
    }
}
