package seedu.address.logic.commands.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELE_HANDLE;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a person to contHACKS.
 */
public class AddPersonCommand extends Command {

    public static final String MESSAGE_USAGE = "add: Adds a person to contHACKS.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_MODULE_CODE + "MODULE_CODE [LESSON_CODE(S)] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_TELE_HANDLE + "TELEGRAM_HANDLE] "
            + "[" + PREFIX_REMARK + "REMARK]\n"
            + "Example: add "
            + PREFIX_NAME + "Ben "
            + PREFIX_EMAIL + "ben123@gmail.com "
            + PREFIX_MODULE_CODE + "CS2103T T12 "
            + PREFIX_MODULE_CODE + "CS2100 T11 B05 "
            + PREFIX_PHONE + "91238456 "
            + PREFIX_TELE_HANDLE + "@BenIsHere "
            + PREFIX_REMARK + "Overseas\n";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON =
            "Warning: A person with similar identity already exist.\n"
            + "Nonetheless, new person added: %1$s";
    public static final String MESSAGE_SAME_PERSON =
            "A person with exactly the same name and email already exists in contHACKS";

    private final Person toAdd;

    /**
     * Creates an AddPersonCommand to add the specified {@code Person}.
     */
    public AddPersonCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_SAME_PERSON);
        }

        CommandResult result;
        if (model.hasSimilarPerson(toAdd)) {
            result = new CommandResult(String.format(MESSAGE_DUPLICATE_PERSON, toAdd));
        } else {
            result = new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        }

        model.addPerson(toAdd);
        return result;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPersonCommand // instanceof handles nulls
                && toAdd.equals(((AddPersonCommand) other).toAdd));
    }
}
