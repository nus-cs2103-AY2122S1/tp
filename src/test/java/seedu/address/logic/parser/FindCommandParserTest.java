package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_GITHUB_FIELD_CANNOT_BE_EMPTY;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_NAME;
import static seedu.address.commons.core.Messages.MESSAGE_TAG_FIELD_CANNOT_BE_EMPTY;
import static seedu.address.commons.core.Messages.MESSAGE_TELEGRAM_FIELD_CANNOT_BE_EMPTY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.GithubContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.TagContainsKeywordsPredicate;
import seedu.address.model.person.TelegramHandleContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyArgGithub_throwsParseException() {
        assertParseFailure(parser, "g/ ",
                MESSAGE_GITHUB_FIELD_CANNOT_BE_EMPTY);
    }

    @Test
    public void parse_emptyArgTag_throwsParseException() {
        assertParseFailure(parser, "t/ ",
                MESSAGE_TAG_FIELD_CANNOT_BE_EMPTY);
    }

    @Test
    public void parse_emptyArgTelegram_throwsParseException() {
        assertParseFailure(parser, "te/ ",
                MESSAGE_TELEGRAM_FIELD_CANNOT_BE_EMPTY);
    }

    @Test
    public void parse_invalidArg1_throwsParseException() {
        assertParseFailure(parser, "@alex",
               MESSAGE_INVALID_NAME);
    }

    @Test
    public void parse_invalidArg2_throwsParseException() {
        assertParseFailure(parser, "tele/alex",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArg3_throwsParseException() {
        assertParseFailure(parser, "git/bern",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArg4_throwsParseException() {
        assertParseFailure(parser, "github/bern",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validGithubUsernameArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new GithubContainsKeywordsPredicate(Arrays
                        .asList("alex", "bern")));
        assertParseSuccess(parser, "g/alex bern", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "g/\n alex \n \t bern  \t", expectedFindCommand);
    }

    @Test
    public void parse_validNameArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays
                        .asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindCommand);
    }

    @Test
    public void parse_validTagArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new TagContainsKeywordsPredicate(Arrays
                        .asList("friends", "family")));
        assertParseSuccess(parser, "t/friends family", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "t/\n friends \n \t family  \t", expectedFindCommand);
    }

    @Test
    public void parse_validTelegramHandleArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new TelegramHandleContainsKeywordsPredicate(Arrays
                        .asList("alex_1", "bern")));
        assertParseSuccess(parser, "te/alex_1 bern", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "te/\n alex_1 \n \t bern  \t", expectedFindCommand);
    }

}
