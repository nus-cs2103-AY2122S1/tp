package nustracker.logic.parser;

import static nustracker.commons.core.Messages.MESSAGE_COMMAND_EXTRANEOUS_SLASHES;
import static nustracker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nustracker.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static nustracker.logic.commands.CommandTestUtil.EVENTNAME_DESC_FINAL;
import static nustracker.logic.commands.CommandTestUtil.EVENTNAME_DESC_TEST;
import static nustracker.logic.commands.CommandTestUtil.INVALID_EVENTNAME_DESC;
import static nustracker.logic.commands.CommandTestUtil.INVALID_STUDENTID_DESC;
import static nustracker.logic.commands.CommandTestUtil.STUDENTID_DESC_AMY;
import static nustracker.logic.commands.CommandTestUtil.STUDENTID_DESC_BOB;
import static nustracker.logic.commands.CommandTestUtil.VALID_EVENTNAME_TEST;
import static nustracker.logic.commands.CommandTestUtil.VALID_STUDENTID_AMY;
import static nustracker.logic.commands.CommandTestUtil.VALID_STUDENTID_BOB;
import static nustracker.logic.parser.CommandParserTestUtil.assertParseFailure;
import static nustracker.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import nustracker.logic.commands.EnrollCommand;
import nustracker.model.event.EventName;
import nustracker.model.student.StudentId;

public class EnrollCommandParserTest {
    private EnrollCommandParser enrollCommandParser = new EnrollCommandParser();

    @Test
    public void parse_allFieldsSpecified_success() {

        // General
        EnrollCommand expectedCommand = new EnrollCommand(
                new StudentId(VALID_STUDENTID_AMY),
                new EventName(VALID_EVENTNAME_TEST));

        String input = STUDENTID_DESC_AMY + EVENTNAME_DESC_TEST;

        assertParseSuccess(enrollCommandParser, input, expectedCommand);

        // Whitespace Preamble
        expectedCommand = new EnrollCommand(
                new StudentId(VALID_STUDENTID_AMY),
                new EventName(VALID_EVENTNAME_TEST));

        input = "                   " + STUDENTID_DESC_AMY + EVENTNAME_DESC_TEST;

        assertParseSuccess(enrollCommandParser, input, expectedCommand);


        // Multiple Student IDs
        expectedCommand = new EnrollCommand(
                new StudentId(VALID_STUDENTID_BOB),
                new EventName(VALID_EVENTNAME_TEST));

        input = STUDENTID_DESC_AMY + EVENTNAME_DESC_TEST + STUDENTID_DESC_AMY + STUDENTID_DESC_BOB;

        assertParseSuccess(enrollCommandParser, input, expectedCommand);

        // Multiple Events
        expectedCommand = new EnrollCommand(
                new StudentId(VALID_STUDENTID_AMY),
                new EventName(VALID_EVENTNAME_TEST));

        input = EVENTNAME_DESC_TEST + EVENTNAME_DESC_FINAL + STUDENTID_DESC_AMY + EVENTNAME_DESC_TEST
                + EVENTNAME_DESC_FINAL + EVENTNAME_DESC_TEST;

        assertParseSuccess(enrollCommandParser, input, expectedCommand);

        // Invalid value followed by valid value
        expectedCommand = new EnrollCommand(
                new StudentId(VALID_STUDENTID_BOB),
                new EventName(VALID_EVENTNAME_TEST));

        input = INVALID_STUDENTID_DESC + EVENTNAME_DESC_TEST + STUDENTID_DESC_BOB;

        assertParseSuccess(enrollCommandParser, input, expectedCommand);

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        // Event Missing
        String input = STUDENTID_DESC_AMY;
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EnrollCommand.MESSAGE_USAGE);

        assertParseFailure(enrollCommandParser, input, expectedMessage);

        // Student ID Missing
        input = EVENTNAME_DESC_TEST;
        expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EnrollCommand.MESSAGE_USAGE);

        assertParseFailure(enrollCommandParser, input, expectedMessage);

        // Both missing
        input = "                       ";
        expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EnrollCommand.MESSAGE_USAGE);

        assertParseFailure(enrollCommandParser, input, expectedMessage);

    }

    @Test
    public void parse_invalidValue_failure() {
        // Preamble Present
        String input = "128249+32" + STUDENTID_DESC_AMY + EVENTNAME_DESC_TEST;
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EnrollCommand.MESSAGE_USAGE);

        assertParseFailure(enrollCommandParser, input, expectedMessage);

        // Extra unrelated parameter
        input = STUDENTID_DESC_AMY + EVENTNAME_DESC_TEST + EMAIL_DESC_AMY;
        expectedMessage = String.format(MESSAGE_COMMAND_EXTRANEOUS_SLASHES, EnrollCommand.MESSAGE_USAGE);

        assertParseFailure(enrollCommandParser, input, expectedMessage);

        // Missing Prefix
        input = STUDENTID_DESC_AMY + VALID_EVENTNAME_TEST;
        expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EnrollCommand.MESSAGE_USAGE);

        assertParseFailure(enrollCommandParser, input, expectedMessage);

        // Invalid Event Name
        input = STUDENTID_DESC_AMY + INVALID_EVENTNAME_DESC;
        expectedMessage = EventName.MESSAGE_CONSTRAINTS;

        assertParseFailure(enrollCommandParser, input, expectedMessage);

        // Invalid Student ID
        input = INVALID_STUDENTID_DESC + EVENTNAME_DESC_TEST;
        expectedMessage = StudentId.MESSAGE_CONSTRAINTS;

        assertParseFailure(enrollCommandParser, input, expectedMessage);


    }

}
