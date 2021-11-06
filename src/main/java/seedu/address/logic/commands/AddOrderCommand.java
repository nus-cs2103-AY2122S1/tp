package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CUSTOMER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LABEL;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.Order;

public class AddOrderCommand extends Command {

    public static final String COMMAND_WORD = "addorder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an order to the application. "
            + "Parameters: "
            + PREFIX_LABEL + "LABEL "
            + PREFIX_CUSTOMER + "CUSTOMER "
            + PREFIX_AMOUNT + "AMOUNT "
            + PREFIX_DATE + "DATE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_LABEL + "School uniform "
            + PREFIX_CUSTOMER + "Alice "
            + PREFIX_AMOUNT + "10.90 "
            + PREFIX_DATE + "20 August 2021";

    public static final String MESSAGE_SUCCESS = "New order added: %1$s";
    public static final String MESSAGE_DUPLICATE_ORDER = "This order already exists in the orderbook.";
    public static final String MESSAGE_CLIENT_NOT_FOUND = "The client for this order has not been added to SalesNote!";

    private final Order toAdd;

    /**
     * Creates an AddTaskCommand to add the specified {@code Task}
     */
    public AddOrderCommand(Order order) {
        requireNonNull(order);
        toAdd = order;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // check if model already contains order
        if (model.hasOrder(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ORDER);
        }

        if (!model.hasPersonWithName(toAdd.getCustomer().getName())) {
            throw new CommandException(MESSAGE_CLIENT_NOT_FOUND);
        }

        model.addOrder(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), CommandResult.DisplayState.ORDER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AddOrderCommand
                && toAdd.isSameOrder(((AddOrderCommand) other).toAdd));
    }

}
