package seedu.address.logic.commands.guest;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSPORT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENDOR_ID;

import java.util.Optional;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.guest.Guest;
import seedu.address.model.guest.PassportNumber;
import seedu.address.model.vendor.Vendor;
import seedu.address.model.vendor.VendorId;

public class ChargeGuestCommand extends Command {

    public static final String COMMAND_WORD = "chargeguest";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Charges a guest that hired services from a vendor.\n"
            + "Parameters: "
            + PREFIX_PASSPORT_NUMBER + "PASSPORT_NUMBER "
            + PREFIX_VENDOR_ID + "VENDOR_ID \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PASSPORT_NUMBER + "SD1208921 "
            + PREFIX_VENDOR_ID + "123";

    public static final String MESSAGE_SUCCESS = "Service from: %1$s \nhas been billed to Guest: %2$s";
    public static final String MESSAGE_NONEXISTENT_VENDOR = "Vendor with this vendorId does not exist";
    public static final String MESSAGE_NONEXISTENT_GUEST = "Guest with this passport number does not exist";

    private final PassportNumber passportNumber;
    private final VendorId vendorId;

    /**
     * Creates an ChargeGuestCommand to charge the specified {@code Guest}
     */
    public ChargeGuestCommand(PassportNumber passportNumber, VendorId vendorId) {
        requireAllNonNull(passportNumber, vendorId);
        this.vendorId = vendorId;
        this.passportNumber = passportNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Optional<Guest> guestInTransaction = model.getGuest(passportNumber);

        if (guestInTransaction.isEmpty()) {
            throw new CommandException(MESSAGE_NONEXISTENT_GUEST);
        }

        Optional<Vendor> vendorInTransaction = model.getVendor(vendorId);

        if (vendorInTransaction.isEmpty()) {
            throw new CommandException(MESSAGE_NONEXISTENT_VENDOR);
        }

        guestInTransaction.get().charge(vendorInTransaction.get());

        return new CommandResult(String.format(MESSAGE_SUCCESS, vendorInTransaction.get(), guestInTransaction.get()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ChargeGuestCommand // instanceof handles nulls
                && passportNumber.equals(((ChargeGuestCommand) other).passportNumber)
                && vendorId.equals(((ChargeGuestCommand) other).vendorId));
    }

}
