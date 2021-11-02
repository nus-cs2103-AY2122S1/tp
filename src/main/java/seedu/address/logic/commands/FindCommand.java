package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.display.DisplayMode.DISPLAY_INVENTORY;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.IdContainsNumberPredicate;
import seedu.address.model.item.NameContainsKeywordsPredicate;
import seedu.address.model.item.TagContainsKeywordsPredicate;

/**
 * Finds and lists all items in inventory whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_INVENTORY_NOT_DISPLAYED =
            "Can't find outside inventory mode. Please use `list` first";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all items whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Able to find multiple names and ids with one command by putting multiple flags"
            + "\n Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_ID + "ID "
            + PREFIX_TAG + "TAG "
            + "\n Example: " + COMMAND_WORD + " "
            + PREFIX_ID + "019381 or "
            + COMMAND_WORD + " " + PREFIX_NAME + "Banana "
            + PREFIX_NAME + "bread.";

    private final NameContainsKeywordsPredicate namePredicate;
    private final IdContainsNumberPredicate idPredicate;
    private final TagContainsKeywordsPredicate tagPredicate;

    /**
     * Creates FindCommand in the case of query by name
     *
     * @param namePredicate name of the item that the user is finding
     */
    public FindCommand(NameContainsKeywordsPredicate namePredicate) {
        this.namePredicate = namePredicate;
        this.idPredicate = null;
        this.tagPredicate = null;
    }

    /**
     * Creates FindCommand in the case of query by id
     *
     * @param idPredicate id of the item that the user is finding
     */
    public FindCommand(IdContainsNumberPredicate idPredicate) {
        this.idPredicate = idPredicate;
        this.namePredicate = null;
        this.tagPredicate = null;
    }

    /**
     * Creates FindCommand in the case of query by tag
     *
     * @param tagPredicate tag of the item that the user is finding
     */
    public FindCommand(TagContainsKeywordsPredicate tagPredicate) {
        this.idPredicate = null;
        this.namePredicate = null;
        this.tagPredicate = tagPredicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.getDisplayMode() != DISPLAY_INVENTORY) {
            throw new CommandException(MESSAGE_INVENTORY_NOT_DISPLAYED);
        }

        if (namePredicate != null) {
            model.updateFilteredItemList(DISPLAY_INVENTORY, namePredicate);
        }
        if (idPredicate != null) {
            model.updateFilteredItemList(DISPLAY_INVENTORY, idPredicate);
        }
        if (tagPredicate != null) {
            model.updateFilteredItemList(DISPLAY_INVENTORY, tagPredicate);
        }

        return new CommandResult(
                String.format(Messages.MESSAGE_ITEMS_LISTED_OVERVIEW, model.getFilteredDisplayList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (idPredicate != null) {
            return other == this // short circuit if same object
                    || (other instanceof FindCommand // instanceof handles nulls
                    && idPredicate.equals(((FindCommand) other).idPredicate)); // state check
        }
        if (namePredicate != null) {
            return other == this // short circuit if same object
                    || (other instanceof FindCommand // instanceof handles nulls
                    && namePredicate.equals(((FindCommand) other).namePredicate)); // state check
        } else {
            return other == this // short circuit if same object
                    || (other instanceof FindCommand // instanceof handles nulls
                    && tagPredicate.equals(((FindCommand) other).tagPredicate));
        }
    }

}
