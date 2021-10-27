package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.storage.JsonCsBookStorage;

public class DecryptCommand extends Command {
    public static final String COMMAND_WORD = "decrypt";
    public static final String MESSAGE_SUCCESS = "CSbook has been decrypted";


    @Override
    public CommandResult execute(Model model) {
        JsonCsBookStorage.setIsEncrypted(false);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
