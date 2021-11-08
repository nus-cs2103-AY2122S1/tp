package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.model.tuition.TuitionClass;

/**
 * Deletes tuition classes from TutAssistor.
 */
public class DeleteClassCommand extends Command {
    public static final String COMMAND_WORD = "deleteclass";
    public static final String SHORTCUT = "delc";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes existing classes.\n"
            + "Parameters: CLASS_INDEX [CLASS_INDEX]... (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 2";
    public static final String MESSAGE_DELETE_CLASSES_SUCCESS = "Deleted Classes: %1$s.\n";
    public static final String MESSAGE_DELETE_CLASSES_FAILURE = "Classes at index : %1$s are not found.";
    private List<Index> classIndices = new ArrayList<>();
    private List<String> removed = new ArrayList<String>();
    private List<Integer> invalidClasses = new ArrayList<>();

    /**
     * Constructor for the DeleteClass command.
     *
     * @param classIndices The list of class indexes to be deleted.
     */
    public DeleteClassCommand(List<Index> classIndices) {
        this.classIndices = classIndices;
        this.removed = new ArrayList<>();
        this.invalidClasses = new ArrayList<>();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredTuitionList(Model.PREDICATE_SHOW_ALL_TUITIONS);
        for (Index currIndex : classIndices) {
            TuitionClass classToDelete = model.getTuitionClass(currIndex);
            if (classToDelete == null) {
                invalidClasses.add(currIndex.getOneBased());
                continue;
            }
            //remove the class from all its enrolled students
            for (String name : classToDelete.getStudentList().getStudents()) {
                if (name != null && model.getSameNameStudent(new Student(new Name(name))) != null) {
                    Student student = model.getSameNameStudent(new Student(new Name(name)));
                    Student updatedStudent = student.removeClass(classToDelete);
                    model.setStudent(student, updatedStudent);
                }
            }
            removed.add(classToDelete.getName().name + "|" + classToDelete.getTimeslot());
            model.deleteTuition(classToDelete);
        }
        if (!invalidClasses.isEmpty() && removed.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_DELETE_CLASSES_FAILURE, invalidClasses));
        }
        return new CommandResult(getMessage(removed, invalidClasses));
    }

    private String getMessage(List<String> removed, List<Integer> invalidClasses) {
        String message = "";
        if (!removed.isEmpty()) {
            message += String.format(MESSAGE_DELETE_CLASSES_SUCCESS, removed);
        }
        if (!invalidClasses.isEmpty()) {
            message += String.format(MESSAGE_DELETE_CLASSES_FAILURE, invalidClasses);
        }
        return message;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeleteClassCommand
                && this.classIndices.containsAll(((DeleteClassCommand) other).classIndices));
    }
}
