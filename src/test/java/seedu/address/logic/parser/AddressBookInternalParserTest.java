package seedu.address.logic.parser;

<<<<<<< HEAD
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_INTERNAL_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_INTERNAL_COMMAND;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.AccessCacheCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AddressBookInternalParserTest {

    private AddressBookInternalParser parser = new AddressBookInternalParser();

    @Test
    public void parse_validCommandWord_success() throws Exception{
        assertTrue(parser.parseCommand(AccessCacheCommand.COMMAND_WORD + " -qqUP")
                instanceof AccessCacheCommand);
    }

    @Test
    public void parse_invalidFormat_failure() throws Exception {
        assertThrows(ParseException.class, () -> parser.parseCommand("asdasdasd"),
                MESSAGE_INVALID_INTERNAL_COMMAND_FORMAT);
    }

    @Test
    public void parse_invalidInternalCommand_failure() {
        assertThrows(ParseException.class, () -> parser.parseCommand("accessMemory -qqLEFT"),
                MESSAGE_UNKNOWN_INTERNAL_COMMAND);
=======
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
>>>>>>> origin/master
    }
}
