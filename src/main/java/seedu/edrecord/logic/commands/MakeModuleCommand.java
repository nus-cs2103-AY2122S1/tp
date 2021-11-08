package seedu.edrecord.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.edrecord.commons.util.CollectionUtil.requireAllNonNull;

import seedu.edrecord.logic.commands.exceptions.CommandException;
import seedu.edrecord.model.Model;
import seedu.edrecord.model.module.Module;

/**
 * Creates a module.
 */
public class MakeModuleCommand extends Command {

    public static final String COMMAND_WORD = "mkmod";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates a new module for classes "
            + "with the module code specified.\n"
            + "Parameters: MODULE_CODE\n"
            + "Example: " + COMMAND_WORD + " CS2103 ";

    public static final String MESSAGE_DUPLICATE_MODULE = "Module already exists!";
    public static final String MESSAGE_SUCCESS = "Module %1$s created!";

    private final Module module;

    /**
     * @param mod to be created.
     */
    public MakeModuleCommand(Module mod) {
        requireAllNonNull(mod);

        module = mod;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasModule(module)) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }

        model.addModule(module);
        return new CommandResult(String.format(MESSAGE_SUCCESS, module));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MakeModuleCommand)) {
            return false;
        }

        // state check
        MakeModuleCommand e = (MakeModuleCommand) other;
        return module.equals(e.module);
    }
}
