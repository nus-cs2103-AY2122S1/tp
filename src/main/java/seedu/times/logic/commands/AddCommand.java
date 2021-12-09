package seedu.times.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.times.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.times.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.times.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.times.logic.parser.CliSyntax.PREFIX_NOK;
import static seedu.times.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.times.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.times.logic.commands.exceptions.CommandException;
import seedu.times.model.Model;
import seedu.times.model.person.Student;
import seedu.times.ui.TabName;

/**
 * Adds a person to the Timestable.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + PREFIX_NOK + " "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "Chemistry "
            + PREFIX_TAG + "Sec 3 \n"
            + PREFIX_NOK + " "
            + PREFIX_NAME + "Jack Doe "
            + PREFIX_PHONE + "10987654 "
            + PREFIX_EMAIL + "jackd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 ";

    public static final String MESSAGE_SUCCESS = "New Student added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Student studentToAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}.
     * We always pass in a new {@code TuitionClass}, and check if it alr exists
     */
    public AddCommand(Student student) {
        requireNonNull(student);
        studentToAdd = student;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasStudent(studentToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addStudent(studentToAdd);

        updateView(TabName.STUDENTS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, studentToAdd));

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && studentToAdd.equals(((AddCommand) other).studentToAdd));
    }
}
