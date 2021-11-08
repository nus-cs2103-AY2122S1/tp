package seedu.address.logic.candidate;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Command;
import seedu.address.logic.CommandResult;
import seedu.address.logic.candidate.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;

/**
 * Changes the remark of an existing person in the HR Manager.
 */
public class RemarkCandidateCommand extends Command {

    public static final String COMMAND_WORD = "remark_c";
    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Remark: %2$s";
    public static final String MESSAGE_ADD_REMARK_SUCCESS = "Added remark to Person: %1$s";
    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Removed remark from Person: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the remark of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing remark will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "remark=REMARK\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "remark=Likes to swim.";
    private final Index index;
    private final Remark remark;

    /**
     * @param index  of the person in the filtered person list to edit the remark
     * @param remark of the person to be updated to
     */
    public RemarkCandidateCommand(Index index, Remark remark) {
        requireAllNonNull(index, remark);

        this.index = index;
        this.remark = remark;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), remark, personToEdit.getTags(), personToEdit.getStatus(),
                personToEdit.getPositions());

        Set<Interview> personInterviews = personToEdit.getInterviews();
        for (Interview i : personInterviews) {
            editedPerson.addInterview(i);
            i.deleteCandidate(personToEdit);
            i.addCandidate(editedPerson);
        }

        model.setPerson(personToEdit, editedPerson);

        return new CommandResult(generateSuccessMessage(editedPerson), CommandResult.CommandType.CANDIDATE);
    }

    /**
     * Generates a command execution success message based on whether
     * the remark is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !remark.value.isEmpty() ? MESSAGE_ADD_REMARK_SUCCESS : MESSAGE_DELETE_REMARK_SUCCESS;
        return String.format(message, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemarkCandidateCommand)) {
            return false;
        }

        // state check
        RemarkCandidateCommand e = (RemarkCandidateCommand) other;
        return index.equals(e.index)
                && remark.equals(e.remark);
    }
}
