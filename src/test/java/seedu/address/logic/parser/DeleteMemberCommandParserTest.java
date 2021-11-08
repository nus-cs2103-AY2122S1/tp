package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEMBER;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteMemberCommand;
import seedu.address.model.member.Id;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside the DeleteMemberCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteMemberCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteMemberCommandParserTest {

    private DeleteMemberCommandParser parser = new DeleteMemberCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteMemberCommand() {
        assertParseSuccess(parser, " " + PREFIX_MEMBER + " "
                + PREFIX_INDEX + " 1", new DeleteMemberCommand(INDEX_FIRST_MEMBER));
        Id id = new Id("00001");
        assertParseSuccess(parser, " " + PREFIX_MEMBER + " "
                + PREFIX_ID + " " + id.value, new DeleteMemberCommand(id));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteMemberCommand.MESSAGE_USAGE));
    }
}
