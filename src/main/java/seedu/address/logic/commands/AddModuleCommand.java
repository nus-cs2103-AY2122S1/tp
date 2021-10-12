package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELE_HANDLE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.student.Student;

/**
 * Adds a student to the address book.
 */
public class AddModuleCommand extends Command {

    public static final String COMMAND_WORD = "add module";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student to the address book. "
            + "Parameters: "
            + PREFIX_STUDENT_ID + "STUDENT ID "
            + PREFIX_NAME + "NAME "
            + PREFIX_TELE_HANDLE + "TELE HANDLE "
            + PREFIX_EMAIL + "EMAIL\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STUDENT_ID + "A1234567A "
            + PREFIX_NAME + "John Doe "
            + PREFIX_TELE_HANDLE + "@johndoe "
            + PREFIX_EMAIL + "johnd@example.com ";

    public static final String MESSAGE_SUCCESS = "New student added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This student already exists in the address book";

    private final Student toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddModuleCommand(Student student) {
        requireNonNull(student);
        toAdd = student;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        //        requireNonNull(model);
        //
        //        if (model.hasStudent(toAdd)) {
        //            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        //        }
        //
        //        model.addStudent(toAdd);
        //        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        return null; //to be edited
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddModuleCommand // instanceof handles nulls
                && toAdd.equals(((AddModuleCommand) other).toAdd));
    }
}
