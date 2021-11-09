package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_FIND_STUDENT_SUCCESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.ModuleNameEqualsKeywordPredicate;
import seedu.address.model.module.student.Student;
import seedu.address.model.module.student.StudentId;
import seedu.address.model.module.student.StudentIdEqualsKeywordPredicate;

/**
 * Finds and lists the student in a module that that has the specified student ID.
 * Keyword matching is case insensitive.
 */
public class FindStudentCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds student with the student ID from a module.\n"
            + "Parameters: "
            + PREFIX_MODULE_NAME + "MODULE NAME "
            + PREFIX_STUDENT_ID + "STUDENT ID\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE_NAME + "CS2103 "
            + PREFIX_STUDENT_ID + "A1234567A";

    private final StudentId studentId;
    private final ModuleName moduleName;

    /**
     * Finds a student in TAB using the student's ID.
     *
     * @param moduleName The module of the student to be found.
     * @param studentId The student ID of the student to be found.
     */
    public FindStudentCommand(ModuleName moduleName, StudentId studentId) {
        this.moduleName = moduleName;
        this.studentId = studentId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = model.getFilteredModuleList();

        for (Module module : lastShownList) {
            if (module.getName().equals(moduleName)) {
                ModuleNameEqualsKeywordPredicate predicate =
                        new ModuleNameEqualsKeywordPredicate(moduleName.getModuleName());
                model.updateFilteredModuleList(predicate);
                findStudentFromModule(module, model);
                return new CommandResult(String.format(MESSAGE_FIND_STUDENT_SUCCESS, studentId));
            }
        }
        throw new CommandException(String.format(Messages.MESSAGE_MODULE_NAME_NOT_FOUND, moduleName.getModuleName()));
    }

    /**
     * Find a student from the specified module.
     *
     * @param module The module the student will be searched from.
     * @param model The TAB model.
     * @throws CommandException Exception thrown when student is not found.
     */
    public void findStudentFromModule(Module module, Model model) throws CommandException {
        List<Student> studentList = module.getFilteredStudentList();
        for (Student student : studentList) {
            if (student.getStudentId().equals(studentId)) {
                StudentIdEqualsKeywordPredicate predicate =
                        new StudentIdEqualsKeywordPredicate(studentId.value);
                module.updateFilteredStudentList(predicate);
                return;
            }
        }
        model.updateFilteredModuleList(Model.PREDICATE_SHOW_ALL_MODULES);
        throw new CommandException(String.format(Messages.MESSAGE_STUDENT_NOT_FOUND, studentId));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindStudentCommand // instanceof handles nulls
                && studentId.equals(((FindStudentCommand) other).studentId)
                && moduleName.equals(((FindStudentCommand) other).moduleName)); // state check
    }
}
