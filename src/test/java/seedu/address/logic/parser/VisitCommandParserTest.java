package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VISIT_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FREQUENCY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OCCURRENCE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_OCCURRENCE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.VisitCommand;

public class VisitCommandParserTest {
    private VisitCommandParser parser = new VisitCommandParser();
    private final String testVisit = "2020-11-11";

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, VisitCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, "", expectedMessage);

        // no index
        assertParseFailure(parser, " " + PREFIX_DATE + testVisit, expectedMessage);

    }

    @Test
    public void parse_invalidOptionalField_failure() {
        String invalidRecurringVisit = String.format(
                "%s %s%s %s%s %s%s", "1", PREFIX_DATE, VALID_VISIT_AMY, PREFIX_FREQUENCY, "", PREFIX_OCCURRENCE, "a");
        String missingFrequencyFlag = String.format("%s %s%s", "1", PREFIX_OCCURRENCE, "5");
        String missingOccurrenceFlag = String.format("%s %s%s", "1", PREFIX_FREQUENCY, "weekly");
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, VisitCommand.MESSAGE_USAGE);

        // empty frequency for multiple occurrence
        assertParseFailure(parser, invalidRecurringVisit, MESSAGE_INVALID_OCCURRENCE);

        // missing one of optional flags
        assertParseFailure(parser, missingFrequencyFlag, expectedMessage);
        assertParseFailure(parser, missingOccurrenceFlag, expectedMessage);
    }


}
