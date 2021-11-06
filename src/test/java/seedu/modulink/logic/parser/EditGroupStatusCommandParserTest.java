package seedu.modulink.logic.parser;

import static seedu.modulink.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modulink.logic.commands.CommandTestUtil.ID_DESC_AMY;
import static seedu.modulink.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.modulink.logic.commands.CommandTestUtil.TAG_DESC_CS2100;
import static seedu.modulink.logic.commands.CommandTestUtil.TAG_DESC_CS2103T;
import static seedu.modulink.logic.commands.CommandTestUtil.VALID_TAG_CS2100;
import static seedu.modulink.logic.commands.CommandTestUtil.VALID_TAG_CS2103T;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_MOD;
import static seedu.modulink.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.modulink.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.modulink.commons.core.Messages;
import seedu.modulink.logic.commands.EditCommand;
import seedu.modulink.logic.commands.EditGroupStatusCommand;
import seedu.modulink.testutil.EditPersonDescriptorBuilder;

public class EditGroupStatusCommandParserTest {

    private EditGroupStatusCommandParser parser = new EditGroupStatusCommandParser();

    @Test
    void parse_oneInput_success() {
        String userInput = TAG_DESC_CS2100;
        EditCommand.EditPersonDescriptor descriptor =
                new EditPersonDescriptorBuilder().withTags(VALID_TAG_CS2100).build();
        EditGroupStatusCommand expectedCommand = new EditGroupStatusCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_noModule_failure() {
        String userInput = " " + PREFIX_MOD;
        assertParseFailure(parser, userInput, EditGroupStatusCommand.MESSAGE_NO_MODULE_SPECIFIED);
    }

    @Test
    void parse_noInput_failure() {
        String userInput = "";
        assertParseFailure(parser, userInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditGroupStatusCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_multipleInputs_failure() {
        String userInput = TAG_DESC_CS2100 + TAG_DESC_CS2103T;
        assertParseFailure(parser, userInput, EditGroupStatusCommand.MESSAGE_MULTIPLE_MODULES_SPECIFIED);
    }

    @Test
    public void parse_invalidPrefix_failure() {
        String userInput = ID_DESC_AMY;
        assertParseFailure(parser, userInput,
                String.format(Messages.MESSAGE_UNKNOWN_PREFIX_FORMAT, EditGroupStatusCommand.MESSAGE_USAGE));

        String userInput2 = VALID_TAG_CS2103T + ID_DESC_AMY;
        assertParseFailure(parser, userInput2,
                String.format(Messages.MESSAGE_UNKNOWN_PREFIX_FORMAT, EditGroupStatusCommand.MESSAGE_USAGE));

        String userInput3 = NAME_DESC_AMY + VALID_TAG_CS2103T + ID_DESC_AMY;
        assertParseFailure(parser, userInput3,
                String.format(Messages.MESSAGE_UNKNOWN_PREFIX_FORMAT, EditGroupStatusCommand.MESSAGE_USAGE));
    }
}
