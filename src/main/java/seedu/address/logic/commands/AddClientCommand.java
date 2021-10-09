package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE_NUMBER;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.client.PhoneNumber;
import seedu.address.model.commons.Name;

public class AddClientCommand extends Command {
    public static final String COMMAND_WORD = "add -c";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Adds a client to the address book. "
                    + "Parameters: "
                    + "NAME "
                    + PREFIX_PHONE_NUMBER + "PHONE_NUMBER \n"
                    + "Example: " + COMMAND_WORD + " "
                    + "John Doe "
                    + PREFIX_PHONE_NUMBER + "98765432";

    public static final String MESSAGE_SUCCESS = "New client added: %1$s";
    public static final String MESSAGE_DUPLICATE_CLIENT = "This client already exists in Sellah";

    private AddClientDescriptor addClientDescriptor;
    private Client clientToAdd;

    /**
     * Constructor of the class `AddClientCommand`.
     *
     * @param addClientDescriptor A descriptor containing the information of a client.
     */
    public AddClientCommand(AddClientDescriptor addClientDescriptor) {
        requireNonNull(addClientDescriptor);
        this.addClientDescriptor = addClientDescriptor;
        this.clientToAdd = createAddedClient(addClientDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasClient(clientToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLIENT);
        }

        model.addClient(clientToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, clientToAdd));
    }

    /**
     * Creates a Client from an AddClientDescriptor.
     *
     * @param addClientDescriptor A descriptor that contains the client's information.
     * @return The client to be added.
     */
    private static Client createAddedClient(AddClientDescriptor addClientDescriptor) {
        Name name = addClientDescriptor.getName();
        PhoneNumber phoneNumber = addClientDescriptor.getPhoneNumber();
        return new Client(name, phoneNumber);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddClientCommand // instanceof handles nulls
                && clientToAdd.equals(((AddClientCommand) other).clientToAdd));
    }

    /**
     * Stores the details of the new client.
     */
    public static class AddClientDescriptor {
        private Name name;
        private PhoneNumber phoneNumber;

        /**
         * Constructor of the class `AddClientDescriptor`.
         *
         * @param name Name of the client.
         */
        public AddClientDescriptor(Name name, PhoneNumber phoneNumber) {
            this.name = name;
            this.phoneNumber = phoneNumber;
        }

        /**
         * Gets the name of a client.
         *
         * @return The name of a client.
         */
        public Name getName() {
            return name;
        }

        public PhoneNumber getPhoneNumber() {
            return phoneNumber;
        }
    }
}
