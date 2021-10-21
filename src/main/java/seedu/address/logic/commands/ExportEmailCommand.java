package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.storage.JsonAddressBookStorage;

/**
 * Exports emails of last searched contact list into filepath
 */
public class ExportEmailCommand extends Command {

    public static final String COMMAND_WORD = "exportemail";
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    public static final String MESSAGE_SUCCESS = "Exported last searched contacts list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Exports email of last searched contacts list into filepath\n"
        + "Parameters: FILEPATH\n"
        + "Example: " + COMMAND_WORD + " newexport.json";

    private final Path filePath;

    public ExportEmailCommand(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        List<String> emailList = new ArrayList<String>();

        for(Person p : lastShownList){
            emailList.add(p.getEmail().toString());
        }


        JsonAddressBookStorage tempStorage = new JsonAddressBookStorage(filePath);
        try {
            tempStorage.saveEmail(emailList);
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return new CommandResult(
            String.format(MESSAGE_SUCCESS + " to " + filePath));
    }

}

