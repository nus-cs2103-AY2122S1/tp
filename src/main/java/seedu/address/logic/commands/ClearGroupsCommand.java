package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearGroupsCommand extends Command {

    public static final String COMMAND_WORD = "clearGroups";
    public static final String MESSAGE_SUCCESS = "Group list has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        AddressBook newAddressBook = new AddressBook();
        newAddressBook.setTasks(model.getFilteredTaskList());
        newAddressBook.setStudents(model.getAllStudentList());
        newAddressBook.clearGroupFromStudents(model.getAllStudentList());

        model.setAddressBook(newAddressBook);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
