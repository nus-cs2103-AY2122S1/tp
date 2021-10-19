package seedu.fast.logic.parser;

import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_LENGTH;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.fast.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.fast.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.fast.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.fast.commons.core.index.Index;
import seedu.fast.logic.commands.RemarkCommand;
import seedu.fast.model.person.Remark;

public class RemarkCommandParserTest {
    private RemarkCommandParser parser = new RemarkCommandParser();
    private final String nonEmptyRemark = "Some remark.";
    private final String oneHundredCharRemark =
            "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean ma";

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

    @Test
    public void parse_invalidRemarkLength_failure() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_REMARK + oneHundredCharRemark;
        String expectedMessage = String.format(MESSAGE_INVALID_LENGTH, RemarkCommand.MESSAGE_USAGE);

        // more than 100 characters
        assertParseFailure(parser, userInput, expectedMessage);

    }
}
