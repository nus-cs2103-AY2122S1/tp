package socialite.logic.parser;

import static socialite.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static socialite.commons.core.Messages.MESSAGE_REMARK_EXCEED_LIMIT;
import static socialite.logic.parser.CliSyntax.PREFIX_REMARK;
import static socialite.logic.parser.CommandParserTestUtil.assertParseFailure;
import static socialite.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static socialite.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import socialite.commons.core.index.Index;
import socialite.logic.commands.RemarkCommand;
import socialite.model.person.Remark;

public class RemarkCommandParserTest {
    private final RemarkCommandParser parser = new RemarkCommandParser();
    private final String nonEmptyRemark = "Some remark.";

    @Test
    public void parse_indexSpecified_success() {
        // have remark
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_REMARK + nonEmptyRemark;
        RemarkCommand expectedCommand = new RemarkCommand(INDEX_FIRST_PERSON, new Remark(nonEmptyRemark));
        assertParseSuccess(parser, userInput, expectedCommand);

        // no remark
        userInput = targetIndex.getOneBased() + " " + PREFIX_REMARK;
        expectedCommand = new RemarkCommand(INDEX_FIRST_PERSON, new Remark(null));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_HELP_GUIDE);

        // no parameters
        assertParseFailure(parser, RemarkCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, RemarkCommand.COMMAND_WORD + " " + nonEmptyRemark, expectedMessage);
    }

    @Test
    public void parse_longCommand_throwsCommandException() {
        String longRemark = "1234567890123456789012345678901234567890123456789012345678901234567890"
                + "1234567890123456789012345678901234567890123456789012345678901234567890"
                + "1234567890123456789012345678901234567890123456789012345678901234567890";
        assertParseFailure(parser,
                INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_REMARK + longRemark,
                MESSAGE_REMARK_EXCEED_LIMIT);
    }
}
