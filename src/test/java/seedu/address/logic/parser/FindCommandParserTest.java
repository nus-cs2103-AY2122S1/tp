package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ID_LENGTH_AND_SIGN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_100PLUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_H20;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.item.IdContainsNumberPredicate;
import seedu.address.model.item.NameContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validIdArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new IdContainsNumberPredicate(Arrays.asList("140262")));
        assertParseSuccess(parser, "140262", expectedFindCommand);
    }

    @Test
    public void parse_negativeIdArgs_throwsParseException() {
        assertParseFailure(parser, "-123123", String.format(
                MESSAGE_INVALID_ID_LENGTH_AND_SIGN, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_notSixDigitsIdArgs_throwsParseException() {
        assertParseFailure(parser, "123", String.format(MESSAGE_INVALID_ID_LENGTH_AND_SIGN, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validNameArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(VALID_NAME_BAGEL, VALID_NAME_DONUT)));
        assertParseSuccess(parser, "Bagel Donut", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "Bagel   Donut", expectedFindCommand);
    }

    @Test
    public void parse_validNameWithNumbersArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(VALID_NAME_100PLUS, VALID_NAME_H20)));
        assertParseSuccess(parser, "100Plus H20", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "100Plus     H20", expectedFindCommand);
    }

}
