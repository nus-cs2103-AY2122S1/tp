package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.model.CommandHistory.getNextCommand;
import static seedu.address.model.CommandHistory.getPreviousCommand;
import static seedu.address.testutil.CommandHistoryBuilder.SAMPLE_COMMAND_1;
import static seedu.address.testutil.CommandHistoryBuilder.SAMPLE_COMMAND_2;
import static seedu.address.testutil.CommandHistoryBuilder.SAMPLE_COMMAND_3;
import static seedu.address.testutil.CommandHistoryBuilder.SAMPLE_COMMAND_4;
import static seedu.address.testutil.CommandHistoryBuilder.emptyHistory;
import static seedu.address.testutil.CommandHistoryBuilder.validHistory;

import org.junit.jupiter.api.Test;

public class CommandHistoryTest {

    @Test
    public void validHistory_success() {
        validHistory();
        assertEquals(getPreviousCommand(), SAMPLE_COMMAND_4);
        assertEquals(getPreviousCommand(), SAMPLE_COMMAND_3);
        assertEquals(getPreviousCommand(), SAMPLE_COMMAND_2);
        assertEquals(getPreviousCommand(), SAMPLE_COMMAND_1);
        assertEquals(getPreviousCommand(), SAMPLE_COMMAND_1);

        assertEquals(getNextCommand(), SAMPLE_COMMAND_2);
        assertEquals(getNextCommand(), SAMPLE_COMMAND_3);
        assertEquals(getNextCommand(), SAMPLE_COMMAND_4);
        assertEquals(getNextCommand(), "");
    }

    @Test
    public void emptyHistory_success() {
        emptyHistory();
        assertEquals(getPreviousCommand(), "");
        assertEquals(getNextCommand(), "");
    }
}
