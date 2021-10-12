package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.data.event.Event;
import seedu.address.model.data.member.Member;

import java.util.List;
import java.util.Set;

import static java.util.Objects.requireNonNull;

/**
 * Lists all members in Ailurus who are in the event.
 */
public class ElistmCommand extends Command {

    public static final String COMMAND_WORD = "elistm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all members in Ailurus who are in the event.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Participants of following event listed: %1$s.\n"
            + "No. of Participants: %2$s";

    private final Index targetIndex;

    public ElistmCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> lastShownList = model.getFilteredEventList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Event eventToList = lastShownList.get(targetIndex.getZeroBased());
        Set<Member> memberList = eventToList.getParticipants();
        model.updateFilteredMemberList(member -> memberList.contains(member));

        return new CommandResult(String.format(MESSAGE_SUCCESS, eventToList, model.getFilteredMemberList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ElistmCommand // instanceof handles nulls
                && targetIndex.equals(((ElistmCommand) other).targetIndex)); // state check
    }
}
