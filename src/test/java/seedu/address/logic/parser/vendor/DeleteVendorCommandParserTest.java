package seedu.address.logic.parser.vendor;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.vendor.TypicalVendorIds.VENDOR_ID_FIRST_PERSON;
import static seedu.address.testutil.vendor.TypicalVendorIds.VENDOR_ID_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.vendor.DeleteVendorCommand;

// @@author NicolasCwy
public class DeleteVendorCommandParserTest {

    private DeleteVendorCommandParser parser = new DeleteVendorCommandParser();

    @Test
    public void parse_validVendorIdArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "deletevendor vid/" + VENDOR_ID_FIRST_PERSON,
                new DeleteVendorCommand(VENDOR_ID_FIRST_PERSON));
        assertParseSuccess(parser, "deletevendor vid/" + VENDOR_ID_SECOND_PERSON,
                new DeleteVendorCommand(VENDOR_ID_SECOND_PERSON));

    }

    @Test
    public void parse_invalidDeleteCommand_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteVendorCommand.MESSAGE_USAGE));
    }
}
