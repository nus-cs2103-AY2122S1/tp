package seedu.edrecord.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.edrecord.logic.commands.exceptions.CommandException;
import seedu.edrecord.model.Model;
import seedu.edrecord.model.module.Module;
import seedu.edrecord.model.person.PartOfModulePredicate;

/**
 * Changes the selected module.
 */
public class CdCommand extends Command {

    public static final String COMMAND_WORD = "cd";
    public static final String WILDCARD_MODULE_CODE = "*";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Changes the currently selected module. Use '*' to show all modules.\n"
            + "Parameters: NAME\n"
            + "Example: " + COMMAND_WORD + " CS1101S";

    public static final String MESSAGE_NO_SUCH_MODULE = "Module %s does not exist!";
    public static final String MESSAGE_SUCCESS = "Switched to %s.";

    private final PartOfModulePredicate predicate;

    public CdCommand(PartOfModulePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        String moduleCode = predicate.getModuleCode();
        if (!(moduleCode.equals(WILDCARD_MODULE_CODE) || model.hasModule(new Module(moduleCode)))) {
            throw new CommandException(String.format(MESSAGE_NO_SUCH_MODULE, moduleCode));
        }

        model.setModuleFilter(predicate);

        String name = moduleCode.equals(WILDCARD_MODULE_CODE) ? "all modules" : moduleCode;
        return new CommandResult(String.format(MESSAGE_SUCCESS, name));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CdCommand // instanceof handles nulls
                && predicate.equals(((CdCommand) other).predicate)); // state check
    }

}
