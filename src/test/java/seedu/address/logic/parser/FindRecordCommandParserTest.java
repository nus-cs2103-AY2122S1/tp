package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindRecordCommand;
import seedu.address.model.residency.exceptions.ResidencyContainsKeywordsPredicate;

public class FindRecordCommandParserTest {
    private FindRecordCommandParser parser = new FindRecordCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindRecordCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindRecordCommand expectedFindCommand =
                new FindRecordCommand(new ResidencyContainsKeywordsPredicate(Arrays.asList("001", "Alice")));
        assertParseSuccess(parser, "001 Alice", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n 001 \n \t Alice \t", expectedFindCommand);
    }
}
