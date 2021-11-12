package seedu.academydirectory.logic.parser;

import static seedu.academydirectory.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_ATTRIBUTE;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_ORDER;
import static seedu.academydirectory.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.academydirectory.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.academydirectory.logic.commands.SortCommand;

public class SortCommandParserTest {
    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);

        final String emptyAttribute = "  ";
        final String nonEmptyAttribute = "NAME ";
        final String emptyOrder = " ";
        final String nonEmptyOrder = "ASC";

        String userInputNoAttribute =
                PREFIX_ATTRIBUTE
                        + emptyAttribute
                        + PREFIX_ORDER
                        + nonEmptyOrder;
        String userInputNoOrder =
                PREFIX_ATTRIBUTE
                        + nonEmptyAttribute
                        + PREFIX_ORDER
                        + emptyOrder;
        String userInputNoAttributeNoOrder =
                PREFIX_ATTRIBUTE
                        + emptyAttribute
                        + PREFIX_ORDER
                        + emptyOrder;

        // no parameters
        assertParseFailure(parser, SortCommand.COMMAND_WORD, expectedMessage);

        // no attribute
        assertParseFailure(parser, userInputNoAttribute, expectedMessage);

        // no order
        assertParseFailure(parser, userInputNoOrder, expectedMessage);

        // no attribute and no order
        assertParseFailure(parser, userInputNoAttributeNoOrder, expectedMessage);

    }

    @Test
    public void parse_validArgs_returnsSortCommand() {
        final SortCommand expectedSortNameAscendingCommand =
                new SortCommand("NAME", true);
        final SortCommand expectedSortParticipationAscendingCommand =
                new SortCommand("PARTICIPATION", true);
        final SortCommand expectedSortAverageAscendingCommand =
                new SortCommand("AVERAGE", true);
        final SortCommand expectedSortRA1AscendingCommand =
                new SortCommand("RA1", true);

        final String userInputSortNameAscending =
                " " + PREFIX_ATTRIBUTE + "NAME " + PREFIX_ORDER + "ASC";
        final String userInputSortParticipationAscending =
                " " + PREFIX_ATTRIBUTE + "PARTICIPATION " + PREFIX_ORDER + "ASC";
        final String userInputSortAverageAscending =
                " " + PREFIX_ATTRIBUTE + "AVERAGE " + PREFIX_ORDER + "ASC";
        final String userInputSortRA1Ascending =
                " " + PREFIX_ATTRIBUTE + "RA1 " + PREFIX_ORDER + "ASC";

        assertParseSuccess(parser, userInputSortNameAscending, expectedSortNameAscendingCommand);
        assertParseSuccess(parser, userInputSortParticipationAscending, expectedSortParticipationAscendingCommand);
        assertParseSuccess(parser, userInputSortAverageAscending, expectedSortAverageAscendingCommand);
        assertParseSuccess(parser, userInputSortRA1Ascending, expectedSortRA1AscendingCommand);


    }
}
