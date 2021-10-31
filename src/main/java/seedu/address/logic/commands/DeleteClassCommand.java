package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.model.tuition.TuitionClass;

public class DeleteClassCommand extends Command {
    public static final String COMMAND_WORD = "deleteclass";
    public static final String SHORTCUT = "delc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes existing classes.\n"
            + "Parameters: CLASS_INDEX [CLASS_INDEX]... (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 2";

    public static final String MESSAGE_DELETE_CLASSES_SUCCESS = "Deleted Classes: %1$s.\n";
    public static final String MESSAGE_DELETE_CLASSES_FAILURE = "Classes at index : %1$s are not found.";

    private List<Index> targetIndex = new ArrayList<>();

    public DeleteClassCommand(List<Index> studentIndexes) {
        targetIndex = studentIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredTuitionList(Model.PREDICATE_SHOW_ALL_TUITIONS);
        List<TuitionClass> lastShownList = model.getFilteredTuitionList();
        List<String> removed = new ArrayList<String>();
        List<Integer> invalidClasses = new ArrayList<>();

        for (int i = 0; i < targetIndex.size(); i++) {
            Index currIndex = targetIndex.get(i);
            if (currIndex.getZeroBased() >= lastShownList.size()) {
                invalidClasses.add(currIndex.getOneBased());
                continue;
            }
            TuitionClass classToDelete = lastShownList.get(currIndex.getZeroBased());
            if (classToDelete == null) {
                invalidClasses.add(currIndex.getOneBased());
                continue;
            }
            List<Student> currStudents = classToDelete
                    .getStudentList().getStudents().stream()
                    .map(x -> model.getSameNameStudent(new Student(new Name(x)))).collect(Collectors.toList());
            for (Student student : currStudents) {
                Student updatedStudent = student.removeClass(classToDelete);
                model.setStudent(student, updatedStudent);
            }
            removed.add(classToDelete.getName().name + "|" + classToDelete.getTimeslot());
            model.deleteTuition(classToDelete);
        }
        if (!invalidClasses.isEmpty() && removed.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_DELETE_CLASSES_FAILURE, invalidClasses));
        }

        String feedback = (!removed.isEmpty()
            ? String.format(MESSAGE_DELETE_CLASSES_SUCCESS, removed) : "")
                + (!invalidClasses.isEmpty()
                    ? String.format(MESSAGE_DELETE_CLASSES_FAILURE, invalidClasses)
                    : "");
        return new CommandResult(feedback);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeleteClassCommand
                && this.targetIndex.containsAll(((DeleteClassCommand) other).targetIndex));
    }
}
