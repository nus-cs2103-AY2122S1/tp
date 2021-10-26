package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.storage.JsonCsBookStorage;

public class EncryptCommand extends Command {

    public static final String COMMAND_WORD = "encrypt";
    public static final String MESSAGE_SUCCESS = "CSbook has been encrypted";


    @Override
    public CommandResult execute(Model model) {
        JsonCsBookStorage.setIsEncrypted(true);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
