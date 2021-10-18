package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELE_HANDLE;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.student.Email;
import seedu.address.model.module.student.Name;
import seedu.address.model.module.student.Student;
import seedu.address.model.module.student.StudentId;
import seedu.address.model.module.student.TeleHandle;

/**
 * Edits a student's information.
 */
public class EditStudentCommand extends EditCommand {

    public static final String COMMAND_WORD = "edit student";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a student's information. "
            + "Parameters: "
            + PREFIX_MODULE_NAME + "MODULE NAME "
            + PREFIX_STUDENT_ID + "STUDENT ID "
            + PREFIX_NAME + "EDITED NAME "
            + PREFIX_TELE_HANDLE + "EDITED TELE HANDLE "
            + PREFIX_EMAIL + "EDITED EMAIL\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE_NAME + "CS2103 "
            + PREFIX_STUDENT_ID + "A1234567A "
            + PREFIX_NAME + "John Doe "
            + PREFIX_TELE_HANDLE + "@johndoe "
            + PREFIX_EMAIL + "johnd@example.com ";

    private StudentId studentId;
    private Name name;
    private TeleHandle teleHandle;
    private Email email;
    private ModuleName moduleName;

    /**
     * Creates an EditCommand to edit the specified {@code Student}
     */
    public EditStudentCommand(ModuleName moduleName, StudentId studentId, Name name, TeleHandle teleHandle,
                              Email email) {
        this.studentId = studentId;
        this.name = name;
        this.teleHandle = teleHandle;
        this.email = email;
        this.moduleName = moduleName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = model.getFilteredModuleList();
        for (Module module : lastShownList) {
            if (module.getName().equals(moduleName)) {
                return editStudentInformation(module);
            }
        }
        throw new CommandException(String.format(Messages.MESSAGE_MODULE_NAME_NOT_FOUND, moduleName.moduleName));
    }

    /**
     * Edits a student's information. the student from the specified module.
     *
     * @param module The module the student will be deleted from.
     * @return Statement indicating that the deletion is successful.
     * @throws CommandException Exception thrown when student is not found.
     */
    public CommandResult editStudentInformation(Module module) throws CommandException {
        List<Student> studentList = module.getFilteredStudentList();
        for (Student student : studentList) {
            if (student.getStudentId().equals(studentId)) {
                StudentId copyStudentId = student.getStudentId();
                Student editedStudent = new Student(copyStudentId, name, teleHandle, email);
                editedStudent.setTaskList(student.getTaskList());
                module.setStudent(student, editedStudent);
                return new CommandResult(String.format(Messages.MESSAGE_EDIT_STUDENT_SUCCESS, studentId));
            }
        }
        throw new CommandException(String.format(Messages.MESSAGE_STUDENT_NOT_FOUND, studentId));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditStudentCommand // instanceof handles nulls
                && moduleName.equals(((EditStudentCommand) other).moduleName)
                && studentId.equals(((EditStudentCommand) other).studentId)
                && name.equals(((EditStudentCommand) other).name)
                && teleHandle.equals(((EditStudentCommand) other).teleHandle)
                && email.equals(((EditStudentCommand) other).email));
    }
}
