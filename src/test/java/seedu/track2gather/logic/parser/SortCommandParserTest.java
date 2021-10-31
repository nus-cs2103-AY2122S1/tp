package seedu.track2gather.logic.parser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.track2gather.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.track2gather.logic.commands.SortCommand.SUPPORTED_PREFIXES;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_CASE_NUMBER;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_SHN_PERIOD_END;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_SHN_PERIOD_START;
import static seedu.track2gather.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.track2gather.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.track2gather.logic.commands.SortCommand;
import seedu.track2gather.logic.commands.SortCommand.Direction;

public class SortCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid preamble
        assertParseFailure(parser, "abc " + PREFIX_NAME, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "abc " + PREFIX_CASE_NUMBER + " " + PREFIX_NAME, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_missingPrefixes_failure() {
        // no prefix specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_nonBlankValues_failure() {
        // some value was specified
        assertParseFailure(parser, PREFIX_NAME + "abc", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, PREFIX_NAME + " " + PREFIX_CASE_NUMBER + "2", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPrefix_failure() {
        // invalid prefix
        Prefix invalidPrefix = new Prefix("abc123/");
        assertFalse(SUPPORTED_PREFIXES.contains(invalidPrefix));
        assertParseFailure(parser, invalidPrefix.toString(), MESSAGE_INVALID_FORMAT);

        // invalid prefix with other valid prefixes specified
        assertParseFailure(parser, invalidPrefix + " " + PREFIX_NAME, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, PREFIX_NAME + " " + invalidPrefix, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, PREFIX_NAME + " " + invalidPrefix + " " + PREFIX_CASE_NUMBER,
                MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_onePrefixWithoutDirection_success() {
        // name
        String userInput = " " + PREFIX_NAME;
        SortCommand expectedCommand = new SortCommand(List.of(PREFIX_NAME), List.of(Direction.ASCENDING));
        assertParseSuccess(parser, userInput, expectedCommand);

        // case number
        userInput = " " + PREFIX_CASE_NUMBER;
        expectedCommand = new SortCommand(List.of(PREFIX_CASE_NUMBER), List.of(Direction.ASCENDING));
        assertParseSuccess(parser, userInput, expectedCommand);

        // shn period start
        userInput = " " + PREFIX_SHN_PERIOD_START;
        expectedCommand = new SortCommand(List.of(PREFIX_SHN_PERIOD_START), List.of(Direction.ASCENDING));
        assertParseSuccess(parser, userInput, expectedCommand);

        // shn period end
        userInput = " " + PREFIX_SHN_PERIOD_END;
        expectedCommand = new SortCommand(List.of(PREFIX_SHN_PERIOD_END), List.of(Direction.ASCENDING));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_onePrefixWithDirection_success() {
        // name ascending
        String userInput = " " + PREFIX_NAME + Direction.ASCENDING;
        SortCommand expectedCommand = new SortCommand(List.of(PREFIX_NAME), List.of(Direction.ASCENDING));
        assertParseSuccess(parser, userInput, expectedCommand);

        // name descending
        userInput = " " + PREFIX_NAME + Direction.DESCENDING;
        expectedCommand = new SortCommand(List.of(PREFIX_NAME), List.of(Direction.DESCENDING));
        assertParseSuccess(parser, userInput, expectedCommand);

        // case number ascending
        userInput = " " + PREFIX_CASE_NUMBER + Direction.ASCENDING;
        expectedCommand = new SortCommand(List.of(PREFIX_CASE_NUMBER), List.of(Direction.ASCENDING));
        assertParseSuccess(parser, userInput, expectedCommand);

        // case number descending
        userInput = " " + PREFIX_CASE_NUMBER + Direction.DESCENDING;
        expectedCommand = new SortCommand(List.of(PREFIX_CASE_NUMBER), List.of(Direction.DESCENDING));
        assertParseSuccess(parser, userInput, expectedCommand);

        // shn period start ascending
        userInput = " " + PREFIX_SHN_PERIOD_START + Direction.ASCENDING;
        expectedCommand = new SortCommand(List.of(PREFIX_SHN_PERIOD_START), List.of(Direction.ASCENDING));
        assertParseSuccess(parser, userInput, expectedCommand);

        // shn period start descending
        userInput = " " + PREFIX_SHN_PERIOD_START + Direction.DESCENDING;
        expectedCommand = new SortCommand(List.of(PREFIX_SHN_PERIOD_START), List.of(Direction.DESCENDING));
        assertParseSuccess(parser, userInput, expectedCommand);

        // shn period end ascending
        userInput = " " + PREFIX_SHN_PERIOD_END + Direction.ASCENDING;
        expectedCommand = new SortCommand(List.of(PREFIX_SHN_PERIOD_END), List.of(Direction.ASCENDING));
        assertParseSuccess(parser, userInput, expectedCommand);

        // shn period end descending
        userInput = " " + PREFIX_SHN_PERIOD_END + Direction.DESCENDING;
        expectedCommand = new SortCommand(List.of(PREFIX_SHN_PERIOD_END), List.of(Direction.DESCENDING));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multiplePrefixesWithoutDirection_success() {
        List<Direction> directions = List.of(Direction.ASCENDING, Direction.ASCENDING);

        // name then case number
        List<Prefix> prefixes = List.of(PREFIX_NAME, PREFIX_CASE_NUMBER);
        String userInput = " " + PREFIX_NAME + " " + PREFIX_CASE_NUMBER;
        SortCommand expectedCommand = new SortCommand(prefixes, directions);
        assertParseSuccess(parser, userInput, expectedCommand);

        // case number then name
        prefixes = List.of(PREFIX_CASE_NUMBER, PREFIX_NAME);
        userInput = " " + PREFIX_CASE_NUMBER + " " + PREFIX_NAME;
        expectedCommand = new SortCommand(prefixes, directions);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multiplePrefixesWithDirection_success() {
        // name ascending then case number ascending
        List<Prefix> prefixes = List.of(PREFIX_NAME, PREFIX_CASE_NUMBER);
        List<Direction> directions = List.of(Direction.ASCENDING, Direction.ASCENDING);
        String userInput = " " + PREFIX_NAME + Direction.ASCENDING + " " + PREFIX_CASE_NUMBER + Direction.ASCENDING;
        SortCommand expectedCommand = new SortCommand(prefixes, directions);
        assertParseSuccess(parser, userInput, expectedCommand);

        // name ascending then case number descending
        prefixes = List.of(PREFIX_NAME, PREFIX_CASE_NUMBER);
        directions = List.of(Direction.ASCENDING, Direction.DESCENDING);
        userInput = " " + PREFIX_NAME + Direction.ASCENDING + " " + PREFIX_CASE_NUMBER + Direction.DESCENDING;
        expectedCommand = new SortCommand(prefixes, directions);
        assertParseSuccess(parser, userInput, expectedCommand);

        // name descending then case number descending
        prefixes = List.of(PREFIX_NAME, PREFIX_CASE_NUMBER);
        directions = List.of(Direction.DESCENDING, Direction.DESCENDING);
        userInput = " " + PREFIX_NAME + Direction.DESCENDING + " " + PREFIX_CASE_NUMBER + Direction.DESCENDING;
        expectedCommand = new SortCommand(prefixes, directions);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multiplePrefixesMixedWithDirection_success() {
        // name then case number ascending
        List<Prefix> prefixes = List.of(PREFIX_NAME, PREFIX_CASE_NUMBER);
        List<Direction> directions = List.of(Direction.ASCENDING, Direction.ASCENDING);
        String userInput = " " + PREFIX_NAME + " " + PREFIX_CASE_NUMBER + Direction.ASCENDING;
        SortCommand expectedCommand = new SortCommand(prefixes, directions);
        assertParseSuccess(parser, userInput, expectedCommand);

        // name ascending then case number
        prefixes = List.of(PREFIX_NAME, PREFIX_CASE_NUMBER);
        directions = List.of(Direction.ASCENDING, Direction.ASCENDING);
        userInput = " " + PREFIX_NAME + Direction.ASCENDING + " " + PREFIX_CASE_NUMBER;
        expectedCommand = new SortCommand(prefixes, directions);
        assertParseSuccess(parser, userInput, expectedCommand);

        // name then case number descending
        prefixes = List.of(PREFIX_NAME, PREFIX_CASE_NUMBER);
        directions = List.of(Direction.ASCENDING, Direction.DESCENDING);
        userInput = " " + PREFIX_NAME + " " + PREFIX_CASE_NUMBER + Direction.DESCENDING;
        expectedCommand = new SortCommand(prefixes, directions);
        assertParseSuccess(parser, userInput, expectedCommand);

        // name descending then case number
        prefixes = List.of(PREFIX_NAME, PREFIX_CASE_NUMBER);
        directions = List.of(Direction.DESCENDING, Direction.ASCENDING);
        userInput = " " + PREFIX_NAME + Direction.DESCENDING + " " + PREFIX_CASE_NUMBER;
        expectedCommand = new SortCommand(prefixes, directions);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedPrefixes_acceptsLast() {
        // single prefix repeated
        List<Prefix> prefixes = List.of(PREFIX_NAME);
        List<Direction> directions = List.of(Direction.ASCENDING);
        String userInput = " " + PREFIX_NAME + " " + PREFIX_NAME;
        SortCommand expectedCommand = new SortCommand(prefixes, directions);
        assertParseSuccess(parser, userInput, expectedCommand);

        // same prefix repeated, accepting last occurrence
        prefixes = List.of(PREFIX_NAME);
        directions = List.of(Direction.DESCENDING);
        userInput = " " + PREFIX_NAME + " " + PREFIX_NAME + Direction.DESCENDING;
        expectedCommand = new SortCommand(prefixes, directions);
        assertParseSuccess(parser, userInput, expectedCommand);

        prefixes = List.of(PREFIX_NAME);
        directions = List.of(Direction.ASCENDING);
        userInput = " " + PREFIX_NAME + Direction.DESCENDING + " " + PREFIX_NAME + Direction.ASCENDING;
        expectedCommand = new SortCommand(prefixes, directions);
        assertParseSuccess(parser, userInput, expectedCommand);

        prefixes = List.of(PREFIX_NAME);
        directions = List.of(Direction.ASCENDING);
        userInput = " " + PREFIX_NAME + Direction.DESCENDING + " " + PREFIX_NAME;
        expectedCommand = new SortCommand(prefixes, directions);
        assertParseSuccess(parser, userInput, expectedCommand);

        // single prefix repeated with rest of prefixes unique
        prefixes = List.of(PREFIX_NAME, PREFIX_CASE_NUMBER);
        directions = List.of(Direction.ASCENDING, Direction.ASCENDING);
        userInput = " " + PREFIX_CASE_NUMBER + Direction.DESCENDING + " " + PREFIX_NAME + " " + PREFIX_CASE_NUMBER;
        expectedCommand = new SortCommand(prefixes, directions);
        assertParseSuccess(parser, userInput, expectedCommand);

        prefixes = List.of(PREFIX_CASE_NUMBER, PREFIX_NAME);
        directions = List.of(Direction.ASCENDING, Direction.ASCENDING);
        userInput = " " + PREFIX_NAME + Direction.DESCENDING + " " + PREFIX_CASE_NUMBER + " " + PREFIX_NAME;
        expectedCommand = new SortCommand(prefixes, directions);
        assertParseSuccess(parser, userInput, expectedCommand);

        // multiple prefixes repeated
        prefixes = List.of(PREFIX_SHN_PERIOD_END, PREFIX_CASE_NUMBER, PREFIX_NAME);
        directions = List.of(Direction.ASCENDING, Direction.ASCENDING, Direction.DESCENDING);
        userInput = " " + PREFIX_SHN_PERIOD_END
                + " " + PREFIX_CASE_NUMBER + Direction.DESCENDING
                + " " + PREFIX_NAME + Direction.ASCENDING
                + " " + PREFIX_NAME
                + " " + PREFIX_CASE_NUMBER
                + " " + PREFIX_CASE_NUMBER + Direction.ASCENDING
                + " " + PREFIX_NAME + Direction.DESCENDING;
        expectedCommand = new SortCommand(prefixes, directions);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
