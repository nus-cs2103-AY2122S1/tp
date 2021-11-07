package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearStudentsCommand extends Command {

    public static final String COMMAND_WORD = "clearStudents";
    public static final String MESSAGE_SUCCESS = "Student list has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        AddressBook newAddressBook = new AddressBook();
        newAddressBook.setTasks(model.getFilteredTaskList());
        newAddressBook.clearStudentsInGroups(model.getAllGroupList());

        model.setAddressBook(newAddressBook);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
