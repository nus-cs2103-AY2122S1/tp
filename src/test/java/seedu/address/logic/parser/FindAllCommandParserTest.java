package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindAllCommand;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.CombinedPredicate;
import seedu.address.model.person.predicates.EmailContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;

public class FindAllCommandParserTest {

    private FindAllCommandParser parser = new FindAllCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAllCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_oneInvalidField_throwsParseException() {
        assertParseFailure(parser, "  nn/test",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAllCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyField_throwsParseException() {
        assertParseFailure(parser, " g/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAllCommandParser.EMPTY_FIELD_MESSAGE + "g/"));
    }

    @Test
    public void parse_oneValidField_returnsFindAllCommand() {
        // no leading and trailing whitespaces
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();
        predicates.add(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"), true));
        CombinedPredicate predicate = new CombinedPredicate(predicates, true);

        FindAllCommand expectedFindAllCommand =
                new FindAllCommand(predicate);
        assertParseSuccess(parser, " n/Alice Bob", expectedFindAllCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/\n Alice \n \t Bob  \t", expectedFindAllCommand);
    }

    @Test
    public void parse_multipleValidFields_returnsFindCommand() {
        // no leading and trailing whitespaces
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();

        predicates.add(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"), true));
        predicates.add(new EmailContainsKeywordsPredicate(Arrays.asList("gmail", "yahoo"), true));

        CombinedPredicate predicate = new CombinedPredicate(predicates, true);

        FindAllCommand expectedFindAllCommand = new FindAllCommand(predicate);

        assertParseSuccess(parser, " n/Alice Bob e/gmail yahoo", expectedFindAllCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/\n Alice \n \t Bob  \t e/\n gmail \t yahoo", expectedFindAllCommand);
    }
}
