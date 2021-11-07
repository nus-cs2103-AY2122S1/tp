package seedu.address.logic.commands.vendor;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.vendor.VendorBook;

public class ClearVendorCommand extends Command {

    public static final String COMMAND_WORD = "clearvendor";
    public static final String MESSAGE_SUCCESS = "Vendor list has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setVendorBook(new VendorBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
