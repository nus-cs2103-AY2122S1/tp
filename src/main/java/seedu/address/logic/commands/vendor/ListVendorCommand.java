package seedu.address.logic.commands.vendor;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_VENDORS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

public class ListVendorCommand extends Command {

    public static final String COMMAND_WORD = "listvendor";

    public static final String MESSAGE_SUCCESS = "Listed all vendors";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredVendorList(PREDICATE_SHOW_ALL_VENDORS);
        return new CommandResult(MESSAGE_SUCCESS, "Vendors");
    }

}
