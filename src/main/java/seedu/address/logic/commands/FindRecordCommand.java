package seedu.address.logic.commands;


import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.residency.exceptions.ResidencyContainsKeywordsPredicate;

/**
 * Finds and lists all past residencies in Trace2Gather whose data contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindRecordCommand extends Command {
    public static final String COMMAND_WORD = "record";

    //TODO add desc
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds the records with the given keywords.";

    private final ResidencyContainsKeywordsPredicate predicate;

    public FindRecordCommand(ResidencyContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredRecordList(predicate);
        return new CommandResult(Messages.MESSAGE_RECORDS_LISTED_OVERVIEW);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindRecordCommand // instanceof handles nulls
                && predicate.equals(((FindRecordCommand) other).predicate)); // state check
    }
}
