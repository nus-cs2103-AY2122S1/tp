package seedu.address.logic.candidate;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Command;
import seedu.address.logic.CommandResult;
import seedu.address.logic.candidate.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes a candidate identified using it's displayed index from the HR Manager.
 */
public class DeleteCandidateCommand extends Command {

    public static final String COMMAND_WORD = "delete_c";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the candidate identified by the index number used in the displayed candidate list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Candidate: %1$s";

    private final Index targetIndex;

    public DeleteCandidateCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePerson(personToDelete);
        model.deletePersonFromInterview(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete),
                CommandResult.CommandType.CANDIDATE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCandidateCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCandidateCommand) other).targetIndex)); // state check
    }
}
