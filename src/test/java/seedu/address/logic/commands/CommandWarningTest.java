package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;

public class CommandWarningTest {
    @Test
    public void getValueTest() {
        String expectedValue = Messages.MESSAGE_INVALID_VISIT_DATE;
        assertEquals(CommandWarning.PAST_NEXT_VISIT_WARNING.getValue(), expectedValue);
    }

    @Test
    public void equals() {
        CommandWarning warning1 = new CommandWarning(Messages.MESSAGE_INVALID_LAST_VISIT_DATE);
        assertTrue(warning1.equals(CommandWarning.FUTURE_LAST_VISIT_WARNING));
    }

}
