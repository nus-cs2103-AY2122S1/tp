package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.student.Student;


/**
 * Purges ProgrammerError.
 */
public class PurgeCommand extends Command {

    public static final String COMMAND_WORD = "purge";
    public static final String MESSAGE_SUCCESS = "ProgrammerError has been purged of data!";
    public static final String MESSAGE_FAIL = "There is no data to purge!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (checkEmpty(model)) {
            return new CommandResult(MESSAGE_FAIL);
        }
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Parse the addressBook and check if its empty.
     * @param model Input the model/data.
     * @return Whether the list is empty or not.
     */
    public Boolean checkEmpty(Model model) {
        List<Student> lastShownList = model.getFilteredStudentList();
        return lastShownList.size() == 0;
    }
}
