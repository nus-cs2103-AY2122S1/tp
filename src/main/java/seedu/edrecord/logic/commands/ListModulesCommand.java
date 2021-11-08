package seedu.edrecord.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.edrecord.model.Model;
import seedu.edrecord.model.module.Module;
import seedu.edrecord.model.module.ReadOnlyModuleSystem;

/**
 * Lists all modules in the module system to the user.
 */
public class ListModulesCommand extends Command {

    public static final String COMMAND_WORD = "lsmod";
    public static final String MESSAGE_SUCCESS = "Here are your modules: %s.";
    public static final String MESSAGE_SUCCESS_EMPTY = "There are no modules saved.";
    public static final String MODULE_LIST_DELIM = ", ";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        ReadOnlyModuleSystem moduleSystem = model.getModuleSystem();
        requireNonNull(moduleSystem);

        List<String> moduleStringList = new ArrayList<>();
        for (Module m : moduleSystem.getModuleList()) {
            moduleStringList.add(m.toString());
        }

        String moduleList = String.join(MODULE_LIST_DELIM, moduleStringList);

        if (moduleList.length() == 0) {
            return new CommandResult(String.format(MESSAGE_SUCCESS_EMPTY, moduleList));
        } else {
            return new CommandResult(String.format(MESSAGE_SUCCESS, moduleList));
        }
    }

}
