package tutoraid.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tutoraid.logic.commands.CommandTestUtil.DESC_AMY;
import static tutoraid.logic.commands.CommandTestUtil.DESC_BOB;
import static tutoraid.logic.commands.CommandTestUtil.VALID_PARENT_NAME_BOB;
import static tutoraid.logic.commands.CommandTestUtil.VALID_PARENT_PHONE_AMY;
import static tutoraid.logic.commands.CommandTestUtil.VALID_STUDENT_NAME_BOB;
import static tutoraid.logic.commands.CommandTestUtil.VALID_STUDENT_PHONE_AMY;
import static tutoraid.logic.commands.CommandTestUtil.assertCommandFailure;
import static tutoraid.logic.commands.CommandTestUtil.showStudentAtIndex;
import static tutoraid.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static tutoraid.testutil.TypicalIndexes.INDEX_SECOND_ITEM;
import static tutoraid.testutil.TypicalLessons.getTypicalLessonBook;
import static tutoraid.testutil.TypicalStudents.getTypicalStudentBook;

import org.junit.jupiter.api.Test;

import tutoraid.commons.core.Messages;
import tutoraid.commons.core.index.Index;
import tutoraid.logic.commands.EditStudentCommand.EditStudentDescriptor;
import tutoraid.logic.commands.exceptions.CommandException;
import tutoraid.model.Model;
import tutoraid.model.ModelManager;
import tutoraid.model.UserPrefs;
import tutoraid.model.student.Student;
import tutoraid.testutil.EditStudentDescriptorBuilder;
import tutoraid.testutil.StudentBuilder;


public class EditStudentCommandTest {

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() throws CommandException {
        Model model = new ModelManager(getTypicalStudentBook(), getTypicalLessonBook(), new UserPrefs());
        Student editedStudent = new StudentBuilder()
                .withStudentName(VALID_STUDENT_NAME_BOB)
                .withStudentPhone(VALID_STUDENT_PHONE_AMY)
                .withParentName(VALID_PARENT_NAME_BOB)
                .withParentPhone(VALID_PARENT_PHONE_AMY)
                .build();
        EditStudentCommand editCommand = new EditStudentCommand(INDEX_FIRST_ITEM,
                new EditStudentDescriptorBuilder()
                        .withStudentName(VALID_STUDENT_NAME_BOB)
                        .withStudentPhone(VALID_STUDENT_PHONE_AMY)
                        .withParentName(VALID_PARENT_NAME_BOB)
                        .withParentPhone(VALID_PARENT_PHONE_AMY)
                        .build());
        editCommand.execute(model);
        assertEquals(editedStudent, model.getFilteredStudentList().get(0));
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() throws CommandException {
        Model model = new ModelManager(getTypicalStudentBook(), getTypicalLessonBook(), new UserPrefs());
        Student editedStudent = new StudentBuilder()
                .withStudentName(VALID_STUDENT_NAME_BOB)
                .withParentPhone(VALID_PARENT_PHONE_AMY)
                .build();
        EditStudentCommand editCommand = new EditStudentCommand(INDEX_FIRST_ITEM,
                new EditStudentDescriptorBuilder()
                        .withStudentName(VALID_STUDENT_NAME_BOB)
                        .withParentPhone(VALID_PARENT_PHONE_AMY)
                        .build());
        editCommand.execute(model);
        assertEquals(editedStudent.getStudentName(), model.getFilteredStudentList().get(0).getStudentName());
        assertEquals(editedStudent.getParentPhone(), model.getFilteredStudentList().get(0).getParentPhone());
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_failure() {
        Model model = new ModelManager(getTypicalStudentBook(), getTypicalLessonBook(), new UserPrefs());
        EditStudentCommand editCommand = new EditStudentCommand(INDEX_FIRST_ITEM, new EditStudentDescriptor());
        assertThrows(CommandException.class, () -> {
            editCommand.execute(model);
        });
    }

    @Test
    public void execute_filteredList_success() throws CommandException {
        Model model = new ModelManager(getTypicalStudentBook(), getTypicalLessonBook(), new UserPrefs());
        showStudentAtIndex(model, INDEX_FIRST_ITEM);
        Student editedStudent = new StudentBuilder()
                .withStudentName(VALID_STUDENT_NAME_BOB)
                .build();
        EditStudentCommand editCommand = new EditStudentCommand(INDEX_FIRST_ITEM,
                new EditStudentDescriptorBuilder()
                        .withStudentName(VALID_STUDENT_NAME_BOB)
                        .build());
        editCommand.execute(model);
        assertEquals(editedStudent.getStudentName(), model.getFilteredStudentList().get(0).getStudentName());
    }

    @Test
    public void execute_duplicateStudentUnfilteredList_failure() {
        Model model = new ModelManager(getTypicalStudentBook(), getTypicalLessonBook(), new UserPrefs());
        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_ITEM.getZeroBased());
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(firstStudent).build();
        EditStudentCommand editCommand = new EditStudentCommand(INDEX_SECOND_ITEM, descriptor);

        assertCommandFailure(editCommand, model, EditStudentCommand.MESSAGE_DUPLICATE_STUDENT);
    }

    @Test
    public void execute_duplicateStudentFilteredList_failure() {
        Model model = new ModelManager(getTypicalStudentBook(), getTypicalLessonBook(), new UserPrefs());
        showStudentAtIndex(model, INDEX_FIRST_ITEM);

        // edit person in filtered list into a duplicate in address book
        Student personInList = model.getStudentBook().getStudentList().get(INDEX_SECOND_ITEM.getZeroBased());
        EditStudentCommand editCommand = new EditStudentCommand(INDEX_FIRST_ITEM,
                new EditStudentDescriptorBuilder(personInList).build());

        assertCommandFailure(editCommand, model, EditStudentCommand.MESSAGE_DUPLICATE_STUDENT);
    }

    @Test
    public void execute_invalidStudentIndexUnfilteredList_failure() {
        Model model = new ModelManager(getTypicalStudentBook(), getTypicalLessonBook(), new UserPrefs());
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder()
                .withStudentName(VALID_STUDENT_NAME_BOB)
                .build();
        EditStudentCommand editCommand = new EditStudentCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidStudentIndexFilteredList_failure() {
        Model model = new ModelManager(getTypicalStudentBook(), getTypicalLessonBook(), new UserPrefs());
        showStudentAtIndex(model, INDEX_FIRST_ITEM);
        Index outOfBoundIndex = INDEX_SECOND_ITEM;

        // ensures that outOfBoundIndex is still in bounds of student book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getStudentBook().getStudentList().size());

        EditStudentCommand editCommand = new EditStudentCommand(outOfBoundIndex,
                new EditStudentDescriptorBuilder().withStudentName(VALID_STUDENT_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditStudentCommand standardCommand = new EditStudentCommand(INDEX_FIRST_ITEM, DESC_AMY);

        // same values -> returns true
        EditStudentDescriptor copyDescriptor = new EditStudentDescriptor(DESC_AMY);
        EditStudentCommand commandWithSameValues = new EditStudentCommand(INDEX_FIRST_ITEM, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditStudentCommand(INDEX_SECOND_ITEM, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditStudentCommand(INDEX_FIRST_ITEM, DESC_BOB)));
    }

}
