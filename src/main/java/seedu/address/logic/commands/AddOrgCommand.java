package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.organisation.Organisation;

public class AddOrgCommand extends Command {
    public static final String COMMAND_WORD = "addorg";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an organisation to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_EMAIL + "EMAIL "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Shopee "
            + PREFIX_EMAIL + "shopee@gmail.com ";
    public static final String MESSAGE_SUCCESS = "New organisation added: %1$s";
    public static final String MESSAGE_DUPLICATE_ORGANISATION = "This organisation already exists in the address book";

    private final Organisation toAdd;

    /**
     * Creates an AddOrgCommand to add the specified {@code Organisation}
     */
    public AddOrgCommand(Organisation organisation) {
        requireNonNull(organisation);
        toAdd = organisation;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasOrganisation(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ORGANISATION);
        }
        model.addOrganisation(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}
