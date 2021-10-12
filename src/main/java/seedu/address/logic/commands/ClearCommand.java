package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.folder.Folder;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "rm -contacts";
    public static final String MESSAGE_SUCCESS = "Address book contacts have been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        AddressBook newAddressBook = copyFolders(model);
        model.setAddressBook(newAddressBook);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Returns a new address book with empty folders with the copied names from model
     * @param model model to copy folder names from
     * @return address book with copied folders
     */
    private AddressBook copyFolders(Model model) {
        ReadOnlyAddressBook addressBook = model.getAddressBook();
        ObservableList<Folder> folders = addressBook.getFolderList();
        AddressBook newAddressBook = new AddressBook();
        for (Folder folder : folders) {
            newAddressBook.addFolder(new Folder(folder.getFolderName()));
        }
        return newAddressBook;
    }
}
