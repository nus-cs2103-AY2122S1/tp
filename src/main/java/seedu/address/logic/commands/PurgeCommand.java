package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears ProgrammerError.
 */
public class PurgeCommand extends Command {

    public static final String COMMAND_WORD = "purge";
    public static final String MESSAGE_SUCCESS = "ProgrammerError has been purged of sample data!";
    public static final String MESSAGE_FAIL = "There is no sample data to purge!";
    private static Boolean sampleStatus = true;


    @Override
    public CommandResult execute(Model model) {
        if (getStatus()) {
            requireNonNull(model);
            model.setAddressBook(new AddressBook());
            setStatus(false);
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            return new CommandResult(MESSAGE_FAIL);
        }
    }

    private boolean getStatus () {
        return sampleStatus;
    }

    private void setStatus (boolean val) {
        sampleStatus = val;
    }
}
