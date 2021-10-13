package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACAD_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACAD_STREAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENT_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_ACTION = "Add Student";

    public static final String COMMAND_WORD = "add";

    public static final String COMMAND_PARAMETERS = PREFIX_NAME + "NAME "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_PARENT_PHONE + "PARENT_PHONE] "
            + "[" + PREFIX_PARENT_EMAIL + "PARENT_EMAIL] "
            + "[" + PREFIX_SCHOOL + "SCHOOL] "
            + "[" + PREFIX_ACAD_STREAM + "ACAD_STREAM] "
            + "[" + PREFIX_ACAD_LEVEL + "ACAD_LVL] "
            + "[" + PREFIX_FEE + "FEE] "
            + "[" + PREFIX_REMARK + "REMARK] "
            + "[" + PREFIX_TAG + "TAG]...";

    public static final String COMMAND_FORMAT = COMMAND_WORD + " " + COMMAND_PARAMETERS;

    public static final String COMMAND_EXAMPLE = COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_PARENT_PHONE + "91234567 "
            + PREFIX_PARENT_EMAIL + "jackd@example.com "
            + PREFIX_SCHOOL + "Nan Chiau High School "
            + PREFIX_ACAD_STREAM + "Express "
            + PREFIX_ACAD_LEVEL + "S1 "
            + PREFIX_FEE + "50 "
            + PREFIX_REMARK + "He owes me a dinner! "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "neighbour";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student to TAB.\n"
            + "Parameters: " + COMMAND_PARAMETERS + "\n"
            + "Note: at least one contact field must be present. \n"
            + "Example: " + COMMAND_EXAMPLE;

    public static final String USER_TIP = "Try adding a student using: \n"
            + COMMAND_WORD + " " + COMMAND_PARAMETERS + "\n"
            + "Example: " + COMMAND_EXAMPLE;

    public static final String MESSAGE_SUCCESS = "New student added: %1$s";
    public static final String MESSAGE_CONTACT_REQUIRED = "This student must have at least one contact field!";
    public static final String MESSAGE_DUPLICATE_PERSON = "This student already exists in TAB!";

    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
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
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }

    @Override
    public String toString() {
        return "Add Command: " + toAdd;
    }
}
