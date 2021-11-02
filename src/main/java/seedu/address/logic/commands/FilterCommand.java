package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.contact.CategoryCode;
import seedu.address.model.contact.IsFilterablePredicate;
import seedu.address.model.contact.Rating;
import seedu.address.model.summary.Summary;
import seedu.address.model.tag.Tag;

/**
 * Filters contacts in the address book by category.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all contacts by category, rating or tag"
            + "and displays them as a list with index numbers.\n"
            + "Parameters: [" + PREFIX_CATEGORY_CODE + "CATEGORY_CODE] "
            + "[" + PREFIX_RATING + "RATING] "
            + "[" + PREFIX_TAG + "TAG] \n"
            + "Example: " + COMMAND_WORD + " c/att" + " ra/5" + " t/outdoor";

    private final Set<CategoryCode> categoryCodes;
    private final Rating rating;
    private final Set<Tag> tags;
    private final IsFilterablePredicate predicate;

    /**
     * constructor for FilterCommand
     * @param categoryCodes types of contacts to be filtered
     */
    public FilterCommand(Set<CategoryCode> categoryCodes, Rating rating, Set<Tag> tags) {
        requireNonNull(categoryCodes);
        this.categoryCodes = categoryCodes;
        this.rating = rating;
        this.tags = tags;
        this.predicate = new IsFilterablePredicate(categoryCodes, rating, tags);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredContactList(predicate);
        Summary summary = new Summary(model.getAddressBook());
        return new CommandResult(
                String.format(Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW,
                        model.getFilteredContactList().size()), summary);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterCommand)) {
            return false;
        }

        // state check
        FilterCommand e = (FilterCommand) other;
        return categoryCodes.equals(e.categoryCodes) && rating.equals(e.rating) && tags.equals(e.tags);
    }
}
