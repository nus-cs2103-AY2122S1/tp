package seedu.modulink.logic.parser;

import static seedu.modulink.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modulink.logic.commands.CommandTestUtil.TAG_DESC_CS2100;
import static seedu.modulink.logic.commands.CommandTestUtil.TAG_DESC_CS2103T;
import static seedu.modulink.logic.commands.CommandTestUtil.VALID_TAG_CS2100;
import static seedu.modulink.logic.commands.CommandTestUtil.VALID_TAG_CS2103T;
import static seedu.modulink.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.modulink.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.modulink.logic.commands.EditCommand;
import seedu.modulink.logic.commands.RemoveModCommand;
import seedu.modulink.testutil.EditPersonDescriptorBuilder;

class RemoveModCommandParserTest {
    private RemoveModCommandParser parser = new RemoveModCommandParser();

    @Test
    void parse_oneInput_success() {
        String userInput = TAG_DESC_CS2100;
        EditCommand.EditPersonDescriptor descriptor =
                new EditPersonDescriptorBuilder().withTags(VALID_TAG_CS2100).build();
        RemoveModCommand expectedCommand = new RemoveModCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    void parse_multipleInputs_failure() {
        String userInput = TAG_DESC_CS2100 + TAG_DESC_CS2103T;
        EditCommand.EditPersonDescriptor descriptor =
                new EditPersonDescriptorBuilder().withTags(VALID_TAG_CS2100, VALID_TAG_CS2103T).build();
        RemoveModCommand expectedCommand = new RemoveModCommand(descriptor);
        assertParseFailure(parser, userInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveModCommand.MESSAGE_USAGE));
    }
}
