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

    public static final String MESSAGE_USAGE = String.format("%1$s %2$s: Adds a student to TutorAid. "
                    + "\nParameters:"
                    + "\n%3$sSTUDENT NAME"
                    + "  [%4$sSTUDENT PHONE]"
                    + "  [%5$sPARENT NAME]"
                    + "  [%6$sPARENT PHONE]"
                    + "\nExample:"
                    + "\n%1$s %2$s %3$sJohn Doe %4$s81234567 %5$sMrs Doe %6$s91234567",
            COMMAND_WORD, COMMAND_FLAG, PREFIX_STUDENT_NAME, PREFIX_STUDENT_PHONE, PREFIX_PARENT_NAME,
            PREFIX_PARENT_PHONE);

    public static final String MESSAGE_SUCCESS = "New student added: %1$s";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in TutorAid.";

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
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.toNameString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddStudentCommand // instanceof handles nulls
                && toAdd.equals(((AddStudentCommand) other).toAdd));
    }
}
