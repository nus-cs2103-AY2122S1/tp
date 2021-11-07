package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.BulkTagCommand;
import seedu.address.model.tag.Tag;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class BulkTagCommandParserTest {

    private BulkTagCommandParser parser = new BulkTagCommandParser();

    @Test
    public void parse_validArgs_returnsBulkTagCommand() {
        HashSet<Tag> tagSet = new HashSet<>();
        tagSet.add(new Tag("friends"));
        BulkTagCommand bulkTagCommand = new BulkTagCommand(tagSet);
        assertParseSuccess(parser, " t/friends", bulkTagCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "t/siddharth srivastava", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                BulkTagCommand.MESSAGE_USAGE));
    }
}
