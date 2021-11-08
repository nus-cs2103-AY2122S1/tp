package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindClassCommand;
import seedu.address.model.tutorialclass.ClassCodeContainsKeywordsPredicate;


public class FindClassCommandParserTest {

    private FindClassCommandParser parser = new FindClassCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindClassCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindClassCommand() {
        // no leading and trailing whitespaces
        FindClassCommand expectedFindClassCommand =
                new FindClassCommand(new ClassCodeContainsKeywordsPredicate(Arrays.asList("G01", "G02")));
        assertParseSuccess(parser, "G01 G02", expectedFindClassCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n G01 \n \t G02  \t", expectedFindClassCommand);
    }

}
