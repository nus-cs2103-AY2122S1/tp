package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_PARTICIPANT_NOT_FOUND;

import java.util.List;

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

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds the participant whose ID matches  "
            + "the given ID (case-insensitive) and displays the participant's details.\n"
            + "Parameters: PARTICIPANT_ID\n"
            + "Example: " + COMMAND_WORD + " aleyeo1";

    public static final String MULTIPLE_MATCHES_FOUND = "Multiple matches were found. Did you mean: \n";

    private String givenId;
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
        List<Participant> matchingParticipants = model.findParticipants(this.predicate);
        int numOfMatches = matchingParticipants.size();

        if (numOfMatches == 0) {
            throw new CommandException(
                    String.format(MESSAGE_PARTICIPANT_NOT_FOUND, this.givenId, ListCommand.COMMAND_WORD));

        } else if (numOfMatches > 1) {
            StringBuilder matches = new StringBuilder();
            for (int i = 0; i < numOfMatches; i++) {
                matches.append(i + 1).append(". ")
                        .append(matchingParticipants.get(i).getParticipantIdValue())
                        .append("\n");
            }
            return new CommandResult(MULTIPLE_MATCHES_FOUND + matches);

        } else {
            return new CommandResult(matchingParticipants.get(0).toString());
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && predicate.equals(((ViewCommand) other).predicate)); // state check
    }
}
