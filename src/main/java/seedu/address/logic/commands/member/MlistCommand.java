package seedu.address.logic.commands.member;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ABSENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTEND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_INDEX;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEMBERS;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.event.Event;
import seedu.address.model.module.member.Member;

/**
 * Lists all members in the address book to the user.
 */
public class MlistCommand extends Command {

    public static final String COMMAND_WORD = "mlist";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all members in Ailurus with filters. \n"
            + "Only one of " + PREFIX_ATTEND + "or " + PREFIX_ABSENT + "may be present\n"
            + "Parameters: [" + PREFIX_EVENT_INDEX + "EVENT_INDEX (must be a positive integer)] "
            + "[" + PREFIX_ATTEND + "] "
            + "[" + PREFIX_ABSENT + "] \n"
            + "Examples: " + COMMAND_WORD + " " + PREFIX_EVENT_INDEX + "1\n"
            + COMMAND_WORD + " " + PREFIX_EVENT_INDEX + "1" + PREFIX_ATTEND + "\n"
            + COMMAND_WORD + " " + PREFIX_EVENT_INDEX + "1" + PREFIX_ABSENT + "\n";

    public static final String MESSAGE_SUCCESS = "Member(s) listed: %1$s members %2$s";

    private Index eventIndex = null;

    private Boolean hasAttended = null;

    public MlistCommand() {}

    /**
     * Constructor for MlistCommand.
     *
     * @param eventIndex is the index of the event
     */
    public MlistCommand(Index eventIndex) {
        this.eventIndex = eventIndex;
    }

    /**
     * Constructor for MlistCommand with filter.
     *
     * @param eventIndex is the index of the event
     * @param hasAttended is string to indicate if checking for absence or attendance
     */
    public MlistCommand(Index eventIndex, boolean hasAttended) {
        this.eventIndex = eventIndex;
        this.hasAttended = hasAttended;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> lastShownList = model.getFilteredEventList();

        if (eventIndex != null && eventIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        if (eventIndex == null) {
            model.updateFilteredMemberList(PREDICATE_SHOW_ALL_MEMBERS);
            model.setCurrentEvent(null);
            return new CommandResult(String.format(MESSAGE_SUCCESS, model.getFilteredMemberList().size(), ""));
        } else {
            Event eventToList = lastShownList.get(eventIndex.getZeroBased());
            Set<Member> memberList = eventToList.getParticipants(hasAttended);
            model.setCurrentEvent(eventToList);
            model.updateFilteredMemberList(member -> memberList.contains(member));

            return new CommandResult(String.format(MESSAGE_SUCCESS, model.getFilteredMemberList().size(),
                    "for the event: " + eventToList));
        }
    }
}
