package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AccessCacheCommand;
import seedu.address.logic.commands.ViewTaskListCommand;

public class AddressBookInternalParserTest {

    private final AddressBookInternalParser parser = new AddressBookInternalParser();

    @Test
    public void parseCommand_cache() throws Exception {
        assertTrue(parser.parseCommand("accesscache -qqUP") instanceof AccessCacheCommand);
    }

    @Test
    public void parseCommand_viewTaskList() throws Exception {
        assertTrue(parser.parseCommand("cat -A") instanceof ViewTaskListCommand);
    }
}
