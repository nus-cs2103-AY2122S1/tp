package manageme.logic.commands.module;

import static java.util.Objects.requireNonNull;

import java.util.List;

import manageme.commons.core.Messages;
import manageme.commons.core.index.Index;
import manageme.logic.commands.Command;
import manageme.logic.commands.CommandResult;
import manageme.logic.commands.exceptions.CommandException;
import manageme.model.Model;
import manageme.model.module.Module;

/**
 * Show all tasks and links associated with a module in a pop-up window.
 */
public class ReadModuleCommand extends Command {
    public static final String COMMAND_WORD = "readMod";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Display the module identified by the index number used in the displayed module list.\n"
            + "Parameters: INDEX (must be a positive integer between 1 and 2147483647)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Displayed module";

    private final Index targetIndex;

    public ReadModuleCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = model.getFilteredModuleList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }

        Module moduleToRead = lastShownList.get(targetIndex.getZeroBased());
        model.setReadModule(moduleToRead);
        return new CommandResult(MESSAGE_SUCCESS, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadModuleCommand // instanceof handles nulls
                && targetIndex.equals(((ReadModuleCommand) other).targetIndex)); // state check
    }
}
