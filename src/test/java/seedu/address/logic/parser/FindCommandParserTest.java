package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertFindCommandParseSuccess;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.Price;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PriceEqualsNumberPredicate;
import seedu.address.model.person.predicates.PriceGreaterThanNumberPredicate;
import seedu.address.model.person.predicates.TagContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyTag_throwsParseException() {
        assertParseFailure(parser, " t/", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyPrice_throwsParseException() {
        assertParseFailure(parser, " pr/", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.PRICE_USAGE));
    }

    @Test
    public void parse_invalidPriceOperator_throwsParseException() {
        assertParseFailure(parser, " pr/1.00", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.PRICE_USAGE));
    }

    @Test
    public void parse_multiplePricePrefix_throwsParseException() {
        assertParseFailure(parser, " pr/=1.00 pr/=0.00",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.PRICE_USAGE));
    }

    @Test
    public void parse_validNameArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")),
                        "name is in [Alice, Bob] ");
        assertFindCommandParseSuccess(parser, "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertFindCommandParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_validTagArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new TagContainsKeywordsPredicate(Arrays.asList("friends")),
                        "tag is in [friends] ");
        assertFindCommandParseSuccess(parser, " t/friends", expectedFindCommand);
    }

    @Test
    public void parse_validPriceEqualArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new PriceEqualsNumberPredicate(new Price("0.00")), "price is =0.00");
        assertFindCommandParseSuccess(parser, " pr/=0.00", expectedFindCommand);
    }

    @Test
    public void parse_validPriceGreaterThanArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new PriceGreaterThanNumberPredicate(new Price("0.00")), "price is >0.00");
        assertFindCommandParseSuccess(parser, " pr/>0.00", expectedFindCommand);
    }

    @Test
    public void parse_validPriceLessThanArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new PriceEqualsNumberPredicate(new Price("0.00")), "price is <0.00");
        assertFindCommandParseSuccess(parser, " pr/<0.00", expectedFindCommand);
    }

    @Test
    public void parse_validPriceLessThanEqualArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new PriceEqualsNumberPredicate(new Price("0.00")), "price is <=0.00");
        assertFindCommandParseSuccess(parser, " pr/<=0.00", expectedFindCommand);
    }

    @Test
    public void parse_validPriceGreaterThanEqualArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new PriceEqualsNumberPredicate(new Price("0.00")), "price is >=0.00");
        assertFindCommandParseSuccess(parser, " pr/>=0.00", expectedFindCommand);
    }

}
