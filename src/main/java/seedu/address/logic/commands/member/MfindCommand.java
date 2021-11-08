package seedu.address.logic.commands.member;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.module.NameContainsKeywordsPredicate;
import seedu.address.model.module.member.Member;

/**
 * Finds and lists all members in Ailurus whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class MfindCommand extends Command {

    public static final String COMMAND_WORD = "mfind";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all members whose names contain any of "
            + "the specified keywords (case-insensitive) and display them as a list of members with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alex bob john";

    private final NameContainsKeywordsPredicate<Member> predicate;

    public MfindCommand(NameContainsKeywordsPredicate<Member> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredMemberList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_MEMBERS_LISTED_OVERVIEW, model.getFilteredMemberList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MfindCommand // instanceof handles nulls
                && predicate.equals(((MfindCommand) other).predicate)); // state check
    }
}
