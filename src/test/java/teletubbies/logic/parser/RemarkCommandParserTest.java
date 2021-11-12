package teletubbies.logic.parser;

import static teletubbies.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static teletubbies.logic.parser.CliSyntax.PREFIX_REMARK;
import static teletubbies.logic.parser.CommandParserTestUtil.assertParseFailure;
import static teletubbies.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static teletubbies.logic.parser.RemarkCommandParser.MESSAGE_EXCEED_REMARK_CHARACTER_LIMIT;
import static teletubbies.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import teletubbies.commons.core.index.Index;
import teletubbies.logic.commands.RemarkCommand;
import teletubbies.model.person.Remark;

public class RemarkCommandParserTest {

    private RemarkCommandParser parser = new RemarkCommandParser();
    private final String nonEmptyRemark = "Some remark.";
    private final String longRemark = "This remark is exactly 41 characters long";

    @Test
    public void parse_invalidRemarkLength_failure() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_REMARK + longRemark;
        String expectedMessage = String.format(MESSAGE_EXCEED_REMARK_CHARACTER_LIMIT, RemarkCommand.MESSAGE_USAGE);

        assertParseFailure(parser, userInput, expectedMessage);
    }

    // @@author: j-lum
    // Reused from
    // https://github.com/se-edu/addressbook-level3/compare/tutorial-add-remark
    @Test
    public void parse_indexSpecified_success() {
        // have remark
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_REMARK + nonEmptyRemark;
        RemarkCommand expectedCommand = new RemarkCommand(INDEX_FIRST_PERSON, new Remark(nonEmptyRemark));
        assertParseSuccess(parser, userInput, expectedCommand);

        // no remark
        userInput = targetIndex.getOneBased() + " " + PREFIX_REMARK;
        expectedCommand = new RemarkCommand(INDEX_FIRST_PERSON, new Remark(""));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, RemarkCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, RemarkCommand.COMMAND_WORD + " " + nonEmptyRemark, expectedMessage);
    }
}
