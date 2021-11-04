package seedu.fast.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.logging.Logger;

import seedu.fast.commons.core.LogsCenter;
import seedu.fast.logic.commands.exceptions.CommandException;
import seedu.fast.model.Model;
import seedu.fast.model.person.Person;



/**
 * Adds a person to FAST.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a client to FAST. \n\n"
            + "Parameters: \n"
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n\n"
            + "Example: \n" + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in FAST";

    private final Logger logger = LogsCenter.getLogger(getClass());

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

        boolean isDuplicatedPerson = duplicatedPersonChecker(model, toAdd);
        if (isDuplicatedPerson) {
            logger.warning("-----Invalid Add Command: Duplicated Client-----");
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        logger.info("-----Add Command: Client added successfully-----");

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    /**
     * Checks if the current model contains the {@code Person} to be checked.
     *
     * @param model The current model for FAST.
     * @param person The person to be checked.
     * @return A boolean indicating whether the person to be checked already exist in the model.
     */
    private boolean duplicatedPersonChecker(Model model, Person person) {
        return model.hasPerson(person);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
