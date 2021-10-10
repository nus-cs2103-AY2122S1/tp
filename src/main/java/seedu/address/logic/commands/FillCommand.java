package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.util.SampleDataUtil;

/**
 * Fills the model with sample data if there is no data.
 */
public class FillCommand extends Command {

    public static final String COMMAND_WORD = "fill";
    public static final String MESSAGE_SUCCESS = "ProgrammerError has been filled with sample data!";
    public static final String MESSAGE_FAIL = "There is existing data! Please purge the "
                                                    + "existing data to import sample data";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (checkEmpty(model)) {
            model.setAddressBook(SampleDataUtil.fillSampleAddressBook());
            return new CommandResult(MESSAGE_SUCCESS);
        }
        return new CommandResult(MESSAGE_FAIL);
    }

    /**
     * Parse the addressBook and check if its empty.
     * @param model Input the model/data.
     * @return Whether the list is empty or not.
     */
    public Boolean checkEmpty(Model model) {
        List<Person> lastShownList = model.getFilteredPersonList();
        if (lastShownList.size() == 0) {
            return true;
        }
        return false;
    }
}
