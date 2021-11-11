package tutoraid.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tutoraid.logic.commands.CommandTestUtil.DESC_MATH;
import static tutoraid.logic.commands.CommandTestUtil.DESC_SCIENCE;
import static tutoraid.logic.commands.CommandTestUtil.VALID_CAPACITY_MATHS_TWO;
import static tutoraid.logic.commands.CommandTestUtil.VALID_LESSON_NAME_MATHS_TWO;
import static tutoraid.logic.commands.CommandTestUtil.VALID_LESSON_NAME_SCIENCE_TWO;
import static tutoraid.logic.commands.CommandTestUtil.VALID_PRICE_MATHS_TWO;
import static tutoraid.logic.commands.CommandTestUtil.VALID_PRICE_SCIENCE_TWO;
import static tutoraid.logic.commands.CommandTestUtil.VALID_TIMING_SCIENCE_TWO;
import static tutoraid.logic.commands.CommandTestUtil.assertCommandFailure;
import static tutoraid.logic.commands.CommandTestUtil.showLessonAtIndex;
import static tutoraid.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static tutoraid.testutil.TypicalIndexes.INDEX_SECOND_ITEM;
import static tutoraid.testutil.TypicalLessons.getTypicalLessonBook;
import static tutoraid.testutil.TypicalStudents.getTypicalStudentBook;

import org.junit.jupiter.api.Test;

import tutoraid.commons.core.Messages;
import tutoraid.commons.core.index.Index;
import tutoraid.logic.commands.EditLessonCommand.EditLessonDescriptor;
import tutoraid.logic.commands.exceptions.CommandException;
import tutoraid.model.Model;
import tutoraid.model.ModelManager;
import tutoraid.model.UserPrefs;
import tutoraid.model.lesson.Lesson;
import tutoraid.testutil.EditLessonDescriptorBuilder;
import tutoraid.testutil.LessonBuilder;


public class EditLessonCommandTest {

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() throws CommandException {
        Model model = new ModelManager(getTypicalStudentBook(), getTypicalLessonBook(), new UserPrefs());
        Lesson editedLesson = new LessonBuilder()
                .withLessonName(VALID_LESSON_NAME_MATHS_TWO)
                .withPrice(VALID_PRICE_MATHS_TWO)
                .withCapacity(VALID_CAPACITY_MATHS_TWO)
                .withTiming(VALID_TIMING_SCIENCE_TWO)
                .build();
        EditLessonCommand editCommand = new EditLessonCommand(INDEX_FIRST_ITEM,
                new EditLessonDescriptorBuilder()
                        .withLessonName(VALID_LESSON_NAME_MATHS_TWO)
                        .withPrice(VALID_PRICE_MATHS_TWO)
                        .withCapacity(VALID_CAPACITY_MATHS_TWO)
                        .withTiming(VALID_TIMING_SCIENCE_TWO)
                        .build());
        editCommand.execute(model);
        assertEquals(editedLesson, model.getFilteredLessonList().get(0));
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() throws CommandException {
        Model model = new ModelManager(getTypicalStudentBook(), getTypicalLessonBook(), new UserPrefs());
        Lesson editedLesson = new LessonBuilder()
                .withLessonName(VALID_LESSON_NAME_MATHS_TWO)
                .withPrice(VALID_PRICE_SCIENCE_TWO)
                .build();
        EditLessonCommand editCommand = new EditLessonCommand(INDEX_FIRST_ITEM,
                new EditLessonDescriptorBuilder()
                        .withLessonName(VALID_LESSON_NAME_MATHS_TWO)
                        .withPrice(VALID_PRICE_SCIENCE_TWO)
                        .build());
        editCommand.execute(model);
        assertEquals(model.getFilteredLessonList().get(0), editedLesson);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_failure() throws CommandException {
        Model model = new ModelManager(getTypicalStudentBook(), getTypicalLessonBook(), new UserPrefs());
        EditLessonCommand editCommand = new EditLessonCommand(INDEX_FIRST_ITEM, new EditLessonDescriptor());
        assertThrows(CommandException.class, () -> {
            editCommand.execute(model);
        });
    }

    @Test
    public void execute_filteredList_success() throws CommandException {
        Model model = new ModelManager(getTypicalStudentBook(), getTypicalLessonBook(), new UserPrefs());
        showLessonAtIndex(model, INDEX_FIRST_ITEM);
        Lesson editedLesson = new LessonBuilder()
                .withLessonName(VALID_LESSON_NAME_MATHS_TWO)
                .build();
        EditLessonCommand editCommand = new EditLessonCommand(INDEX_FIRST_ITEM,
                new EditLessonDescriptorBuilder().withLessonName(VALID_LESSON_NAME_MATHS_TWO).build());
        editCommand.execute(model);
        assertEquals(model.getFilteredLessonList().get(0), editedLesson);
    }

    @Test
    public void execute_duplicateLessonUnfilteredList_failure() {
        Model model = new ModelManager(getTypicalStudentBook(), getTypicalLessonBook(), new UserPrefs());
        Lesson firstLesson = model.getFilteredLessonList().get(INDEX_FIRST_ITEM.getZeroBased());
        EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder(firstLesson).build();
        EditLessonCommand editCommand = new EditLessonCommand(INDEX_SECOND_ITEM, descriptor);
        assertCommandFailure(editCommand, model, EditLessonCommand.MESSAGE_DUPLICATE_LESSON);
    }

    @Test
    public void execute_duplicateLessonFilteredList_failure() {
        Model model = new ModelManager(getTypicalStudentBook(), getTypicalLessonBook(), new UserPrefs());
        showLessonAtIndex(model, INDEX_FIRST_ITEM);

        // edit person in filtered list into a duplicate in address book
        Lesson personInList = model.getLessonBook().getLessonList().get(INDEX_SECOND_ITEM.getZeroBased());
        EditLessonCommand editCommand = new EditLessonCommand(INDEX_FIRST_ITEM,
                new EditLessonDescriptorBuilder(personInList).build());

        assertCommandFailure(editCommand, model, EditLessonCommand.MESSAGE_DUPLICATE_LESSON);
    }

    @Test
    public void execute_invalidLessonIndexUnfilteredList_failure() {
        Model model = new ModelManager(getTypicalStudentBook(), getTypicalLessonBook(), new UserPrefs());
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredLessonList().size() + 1);
        EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder()
                .withLessonName(VALID_LESSON_NAME_SCIENCE_TWO)
                .build();
        EditLessonCommand editCommand = new EditLessonCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidLessonIndexFilteredList_failure() {
        Model model = new ModelManager(getTypicalStudentBook(), getTypicalLessonBook(), new UserPrefs());
        showLessonAtIndex(model, INDEX_FIRST_ITEM);
        Index outOfBoundIndex = INDEX_SECOND_ITEM;
        // ensures that outOfBoundIndex is still in bounds of lesson book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getLessonBook().getLessonList().size());

        EditLessonCommand editCommand = new EditLessonCommand(outOfBoundIndex,
                new EditLessonDescriptorBuilder().withLessonName(VALID_LESSON_NAME_MATHS_TWO).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditLessonCommand standardCommand = new EditLessonCommand(INDEX_FIRST_ITEM, DESC_MATH);

        // same values -> returns true
        EditLessonDescriptor copyDescriptor = new EditLessonDescriptor(DESC_MATH);
        EditLessonCommand commandWithSameValues = new EditLessonCommand(INDEX_FIRST_ITEM, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditLessonCommand(INDEX_SECOND_ITEM, DESC_MATH)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditLessonCommand(INDEX_FIRST_ITEM, DESC_SCIENCE)));
    }

}
