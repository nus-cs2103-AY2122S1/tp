package seedu.times.logic.commands.classcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.times.commons.core.Messages.MESSAGE_INVALID_CLASS_DISPLAYED_INDEX;
import static seedu.times.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.times.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.times.logic.commands.classcommands.EditClassCommand.EditClassDescriptor;
import static seedu.times.logic.commands.classcommands.EditClassCommand.MESSAGE_EDIT_CLASS_SUCCESS;
import static seedu.times.model.tuitionclass.exceptions.DuplicateClassException.DUPLICATE_CLASS_ERROR_MESSAGE;
import static seedu.times.model.tuitionclass.exceptions.OverlappingClassException.OVERLAP_ERROR_MESSAGE;
import static seedu.times.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.times.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.times.testutil.TypicalTimestable.JC_MATHS;
import static seedu.times.testutil.TypicalTimestable.JC_PHYSICS;
import static seedu.times.testutil.TypicalTimestable.getTypicalTimesTable;

import org.junit.jupiter.api.Test;

import seedu.times.commons.core.index.Index;
import seedu.times.model.Model;
import seedu.times.model.ModelManager;
import seedu.times.model.TimesTable;
import seedu.times.model.UserPrefs;
import seedu.times.model.tuitionclass.TuitionClass;
import seedu.times.testutil.EditClassDescriptorBuilder;
import seedu.times.testutil.TuitionClassBuilder;

public class EditClassCommandTest {

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        //create model to used in actual execution
        Model model = new ModelManager(getTypicalTimesTable(), new UserPrefs());
        Index targetIndex = INDEX_FIRST;

        //create command to be tested
        //create descriptor
        TuitionClass editedClass = new TuitionClassBuilder().build();
        EditClassDescriptor descriptor = new EditClassDescriptorBuilder(editedClass).build();
        //create command
        EditClassCommand editClassCommand = new EditClassCommand(targetIndex, descriptor);

        //create expected model by manually setting target class to updated class
        Model expectedModel = new ModelManager(new TimesTable(model.getTimesTable()), new UserPrefs());
        expectedModel.setClass(model.getFilteredTuitionClassList().get(targetIndex.getZeroBased()), editedClass);

        //run assertCommand function with corresponding message
        String expectedMessage = String.format(MESSAGE_EDIT_CLASS_SUCCESS, editedClass);
        assertCommandSuccess(editClassCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Model model = new ModelManager(getTypicalTimesTable(), new UserPrefs());
        Index targetIndex = INDEX_FIRST;

        TuitionClass editedClass = new TuitionClassBuilder(JC_PHYSICS).withClassName("Discrete Maths").build();
        EditClassDescriptor descriptor = new EditClassDescriptorBuilder().withClassName("Discrete Maths").build();
        EditClassCommand editClassCommand = new EditClassCommand(targetIndex, descriptor);

        Model expectedModel = new ModelManager(new TimesTable(model.getTimesTable()), new UserPrefs());
        expectedModel.setClass(model.getFilteredTuitionClassList().get(targetIndex.getZeroBased()), editedClass);

        String expectedMessage = String.format(MESSAGE_EDIT_CLASS_SUCCESS, editedClass);
        assertCommandSuccess(editClassCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editedClassIsSameAsOriginal_success() {
        Model model = new ModelManager(getTypicalTimesTable(), new UserPrefs());
        Index targetIndex = INDEX_FIRST;

        TuitionClass editedClass = JC_PHYSICS;
        EditClassDescriptor descriptor = new EditClassDescriptorBuilder(JC_PHYSICS).build();
        EditClassCommand editClassCommand = new EditClassCommand(targetIndex, descriptor);

        Model expectedModel = new ModelManager(new TimesTable(model.getTimesTable()), new UserPrefs());

        String expectedMessage = String.format(MESSAGE_EDIT_CLASS_SUCCESS, editedClass);;
        assertCommandSuccess(editClassCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editedClassIsDuplicateOfOther_failure() {
        Model model = new ModelManager(getTypicalTimesTable(), new UserPrefs());
        Index targetIndex = INDEX_FIRST;

        EditClassDescriptor descriptor = new EditClassDescriptorBuilder(JC_MATHS).build();
        EditClassCommand editClassCommand = new EditClassCommand(targetIndex, descriptor);

        String expectedMessage = DUPLICATE_CLASS_ERROR_MESSAGE;
        assertCommandFailure(editClassCommand, model, expectedMessage);
    }

    @Test
    public void execute_editedClassOverlapClassTiming_failure() {
        Model model = new ModelManager(getTypicalTimesTable(), new UserPrefs());
        Index targetIndex = INDEX_FIRST;

        //overlap with JC_MATHS
        TuitionClass editedClass = new TuitionClassBuilder().build();
        EditClassDescriptor descriptor = new EditClassDescriptorBuilder(editedClass).withClassTiming("Wed 14:00-16:00")
                .build();
        EditClassCommand editClassCommand = new EditClassCommand(targetIndex, descriptor);

        String expectedMessage = OVERLAP_ERROR_MESSAGE;
        assertCommandFailure(editClassCommand, model, expectedMessage);
    }

    @Test
    public void execute_indexOutOfRange_failure() {
        Model model = new ModelManager(getTypicalTimesTable(), new UserPrefs());
        Index targetIndex = Index.fromOneBased(999);

        TuitionClass editedClass = new TuitionClassBuilder().build();
        EditClassDescriptor descriptor = new EditClassDescriptorBuilder(editedClass).build();
        EditClassCommand editClassCommand = new EditClassCommand(targetIndex, descriptor);

        String expectedMessage = MESSAGE_INVALID_CLASS_DISPLAYED_INDEX;
        assertCommandFailure(editClassCommand, model, expectedMessage);
    }


    @Test
    public void equals() {
        TuitionClass editedClass = new TuitionClassBuilder().build();
        EditClassDescriptor descriptor = new EditClassDescriptorBuilder(editedClass).build();
        EditClassCommand editClassCommand = new EditClassCommand(INDEX_FIRST, descriptor);
        EditClassCommand editClassCommand2 = new EditClassCommand(INDEX_FIRST, descriptor);

        //same obj -> true
        assertTrue(editClassCommand.equals(editClassCommand));

        //same values -> true
        assertTrue(editClassCommand.equals(editClassCommand2));

        //different Index -> false
        editClassCommand2 = new EditClassCommand(INDEX_SECOND, descriptor);
        assertFalse(editClassCommand.equals(editClassCommand2));

        //different descriptor -> false
        EditClassDescriptor descriptor2 = new EditClassDescriptorBuilder(editedClass).withLocation("Wellington Street")
                .build();
        editClassCommand2 = new EditClassCommand(INDEX_FIRST, descriptor2);
        assertFalse(editClassCommand.equals(editClassCommand2));
    }
}
