package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

//@author xianlinc-reused
//Reused from https://nus-cs2103-ay2122s1.github.io/tp/tutorials/AddRemark.html

/**
 * Adds or edits a note of an existing person in the InsurancePal
 */
public class NoteCommand extends Command {

    public static final String COMMAND_WORD = "note";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds or Edits a note to the person identified "
            + "by the index number used in the last person listing. "
            + "Existing note will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "NOTE\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "Enjoys western cuisine";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET =
            "Note command not implemented yet";
    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Remark: %2$s";

    private final Index index;
    private final String note;

    /**
     * @param index of the person in the filtered person list to edit the note
     * @param note of the person to be updated to
     */
    public NoteCommand(Index index, String note) {
        requireAllNonNull(index, note);

        this.index = index;
        this.note = note;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(
                String.format(MESSAGE_ARGUMENTS, index.getOneBased(), note));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NoteCommand)) {
            return false;
        }

        // state check
        NoteCommand e = (NoteCommand) other;
        return index.equals(e.index)
                && note.equals(e.note);
    }
}
