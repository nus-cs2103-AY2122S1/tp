package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_INTERNAL_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.AccessCacheCommand;

public class AccessCacheCommandParserTest {
    private AccessCacheCommandParser accessCacheCommandParser = new AccessCacheCommandParser();

    @Test
    public void parse_compulsoryFieldsPresent_success() {
        // Correct keys pressed
        assertParseSuccess(accessCacheCommandParser, "accesscache -qqUP", new AccessCacheCommand("UP"));
        assertParseSuccess(accessCacheCommandParser, "accesscache -qqDOWN",
                new AccessCacheCommand("DOWN"));
    }

    @Test
    public void parse_compulsoryFieldsMissing_failure() {
        // Wrong key pressed
        assertParseFailure(accessCacheCommandParser, "accesscache LEFT",
                MESSAGE_INVALID_INTERNAL_COMMAND_FORMAT);
    }
}
