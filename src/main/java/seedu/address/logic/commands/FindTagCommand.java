package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.PersonTagsContainsTagsPredicate;

public abstract class FindTagCommand extends Command {
    private PersonTagsContainsTagsPredicate predicate;

    public FindTagCommand(PersonTagsContainsTagsPredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Executes FindTag to find the people with the matching tags.
     *
     * @param model {@code Model} which the command should operate on.
     * @return result of FindTagCommand
     */
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    public PersonTagsContainsTagsPredicate getPredicate() {
        return this.predicate;
    }
}
