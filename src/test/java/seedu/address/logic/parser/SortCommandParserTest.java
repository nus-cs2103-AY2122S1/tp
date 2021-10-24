package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CASE_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOME_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEXT_OF_KIN_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEXT_OF_KIN_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEXT_OF_KIN_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUARANTINE_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SHN_PERIOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WORK_ADDRESS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;

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
        // unsupported sort by phone
        assertParseFailure(parser, PREFIX_PHONE.toString(), MESSAGE_INVALID_FORMAT);
        // unsupported sort by email
        assertParseFailure(parser, PREFIX_EMAIL.toString(), MESSAGE_INVALID_FORMAT);
        // unsupported sort by home address
        assertParseFailure(parser, PREFIX_HOME_ADDRESS.toString(), MESSAGE_INVALID_FORMAT);
        // unsupported sort by work address
        assertParseFailure(parser, PREFIX_WORK_ADDRESS.toString(), MESSAGE_INVALID_FORMAT);
        // unsupported sort by quarantine address
        assertParseFailure(parser, PREFIX_QUARANTINE_ADDRESS.toString(), MESSAGE_INVALID_FORMAT);
        // unsupported sort by SHN period
        assertParseFailure(parser, PREFIX_SHN_PERIOD.toString(), MESSAGE_INVALID_FORMAT);
        // unsupported sort by next of kin name
        assertParseFailure(parser, PREFIX_NEXT_OF_KIN_NAME.toString(), MESSAGE_INVALID_FORMAT);
        // unsupported sort by next of kin phone
        assertParseFailure(parser, PREFIX_NEXT_OF_KIN_PHONE.toString(), MESSAGE_INVALID_FORMAT);
        // unsupported sort by next of kin address
        assertParseFailure(parser, PREFIX_NEXT_OF_KIN_ADDRESS.toString(), MESSAGE_INVALID_FORMAT);

        // invalid prefix
        Prefix invalidPrefix = new Prefix("abc123/");
        assertParseFailure(parser, invalidPrefix.toString(), MESSAGE_INVALID_FORMAT);

        // invalid prefix with other valid prefixes specified
        assertParseFailure(parser, invalidPrefix + " " + PREFIX_NAME, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, PREFIX_NAME + " " + invalidPrefix, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, PREFIX_NAME + " " + invalidPrefix + " " + PREFIX_CASE_NUMBER,
                MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_onePrefixSpecified_success() {
        // name
        String userInput = PREFIX_NAME.toString();
        SortCommand expectedCommand = new SortCommand(List.of(PREFIX_NAME));
        assertParseSuccess(parser, userInput, expectedCommand);

        // case number
        userInput = PREFIX_CASE_NUMBER.toString();
        expectedCommand = new SortCommand(List.of(PREFIX_CASE_NUMBER));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multiplePrefixesSpecified_success() {
        // name then case number
        String userInput = PREFIX_NAME + " " + PREFIX_CASE_NUMBER;
        SortCommand expectedCommand = new SortCommand(List.of(PREFIX_NAME, PREFIX_CASE_NUMBER));
        assertParseSuccess(parser, userInput, expectedCommand);

        // case number then name
        userInput = PREFIX_CASE_NUMBER + " " + PREFIX_NAME;
        expectedCommand = new SortCommand(List.of(PREFIX_CASE_NUMBER, PREFIX_NAME));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedPrefixes_acceptsLast() {
        // same prefix repeated
        String userInput = PREFIX_NAME + " " + PREFIX_NAME;
        SortCommand expectedCommand = new SortCommand(List.of(PREFIX_NAME));
        assertParseSuccess(parser, userInput, expectedCommand);

        // single prefix repeated with rest of prefixes unique
        userInput = PREFIX_CASE_NUMBER + " " + PREFIX_NAME + " " + PREFIX_CASE_NUMBER;
        expectedCommand = new SortCommand(List.of(PREFIX_NAME, PREFIX_CASE_NUMBER));
        assertParseSuccess(parser, userInput, expectedCommand);

        userInput = PREFIX_NAME + " " + PREFIX_CASE_NUMBER + " " + PREFIX_NAME;
        expectedCommand = new SortCommand(List.of(PREFIX_CASE_NUMBER, PREFIX_NAME));
        assertParseSuccess(parser, userInput, expectedCommand);

        // multiple prefix repeated
        userInput = PREFIX_NAME + " " + PREFIX_CASE_NUMBER + " " + PREFIX_NAME + " " + PREFIX_NAME + " "
                + PREFIX_CASE_NUMBER + " " + PREFIX_NAME;
        expectedCommand = new SortCommand(List.of(PREFIX_CASE_NUMBER, PREFIX_NAME));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
