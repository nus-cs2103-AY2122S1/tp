package seedu.times.logic.commands.classcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.times.commons.core.Messages.MESSAGE_INVALID_CLASS_DISPLAYED_INDEX;
import static seedu.times.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.times.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.times.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.times.logic.commands.classcommands.AddToClassCommand.MESSAGE_ADD_SUCCESS;
import static seedu.times.logic.commands.classcommands.EditClassCommand.MESSAGE_DUPLICATE_STUDENT;
import static seedu.times.testutil.TestUtil.getClassOneBased;
import static seedu.times.testutil.TestUtil.getIndexList;
import static seedu.times.testutil.TestUtil.getStudentOneBased;
import static seedu.times.testutil.TypicalTimestable.BENSON;
import static seedu.times.testutil.TypicalTimestable.JC_CHEMISTRY;
import static seedu.times.testutil.TypicalTimestable.getTypicalTimesTable;

import org.junit.jupiter.api.Test;

import seedu.times.logic.commands.ClearCommand;
import seedu.times.model.Model;
import seedu.times.model.ModelManager;
import seedu.times.model.TimesTable;
import seedu.times.model.UserPrefs;
import seedu.times.model.tuitionclass.TuitionClass;
import seedu.times.testutil.TuitionClassBuilder;



public class AddToClassCommandTest {
    @Test
    public void execute_addSingleStudentToClass_success() {
        //create model to used in actual execution
        Model model = new ModelManager(getTypicalTimesTable(), new UserPrefs());

        //create command to be tested
        AddToClassCommand addToClassCommand = new AddToClassCommand(getIndexList(1, 3));

        //create expected model by manually setting target class to updated class
        Model expectedModel = new ModelManager(new TimesTable(getTypicalTimesTable()), new UserPrefs());
        TuitionClass classToAddto = getClassOneBased(expectedModel, 1);
        TuitionClass editedClass = new TuitionClassBuilder(classToAddto)
                .withStudentList(classToAddto.getStudentList().getNames()).build();
        editedClass.addStudent(getStudentOneBased(model, 3).getName());
        expectedModel.setClass(classToAddto, editedClass);

        //run assertCommand function with corresponding message
        String expectedMessage = MESSAGE_ADD_SUCCESS;
        assertCommandSuccess(addToClassCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addMultipleStudentsToClass_success() {
        Model model = new ModelManager(getTypicalTimesTable(), new UserPrefs());

        AddToClassCommand addToClassCommand = new AddToClassCommand(getIndexList(2, 1, 2));

        Model expectedModel = new ModelManager(new TimesTable(getTypicalTimesTable()), new UserPrefs());
        TuitionClass classToAddto = getClassOneBased(expectedModel, 2);
        TuitionClass editedClass = new TuitionClassBuilder(classToAddto)
                .withStudentList(classToAddto.getStudentList().getNames()).build();
        editedClass.addAllStudents(
                getStudentOneBased(model, 1).getName(),
                getStudentOneBased(model, 2).getName()
        );
        expectedModel.setClass(classToAddto, editedClass);

        String expectedMessage = MESSAGE_ADD_SUCCESS;
        assertCommandSuccess(addToClassCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_classIndexOutOfRange_failure() {
        Model model = new ModelManager(getTypicalTimesTable(), new UserPrefs());
        AddToClassCommand addToClassCommand = new AddToClassCommand(getIndexList(22, 1, 2));
        assertCommandFailure(addToClassCommand, model, MESSAGE_INVALID_CLASS_DISPLAYED_INDEX);
    }

    @Test
    public void execute_indexOutOfRange_failure() {
        Model model = new ModelManager(getTypicalTimesTable(), new UserPrefs());
        AddToClassCommand addToClassCommand = new AddToClassCommand(getIndexList(1, 12, 2));
        assertCommandFailure(addToClassCommand, model, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_addDuplicateStudentsToClass_failure() {
        Model model = new ModelManager(new TimesTable(), new UserPrefs());

        model.addStudent(BENSON);
        TuitionClass newClass = new TuitionClassBuilder(JC_CHEMISTRY).withStudentList(BENSON.getName().fullName)
                .build();
        model.addTuitionClass(newClass);

        AddToClassCommand addToClassCommand = new AddToClassCommand(getIndexList(1, 1));

        String expectedMessage = MESSAGE_DUPLICATE_STUDENT + BENSON.getName();
        assertCommandFailure(addToClassCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        AddToClassCommand standardCommand = new AddToClassCommand(getIndexList(1, 2, 3));

        // failure

        //null returns false
        assertFalse(standardCommand.equals(null));

        //different value returns false
        assertFalse(standardCommand.equals(new AddToClassCommand(getIndexList(1, 4, 3))));

        //different command returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // success

        //same obj returns true
        assertTrue(standardCommand.equals(standardCommand));

        //same value returns true
        assertTrue(standardCommand.equals(new AddToClassCommand(getIndexList(1, 2, 3))));
    }
}

