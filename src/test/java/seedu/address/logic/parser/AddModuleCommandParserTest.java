package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_NAME_DESC_0;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_NAME_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalModules.MODULE_1;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddModuleCommand;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;

public class AddModuleCommandParserTest {
    private AddModuleCommandParser addModuleCommandParser = new AddModuleCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Module expectedModule = MODULE_1;

        // whitespace only preamble
        assertParseSuccess(addModuleCommandParser, PREAMBLE_WHITESPACE + MODULE_NAME_DESC_0,
                new AddModuleCommand(expectedModule));

        // multiple module names -> last one accepted
        assertParseSuccess(addModuleCommandParser, MODULE_NAME_DESC_1 + MODULE_NAME_DESC_0,
                new AddModuleCommand(expectedModule));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModuleCommand.MESSAGE_USAGE);

        // missing module name
        assertParseFailure(addModuleCommandParser, "", expectedMessage);
    }

    @Test
    public void parse_invalidValueFailure() {
        // invalid module name
        assertParseFailure(addModuleCommandParser, INVALID_MODULE_NAME_DESC, ModuleName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(addModuleCommandParser, PREAMBLE_NON_EMPTY + MODULE_NAME_DESC_0,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModuleCommand.MESSAGE_USAGE));
    }
}
