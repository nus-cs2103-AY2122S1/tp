package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSPORT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENDOR_ID;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.guest.TypicalPassportNumbers.PASSPORT_NUMBER_DEFAULT;
import static seedu.address.testutil.guest.TypicalPassportNumbers.PASSPORT_NUMBER_FIRST_PERSON;
import static seedu.address.testutil.vendor.TypicalVendorIds.VENDOR_ID_DEFAULT;
import static seedu.address.testutil.vendor.TypicalVendorIds.VENDOR_ID_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.guest.CheckInNewGuestCommand;
import seedu.address.logic.commands.guest.ClearGuestCommand;
import seedu.address.logic.commands.guest.DeleteGuestCommand;
import seedu.address.logic.commands.guest.EditGuestCommand;
import seedu.address.logic.commands.guest.EditGuestCommand.EditGuestDescriptor;
import seedu.address.logic.commands.guest.ListGuestCommand;
import seedu.address.logic.commands.vendor.AddVendorCommand;
import seedu.address.logic.commands.vendor.ClearVendorCommand;
import seedu.address.logic.commands.vendor.DeleteVendorCommand;
import seedu.address.logic.commands.vendor.EditVendorCommand;
import seedu.address.logic.commands.vendor.EditVendorCommand.EditVendorDescriptor;
import seedu.address.logic.commands.vendor.ListVendorCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.guest.Guest;
import seedu.address.model.vendor.Vendor;
import seedu.address.testutil.guest.EditGuestDescriptorBuilder;
import seedu.address.testutil.guest.GuestBuilder;
import seedu.address.testutil.guest.GuestUtil;
import seedu.address.testutil.vendor.EditVendorDescriptorBuilder;
import seedu.address.testutil.vendor.VendorBuilder;
import seedu.address.testutil.vendor.VendorUtil;

public class PocketHotelParserTest {

    private final PocketHotelParser parser = new PocketHotelParser();

    @Test
    public void parseCommand_checkInGuest() throws Exception {
        Guest guest = new GuestBuilder().build();
        CheckInNewGuestCommand command = (CheckInNewGuestCommand) parser.parseCommand(GuestUtil.getAddCommand(guest));
        assertEquals(new CheckInNewGuestCommand(guest), command);
    }

    @Test
    public void parseCommand_checkOutGuest() throws Exception {

    }

    @Test
    public void parseCommand_chargeGuest() throws Exception {

    }

    @Test
    public void parseCommand_addVendor() throws Exception {
        Vendor vendor = new VendorBuilder().build();
        AddVendorCommand command = (AddVendorCommand) parser.parseCommand(VendorUtil.getAddCommand(vendor));
        assertEquals(new AddVendorCommand(vendor), command);
    }

    @Test
    public void parseCommand_clearGuest() throws Exception {
        assertTrue(parser.parseCommand(ClearGuestCommand.COMMAND_WORD) instanceof ClearGuestCommand);
        assertTrue(parser.parseCommand(ClearGuestCommand.COMMAND_WORD + " 3") instanceof ClearGuestCommand);
    }

    @Test
    public void parseCommand_clearVendor() throws Exception {
        assertTrue(parser.parseCommand(ClearVendorCommand.COMMAND_WORD) instanceof ClearVendorCommand);
        assertTrue(parser.parseCommand(ClearVendorCommand.COMMAND_WORD + " 3") instanceof ClearVendorCommand);
    }

    @Test
    public void parseCommand_deleteGuest() throws Exception {
        DeleteGuestCommand command = (DeleteGuestCommand) parser.parseCommand(
                DeleteGuestCommand.COMMAND_WORD + " "
                        + PREFIX_PASSPORT_NUMBER + PASSPORT_NUMBER_FIRST_PERSON.toString());
        assertEquals(new DeleteGuestCommand(PASSPORT_NUMBER_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_deleteVendor() throws Exception {
        DeleteVendorCommand command = (DeleteVendorCommand) parser.parseCommand(
                DeleteVendorCommand.COMMAND_WORD + " "
                        + PREFIX_VENDOR_ID + VENDOR_ID_FIRST_PERSON.toString());
        assertEquals(new DeleteVendorCommand(VENDOR_ID_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_editGuest() throws Exception {
        Guest guest = new GuestBuilder().build();
        EditGuestDescriptor descriptor = new EditGuestDescriptorBuilder(guest).build();
        EditGuestCommand command = (EditGuestCommand) parser.parseCommand(EditGuestCommand.COMMAND_WORD + " "
                + PASSPORT_NUMBER_DEFAULT + " " + GuestUtil.getEditGuestDescriptorDetails(descriptor));
        assertEquals(new EditGuestCommand(PASSPORT_NUMBER_DEFAULT, descriptor), command);
    }

    @Test
    public void parseCommand_editVendor() throws Exception {
        Vendor vendor = new VendorBuilder().build();
        EditVendorDescriptor descriptor = new EditVendorDescriptorBuilder(vendor).build();
        EditVendorCommand command = (EditVendorCommand) parser.parseCommand(EditVendorCommand.COMMAND_WORD + " "
                + VENDOR_ID_DEFAULT.toString() + " " + VendorUtil.getEditVendorDescriptorDetails(descriptor));
        assertEquals(new EditVendorCommand(VENDOR_ID_DEFAULT, descriptor), command);
    }

    @Test
    public void parseCommand_filterGuest() throws Exception {
        Guest guest = new GuestBuilder().build();
    }

    @Test
    public void parseCommand_filterVendor() throws Exception {
        Vendor vendor = new VendorBuilder().build();
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_listGuest() throws Exception {
        assertTrue(parser.parseCommand(ListGuestCommand.COMMAND_WORD) instanceof ListGuestCommand);
        assertTrue(parser.parseCommand(ListGuestCommand.COMMAND_WORD + " 3") instanceof ListGuestCommand);
    }

    @Test
    public void parseCommand_listVendor() throws Exception {
        assertTrue(parser.parseCommand(ListVendorCommand.COMMAND_WORD) instanceof ListVendorCommand);
        assertTrue(parser.parseCommand(ListVendorCommand.COMMAND_WORD + " 3") instanceof ListVendorCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), (
        ) -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
