package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER_INDEX;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.Model;
import seedu.address.model.module.event.Event;
import seedu.address.model.module.member.Member;

/**
 * Removes a member to an event identified using it's displayed index from the Ailurus.
 */
public class EmdelCommand extends Command {

    public static final String COMMAND_WORD = "emdel";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Remove members from event identified by the corresponding index number.\n"
            + "Parameters: "
            + PREFIX_EVENT_INDEX + "EVENT_INDEX (must be a positive integer) "
            + PREFIX_MEMBER_INDEX + "MEMBER_INDEX (must be a positive integer) ["
            + PREFIX_MEMBER_INDEX + "MORE_MEMBER_INDEX]…\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EVENT_INDEX + "1 "
            + PREFIX_MEMBER_INDEX + "1 " + PREFIX_MEMBER_INDEX + "2";

    public static final String MESSAGE_DELETE_MEMBER_SUCCESS = "Removed members: %1$s. Event: %2$s. ";
    public static final String MESSAGE_MEMBER_NOT_IN_EVENT = "The following member is not in the event: %1$s";

    private final Index eventIndex;
    private final Set<Index> indexSet;
    private final Set<Member> memberSet = new HashSet<>();

    /**
     * Constructor for EmdelCommand
     * @param index of the event in filtered event list to delete members
     * @param indexList list of member indices to delete
     */
    public EmdelCommand(Index index, Set<Index> indexList) {
        requireAllNonNull(index, indexList);
        this.eventIndex = index;
        this.indexSet = indexList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> lastShownEventList = model.getFilteredEventList();
        List<Member> lastShownMemberList = model.getFilteredMemberList();
        if (eventIndex.getZeroBased() >= lastShownEventList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Event selectedEvent = lastShownEventList.get(eventIndex.getZeroBased());

        // adds all members in indexSet to event
        for (Index targetIndex : indexSet) {
            int index = targetIndex.getZeroBased();
            if (index >= lastShownMemberList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
            }
            Member memberToAdd = lastShownMemberList.get(index);
            if (!selectedEvent.isParticipatingInEvent(memberToAdd)) {
                throw new CommandException(String.format(MESSAGE_MEMBER_NOT_IN_EVENT, memberToAdd.getName()));
            }
            memberSet.add(memberToAdd);
        }

        selectedEvent.removeParticipants(memberSet);
        return new CommandResult(String.format(MESSAGE_DELETE_MEMBER_SUCCESS,
                ParserUtil.memberSetToString(memberSet),
                selectedEvent));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmdelCommand // instanceof handles nulls
                && eventIndex.equals(((EmdelCommand) other).eventIndex)
                && indexSet.equals(((EmdelCommand) other).indexSet)); // state check
    }
}
