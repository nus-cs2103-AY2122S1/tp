package seedu.times.logic.commands.classcommands;

import static java.util.Objects.requireNonNull;
import static seedu.times.logic.commands.classcommands.EditClassCommand.EditClassDescriptor;
import static seedu.times.logic.commands.classcommands.EditClassCommand.MESSAGE_DUPLICATE_STUDENT;

import java.util.ArrayList;
import java.util.List;

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
import seedu.times.model.tuitionclass.exceptions.DuplicateStudentInClassException;
import seedu.times.ui.TabName;

/**
 * Adds existing students to existing tuition class.
 */
public class AddToClassCommand extends Command {

    public static final String COMMAND_WORD = "addtoclass";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds students to a class by the index number used "
            + "in the displayed class and student list in students tab.\n"
            + "Indexes must be non-zero positive integers.\n"
            + "Parameters: CLASS_INDEX "
            + "STUDENT_INDEX...\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 "
            + "2 3 5 "
            + "(adds students indexed 2, 3 and 5 to class indexed 1)";

    public static final String MESSAGE_ADD_SUCCESS = "Successfully added students to class ";
    public static final String NO_STUDENT_INDEX_PROVIDED_MESSAGE = "No student index is provided!";
    public static final String INVALID_OR_MISSING_CLASS_INDEX = "Class index is invalid or is not provided";
    public static final int CLASS_INDEX_POSITION = 0;
    public static final int STUDENT_INDEX_STARTING_POSITION = 1;

    private final Index toEditClassIndex;
    private final List<Index> studentIndices;

    /**
     * Constructs AddToClassCommand.
     *
     * @param indexArray ArrayList of index.
     */
    public AddToClassCommand(List<Index> indexArray) {
        requireNonNull(indexArray);

        //the first index is the index of class in filtered class list that students would be added to
        toEditClassIndex = indexArray.get(CLASS_INDEX_POSITION);

        //the remaining indices are those of the students in filtered student list
        studentIndices = indexArray.subList(STUDENT_INDEX_STARTING_POSITION, indexArray.size());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        //check indices are not out-of-range
        List<Student> lastShownStudentList = model.getFilteredStudentList();
        List<TuitionClass> lastShownTuitionClassList = model.getFilteredTuitionClassList();
        checkIndicesAreInRange(lastShownStudentList, lastShownTuitionClassList);

        //get names to be added and class to add to
        ArrayList<Name> namesToAdd = createNameList(studentIndices, lastShownStudentList);
        TuitionClass classToAddTo = lastShownTuitionClassList.get(toEditClassIndex.getZeroBased());

        //get updated student list
        StudentNameList currentStudentNameList = classToAddTo.getStudentList();
        StudentNameList updatedStudentNameList = new StudentNameList();
        try {
            updatedStudentNameList.addAll(currentStudentNameList);
            updatedStudentNameList.addAll(namesToAdd);
        } catch (DuplicateStudentInClassException e) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT + e.getMessage());
        }

        //create edit class descriptor
        EditClassDescriptor editClassDescriptor = new EditClassDescriptor();
        editClassDescriptor.setStudentList(updatedStudentNameList);

        //swap out old tuition class with new tuition class
        TuitionClass editedClass = EditClassCommand.createEditedClass(classToAddTo, editClassDescriptor);
        model.setClass(classToAddTo, editedClass);

        // Switches the view to the class view and updates the class
        updateView(TabName.CLASSES);
        updateClass(toEditClassIndex.getZeroBased());

        return new CommandResult(String.format(MESSAGE_ADD_SUCCESS, editedClass));
    }

    private ArrayList<Name> createNameList(List<Index> studentIndices, List<Student> lastShownStudentList) {
        ArrayList<Name> nameList = new ArrayList<>();
        studentIndices.stream().forEach(index -> {
            Student student = lastShownStudentList.get(index.getZeroBased());
            nameList.add(student.getName());
        });
        return nameList;
    }

    private void checkIndicesAreInRange(List<Student> lastShownStudentList, List<TuitionClass> lastShownTuitionClass)
            throws CommandException {
        int studentListSize = lastShownStudentList.size();
        int classListSize = lastShownTuitionClass.size();

        if (studentListSize == 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        assert studentListSize > 0;
        assert classListSize >= 0;

        if (toEditClassIndex.getZeroBased() >= classListSize) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLASS_DISPLAYED_INDEX);
        }

        for (Index index : studentIndices) {
            if (index.getZeroBased() >= studentListSize) {
                throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof AddToClassCommand)) {
            return false;
        }

        AddToClassCommand other = ((AddToClassCommand) o);
        return other.toEditClassIndex.equals(toEditClassIndex)
                && other.studentIndices.equals(studentIndices);
    }
}
