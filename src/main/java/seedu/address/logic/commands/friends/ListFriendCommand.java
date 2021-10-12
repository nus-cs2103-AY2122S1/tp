package seedu.address.logic.commands.friends;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendIdContainsKeywordPredicate;

/**
 * Lists all persons in the address book to the user.
 */
public class ListFriendCommand extends Command {

    public static final String COMMAND_WORD = "--list";

    public static final String MESSAGE_SUCCESS_PREPEND = "Listed all %s";

    public static final String FRIEND_LIST = "friends";

    public static final String MESSAGE_UNKNOWN_PREDICATE = "Unknown search filter entered";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all friends/games whose id contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: --friend/--game KEYWORD \n"
            + "Example: " + COMMAND_WORD + "--friend alice";

    private final Predicate<Friend> predicate;

    public ListFriendCommand(Predicate<Friend> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (predicate instanceof FriendIdContainsKeywordPredicate) {
            FriendIdContainsKeywordPredicate friendIdContainsKeywordPredicate =
                    (FriendIdContainsKeywordPredicate) predicate;
            model.updateFilteredFriendsList(friendIdContainsKeywordPredicate);
            return new CommandResult(getMessageSuccess());
        }
        // ListCommand initialized with unknown predicate
        throw new CommandException(MESSAGE_UNKNOWN_PREDICATE);
    }

    /**
     * Returns success message to display after running list
     *
     * @return String containing success message
     */
    public String getMessageSuccess() {
        if (predicate instanceof FriendIdContainsKeywordPredicate) {
            FriendIdContainsKeywordPredicate friendIdContainsKeywordPredicate =
                    (FriendIdContainsKeywordPredicate) predicate;
            String keyword = friendIdContainsKeywordPredicate.getKeyword();
            String messageEnd = keyword.isEmpty()
                    ? ""
                    : " whose id contains the keyword: " + keyword;
            return String.format(MESSAGE_SUCCESS_PREPEND, FRIEND_LIST) + messageEnd;
        }
        return String.format(MESSAGE_SUCCESS_PREPEND, FRIEND_LIST);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListFriendCommand // instanceof handles nulls
                && predicate.equals(((ListFriendCommand) other).predicate)); // state check
    }
}
