package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.event.Event;
import seedu.address.model.module.event.EventDate;
import seedu.address.model.module.member.Member;

/**
 * Adds an event to Ailurus.
 */
public class EaddCommand extends Command {

    public static final String COMMAND_WORD = "eadd";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an event to Ailurus.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_DATE + "DATE (format: " + EventDate.DATE_TIME_FORMAT + ") "
            + "[" + PREFIX_MEMBER_INDEX + "MEMBER_INDEX (must be a positive integer)]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Freshmen Orientation Week "
            + PREFIX_DATE + "11/07/2021 "
            + PREFIX_MEMBER_INDEX + "1 "
            + PREFIX_MEMBER_INDEX + "2";

    public static final String MESSAGE_SUCCESS = "New event added: %1$s";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in the Ailurus.";

    private final Event toAdd;
    private final Set<Index> indexSet;
    private final Set<Member> memberSet = new HashSet<>();

    /**
     * Creates an EaddCommand to add the specified {@code Event} with specified {@code Set<Index>}
     */
    public EaddCommand(Event event, Set<Index> indexList) {
        requireNonNull(event);
        toAdd = event;
        indexSet = indexList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Member> lastShownList = model.getFilteredMemberList();


        // adds all members in indexSet to participant list in event
        for (Index targetIndex : indexSet) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
            }
            Member memberAsParticipant = lastShownList.get(targetIndex.getZeroBased());
            memberSet.add(memberAsParticipant);
        }
        toAdd.addParticipants(memberSet);

        if (model.hasEvent(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        model.addEvent(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EaddCommand // instanceof handles nulls
                && toAdd.equals(((EaddCommand) other).toAdd)
                && indexSet.equals(((EaddCommand) other).indexSet));
    }
}
