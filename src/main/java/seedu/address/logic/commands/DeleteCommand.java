package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tuition.TuitionClass;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the students identified by the index numbers used in the Students list.\n"
            + "Parameters: STUDENT_INDEX STUDENT_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 2";

    public static final String MESSAGE_DELETE_STUDENTS_SUCCESS = "Deleted Students: %1$s.\n";
    public static final String MESSAGE_DELETE_STUDENTS_FAILURE = "Students at index: %1$s are not found.";

    private List<Index> targetIndex = new ArrayList<>();

    public DeleteCommand(List<Index> targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        List<String> removed = new ArrayList<>();
        List<Integer> invalidStudents = new ArrayList<>();

        for (int i = 0; i < targetIndex.size(); i++) {
            Index currIndex = targetIndex.get(i);
            if (currIndex.getZeroBased() >= lastShownList.size()) {
                invalidStudents.add(currIndex.getOneBased());
                continue;
            }
            Person personToDelete = lastShownList.get(currIndex.getZeroBased());
            if (personToDelete == null) {
                invalidStudents.add(currIndex.getOneBased());
                continue;
            }
            if (personToDelete != null) {
                for (Integer tuitionClassId : personToDelete.getClasses().getClasses()) {
                    TuitionClass tuitionClass = model.getClassById(tuitionClassId);
                    if (tuitionClass != null) {
                        TuitionClass updatedClass = tuitionClass.removeStudent(personToDelete);
                        model.setTuition(tuitionClass, updatedClass);
                    }
                }
                removed.add(personToDelete.getName().fullName);
                model.deletePerson(personToDelete);
            }
            model.updateFilteredTuitionList(Model.PREDICATE_SHOW_ALL_TUITIONS);
            model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        }
        String feedback = (!removed.isEmpty()
                ? String.format(MESSAGE_DELETE_STUDENTS_SUCCESS, removed) : "")
                + (!invalidStudents.isEmpty()
                        ? String.format(MESSAGE_DELETE_STUDENTS_FAILURE, invalidStudents)
                        : "");

        return new CommandResult(feedback);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeleteCommand
                && targetIndex.containsAll((((DeleteCommand) other).targetIndex)));
    }
}
