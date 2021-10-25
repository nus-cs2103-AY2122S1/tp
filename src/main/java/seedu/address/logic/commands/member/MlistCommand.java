package seedu.address.logic.commands.member;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEMBERS;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.event.Event;
import seedu.address.model.module.member.Member;

import java.util.List;
import java.util.Set;

/**
 * Lists all members in the address book to the user.
 */
public class MlistCommand extends Command {

    public static final String COMMAND_WORD = "mlist";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all members in Ailurus with filters.\n"
            + "Parameters: [" + PREFIX_EVENT + " EVENT_ID (must be a positive integer)]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_EVENT + " 1";

    public static final String MESSAGE_SUCCESS = "Members listed: %1$s members %2$s";

    private Index eventIndex = null;

    public MlistCommand() {}

    public MlistCommand(Index eventIndex) {
        this.eventIndex = eventIndex;
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
            return new CommandResult(String.format(MESSAGE_SUCCESS, model.getFilteredMemberList().size(), ""));
        } else {
            Event eventToList = lastShownList.get(eventIndex.getZeroBased());
            Set<Member> memberList = eventToList.getParticipants();
            model.updateFilteredMemberList(member -> memberList.contains(member));

            return new CommandResult(String.format(MESSAGE_SUCCESS, model.getFilteredMemberList().size(),
                    "for event: " + eventToList));
        }
    }
}
