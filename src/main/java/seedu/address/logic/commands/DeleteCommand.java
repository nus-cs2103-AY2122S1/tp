package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;

import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Item;
import seedu.address.model.item.Name;

/**
 * Deletes an item identified using it's displayed index from the inventory.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the item identified by the index number used in the displayed item list.\n"
            + "Parameters: NAME "
            + "Or " + PREFIX_ID + "ID"
            + "[" + PREFIX_COUNT + "COUNT" + "]"
            + "Example: " + COMMAND_WORD + "Apple Pie";

    public static final String MESSAGE_ITEM_ID_NOT_FOUND = "Item name not in inventory: %1$s";
    public static final String MESSAGE_ITEM_NAME_NOT_FOUND = "Item id not in inventory: %1$s";
    public static final String MESSAGE_DELETE_ITEM_SUCCESS = "Deleted Item: %1$s";


    private final Optional<Name> name;
    private final Optional<String> id;
    private final int count; //value == -1 if to delete all of specified item

    /**
     * @param name of the item in the filtered item list to delete
     * @param count number of the specified item to delete (Let value = -1 if deleting all)
     */
    public DeleteCommand(Name name, int count) {
        this.name = Optional.of(name);
        this.id = Optional.empty();
        this.count = count;
    }

    /**
     * @param id of the item in the filtered item list to delete
     * @param count number of the specified item to delete (Let value = -1 if deleting all)
     */
    public DeleteCommand(String id, int count) {
        this.name = Optional.empty();
        this.id = Optional.of(id);
        this.count = count;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (name.isPresent() && !model.hasItem(name.get())) {
            throw new CommandException(String.format(MESSAGE_ITEM_NAME_NOT_FOUND, name.get()));
        } else if (id.isPresent() && !model.hasItem(id.get())) {
            throw new CommandException(String.format(MESSAGE_ITEM_ID_NOT_FOUND, id.get()));
        }

        Item deletedItem;
        if (name.isPresent()) {
            deletedItem = model.deleteItem(name.get(), count);
        } else {
            deletedItem = model.deleteItem(id.get(), count);
        }

        return new CommandResult(String.format(MESSAGE_DELETE_ITEM_SUCCESS, deletedItem));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherCommand = (DeleteCommand) other;

        return name.equals(otherCommand.name)
                && id.equals(otherCommand.id)
                && count == otherCommand.count;
    }
}
