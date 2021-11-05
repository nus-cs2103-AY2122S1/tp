package seedu.address.logic.parser;

import static seedu.address.logic.commands.FindCommand.MESSAGE_INVALID_COMMAND_FORMAT_EMPTY_PREDICATE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicate.MultiplePredicates;
import seedu.address.model.person.predicate.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicate.NationalityContainsKeywordsPredicate;
import seedu.address.model.person.predicate.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.predicate.TutorialGroupContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT_EMPTY_PREDICATE, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        MultiplePredicates predicate = new MultiplePredicates(new ArrayList<Predicate<Person>>(List.of(
                new NameContainsKeywordsPredicate(List.of("Alice")),
                new PhoneContainsKeywordsPredicate(List.of("91234456")),
                new NationalityContainsKeywordsPredicate(List.of("MY")),
                new TutorialGroupContainsKeywordsPredicate(List.of("19"))
        )));

        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(predicate);
        assertParseSuccess(parser, " n/Alice p/91234456 nat/MY tg/19", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser,
                " \n n/Alice \n \t p/91234456 \n \t nat/MY \n \t tg/19 \t", expectedFindCommand);
    }

}
