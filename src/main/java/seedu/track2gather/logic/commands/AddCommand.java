package seedu.track2gather.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.track2gather.commons.core.Messages.MESSAGE_PREDICATE_SHOW_ALL_PERSONS;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_CASE_NUMBER;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_HOME_ADDRESS;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_NEXT_OF_KIN_ADDRESS;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_NEXT_OF_KIN_NAME;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_NEXT_OF_KIN_PHONE;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_QUARANTINE_ADDRESS;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_SHN_PERIOD;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_WORK_ADDRESS;

import seedu.track2gather.logic.commands.exceptions.CommandException;
import seedu.track2gather.model.Model;
import seedu.track2gather.model.person.Person;

/**
 * Adds a person to the persons list for tracking.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the persons list for tracking. \n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE_NUMBER "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_CASE_NUMBER + "CASE_NUMBER "
            + PREFIX_HOME_ADDRESS + "HOME_ADDRESS "
            + "[" + PREFIX_WORK_ADDRESS + "WORK_ADDRESS] "
            + "[" + PREFIX_QUARANTINE_ADDRESS + "QUARANTINE_ADDRESS] "
            + "[" + PREFIX_SHN_PERIOD + "SHN_PERIOD] "
            + "[" + PREFIX_NEXT_OF_KIN_NAME + "NEXT_OF_KIN_NAME] "
            + "[" + PREFIX_NEXT_OF_KIN_PHONE + "NEXT_OF_KIN_PHONE] "
            + "[" + PREFIX_NEXT_OF_KIN_ADDRESS + "NEXT_OF_KIN_ADDRESS] \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_CASE_NUMBER + "123 "
            + PREFIX_HOME_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_SHN_PERIOD + "2021-10-23 2021-10-30 "
            + PREFIX_NEXT_OF_KIN_NAME + "Bob Doe";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s\n" + MESSAGE_PREDICATE_SHOW_ALL_PERSONS;
    public static final String MESSAGE_DUPLICATE_PERSON = "This case number already exists in the contacts list.";

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
}
