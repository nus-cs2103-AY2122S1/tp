package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Item;
import seedu.address.model.item.Name;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_ID + "ID "
            + PREFIX_COUNT + "COUNT "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Banana Bread "
            + PREFIX_ID + "#019381 "
            + PREFIX_COUNT + "10 "
            + PREFIX_TAG + "baked "
            + PREFIX_TAG + "popular";

    public static final String MESSAGE_SUCCESS = "New item added: %1$s";
    public static final String MESSAGE_SUCCESS_REPLENISH = "Item replenished: %1$s";
    public static final String MESSAGE_INCOMPLETE_INFO = "For first time adding, please provide both name and id";

    private final Item toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Item item) {
        requireNonNull(item);
        toAdd = item;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasItem(toAdd)) {
            Item inInventory = toAdd.getId() == "999999"
                    ? model.getItemWithName(toAdd.getName().toString())
                    : model.getItemWithId(toAdd.getId());
            inInventory.replenishItem(toAdd.getCount());
            model.setItem(inInventory, inInventory);

            return new CommandResult(String.format(MESSAGE_SUCCESS_REPLENISH, inInventory));
        }

        if (toAdd.getId() == "999999" || toAdd.getName().equals(new Name("dummy name"))) {
            throw new CommandException(MESSAGE_INCOMPLETE_INFO);
        }

        model.addItem(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
