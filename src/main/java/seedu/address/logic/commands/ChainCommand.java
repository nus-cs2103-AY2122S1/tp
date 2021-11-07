package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Chains two commands together.
 */
public class ChainCommand extends Command {

    private final Command leftCommand;
    private final Command rightCommand;

    /**
     * Constructor for Chain Command.
     * @param leftCommand Command on the left to be executed first
     * @param rightCommand Command on the right to be executed second
     */
    public ChainCommand(Command leftCommand, Command rightCommand) {
        this.leftCommand = leftCommand;
        this.rightCommand = rightCommand;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        CommandResult leftCommandResult = leftCommand.execute(model);
        CommandResult rightCommandResult = rightCommand.execute(model);
        return new CommandResult(leftCommandResult.getFeedbackToUser() + "\n"
                + rightCommandResult.getFeedbackToUser(),
                leftCommandResult.isShowHelp() || rightCommandResult.isShowHelp(),
                leftCommandResult.isExit() || rightCommandResult.isExit(),
                rightCommandResult.isShowDetails());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ChainCommand // instanceof handles nulls
                && leftCommand.equals(((ChainCommand) other).leftCommand))
                && rightCommand.equals(((ChainCommand) other).rightCommand); // state check
    }
}
