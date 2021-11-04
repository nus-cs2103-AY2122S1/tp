package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AccessCacheCommand;
import seedu.address.logic.commands.ListCommand;

public class AddressBookInternalParserTest {

    private final AddressBookInternalParser parser = new AddressBookInternalParser();

    @Test
    public void parseCommand_list() throws Exception {
        Strin 
        assertTrue(parser.parseCommand(AccessCacheCommand.COMMAND_WORD) instanceof AccessCacheCommand);
        assertTrue(parser.parseCommand(AccessCacheCommand.COMMAND_WORD + " -qqUP") instanceof AccessCacheCommand);
    }
}
