package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM_HANDLE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;


/**
 * Adds a student to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student to the CSBook. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_TELEGRAM_HANDLE + "TELEGRAM HANDLE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_GROUP_NAME + "GROUPNAME "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_TELEGRAM_HANDLE + "@john_doe "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_GROUP_NAME + "CS2103T ";

    public static final String MESSAGE_SUCCESS = "New student added: %1$s";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in the address book";
    public static final String MESSAGE_GROUP_NONEXISTENT =
            "The group indicated does not exist. Please create it first.";

    private final Student toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Student}
     */
    public AddCommand(Student student) {
        requireNonNull(student);
        toAdd = student;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasStudent(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        // Check that given groupName can identify an existing group in the model
        if (!model.hasGroup(toAdd.getGroupName())) {
            throw new CommandException(MESSAGE_GROUP_NONEXISTENT);
        }

        Student studentToAdd =
                new Student(toAdd.getName(),
                        toAdd.getTelegramHandle(), toAdd.getEmail(), toAdd.getNote(), toAdd.getGroupName());

        // Add student with the group fetched from the data in the model
        model.addStudent(studentToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
