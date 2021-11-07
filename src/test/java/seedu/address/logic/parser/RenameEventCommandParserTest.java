package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_NAME_DESC_PARTY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EVENT_NAME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_PARTY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RenameEventCommand;
import seedu.address.testutil.RenameEventDescriptorBuilder;

public class RenameEventCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, RenameEventCommand.MESSAGE_USAGE);

    private RenameEventCommandParser parser = new RenameEventCommandParser();

    @Test
    public void parse_missingNewName_failure() {
        // no index specified
        assertParseFailure(parser, VALID_EVENT_NAME_PARTY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", RenameEventCommand.MESSAGE_NOT_RENAMED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_EVENT_NAME_PARTY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_EVENT_NAME_PARTY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_EVENT_NAME,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RenameEventCommand.MESSAGE_USAGE)); // invalid name
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + EVENT_NAME_DESC_PARTY;

        RenameEventCommand.RenameEventDescriptor descriptor = new RenameEventDescriptorBuilder()
                .withName(VALID_EVENT_NAME_PARTY).build();
        RenameEventCommand expectedCommand = new RenameEventCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
