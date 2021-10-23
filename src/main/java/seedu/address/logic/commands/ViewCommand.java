package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_PARTICIPANT_NOT_FOUND;

import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.participant.Participant;
import seedu.address.model.participant.ParticipantIdMatchesGivenIdPredicate;

/**
 * Finds a Participant in Managera whose ID matches the given ID.
 * Keyword matching is case insensitive.
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds the participant identified by the "
            + "index number used in the displayed participant list and displays the participant's details.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final String givenId;
    private final ParticipantIdMatchesGivenIdPredicate predicate;

    /**
     * A constructor for ViewCommand.
     *
     * @param givenId The given user input.
     */
    public ViewCommand(String givenId) {
        this.givenId = givenId;
        this.predicate = new ParticipantIdMatchesGivenIdPredicate(givenId);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Optional<Participant> matchingParticipant = model.findParticipant(predicate);

        if (matchingParticipant.isEmpty()) {
            throw new CommandException(
                    String.format(MESSAGE_PARTICIPANT_NOT_FOUND, this.givenId, ListCommand.COMMAND_WORD));

        } else {
            return new CommandResult(matchingParticipant.get().toString());
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && predicate.equals(((ViewCommand) other).predicate)); // state check
    }
}
