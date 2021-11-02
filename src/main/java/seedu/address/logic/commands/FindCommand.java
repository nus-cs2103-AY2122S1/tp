package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.display.DisplayMode.DISPLAY_INVENTORY;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Item;

/**
 * Finds and lists all items in inventory whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_INVENTORY_NOT_DISPLAYED =
            "Can't find outside inventory mode. Please use `list` first";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all items whose description matches any of "
            + "the specified names, ids, or tags, and displays them as a list with index numbers.\n"
            + "\n Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_ID + "ID "
            + PREFIX_TAG + "TAG "
            + "\n Example: " + COMMAND_WORD + " "
            + PREFIX_ID + "019381 or "
            + COMMAND_WORD + " " + PREFIX_NAME + "Banana "
            + PREFIX_NAME + "bread.";

    private final List<Predicate<Item>> predicates;

    /**
     * Creates FindCommand in the case of query by name
     *
     * @param predicates list of predicates that describe the item(s) that the user is finding.
     *                   (list cannot be empty).
     */
    public FindCommand(List<Predicate<Item>> predicates) {
        assert predicates.size() > 0;
        this.predicates = predicates;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.getDisplayMode() != DISPLAY_INVENTORY) {
            throw new CommandException(MESSAGE_INVENTORY_NOT_DISPLAYED);
        }

        Predicate<Item> combinedPredicate = item ->
                predicates.stream().anyMatch(predicate -> predicate.test(item));

        model.updateFilteredItemList(DISPLAY_INVENTORY, combinedPredicate);

        return new CommandResult(
                String.format(Messages.MESSAGE_ITEMS_LISTED_OVERVIEW, model.getFilteredDisplayList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicates.equals(((FindCommand) other).predicates)); // state check
    }

}
