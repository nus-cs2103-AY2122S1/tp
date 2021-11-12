package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindAnyCommand;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.CombinedPredicate;
import seedu.address.model.person.predicates.EmailContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;

public class FindAnyCommandParserTest {

    private FindAnyCommandParser parser = new FindAnyCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAnyCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_oneInvalidField_throwsParseException() {
        assertParseFailure(parser, "  nn/test",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAnyCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyField_throwsParseException() {
        assertParseFailure(parser, " g/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAnyCommandParser.EMPTY_FIELD_MESSAGE + "g/"));
    }

    @Test
    public void parse_oneValidField_returnsFindCommand() {
        // no leading and trailing whitespaces
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        predicates.add(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"), false));
        CombinedPredicate predicate = new CombinedPredicate(predicates, false);

        FindAnyCommand expectedFindAnyCommand =
                new FindAnyCommand(predicate);
        assertParseSuccess(parser, " n/Alice Bob", expectedFindAnyCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/\n Alice \n \t Bob  \t", expectedFindAnyCommand);
    }

    @Test
    public void parse_multipleValidFields_returnsFindCommand() {
        // no leading and trailing whitespaces
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();

        predicates.add(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"), false));
        predicates.add(new EmailContainsKeywordsPredicate(Arrays.asList("gmail", "yahoo"), false));

        CombinedPredicate predicate = new CombinedPredicate(predicates, false);

        FindAnyCommand expectedFindAnyCommand = new FindAnyCommand(predicate);

        assertParseSuccess(parser, " n/Alice Bob e/gmail yahoo", expectedFindAnyCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/\n Alice \n \t Bob  \t e/\n gmail \t yahoo", expectedFindAnyCommand);
    }
}
