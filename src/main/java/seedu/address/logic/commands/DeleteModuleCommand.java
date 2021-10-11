package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.student.Student;

/**
 * Deletes a module identified using its name displayed in TAB.
 */
public class DeleteModuleCommand extends DeleteCommand {

    public static final String COMMAND_WORD = "delete module";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the module identified by its name used in the displayed student list.\n"
            + "Parameters: Module_Name (must be a string)\n"
            + "Example: " + COMMAND_WORD + "m/CS2103";

    public static final String MESSAGE_DELETE_MODULE_SUCCESS = "Deleted Module: %1$s";

    private final ModuleName moduleName;

    public DeleteModuleCommand(ModuleName moduleName) {
        this.moduleName = moduleName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = model.getFilteredModuleList();

        Module moduleToDelete;
        for (Module module : lastShownList) {
            if (module.getName().equals(moduleName)) {
                moduleToDelete = module;
                model.deleteModule(moduleToDelete);
                return new CommandResult(String.format(MESSAGE_DELETE_MODULE_SUCCESS, moduleToDelete));
            }
            ;
        }
        throw new CommandException(Messages.MESSAGE_MODULE_NAME_NOT_FOUND);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteModuleCommand // instanceof handles nulls
                && moduleName.equals(((DeleteModuleCommand) other).moduleName)); // state check
    }
}
