package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.organisation.Organisation;

/**
 * Adds a person to the address book.
 */
public class AddOrganisationCommand extends Command {

    public static final String COMMAND_WORD = "addorg";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an organisation. "
            + "Parameters: "
            + PREFIX_NAME + "NAME \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Facebook ";

    public static final String MESSAGE_SUCCESS = "New organisation added: %1$s";
    public static final String MESSAGE_DUPLICATE_ORGANISATION = "This organisation already exists";

    private final Organisation toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddOrganisationCommand(Organisation organisation) {
        requireNonNull(organisation);
        toAdd = organisation;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

//        if (model.hasPerson(toAdd)) {
//            throw new CommandException(MESSAGE_DUPLICATE_ORGANISATION);
//        }
//
//        model.addPerson(toAdd);
//        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddOrganisationCommand // instanceof handles nulls
                && toAdd.equals(((AddOrganisationCommand) other).toAdd));
    }
}
