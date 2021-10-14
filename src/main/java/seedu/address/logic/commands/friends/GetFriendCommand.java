package seedu.address.logic.commands.friends;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.friend.FriendIdMatchesKeywordPredicate;

/**
 * Gets and displays the complete information of a friend from the address book whose FRIEND_ID matches the given
 * argument keywords.
 * Keyword matching is case-insensitive.
 */
public class GetFriendCommand extends Command {

    public static final String COMMAND_WORD = "--get";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Gets all the information about a friend whose "
            + "FRIEND_ID matches the given keyword(s) exactly (case-insensitive) and displays them in a "
            + "easy-to-read format.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " Draco";
    public static final String MESSAGE_FRIEND_FULL_INFORMATION = "Showing the complete information of %1$s";

    private final FriendIdMatchesKeywordPredicate predicate;

    public GetFriendCommand(FriendIdMatchesKeywordPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredFriendsList(predicate);

        if (model.getFilteredFriendsList().size() == 0) {
            throw new CommandException(
                    String.format(Messages.MESSAGE_INVALID_FRIEND_ID, predicate.getKeyword()));
        }

        return new CommandResult(
                String.format(MESSAGE_FRIEND_FULL_INFORMATION, predicate.getKeyword()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GetFriendCommand // instanceof handles nulls
                && predicate.equals(((GetFriendCommand) other).predicate)); // state check
    }
}
