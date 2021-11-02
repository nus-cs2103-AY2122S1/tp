package seedu.address.logic.commands;

import java.util.ArrayList;

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
        ObservableList<Client> clients = model.getFilteredClientList();
        ArrayList<Client> clientsWithOrders = new ArrayList<>();
        for (Client client : clients) {
            if (!client.getOrders().isEmpty()) {
                clientsWithOrders.add(client);
            }
        }
        if (clientsWithOrders.isEmpty()) {
            throw new CommandException(MESSAGE_FAILURE);
        }
        return new CommandResult(MESSAGE_SUCCESS, CommandType.STAT, null, false);
    }
}
