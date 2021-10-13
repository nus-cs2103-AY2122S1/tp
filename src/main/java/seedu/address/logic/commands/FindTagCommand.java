package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.PersonTagsContainsTagsPredicate;

import static java.util.Objects.requireNonNull;

public abstract class FindTagCommand extends Command {
    PersonTagsContainsTagsPredicate predicate;

    public FindTagCommand(PersonTagsContainsTagsPredicate predicate) {
        this.predicate = predicate;
    }

    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }
}
