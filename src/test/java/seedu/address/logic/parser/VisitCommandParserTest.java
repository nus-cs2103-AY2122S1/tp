package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.VisitCommand;

public class VisitCommandParserTest {
    private VisitCommandParser parser = new VisitCommandParser();
    private final String testVisit = "2020-11-11";

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, VisitCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, VisitCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, VisitCommand.COMMAND_WORD + " " + PREFIX_DATE + testVisit, expectedMessage);
    }
}
