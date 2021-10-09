package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Progress;

/**
 * Adds a progress to an exiting student in TutorAid. Updates the progress if one already exists.
 */
public class AddProgressCommand extends Command {

    public static final String COMMAND_WORD = "add -p";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a progress for a student in TutorAid identified "
            + "by the index number used in the last person listing. "
            + "Existing progress will be overwritten by the input.\n"
            + "Parameters: STUDENT_INDEX (must be a positive integer) "
            + "PROGRESS\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "Finishes Prelims.";

    public static final String MESSAGE_SUCCESS = "Added progress: %1$s\nFor this student: %2$s";

    private final Index targetIndex;
    private final Progress progress;

    /**
     * @param targetIndex of the student in the filtered student list to add progress
     * @param progress Progress object to be added to the student
     */
    public AddProgressCommand(Index targetIndex, Progress progress) {
        this.targetIndex = targetIndex;
        this.progress = progress;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person studentToEdit = lastShownList.get(targetIndex.getZeroBased());
        Person editedStudent = new Person(
                studentToEdit.getName(), studentToEdit.getPhone(), studentToEdit.getEmail(),
                studentToEdit.getAddress(), this.progress, studentToEdit.getPaymentStatus(), studentToEdit.getTags());

        model.setPerson(studentToEdit, editedStudent);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, progress, studentToEdit));
    }
}
