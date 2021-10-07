package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENT_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_PHONE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddStudentCommand extends Command {

    public static final String COMMAND_WORD = "add -s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student to TutorAid. "
            + "Parameters: "
            + PREFIX_STUDENT_NAME + "STUDENT NAME "
            + PREFIX_STUDENT_PHONE + "STUDENT PHONE "
            + PREFIX_PARENT_NAME + "STUDENT NAME "
            + PREFIX_PARENT_PHONE + "STUDENT PHONE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STUDENT_NAME + "John Doe "
            + PREFIX_STUDENT_PHONE + "81234567 "
            + PREFIX_PARENT_NAME + "Mrs Doe "
            + PREFIX_PARENT_PHONE + "91234567 ";

    public static final String MESSAGE_SUCCESS = "New student added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This student already exists in TutorAid";

    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddStudentCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddStudentCommand // instanceof handles nulls
                && toAdd.equals(((AddStudentCommand) other).toAdd));
    }
}
