package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VISIT_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FREQUENCY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OCCURRENCE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_OCCURRENCE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.VisitCommand;
import seedu.address.model.person.Frequency;
import seedu.address.model.person.Occurrence;
import seedu.address.model.person.Visit;
import seedu.address.testutil.DateTimeUtil;

public class VisitCommandParserTest {
    private static final String VALID_VISIT_DATETIME = DateTimeUtil.getValidVisitString();

    private VisitCommandParser parser = new VisitCommandParser();

    @Test
    public void parser_compulsoryFieldsPresent_success() {
        Optional<Visit> visit = Optional.of(new Visit(VALID_VISIT_DATETIME));
        Optional<Frequency> frequency = Optional.of(Frequency.EMPTY);
        Optional<Occurrence> occurrence = Optional.of(new Occurrence(1));
        VisitCommand command = new VisitCommand(INDEX_FIRST_PERSON, visit, frequency, occurrence);
        assertParseSuccess(parser, String.format("%s %s%s", "1", PREFIX_DATE, VALID_VISIT_DATETIME), command);
    }

    @Test
    public void parser_optionalFieldsPresent_success() {
        Optional<Visit> visit = Optional.of(new Visit(VALID_VISIT_DATETIME));
        Optional<Frequency> frequency = Optional.of(Frequency.WEEKLY);
        Optional<Occurrence> occurrence = Optional.of(new Occurrence(2));
        VisitCommand command = new VisitCommand(INDEX_FIRST_PERSON, visit, frequency, occurrence);
        String userInput = String.format("%s %s%s %s%s %s%s", "1", PREFIX_DATE, VALID_VISIT_DATETIME, PREFIX_FREQUENCY, "Weekly",
                PREFIX_OCCURRENCE, 2);
        assertParseSuccess(parser, userInput, command);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, VisitCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, "", expectedMessage);

        // no index
        assertParseFailure(parser, " " + PREFIX_DATE + VALID_VISIT_DATETIME, expectedMessage);

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
