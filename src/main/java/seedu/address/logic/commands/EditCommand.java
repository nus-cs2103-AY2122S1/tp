package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COSTPRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALESPRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITEMS;
import static seedu.address.model.display.DisplayMode.DISPLAY_INVENTORY;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.display.Displayable;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemDescriptor;
import seedu.address.model.item.Name;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing item in the inventory.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the item identified "
            + "by the index number used in the displayed item list.\n"
            + " Parameters: INDEX"
            + " (" + PREFIX_NAME + "NAME)"
            + " (" + PREFIX_ID + "ID) "
            + " (" + PREFIX_COSTPRICE + "COSTPRICE)"
            + " (" + PREFIX_SALESPRICE + "SALESPRICE)"
            + " (" + PREFIX_TAG + "TAG)...\n"
            + " Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ID + "192028 ";

    public static final String MESSAGE_EDIT_ITEM_SUCCESS = "Edited Item: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ITEM = "This item already exists in the inventory.";
    public static final String MESSAGE_COUNT_CNT_BE_EDITED = "Count cannot be directly edited. Please remove/delete "
            + "the item and add it back into the inventory.";
    public static final String MESSAGE_DUPLICATE_ID = "This id clashes with another item in the inventory.";
    public static final String MESSAGE_DUPLICATE_NAME = "This name clashes with another item in the inventory.";
    public static final String MESSAGE_INVENTORY_NOT_DISPLAYED =
            "Can't edit if not in inventory mode. Please use \"list\" first";

    private final Index index;
    private final ItemDescriptor toEditDescriptor;

    /**
     * @param index of the item in the filtered item list to edit
     * @param itemDescriptor details to edit the item with
     */
    public EditCommand(Index index, ItemDescriptor itemDescriptor) {
        requireNonNull(index);
        requireNonNull(itemDescriptor);

        this.index = index;
        this.toEditDescriptor = new ItemDescriptor(itemDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.getDisplayMode() != DISPLAY_INVENTORY) {
            throw new CommandException(MESSAGE_INVENTORY_NOT_DISPLAYED);
        }

        List<Displayable> lastShownList = model.getFilteredDisplayList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
        }

        Item itemToEdit = (Item) lastShownList.get(index.getZeroBased());
        Item editedItem = createEditedItem(itemToEdit, toEditDescriptor);

        if (!itemToEdit.isSameItem(editedItem) && model.hasItem(editedItem)) {
            throw new CommandException(MESSAGE_DUPLICATE_ITEM);
        }
        if (model.hasId(editedItem) && !toEditDescriptor.getId().equals(Optional.empty())) {
            throw new CommandException(MESSAGE_DUPLICATE_ID);
        }
        if (model.hasName(editedItem) && !toEditDescriptor.getName().equals(Optional.empty())) {
            throw new CommandException(MESSAGE_DUPLICATE_NAME);
        }
        if ((!toEditDescriptor.getCount().equals(Optional.empty()))
                && !(toEditDescriptor.getCount().get().equals(itemToEdit.getCount()))) {
            throw new CommandException(MESSAGE_COUNT_CNT_BE_EDITED);
        }
        model.setItem(itemToEdit, editedItem);
        model.updateFilteredDisplayList(DISPLAY_INVENTORY, PREDICATE_SHOW_ALL_ITEMS);
        if ((!toEditDescriptor.getCount().equals(Optional.empty()))) {
            return new CommandResult(String.format(MESSAGE_COUNT_CNT_BE_EDITED, editedItem));
        }
        return new CommandResult(String.format(MESSAGE_EDIT_ITEM_SUCCESS, editedItem));
    }

    /**
     * Creates and returns a {@code Item} with the details of {@code itemToEdit}
     * edited with {@code itemDescriptor}.
     */
    private static Item createEditedItem(Item itemToEdit, ItemDescriptor itemDescriptor) {
        assert itemToEdit != null;

        Name updatedName = itemDescriptor.getName().orElse(itemToEdit.getName());
        Integer updatedId = itemDescriptor.getId().orElse(itemToEdit.getId());
        Integer updatedCount = itemDescriptor.getCount().orElse(itemToEdit.getCount());
        Double updatedCostPrice = itemDescriptor.getCostPrice().orElse(itemToEdit.getCostPrice());
        Double updatedSalesPrice = itemDescriptor.getSalesPrice().orElse(itemToEdit.getSalesPrice());
        Set<Tag> updatedTags = itemDescriptor.getTags().orElse(itemToEdit.getTags());

        return new Item(updatedName, updatedId, updatedCount, updatedTags, updatedCostPrice, updatedSalesPrice);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && toEditDescriptor.equals(e.toEditDescriptor);
    }

}
