package seedu.edrecord.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.edrecord.commons.util.CollectionUtil.requireAllNonNull;

import seedu.edrecord.logic.commands.exceptions.CommandException;
import seedu.edrecord.model.Model;
import seedu.edrecord.model.module.Module;

/**
 * Deletes a module.
 */
public class DeleteModuleCommand extends Command {

    public static final String COMMAND_WORD = "dlmod";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes an existing module "
            + "with the module code specified.\n"
            + "Parameters: MODULE_CODE\n"
            + "Example: " + COMMAND_WORD + " CS2103 ";

    public static final String MESSAGE_SUCCESS = "Module %1$s deleted!";

    private final Module module;

    /**
     * @param mod to be deleted.
     */
    public DeleteModuleCommand(Module mod) {
        requireAllNonNull(mod);

        module = mod;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasModule(module)) {
            throw new CommandException(Module.MESSAGE_DOES_NOT_EXIST);
        }

        // TODO: Go through students and delete module if same

        model.deleteModule(module);
        return new CommandResult(String.format(MESSAGE_SUCCESS, module));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteModuleCommand)) {
            return false;
        }

        // state check
        DeleteModuleCommand e = (DeleteModuleCommand) other;
        return module.equals(e.module);
    }
}
