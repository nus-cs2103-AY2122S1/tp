package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.table.Table;

/**
 * Adds a set of tables to the address book.
 */
public class SetTablesCommand extends Command {
    public static final String COMMAND_WORD = "settables";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Set the number of tables and size of each table in the restaurant.\n"
            + "Parameters: number of seats in a table [separated by comma] "
            + "(format: <table size>, ... OR <table size>x<number of tables with this size>, ...\n"
            + "Example: " + COMMAND_WORD + " 5, 4, 3x10, 2x5, 1x2";

    public static final String MESSAGE_SUCCESS =
            "Tables set successfully, Restaurant has %1$d table(s) now.\nAll reservations have been deleted.";

    private final List<Integer> tableSizes;

    /**
     * Creates an SetTablesCommand to add the specified {@code Tables}
     */
    public SetTablesCommand(List<Integer> tableSizes) {
        requireNonNull(tableSizes);
        this.tableSizes = tableSizes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.resetTableCount();
        model.resetReservations();
        List<Table> newTableList = new ArrayList<>();
        for (int tableSize : tableSizes) {
            newTableList.add(new Table(tableSize));
        }
        model.setTableList(newTableList);

        return new CommandResult(String.format(MESSAGE_SUCCESS, newTableList.size()),
                false, false, false, false, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SetTablesCommand // instanceof handles nulls
                && tableSizes.equals(((SetTablesCommand) other).tableSizes));
    }
}
