package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEW_MODULE_NAME;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.student.UniqueStudentList;
import seedu.address.model.task.UniqueTaskList;


/**
 * Edits the module's name.
 */
public class EditModuleCommand extends EditCommand {

    public static final String COMMAND_WORD = "edit module";

    public static final String MESSAGE_NOT_EDITED = "Both old module name and new module name must be provided "
            + "and must be different from one another";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a module's name. Must provide "
            + "old module name and the new module name\n"
            + "Parameters: "
            + PREFIX_MODULE_NAME + "OLD MODULE NAME "
            + PREFIX_NEW_MODULE_NAME + "NEW MODULE NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE_NAME + "CS2103 "
            + PREFIX_NEW_MODULE_NAME + "CS2105";

    private EditModuleCommand.EditModuleDescriptor editModuleDescriptor;
    private ModuleName moduleName;

    /**
     * Creates an EditModuleCommand to edit the specified {@code Module}.
     *
     * @param moduleName The name of the module to edit.
     * @param editModuleDescriptor The edited module descriptor.
     */
    public EditModuleCommand(ModuleName moduleName, EditModuleCommand.EditModuleDescriptor editModuleDescriptor) {
        requireNonNull(editModuleDescriptor);
        requireNonNull(moduleName);
        this.editModuleDescriptor = editModuleDescriptor;
        this.moduleName = moduleName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = model.getFilteredModuleList();
        ModuleName updatedName = editModuleDescriptor.getModuleName();
        if (moduleName.equals(updatedName)) {
            throw new CommandException(String.format(MESSAGE_NOT_EDITED));
        }
        if (model.hasModuleName(updatedName)) {
            throw new CommandException(String.format(Messages.MESSAGE_DUPLICATE_MODULE, updatedName.getModuleName()));
        }
        for (Module module : lastShownList) {
            if (module.getName().equals(moduleName)) {
                return editModuleInformation(model, module);
            }
        }
        throw new CommandException(String.format(Messages.MESSAGE_MODULE_NAME_NOT_FOUND, moduleName.getModuleName()));
    }

    /**
     * Edits a module's information from TAB.
     * Error checking for module not found is handled by {@code this.execute()}.
     *
     * @param module The module to be edited.
     * @param model The TAB model that stores the list of modules.
     * @return Statement indicating that the edition is successful.
     */
    public CommandResult editModuleInformation(Model model, Module module) {
        Module editedModule = createEditedModule(module, editModuleDescriptor);
        model.setModule(module, editedModule);
        return new CommandResult(String.format(Messages.MESSAGE_EDIT_MODULE_SUCCESS,
                module.getName().getModuleName()));
    }

    /**
     * Creates the edited module.
     *
     * @param moduleToEdit The module to be edited.
     * @param editModuleDescriptor The EditModuleDescriptor object which contains the edited information.
     * @return The edited module.
     */
    private static Module createEditedModule(Module moduleToEdit,
                                             EditModuleCommand.EditModuleDescriptor editModuleDescriptor) {
        assert moduleToEdit != null;

        ModuleName updatedName = editModuleDescriptor.getModuleName();

        UniqueStudentList studentList = moduleToEdit.getUniqueStudentList();
        UniqueTaskList taskList = moduleToEdit.getTaskList();

        return new Module(updatedName, studentList, taskList);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditModuleCommand)) {
            return false;
        }

        // state check
        EditModuleCommand e = (EditModuleCommand) other;
        return moduleName.equals(e.moduleName)
                && editModuleDescriptor.equals(e.editModuleDescriptor);
    }

    /**
     * Stores the details to edit the module with. Each non-empty field value will replace the
     * corresponding field value of the module.
     */
    public static class EditModuleDescriptor {
        private ModuleName name;

        public EditModuleDescriptor() {}

        /**
         * Copy constructor.
         *
         * @param  toCopy The edit module descriptor to be copied.
         */
        public EditModuleDescriptor(EditModuleCommand.EditModuleDescriptor toCopy) {
            setModuleName(toCopy.name);
        }

        /**
         * Returns true if at least one field is edited.
         *
         * @return A boolean stating whether there is any edited field.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name);
        }

        public void setModuleName(ModuleName name) {
            this.name = name;
        }

        public ModuleName getModuleName() {
            return this.name;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditModuleCommand.EditModuleDescriptor)) {
                return false;
            }

            // state check
            EditModuleCommand.EditModuleDescriptor e = (EditModuleCommand.EditModuleDescriptor) other;

            return getModuleName().equals(e.getModuleName());
        }
    }

}
