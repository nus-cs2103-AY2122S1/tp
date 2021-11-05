package seedu.address.logic.commands.friends;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.CMD_FRIEND;
import static seedu.address.logic.parser.CliSyntax.FLAG_LIST;

import java.util.function.Predicate;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendIdContainsKeywordPredicate;

/**
 * Lists all persons in the address book to the user.
 */
public class ListFriendCommand extends Command {

    public static final String COMMAND_WORD = "--list";

    public static final String MESSAGE_USAGE = "Format: "
            + CMD_FRIEND + " " + FLAG_LIST + "[KEYWORD]\n"
            + "Example: "
            + CMD_FRIEND + " " + FLAG_LIST + "alice";
    public static final String MESSAGE_SUCCESS_PREPEND = "Listed all friends";
    public static final String MESSAGE_UNKNOWN_PREDICATE = "Unknown search filter entered";

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
            model.updateFilteredAndSortedFriendsList(friendIdContainsKeywordPredicate);
            return new CommandResult(getMessageSuccess(), CommandType.FRIEND_LIST);
        }
        // ListCommand initialized with unknown predicate
        throw new CommandException(MESSAGE_UNKNOWN_PREDICATE);
    }

    /**
     * Returns success message to display after running the list command.
     *
     * @return String containing success message
     */
    public String getMessageSuccess() throws CommandException {
        if (predicate instanceof FriendIdContainsKeywordPredicate) {
            FriendIdContainsKeywordPredicate friendIdContainsKeywordPredicate =
                    (FriendIdContainsKeywordPredicate) predicate;
            String keyword = friendIdContainsKeywordPredicate.getKeyword();
            String messageEnd = keyword.isEmpty()
                    ? ""
                    : " matching filter - KEYWORD: " + keyword;
            return MESSAGE_SUCCESS_PREPEND + messageEnd;
        }
        throw new CommandException(MESSAGE_UNKNOWN_PREDICATE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListFriendCommand // instanceof handles nulls
                && predicate.equals(((ListFriendCommand) other).predicate)); // state check
    }
}
