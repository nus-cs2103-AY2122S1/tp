package seedu.tuitione.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.tuitione.model.student.Student.MAX_REMARK_SIZE;

import seedu.tuitione.logic.commands.exceptions.CommandException;
import seedu.tuitione.model.Model;
import seedu.tuitione.model.student.Student;

/**
 * Adds a student to the tuitione book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = "Command: "
            + COMMAND_WORD + "\nAdds a student to the tuitione book.\n\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PARENT_CONTACT "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_GRADE + "GRADE "
            + "[" + PREFIX_REMARK + "REMARK]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_GRADE + "S3 "
            + PREFIX_REMARK + "overdue";

    public static final String MESSAGE_SUCCESS = "✔\tSuccess:\n\nNew student added:\n%1$s";
    public static final String MESSAGE_DUPLICATE_STUDENT =
            "⚠\tAlert:\n\nThis student already exists in the TuitiONE book.";
    public static final String MESSAGE_T00_MANY_REMARKS =
            "⚠\tAlert:\n\nYou can only tag 5 remarks to a student!";

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

        if (toAdd.getRemarks().size() > MAX_REMARK_SIZE) {
            throw new CommandException(MESSAGE_T00_MANY_REMARKS);
        }

        model.addStudent(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
