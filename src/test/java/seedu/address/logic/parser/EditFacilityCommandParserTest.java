package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CAPACITY_DESC_COURT;
import static seedu.address.logic.commands.CommandTestUtil.CAPACITY_DESC_FIELD;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_COURT;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_FIELD;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_COURT;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_FIELD;
import static seedu.address.logic.commands.CommandTestUtil.TIME_DESC_COURT;
import static seedu.address.logic.commands.CommandTestUtil.TIME_DESC_FIELD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CAPACITY_COURT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CAPACITY_FIELD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FACILITY_NAME_COURT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FACILITY_NAME_FIELD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_COURT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_FIELD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_COURT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_FIELD;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditFacilityCommand;
import seedu.address.testutil.EditFacilityDescriptorBuilder;

public class EditFacilityCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditFacilityCommand.MESSAGE_USAGE);

    private EditFacilityCommandParser parser = new EditFacilityCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_FACILITY_NAME_COURT, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditFacilityCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_COURT, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_COURT, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + NAME_DESC_COURT + LOCATION_DESC_COURT
                + CAPACITY_DESC_COURT + TIME_DESC_COURT;
        EditFacilityCommand.EditFacilityDescriptor descriptor = new EditFacilityDescriptorBuilder()
                .withFacilityName(VALID_FACILITY_NAME_COURT).withLocation(VALID_LOCATION_COURT)
                .withCapacity(VALID_CAPACITY_COURT).withTime(VALID_TIME_COURT).build();
        EditFacilityCommand expectedCommand = new EditFacilityCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + NAME_DESC_COURT + LOCATION_DESC_COURT;

        EditFacilityCommand.EditFacilityDescriptor descriptor = new EditFacilityDescriptorBuilder()
                .withFacilityName(VALID_FACILITY_NAME_COURT).withLocation(VALID_LOCATION_COURT).build();
        EditFacilityCommand expectedCommand = new EditFacilityCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + NAME_DESC_COURT;
        EditFacilityCommand.EditFacilityDescriptor descriptor = new EditFacilityDescriptorBuilder()
                .withFacilityName(VALID_FACILITY_NAME_COURT).build();
        EditFacilityCommand expectedCommand = new EditFacilityCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // location
        userInput = targetIndex.getOneBased() + LOCATION_DESC_COURT;
        descriptor = new EditFacilityDescriptorBuilder()
                .withLocation(VALID_LOCATION_COURT).build();
        expectedCommand = new EditFacilityCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // time
        userInput = targetIndex.getOneBased() + TIME_DESC_COURT;
        descriptor = new EditFacilityDescriptorBuilder()
                .withTime(VALID_TIME_COURT).build();
        expectedCommand = new EditFacilityCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // capacity
        userInput = targetIndex.getOneBased() + CAPACITY_DESC_COURT;
        descriptor = new EditFacilityDescriptorBuilder()
                .withCapacity(VALID_CAPACITY_COURT).build();
        expectedCommand = new EditFacilityCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased()
                + NAME_DESC_COURT + LOCATION_DESC_COURT + CAPACITY_DESC_COURT + TIME_DESC_COURT
                + NAME_DESC_COURT + LOCATION_DESC_COURT + CAPACITY_DESC_COURT + TIME_DESC_COURT
                + NAME_DESC_FIELD + LOCATION_DESC_FIELD + CAPACITY_DESC_FIELD + TIME_DESC_FIELD;

        EditFacilityCommand.EditFacilityDescriptor descriptor = new EditFacilityDescriptorBuilder()
                .withFacilityName(VALID_FACILITY_NAME_FIELD).withLocation(VALID_LOCATION_FIELD)
                .withCapacity(VALID_CAPACITY_FIELD).withTime(VALID_TIME_FIELD).build();
        EditFacilityCommand expectedCommand = new EditFacilityCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
