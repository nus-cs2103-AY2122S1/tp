package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Money;
import seedu.address.logic.commands.RevenueCommand;
import seedu.address.model.person.Revenue;

public class RevenueCommandParserTest {

    private RevenueCommandParser parser = new RevenueCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RevenueCommand.COMMAND_WORD));
    }

    @Test
    public void parse_validArgs_returnsRevenueCommand() {
        // no leading and trailing whitespaces
        RevenueCommand expectedRevenueCommand =
                new RevenueCommand(INDEX_FIRST_PERSON, new Revenue(new Money(100.21f)));
        assertParseSuccess(parser, "1 r/100.21", expectedRevenueCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // no leading and trailing whitespaces
        assertParseFailure(parser, "1 r10", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RevenueCommand.COMMAND_WORD));
    }
}
