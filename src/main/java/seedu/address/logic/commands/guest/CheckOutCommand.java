package seedu.address.logic.commands.guest;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSPORT_NUMBER;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.invoice.Invoice;
import seedu.address.model.Model;
import seedu.address.model.guest.Guest;
import seedu.address.model.guest.PassportNumber;

/**
 * Checks out a guest from the guest book.
 */
public class CheckOutCommand extends Command {

    public static final String COMMAND_WORD = "checkout";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Checks out the guest by the passport number in the displayed guest list.\n"
            + "Parameters: "
            + PREFIX_PASSPORT_NUMBER + "PASSPORT_NUMBER\n"
            + "Example: " + COMMAND_WORD + " pn/A021231B";

    public static final String MESSAGE_CHECKOUT_INVOICE_GENERATED_SUCCESSFUL =
            "Checked out Guest: %1$s\nInvoice has been generated!";
    public static final String MESSAGE_CHECKOUT_NO_INVOICE_SUCCESSFUL =
            "Checked out Guest: %1$s\nNo invoice generated as guest did use any services";

    private final PassportNumber passportNumber;

    public CheckOutCommand(PassportNumber passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        String checkoutMessage = "";

        requireNonNull(model);
        List<Guest> lastShownList = model.getFilteredGuestList();
        Guest guestToCheckOut;

        Optional<Guest> guestToLocateInArchive = model.getArchivedGuest(passportNumber);
        if (guestToLocateInArchive.isPresent()) {
            throw new CommandException(Messages.MESSAGE_GUEST_TO_CHECK_OUT_IS_IN_ARCHIVE);
        } else {
            guestToCheckOut = lastShownList.stream().filter(p ->
                    p.getPassportNumber().equals(passportNumber)).findAny().orElse(null);
            if (guestToCheckOut == null) {
                throw new CommandException(Messages.MESSAGE_GUEST_TO_CHECK_OUT_DOES_NOT_EXIST);
            }
        }

        if (guestToCheckOut.hasChargeables()) {
            // generate invoice
            try {
                Invoice.generateInvoicePdf(guestToCheckOut);
            } catch (IOException e) {
                e.printStackTrace();
            }

            guestToCheckOut.clearChargeables();
            checkoutMessage = MESSAGE_CHECKOUT_INVOICE_GENERATED_SUCCESSFUL;
        } else {
            checkoutMessage = MESSAGE_CHECKOUT_NO_INVOICE_SUCCESSFUL;
        }

        model.deleteGuest(guestToCheckOut); // removes the guest from the guest book
        model.addArchivedGuest(guestToCheckOut); // adds the guest to the archive
        return new CommandResult(String.format(checkoutMessage, guestToCheckOut));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CheckOutCommand // instanceof handles nulls
                && passportNumber.equals(((CheckOutCommand) other).passportNumber)); // state check
    }
}
