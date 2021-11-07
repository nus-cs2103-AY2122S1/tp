package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NameEqualKeywordPredicate;
import seedu.address.model.person.Person;

/**
 * Finds and displays the details of person whose name matches the keyword exactly.
 * Keyword matching is exact and case sensitive.
 */
public class DetailsCommand extends Command {

    public static final String COMMAND_WORD = "details";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays the details of a member from the "
            + "specified keywords (case-sensitive and exact match of name) or index (must be a positive integer).\n"
            + "Parameters: NAME (case-sensitive) or INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " Xiao Ming";

    private NameEqualKeywordPredicate predicate;
    private Index targetIndex;

    /**
     * Creates a DetailsCommand to display details.
     *
     * @param predicate The predicate matching name of Person to find.
     */
    public DetailsCommand(NameEqualKeywordPredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    /**
     * Creates a DetailsCommand to display details.
     *
     * @param targetIndex The index of the Person in the list.
     */
    public DetailsCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        createPredicateFromIndex(model);
        return executePredicate(model);
    }

    private void createPredicateFromIndex(Model model) throws CommandException {
        requireNonNull(model);
        if (targetIndex != null) {
            // slight violations of law of demeter, this is consistent with DeleteCommand
            List<Person> lastShownList = model.getFilteredPersonList();
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Person personToDisplay = lastShownList.get(targetIndex.getZeroBased());
            predicate = new NameEqualKeywordPredicate(personToDisplay.getName());
        }
    }

    private CommandResult executePredicate(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        assert model.getFilteredPersonList().size() < 2;
        if (model.getFilteredPersonList().size() == 0) {
            return new CommandResult(
                    String.format(Messages.MESSAGE_PERSON_DETAILS_NOT_FOUND, predicate),
                    false, false, true);
        } else {
            return new CommandResult(
                    String.format(Messages.MESSAGE_PERSON_DETAILS_FOUND, predicate),
                    false, false, true);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof DetailsCommand)) {
            return false;
        }
        DetailsCommand o = (DetailsCommand) other;
        if (predicate == null) {
            return o.predicate == null && targetIndex.equals(o.targetIndex);
        }
        if (targetIndex == null) {
            return o.targetIndex == null && predicate.equals(o.predicate);
        }
        return targetIndex.equals(o.targetIndex) && predicate.equals(o.predicate);
    }
}
