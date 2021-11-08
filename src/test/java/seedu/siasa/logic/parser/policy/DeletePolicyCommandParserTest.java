package seedu.siasa.logic.parser.policy;

import static seedu.siasa.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.siasa.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.siasa.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.siasa.testutil.TypicalIndexes.INDEX_FIRST_POLICY;

import org.junit.jupiter.api.Test;

import seedu.siasa.logic.commands.policy.DeletePolicyCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeletePolicyCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteContactCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeletePolicyCommandParserTest {

    private DeletePolicyCommandParser parser = new DeletePolicyCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeletePolicyCommand(INDEX_FIRST_POLICY));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePolicyCommand.MESSAGE_USAGE));
    }
}
