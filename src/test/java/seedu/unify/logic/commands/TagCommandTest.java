package seedu.unify.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.unify.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.unify.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.unify.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.unify.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.unify.testutil.TypicalTasks.getTypicalUniFy;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.unify.commons.core.Messages;
import seedu.unify.commons.core.index.Index;
import seedu.unify.model.Model;
import seedu.unify.model.ModelManager;
import seedu.unify.model.UserPrefs;
import seedu.unify.model.tag.Tag;
import seedu.unify.model.task.Task;

public class TagCommandTest {

    private Model model = new ModelManager(getTypicalUniFy(), new UserPrefs());

    @Test
    public void execute_validIndexEditTag_success() {
        TagCommand.TagTaskDescriptor tagTaskDescriptor = new TagCommand.TagTaskDescriptor();
        Set<Tag> newTags = new HashSet<>();
        newTags.add(new Tag("Tag1"));
        tagTaskDescriptor.setTags(newTags);

        Task taggedTask = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        TagCommand tagCommand = new TagCommand(INDEX_FIRST_TASK, tagTaskDescriptor);

        Task expectedTask = new Task(
                taggedTask.getName(),
                taggedTask.getTime(),
                taggedTask.getDate(),
                newTags,
                taggedTask.getState(),
                taggedTask.getPriority());

        String expectedMessage = String.format(TagCommand.MESSAGE_TAG_TASK_SUCCESS, expectedTask);

        Model expectedModel = new ModelManager(getTypicalUniFy(), new UserPrefs());
        expectedModel.setTask(model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased()), expectedTask);


        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexTagCommand_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        TagCommand.TagTaskDescriptor tagTaskDescriptor = new TagCommand.TagTaskDescriptor();
        TagCommand tagCommand = new TagCommand(outOfBoundIndex, tagTaskDescriptor);

        assertCommandFailure(tagCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidTagLength_throwsIllegalArgumentException() {
        Set<Tag> newTags = new HashSet<>();

        assertThrows(IllegalArgumentException.class, ()-> newTags.add(new Tag("A123456789123456")));

    }




    @Test
    public void equals() {
        TagCommand.TagTaskDescriptor tagTaskDescriptor = new TagCommand.TagTaskDescriptor();
        TagCommand firstTag = new TagCommand(INDEX_FIRST_TASK, tagTaskDescriptor);
        TagCommand secondTag = new TagCommand(INDEX_SECOND_TASK, tagTaskDescriptor);


        // same object -> returns true
        assertTrue(firstTag.equals(firstTag));

        // same values -> returns true
        TagCommand firstCopy = new TagCommand(INDEX_FIRST_TASK, tagTaskDescriptor);
        assertTrue(firstTag.equals(firstCopy));

        // different types -> returns false
        assertFalse(firstTag.equals(INDEX_FIRST_TASK));

        // null -> returns false
        assertFalse(firstTag.equals(null));

        // different tasks -> returns false
        assertFalse(firstTag.equals(secondTag));
    }
}
