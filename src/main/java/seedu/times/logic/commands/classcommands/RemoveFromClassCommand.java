package seedu.times.logic.commands.classcommands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.times.commons.core.Messages;
import seedu.times.commons.core.index.Index;
import seedu.times.logic.commands.Command;
import seedu.times.logic.commands.CommandResult;
import seedu.times.logic.commands.exceptions.CommandException;
import seedu.times.model.Model;
import seedu.times.model.person.Name;
import seedu.times.model.person.Student;
import seedu.times.model.tuitionclass.StudentNameList;
import seedu.times.model.tuitionclass.TuitionClass;
import seedu.times.ui.TabName;

/**
 * Removes students from a tuition class.
 */
public class RemoveFromClassCommand extends Command {
    public static final String COMMAND_WORD = "removefromclass";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes students from a class by the index"
            + " number used "
            + "in the displayed class and student list in classes tab.\n"
            + "Parameters: CLASS_INDEX "
            + "STUDENT_INDEX... (Indexes must be non-zero positive integers)\n"
            + "Example: " + COMMAND_WORD + " "
            + "2 "
            + "3 4 5 "
            + "(removes students indexed 3, 4 and 5 from class indexed 2)";

    public static final String NO_STUDENT_INDEX_PROVIDED_MESSAGE = "No student index is provided!";
    public static final String MESSAGE_REMOVE_SUCCESS = "Successfully removed students from class ";

    private static Logger logger = Logger.getLogger("RemoveFromClassCommand");

    private final Index toEditClassIndex;
    private final List<Index> studentIndicesToRemove;

    /**
     * Constructs a remove from class command.
     *
     * @param indexArray Array with index of class to remove from and index of students.
     */
    public RemoveFromClassCommand(List<Index> indexArray) {
        requireNonNull(indexArray);

        //the first index is the index of class in filtered class list that students would be removed from
        toEditClassIndex = indexArray.get(0);
        assert toEditClassIndex.getOneBased() > 0;

        //the remaining indices are those of the students in filtered student list
        studentIndicesToRemove = indexArray.subList(1, indexArray.size());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        //get class to remove from
        List<TuitionClass> lastShownClassList = model.getFilteredTuitionClassList();
        if (lastShownClassList.size() == 0) {
            logger.log(Level.INFO, "There are no classes displayed.");
            throw new CommandException(Messages.MESSAGE_INVALID_CLASS_DISPLAYED_INDEX);
        } else if (toEditClassIndex.getOneBased() > lastShownClassList.size()) {
            logger.log(Level.INFO, "There are only " + lastShownClassList.size() + " classes displayed.");
            throw new CommandException(Messages.MESSAGE_INVALID_CLASS_DISPLAYED_INDEX);
        }
        TuitionClass classToRemoveFrom = lastShownClassList.get(toEditClassIndex.getZeroBased());

        //get names to be removed
        StudentNameList currentStudentNameList = classToRemoveFrom.getStudentList();
        List<Student> filteredStudentList = model.getFilteredStudentList().filtered(student ->
                classToRemoveFrom.containsStudent(student.getName()));
        checkIndicesAreValid(studentIndicesToRemove, filteredStudentList);
        ArrayList<Name> namesToRemove = createNewNameList(studentIndicesToRemove, filteredStudentList);


        //get updated student list
        StudentNameList updatedStudentNameList = new StudentNameList();
        updatedStudentNameList.addAll(currentStudentNameList);
        updatedStudentNameList.removeAll(namesToRemove);

        //create edit class descriptor
        EditClassCommand.EditClassDescriptor editClassDescriptor = new EditClassCommand.EditClassDescriptor();
        editClassDescriptor.setStudentList(updatedStudentNameList);

        //swap out old tuition class with new tuition class
        TuitionClass editedClass = EditClassCommand.createEditedClass(classToRemoveFrom, editClassDescriptor);
        model.setClass(classToRemoveFrom, editedClass);

        // Switches the view to the class view and updates the class
        updateView(TabName.CLASSES);
        updateClass(toEditClassIndex.getZeroBased());

        logger.log(Level.INFO, "Class name list updated.");

        return new CommandResult(String.format(MESSAGE_REMOVE_SUCCESS, editedClass));
    }

    private ArrayList<Name> createNewNameList(List<Index> studentIndices, List<Student> studentList) {
        ArrayList<Name> newNameList = new ArrayList<>();
        studentIndices.stream().forEach(index -> {
            Name name = studentList.get(index.getZeroBased()).getName();
            newNameList.add(name);
        });
        return newNameList;
    }

    private void checkIndicesAreValid(List<Index> studentIndices, List<Student> studentList)
            throws CommandException {
        int size = studentList.size();
        for (Index index : studentIndices) {
            if (index.getZeroBased() >= size) {
                logger.log(Level.INFO, "There are only " + size + " students displayed.");
                throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
            }
        }
        logger.log(Level.INFO, "All indices are valid.");
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof RemoveFromClassCommand)) {
            return false;
        }

        RemoveFromClassCommand other = ((RemoveFromClassCommand) o);
        return other.toEditClassIndex.equals(toEditClassIndex)
                && other.studentIndicesToRemove.equals(studentIndicesToRemove);
    }
}
