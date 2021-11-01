package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NEW_MODULE_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_NAME_DESC_0;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_NAME_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.NEW_MODULE_NAME_DESC_0;
import static seedu.address.logic.commands.CommandTestUtil.NEW_MODULE_NAME_DESC_1;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_0;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_1;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditModuleCommand;
import seedu.address.logic.commands.EditModuleCommand.EditModuleDescriptor;
import seedu.address.model.module.ModuleName;

public class EditModuleCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditModuleCommand.MESSAGE_USAGE);
    private EditModuleCommandParser parser = new EditModuleCommandParser();


    @Test
    public void parse_missingParts_failure() {
        // no new module name specified
        assertParseFailure(parser, MODULE_NAME_DESC_0, MESSAGE_INVALID_FORMAT);

        // no old module name specified
        assertParseFailure(parser, NEW_MODULE_NAME_DESC_0, MESSAGE_INVALID_FORMAT);

        // no module name specified at all
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditModuleCommand.MESSAGE_USAGE);
        // invalid module name.
        assertParseFailure(parser, INVALID_MODULE_NAME_DESC, expectedMessage);

        //invalid new module name
        assertParseFailure(parser, INVALID_NEW_MODULE_NAME_DESC, expectedMessage);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = MODULE_NAME_DESC_1 + NEW_MODULE_NAME_DESC_0;
        EditModuleDescriptor editModuleDescriptor = new EditModuleDescriptor();
        editModuleDescriptor.setModuleName(new ModuleName(MODULE_NAME_0));
        EditModuleCommand expectedCommand = new EditModuleCommand(new ModuleName(MODULE_NAME_1), editModuleDescriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedValues_acceptsLast() {
        String userInput = MODULE_NAME_DESC_0 + NEW_MODULE_NAME_DESC_0 + NEW_MODULE_NAME_DESC_1;
        EditModuleDescriptor editModuleDescriptor = new EditModuleDescriptor();
        editModuleDescriptor.setModuleName(new ModuleName(MODULE_NAME_1));
        EditModuleCommand expectedCommand = new EditModuleCommand(new ModuleName(MODULE_NAME_0), editModuleDescriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValuesFollowedByValidValue_success() {
        String userInput = MODULE_NAME_DESC_0 + INVALID_NEW_MODULE_NAME_DESC + NEW_MODULE_NAME_DESC_1;
        EditModuleDescriptor editModuleDescriptor = new EditModuleDescriptor();
        editModuleDescriptor.setModuleName(new ModuleName(MODULE_NAME_1));
        EditModuleCommand expectedCommand = new EditModuleCommand(new ModuleName(MODULE_NAME_0), editModuleDescriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
