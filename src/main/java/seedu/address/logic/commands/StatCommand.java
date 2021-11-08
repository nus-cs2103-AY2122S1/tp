package seedu.address.logic.commands;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;

public class StatCommand extends Command {
    public static final String COMMAND_WORD = "stat";
    public static final String MESSAGE_SUCCESS = "Showed stats";
    public static final String MESSAGE_FAILURE = "None of the clients have any orders";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        ObservableList<Client> clientList = model.getFilteredClientList();
        Predicate<Client> clientHasOrder = client -> !client.getOrders().isEmpty();

        if (clientList.filtered(clientHasOrder).isEmpty()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        return new CommandResult(MESSAGE_SUCCESS, CommandType.STAT, null, false);
    }
}
