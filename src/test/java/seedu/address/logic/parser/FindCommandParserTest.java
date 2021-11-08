package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.member.EmailContainsKeywordsPredicate;
import seedu.address.model.member.IdContainsKeywordsPredicate;
import seedu.address.model.member.NameContainsKeywordsPredicate;
import seedu.address.model.member.PhoneContainsKeywordsPredicate;
import seedu.address.model.member.RegistrationDateContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code FindCommandParser}.
 */
public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validIdArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new IdContainsKeywordsPredicate(Arrays.asList("00001", "00002")));
        assertParseSuccess(parser, " " + PREFIX_MEMBER + " " + PREFIX_ID + " 00001 00002",
                expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_MEMBER + " " + PREFIX_ID + " \n 00001 \n \t 00002  \t",
                expectedFindCommand);
    }

    @Test
    public void parse_validNameArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, " " + PREFIX_MEMBER + " " + PREFIX_NAME + " Alice Bob",
                expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_MEMBER + " " + PREFIX_NAME + " \n Alice \n \t Bob  \t",
                expectedFindCommand);
    }

    @Test
    public void parse_validPhoneArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new PhoneContainsKeywordsPredicate(Arrays.asList("87328807", "99272758")));
        assertParseSuccess(parser, " " + PREFIX_MEMBER + " " + PREFIX_PHONE + " 87328807 99272758",
                expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_MEMBER + " " + PREFIX_PHONE
                + " \n 87328807 \n \t 99272758  \t", expectedFindCommand);
    }

    @Test
    public void parse_validEmailArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new EmailContainsKeywordsPredicate(Arrays.asList("ben@gmail.com", "sunny@gmail.com")));
        assertParseSuccess(parser, " " + PREFIX_MEMBER + " " + PREFIX_EMAIL + " ben@gmail.com sunny@gmail.com",
                expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_MEMBER + " " + PREFIX_EMAIL
                + " \n ben@gmail.com \n \t sunny@gmail.com  \t", expectedFindCommand);
    }

    @Test
    public void parse_validRegistrationDateArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand = new FindCommand(new RegistrationDateContainsKeywordsPredicate(
                Arrays.asList("2021-01-01", "2021-01-02")));
        assertParseSuccess(parser, " " + PREFIX_MEMBER + " " + PREFIX_DATE
                + " 2021-01-01 2021-01-02", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_MEMBER + " " + PREFIX_DATE
                + " \n 2021-01-01 \n \t 2021-01-02  \t", expectedFindCommand);
    }
}
