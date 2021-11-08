package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.AddressContainsKeywordsPredicate;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.TagsContainKeywordsPredicate;
import seedu.address.model.person.TasksContainKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand idealFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(List.of("Alice")));
        assertParseSuccess(parser, "-n Alice", idealFindCommand);

        // leading and trailing whitespaces
        FindCommand longFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(List.of("Alice")));
        assertParseSuccess(parser, "   -n Alice   ", longFindCommand);

        // using different flags and different predicates
        FindCommand expectedPhoneFindCommand =
                new FindCommand(new PhoneContainsKeywordsPredicate(List.of("88")));
        FindCommand expectedEmailFindCommand =
                new FindCommand(new EmailContainsKeywordsPredicate(List.of("alice")));
        FindCommand expectedAddressFindCommand =
                new FindCommand(new AddressContainsKeywordsPredicate(List.of("10th")));
        FindCommand expectedTagFindCommand =
                new FindCommand(new TagsContainKeywordsPredicate(List.of("owes")));
        FindCommand expectedTasksFindCommand =
                new FindCommand(new TasksContainKeywordsPredicate(List.of("study")));
        assertParseSuccess(parser, "-p 88", expectedPhoneFindCommand);
        assertParseSuccess(parser, "-e alice", expectedEmailFindCommand);
        assertParseSuccess(parser, "-a 10th", expectedAddressFindCommand);
        assertParseSuccess(parser, "-l owes", expectedTagFindCommand);
        assertParseSuccess(parser, "-tn study", expectedTasksFindCommand);
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(parser, "-t ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "-sadasd ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "-sadasd sadsad",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }
}
