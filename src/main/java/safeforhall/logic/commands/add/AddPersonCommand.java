package safeforhall.logic.commands.add;

import static java.util.Objects.requireNonNull;

import safeforhall.logic.commands.Command;
import safeforhall.logic.commands.CommandResult;
import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.logic.parser.CliSyntax;
import safeforhall.model.Model;
import safeforhall.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddPersonCommand extends Command {

    public static final String COMMAND_WORD = "add";
    public static final String PARAMETERS = "n/NAME r/ROOM p/PHONE e/EMAIL v/VACCINATION_STATUS f/FACULTY "
            + "[fd/LAST_FET_DATE] [cd/LAST_COLLECTION_DATE]";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a resident to the address book.\n"
            + "Parameters: "
            + CliSyntax.PREFIX_NAME + "NAME "
            + CliSyntax.PREFIX_ROOM + "ROOM "
            + CliSyntax.PREFIX_PHONE + "PHONE "
            + CliSyntax.PREFIX_EMAIL + "EMAIL "
            + CliSyntax.PREFIX_VACCSTATUS + "VACCINATION STATUS "
            + CliSyntax.PREFIX_FACULTY + "FACULTY "
            + CliSyntax.PREFIX_FETDATE + "LAST FET DATE "
            + CliSyntax.PREFIX_COLLECTIONDATE + "LAST COLLECTION DATE \n"

            + "Example: " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_NAME + "John Doe "
            + CliSyntax.PREFIX_ROOM + "A100 "
            + CliSyntax.PREFIX_PHONE + "98765432 "
            + CliSyntax.PREFIX_EMAIL + "johnd@example.com "
            + CliSyntax.PREFIX_VACCSTATUS + "T "
            + CliSyntax.PREFIX_FACULTY + "SoC "
            + CliSyntax.PREFIX_FETDATE + "20-10-2021 "
            + CliSyntax.PREFIX_COLLECTIONDATE + "23-10-2021 ";

    public static final String MESSAGE_SUCCESS = "New resident added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This resident or room already exists in the address book";

    private final Person toAdd;

    /**
     * Creates an AddPersonCommand to add the specified {@code Person}
     */
    public AddPersonCommand(Person person) {
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
                || (other instanceof AddPersonCommand // instanceof handles nulls
                && toAdd.equals(((AddPersonCommand) other).toAdd));
    }
}
