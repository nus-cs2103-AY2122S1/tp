package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;

import seedu.address.model.Model;
import seedu.address.model.module.Module;

/**
 * Shows the homepage of TAB.
 */
public class HomeCommand extends Command {

    public static final String COMMAND_WORD = "home";

    public static final String MESSAGE_SUCCESS = "Listed all modules and students";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        List<Module> moduleList = model.getFilteredModuleList();
        for (Module module : moduleList) {
            module.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
