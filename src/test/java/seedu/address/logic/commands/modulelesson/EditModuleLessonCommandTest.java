package seedu.address.logic.commands.modulelesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_MODULE_LESSON_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2030S;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2040S;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2030S_T12;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showLessonAtIndex;
import static seedu.address.logic.commands.modulelesson.EditModuleLessonCommand.MESSAGE_DUPLICATE_LESSON;
import static seedu.address.logic.commands.modulelesson.EditModuleLessonCommand.MESSAGE_EDIT_LESSON_SUCCESS;
import static seedu.address.logic.commands.modulelesson.EditModuleLessonCommand.MESSAGE_OVERLAPPING_LESSON;
import static seedu.address.model.util.SampleDataUtil.parseModuleCode;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalModuleLessons.getTypicalConthacks;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.modulelesson.EditModuleLessonCommand.EditLessonDescriptor;
import seedu.address.model.Conthacks;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.modulelesson.LessonDay;
import seedu.address.model.modulelesson.LessonTime;
import seedu.address.model.modulelesson.ModuleLesson;
import seedu.address.testutil.EditLessonDescriptorBuilder;
import seedu.address.testutil.ModuleLessonBuilder;

/**
 * Contains integration tests (interaction with {@code Model}) and unit tests for {@EditModuleLessonCommand}.
 */
public class EditModuleLessonCommandTest {

    private Model model = new ModelManager(getTypicalConthacks(), new UserPrefs());
    private Model expectedModel = new ModelManager(new Conthacks(model.getConthacks()), new UserPrefs());

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastLesson = Index.fromOneBased(model.getFilteredModuleLessonList().size());
        ModuleLesson lastLesson = model.getFilteredModuleLessonList().get(indexLastLesson.getZeroBased());

        ModuleLessonBuilder lessonInList = new ModuleLessonBuilder(lastLesson);
        ModuleLesson editedLesson = lessonInList.withModuleCode(VALID_MODULE_CODE_CS2030S_T12)
                .withLessonDay("5").build();

        EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder()
                .withModuleCode(parseModuleCode(VALID_MODULE_CODE_CS2030S_T12))
                .withLessonDay(new LessonDay("5")).build();
        EditModuleLessonCommand editModuleLessonCommand = new EditModuleLessonCommand(indexLastLesson, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_LESSON_SUCCESS, editedLesson);
        expectedModel.setModuleLesson(lastLesson, editedLesson);

        assertCommandSuccess(editModuleLessonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showLessonAtIndex(model, INDEX_FIRST);

        ModuleLesson lessonInFilteredList = model.getFilteredModuleLessonList().get(INDEX_FIRST.getZeroBased());
        ModuleLesson editedLesson = new ModuleLessonBuilder(lessonInFilteredList).withLessonDay("7").build();
        EditModuleLessonCommand command = new EditModuleLessonCommand(INDEX_FIRST,
                new EditLessonDescriptorBuilder().withLessonDay(new LessonDay("7")).build());
        String expectedMessage = String.format(MESSAGE_EDIT_LESSON_SUCCESS, editedLesson);

        expectedModel.setModuleLesson(lessonInFilteredList, editedLesson);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateLessonUnfilteredList_failure() {
        ModuleLesson firstLesson = model.getFilteredModuleLessonList().get(INDEX_FIRST.getZeroBased());
        EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder(firstLesson).build();
        EditModuleLessonCommand command = new EditModuleLessonCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(command, model, MESSAGE_DUPLICATE_LESSON);
    }

    @Test
    public void execute_duplicateLessonFilteredList_failure() {
        ModuleLesson firstLesson = model.getFilteredModuleLessonList().get(INDEX_FIRST.getZeroBased());

        showLessonAtIndex(model, INDEX_SECOND);

        EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder(firstLesson).build();
        EditModuleLessonCommand command = new EditModuleLessonCommand(INDEX_FIRST, descriptor);

        assertCommandFailure(command, model, MESSAGE_DUPLICATE_LESSON);
    }

    @Test
    public void execute_overlappingTimeLessonUnfilteredList_successWithWarning() {
        ModuleLesson firstLesson = model.getFilteredModuleLessonList().get(INDEX_FIRST.getZeroBased());
        ModuleLesson secondLesson = model.getFilteredModuleLessonList().get(INDEX_SECOND.getZeroBased());
        LessonDay day = firstLesson.getDay();
        LessonTime startTime = firstLesson.getLessonStartTime();
        LessonTime endTime = firstLesson.getLessonEndTime();
        EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder(secondLesson).withLessonDay(day)
                .withLessonStartTime(startTime).withLessonEndTime(endTime).build();
        ModuleLesson editedLesson = new ModuleLessonBuilder(secondLesson).withLessonDay(day.getDayAsIntString())
                .withLessonStartTime(startTime.toString()).withLessonEndTime(endTime.toString()).build();
        EditModuleLessonCommand command = new EditModuleLessonCommand(INDEX_SECOND, descriptor);

        String expectedMessage = String.format(MESSAGE_OVERLAPPING_LESSON, editedLesson);
        expectedModel.setModuleLesson(secondLesson, editedLesson);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_overlappingTimeLessonFilteredList_successWithWarning() {
        ModuleLesson firstLesson = model.getFilteredModuleLessonList().get(INDEX_FIRST.getZeroBased());
        ModuleLesson secondLesson = model.getFilteredModuleLessonList().get(INDEX_SECOND.getZeroBased());

        showLessonAtIndex(model, INDEX_SECOND);

        LessonDay day = firstLesson.getDay();
        LessonTime startTime = firstLesson.getLessonStartTime();
        LessonTime endTime = firstLesson.getLessonEndTime();
        EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder(secondLesson).withLessonDay(day)
                .withLessonStartTime(startTime).withLessonEndTime(endTime).build();
        ModuleLesson editedLesson = new ModuleLessonBuilder(secondLesson).withLessonDay(day.getDayAsIntString())
                .withLessonStartTime(startTime.toString()).withLessonEndTime(endTime.toString()).build();
        EditModuleLessonCommand command = new EditModuleLessonCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(MESSAGE_OVERLAPPING_LESSON, editedLesson);
        expectedModel.setModuleLesson(secondLesson, editedLesson);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index invalidIndex = Index.fromZeroBased(model.getFilteredModuleLessonList().size() + 1);
        EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder()
                        .withModuleCode(parseModuleCode(VALID_MODULE_CODE_CS2030S_T12)).build();
        EditModuleLessonCommand command = new EditModuleLessonCommand(invalidIndex, descriptor);

        assertCommandFailure(command, model, MESSAGE_INVALID_MODULE_LESSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showLessonAtIndex(model, INDEX_FIRST);
        Index invalidIndex = Index.fromZeroBased(model.getFilteredModuleLessonList().size() + 1);

        // ensures that invalidIndex is still in bounds of Conthacks list
        assertTrue(invalidIndex.getZeroBased() < model.getConthacks().getModuleLessonList().size());

        EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder()
                        .withModuleCode(parseModuleCode(VALID_MODULE_CODE_CS2030S_T12)).build();
        EditModuleLessonCommand command = new EditModuleLessonCommand(invalidIndex, descriptor);

        assertCommandFailure(command, model, MESSAGE_INVALID_MODULE_LESSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditModuleLessonCommand standardCommand = new EditModuleLessonCommand(INDEX_FIRST, DESC_CS2030S);

        // same values -> returns true
        EditLessonDescriptor copyDescriptor = new EditLessonDescriptor(DESC_CS2030S);
        EditModuleLessonCommand cmdWithSameValues = new EditModuleLessonCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(cmdWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> return false
        assertFalse(standardCommand.equals(null));

        // different type -> return false;
        assertFalse(standardCommand.equals(new ListModuleLessonCommandTest()));

        // different index -> return false;
        assertFalse(standardCommand.equals(new EditModuleLessonCommand(INDEX_SECOND, DESC_CS2030S)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditModuleLessonCommand(INDEX_FIRST, DESC_CS2040S)));
    }
}
