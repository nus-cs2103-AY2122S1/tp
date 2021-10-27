package seedu.address.logic.commands;

public class SystemCommand {

    /**
     * Executes help message when user is typing
     * @param userInput
     * @return
     */
    public static CommandResult execute(String userInput) {
        String[] parsedInput = userInput.split(" ");
        String commandWord = parsedInput[0];
        switch (commandWord) {
        case (AddCommand.COMMAND_WORD):
            return new CommandResult(AddCommand.MESSAGE_USAGE, false, false);
        default:
            return new CommandResult("", false, false);
        }
    }
}
