package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_POSITION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.POSITION_DESC_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.POSITION_DESC_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_BYTEDANCE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalApplications.AMAZON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.application.Application;
import seedu.address.model.application.Company;
import seedu.address.model.application.Deadline;
import seedu.address.model.application.Position;
import seedu.address.testutil.ApplicationBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allCompulsoryFieldsPresent_success() {
        Application expectedApplication = new ApplicationBuilder(AMAZON).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMAZON
                + POSITION_DESC_AMAZON + DEADLINE_DESC_AMAZON,
                new AddCommand(expectedApplication));

        // multiple company names - last company name accepted
        assertParseSuccess(parser, NAME_DESC_BYTEDANCE + NAME_DESC_AMAZON
                + POSITION_DESC_AMAZON + DEADLINE_DESC_AMAZON,
                new AddCommand(expectedApplication));

        // multiple positions - last position accepted
        assertParseSuccess(parser, NAME_DESC_AMAZON + POSITION_DESC_BYTEDANCE
                + POSITION_DESC_AMAZON + DEADLINE_DESC_AMAZON,
                new AddCommand(expectedApplication));

        // multiple deadlines - last deadline accepted
        assertParseSuccess(parser, NAME_DESC_AMAZON + POSITION_DESC_AMAZON
                + DEADLINE_DESC_BYTEDANCE + DEADLINE_DESC_AMAZON,
                new AddCommand(expectedApplication));

        // multiple tags - all accepted
        Application expectedApplicationMultipleTags = new ApplicationBuilder(AMAZON)
                .build();
        assertParseSuccess(parser, NAME_DESC_AMAZON
                + POSITION_DESC_AMAZON + DEADLINE_DESC_AMAZON,
                new AddCommand(expectedApplicationMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Application expectedApplication = new ApplicationBuilder(AMAZON).build();
        assertParseSuccess(parser, NAME_DESC_AMAZON + POSITION_DESC_AMAZON
                        + DEADLINE_DESC_AMAZON,
                new AddCommand(expectedApplication));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BYTEDANCE + POSITION_DESC_BYTEDANCE + DEADLINE_DESC_BYTEDANCE,
                expectedMessage);

        // missing position prefix
        assertParseFailure(parser, NAME_DESC_BYTEDANCE + VALID_POSITION_BYTEDANCE + DEADLINE_DESC_BYTEDANCE,
                expectedMessage);

        // missing deadline prefix
        assertParseFailure(parser, NAME_DESC_BYTEDANCE + POSITION_DESC_BYTEDANCE + VALID_DEADLINE_BYTEDANCE,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BYTEDANCE + VALID_POSITION_BYTEDANCE + VALID_DEADLINE_BYTEDANCE,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + POSITION_DESC_BYTEDANCE + DEADLINE_DESC_BYTEDANCE,
                Company.MESSAGE_CONSTRAINTS);

        // invalid position
        assertParseFailure(parser, NAME_DESC_BYTEDANCE + INVALID_POSITION_DESC + DEADLINE_DESC_BYTEDANCE,
                Position.MESSAGE_CONSTRAINTS);

        // invalid deadline
        assertParseFailure(parser, NAME_DESC_BYTEDANCE + POSITION_DESC_BYTEDANCE + INVALID_DEADLINE_DESC,
                Deadline.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_POSITION_DESC + DEADLINE_DESC_BYTEDANCE,
                Company.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BYTEDANCE + POSITION_DESC_BYTEDANCE
                + DEADLINE_DESC_BYTEDANCE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
