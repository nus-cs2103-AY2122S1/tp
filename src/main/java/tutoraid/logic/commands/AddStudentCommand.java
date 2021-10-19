package tutoraid.logic.commands;

import static java.util.Objects.requireNonNull;
import static tutoraid.logic.parser.CliSyntax.PREFIX_PARENT_NAME;
import static tutoraid.logic.parser.CliSyntax.PREFIX_PARENT_PHONE;
import static tutoraid.logic.parser.CliSyntax.PREFIX_STUDENT_NAME;
import static tutoraid.logic.parser.CliSyntax.PREFIX_STUDENT_PHONE;

import tutoraid.logic.commands.exceptions.CommandException;
import tutoraid.model.Model;
import tutoraid.model.student.Student;

/**
 * Adds a student to TutorAid.
 */
public class AddStudentCommand extends AddCommand {

    public static final String COMMAND_FLAG = "-s";

    public static final String MESSAGE_USAGE = COMMAND_FLAG + ": Adds a student to TutorAid. "
            + "Parameters: "
            + PREFIX_STUDENT_NAME + "STUDENT NAME "
            + PREFIX_STUDENT_PHONE + "STUDENT PHONE "
            + PREFIX_PARENT_NAME + "PARENT NAME "
            + PREFIX_PARENT_PHONE + "PARENT PHONE "
            + "Example: " + COMMAND_FLAG + " "
            + PREFIX_STUDENT_NAME + "John Doe "
            + PREFIX_STUDENT_PHONE + "81234567 "
            + PREFIX_PARENT_NAME + "Mrs Doe "
            + PREFIX_PARENT_PHONE + "91234567 ";

    public static final String MESSAGE_SUCCESS = "New student added: %1$s";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in TutorAid";

    private final Student toAdd;

    /**
     * Creates an AddStudentCommand to add the specified {@code Student}
     */
    public AddStudentCommand(Student student) {
        requireNonNull(student);
        toAdd = student;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasStudent(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        model.addStudent(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddStudentCommand // instanceof handles nulls
                && toAdd.equals(((AddStudentCommand) other).toAdd));
    }
}
