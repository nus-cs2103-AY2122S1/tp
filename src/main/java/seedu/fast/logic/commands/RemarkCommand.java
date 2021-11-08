package seedu.fast.logic.commands;

import static seedu.fast.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.logging.Logger;

import seedu.fast.commons.core.LogsCenter;
import seedu.fast.commons.core.Messages;
import seedu.fast.commons.core.index.Index;
import seedu.fast.commons.util.CommandUtil;
import seedu.fast.logic.commands.exceptions.CommandException;
import seedu.fast.model.Model;
import seedu.fast.model.person.Person;
import seedu.fast.model.person.Remark;

/**
 * Changes the remark of an existing person in FAST.
 */
public class RemarkCommand extends Command {

    public static final String COMMAND_WORD = "rmk";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the remark of the client identified "
            + "by the index number used in the last client listing. "
            + "Existing remark will be overwritten by the input.\n\n"
            + "Parameters: \nINDEX (must be a postive integer) "
            + "r/ [REMARK]\n"
            + "[Remark] cannot exceed 45 characters \n\n"
            + "Example: \n" + COMMAND_WORD + " 1 "
            + "r/ Likes to swim.";

    public static final String MESSAGE_ADD_REMARK_SUCCESS = "Added remark to Client: %1$s";
    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Removed remark from Client: %1$s";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private final Index index;
    private Remark remark;

    /**
     * Construct for a {@code RemarkCommand}
     *
     * @param index index of the person in the filtered person list to edit the remark
     * @param remark remark of the person to be updated to
     */
    public RemarkCommand(Index index, Remark remark) {
        requireAllNonNull(index, remark);

        this.index = index;
        this.remark = remark;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (CommandUtil.checkIndexExceedLimit(index, lastShownList)) {
            logger.warning("-----Invalid Remark Command: Invalid Index-----");
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), remark, personToEdit.getTags(), personToEdit.getAppointment(),
                personToEdit.getCount());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        logger.info("-----Remark Command: Remark added successfully-----");

        return new CommandResult(generateSuccessMessage(editedPerson));
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
        if (!(other instanceof RemarkCommand)) {
            return false;
        }

        // state check
        RemarkCommand e = (RemarkCommand) other;
        return index.equals(e.index) && remark.equals(e.remark);
    }
}
