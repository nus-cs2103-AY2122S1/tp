package seedu.modulink.logic.parser;

import static seedu.modulink.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modulink.logic.commands.CommandTestUtil.TAG_DESC_CS2100;
import static seedu.modulink.logic.commands.CommandTestUtil.TAG_DESC_CS2103T;
import static seedu.modulink.logic.commands.CommandTestUtil.VALID_TAG_CS2100;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_MOD;
import static seedu.modulink.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.modulink.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.modulink.commons.core.Messages;
import seedu.modulink.logic.commands.AddModCommand;
import seedu.modulink.logic.commands.EditCommand;
import seedu.modulink.testutil.EditPersonDescriptorBuilder;

class AddModCommandParserTest {
    private AddModCommandParser parser = new AddModCommandParser();

    @Test
    void parse_oneInput_success() {
        String userInput = TAG_DESC_CS2100;
        EditCommand.EditPersonDescriptor descriptor =
                new EditPersonDescriptorBuilder().withTags(VALID_TAG_CS2100).build();
        AddModCommand expectedCommand = new AddModCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    void parse_multipleInputs_failure() {
        String userInput = TAG_DESC_CS2100 + TAG_DESC_CS2103T;
        assertParseFailure(parser, userInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_randomInput_failure() {
        String userInput = " " + PREFIX_ID + VALID_TAG_CS2100;
        assertParseFailure(parser, userInput, AddModCommand.MESSAGE_NO_CHANGE);

        String userInput2 = " " + PREFIX_MOD;
        assertParseFailure(parser, userInput2, AddModCommand.MESSAGE_NO_CHANGE);
    }

    @Test
    void parse_noInput_failure() {
        String userInput = "";
        assertParseFailure(parser, userInput,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddModCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_multiple_failure() {
        String userInput = TAG_DESC_CS2100 + " " + PREFIX_ID + VALID_TAG_CS2100;
        assertParseFailure(parser, userInput,
                String.format(Messages.MESSAGE_UNKNOWN_PREFIX_FORMAT, AddModCommand.MESSAGE_USAGE));
    }

}
