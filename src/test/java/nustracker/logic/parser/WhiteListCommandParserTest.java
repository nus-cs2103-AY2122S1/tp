package nustracker.logic.parser;

import static nustracker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nustracker.logic.commands.CommandTestUtil.EVENTNAME_DESC_FINAL;
import static nustracker.logic.commands.CommandTestUtil.EVENTNAME_DESC_TEST;
import static nustracker.logic.commands.CommandTestUtil.INVALID_STUDENTID_DESC;
import static nustracker.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static nustracker.logic.commands.CommandTestUtil.STUDENTID_DESC_AMY;
import static nustracker.logic.commands.CommandTestUtil.STUDENTID_DESC_BOB;
import static nustracker.logic.commands.CommandTestUtil.VALID_EVENTNAME_FINAL;
import static nustracker.logic.commands.CommandTestUtil.VALID_EVENTNAME_TEST;
import static nustracker.logic.commands.CommandTestUtil.VALID_STUDENTID_AMY;
import static nustracker.logic.commands.CommandTestUtil.VALID_STUDENTID_BOB;
import static nustracker.logic.parser.CommandParserTestUtil.assertParseFailure;
import static nustracker.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static nustracker.testutil.TypicalEvents.TEST;

import org.junit.jupiter.api.Test;

import nustracker.logic.commands.CommandTestUtil;
import nustracker.logic.commands.WhiteListCommand;
import nustracker.model.event.Event;
import nustracker.model.event.EventName;
import nustracker.model.student.StudentId;
import nustracker.testutil.EventBuilder;

public class WhiteListCommandParserTest {
    private WhiteListCommandParser parser = new WhiteListCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Event expectedEvent = new EventBuilder(TEST).build();

        // whitespace only preamble
        assertParseSuccess(parser, " "
                        + STUDENTID_DESC_AMY + " "
                        + EVENTNAME_DESC_TEST,
                new WhiteListCommand(new StudentId(VALID_STUDENTID_AMY), new EventName(VALID_EVENTNAME_TEST)));

        // multiple student ids - last student id accepted
        assertParseSuccess(parser, " "
                        + STUDENTID_DESC_AMY + " "
                        + STUDENTID_DESC_BOB + " "
                        + EVENTNAME_DESC_TEST,
                new WhiteListCommand(new StudentId(VALID_STUDENTID_BOB), new EventName(VALID_EVENTNAME_TEST)));

        // multiple events - last events accepted
        assertParseSuccess(parser, " "
                        + STUDENTID_DESC_AMY + " "
                        + EVENTNAME_DESC_TEST + " "
                        + EVENTNAME_DESC_FINAL,
                new WhiteListCommand(new StudentId(VALID_STUDENTID_AMY), new EventName(VALID_EVENTNAME_FINAL)));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, WhiteListCommand.MESSAGE_USAGE);

        // missing event prefix
        assertParseFailure(parser, " "
                        + VALID_STUDENTID_AMY + " "
                        + EVENTNAME_DESC_TEST,
                expectedMessage);

        // missing event prefix
        assertParseFailure(parser, " "
                        + STUDENTID_DESC_AMY + " "
                        + VALID_EVENTNAME_TEST,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, " "
                        + VALID_STUDENTID_AMY + " "
                        + VALID_EVENTNAME_TEST,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid student id
        assertParseFailure(parser, " "
                        + INVALID_STUDENTID_DESC
                        + EVENTNAME_DESC_TEST,
                StudentId.MESSAGE_CONSTRAINTS);

        // invalid event
        assertParseFailure(parser, " "
                        + STUDENTID_DESC_AMY
                        + CommandTestUtil.INVALID_EVENTNAME_DESC,
                EventName.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, " "
                        + INVALID_STUDENTID_DESC
                        + CommandTestUtil.INVALID_EVENTNAME_DESC,
                StudentId.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY
                        + STUDENTID_DESC_AMY
                        + EVENTNAME_DESC_TEST,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, WhiteListCommand.MESSAGE_USAGE));
    }
}
