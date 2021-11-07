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
 * Unmark a member in an event identified using it's displayed index from the address book.
 */
public class EunmarkCommand extends Command {

    public static final String COMMAND_WORD = "eunmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unmark members identified by the corresponding index number.\n"
            + "Parameters: "
            + PREFIX_EVENT_INDEX + "EVENT_INDEX (must be a positive integer)"
            + PREFIX_MEMBER_INDEX + "MEMBER_INDEX (must be a positive integer) ["
            + PREFIX_MEMBER_INDEX + "MORE_MEMBER_INDEX]…\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EVENT_INDEX + "1 "
            + PREFIX_MEMBER_INDEX + "1 " + PREFIX_MEMBER_INDEX + "2";

    public static final String MESSAGE_UNMARK_MEMBER_SUCCESS = "Unmarked members: %1$s, Event: %2$s";
    public static final String MESSAGE_MEMBER_NOT_IN_EVENT = "The following member is not in the event: %1$s";

    private final Index eventIndex;
    private final Set<Index> indexSet;
    private final Set<Member> memberSet = new HashSet<>();

    /**
     * Constructor for EunmarkCommand
     * @param index of the event      * @param index of the event in filtered event list to unmark members
     * @param indexList list of member indices to unmark
     */
    public EunmarkCommand(Index index, Set<Index> indexList) {
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

        // marks all members in indexSet as present in event
        for (Index targetIndex : indexSet) {
            int index = targetIndex.getZeroBased();
            if (index >= lastShownMemberList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
            }
            Member memberAsAbsent = lastShownMemberList.get(index);
            if (!selectedEvent.isParticipatingInEvent(memberAsAbsent)) {
                throw new CommandException(String.format(MESSAGE_MEMBER_NOT_IN_EVENT, memberAsAbsent.getName()));
            }
            memberSet.add(memberAsAbsent);
        }

        selectedEvent.markAbsent(memberSet);
        return new CommandResult(String.format(MESSAGE_UNMARK_MEMBER_SUCCESS,
                ParserUtil.memberSetToString(memberSet),
                selectedEvent));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EunmarkCommand // instanceof handles nulls
                && eventIndex.equals(((EunmarkCommand) other).eventIndex)
                && indexSet.equals(((EunmarkCommand) other).indexSet)); // state check
    }
}
