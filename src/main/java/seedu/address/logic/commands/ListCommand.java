package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.friend.FriendIdContainsKeywordPredicate;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    private final Predicate predicate;

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all";

    public static final String MESSAGE_UNKNOWN_PREDICATE = "Unknown search filter entered";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all friends/games whose id contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: --friend/--game KEYWORD \n"
            + "Example: " + COMMAND_WORD + "--friend alice";

    public ListCommand(Predicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateFilteredFriendsList(predicate);
        if (predicate instanceof FriendIdContainsKeywordPredicate) {
            String keyword = ((FriendIdContainsKeywordPredicate) predicate).getKeyword();
            String messageEnd = keyword.isEmpty()
                    ? " friends"
                    : " friends whose id contains the keyword: " + keyword;
            return new CommandResult(MESSAGE_SUCCESS +  messageEnd);
        }
        // ListCommand initialized with unknown predicate
        throw new CommandException(MESSAGE_UNKNOWN_PREDICATE);
    }
}
