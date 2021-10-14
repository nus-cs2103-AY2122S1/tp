package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;
import seedu.address.logic.UndoRedoStack;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.LessonBuilder;
import seedu.address.testutil.PersonBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_LESSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

class UndoCommandTest {
    private static final Model DEFAULT_MODEL = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private DummyCommand dummyCommand = new DummyCommand();
    private DummyUndoableCommand dummyUndoableCommandOne = new DummyUndoableCommand();
    private DummyUndoableCommand dummyUndoableCommandTwo = new DummyUndoableCommand();

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private UndoRedoStack undoRedoStack = new UndoRedoStack();

    @Test
    public void execute_success() {
        //Non-Undoable Command executed before undo chain
        UndoCommand undoCommand = prepareUndoCommandIntegrated(Arrays.asList(dummyCommand,
                dummyUndoableCommandOne, dummyUndoableCommandTwo));

        //2 Undoable commands already called
        assertCommandSuccess(undoCommand, model, UndoCommand.MESSAGE_SUCCESS, DEFAULT_MODEL);
        assertStackStatus(Collections.singletonList(dummyUndoableCommandOne)
                , Collections.singletonList(dummyUndoableCommandTwo));

        //1 Undoable command already called
        assertCommandSuccess(undoCommand, model, UndoCommand.MESSAGE_SUCCESS, DEFAULT_MODEL);
        assertStackStatus(Collections.emptyList(), Arrays.asList(dummyUndoableCommandTwo, dummyUndoableCommandOne));
    }

    @Test
    public void execute_failure() {
        //non-Undoable Command executed before undo
        UndoCommand undoCommand = prepareUndoCommandIntegrated(Collections.singletonList(dummyCommand));
        assertCommandFailure(undoCommand, model, UndoCommand.MESSAGE_FAILURE);
        assertStackStatus(Collections.emptyList(), Collections.emptyList());

        //No commands to undo
        assertCommandFailure(undoCommand, model, UndoCommand.MESSAGE_FAILURE);
        assertStackStatus(Collections.emptyList(), Collections.emptyList());
    }

    @Test
    public void execute_undoAddCommand() {
        //Add valid person to empty default model and empty undoRedoStack
        Person validPerson = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validPerson);
        UndoCommand undoCommand = prepareUndoCommandIntegrated(Collections.singletonList(addCommand));

        //Check stack after Add Command executed
        assertStackStatus(Collections.singletonList(addCommand), Collections.emptyList());

        //Undo Add Command
        assertCommandSuccess(undoCommand, model, UndoCommand.MESSAGE_SUCCESS, DEFAULT_MODEL);
        assertStackStatus(Collections.emptyList(), Collections.singletonList(addCommand));
    }

    @Test
    public void execute_undoClearCommand() {
        ClearCommand clearCommand = new ClearCommand();
        UndoCommand undoCommand = prepareUndoCommandIntegrated(
                Collections.singletonList(clearCommand));

        //Check stack after Clear Command
        assertStackStatus(Collections.singletonList(clearCommand), Collections.emptyList());

        //Undo Clear Command
        assertCommandSuccess(undoCommand, model, UndoCommand.MESSAGE_SUCCESS, DEFAULT_MODEL);
        assertStackStatus(Collections.emptyList(), Collections.singletonList(clearCommand));
    }

    @Test
    public void execute_undoDeleteCommand() {
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);
        UndoCommand undoCommand = prepareUndoCommandIntegrated(Collections.singletonList(deleteCommand));

        //Check stack after Delete Command
        assertStackStatus(Collections.singletonList(deleteCommand), Collections.emptyList());

        //Undo Delete Command
        assertCommandSuccess(undoCommand, model, UndoCommand.MESSAGE_SUCCESS, DEFAULT_MODEL);
        assertStackStatus(Collections.emptyList(), Collections.singletonList(deleteCommand));
    }

    @Test
    public void execute_undoEditCommand() {
        Person editedPerson = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);
        UndoCommand undoCommand = prepareUndoCommandIntegrated(Collections.singletonList(editCommand));

        //Check stack after Edit Command
        assertStackStatus(Collections.singletonList(editCommand), Collections.emptyList());

        //Undo Edit Command
        assertCommandSuccess(undoCommand, model, UndoCommand.MESSAGE_SUCCESS, DEFAULT_MODEL);
        assertStackStatus(Collections.emptyList(), Collections.singletonList(editCommand));
    }

    @Test
    public void execute_undoLessonAddCommand() {
        Lesson sampleLesson = new LessonBuilder().build();
        LessonAddCommand lessonAddCommand = new LessonAddCommand(INDEX_FIRST_PERSON, sampleLesson);
        UndoCommand undoCommand = prepareUndoCommandIntegrated(Collections.singletonList(lessonAddCommand));

        //Check stack after Lesson Add
        assertStackStatus(Collections.singletonList(lessonAddCommand), Collections.emptyList());

        //Undo Lesson Add Command
        assertCommandSuccess(undoCommand, model, UndoCommand.MESSAGE_SUCCESS, DEFAULT_MODEL);
        assertStackStatus(Collections.emptyList(), Collections.singletonList(lessonAddCommand));
    }

    @Test
    public void execute_undoLessonDeleteCommand() {
        //Add lesson to first student
        Lesson lesson = new LessonBuilder().build();
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withLessons(lesson).build();
        model.setPerson(firstPerson, editedPerson);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        LessonDeleteCommand lessonDeleteCommand = new LessonDeleteCommand(INDEX_FIRST_PERSON, INDEX_FIRST_LESSON);
        UndoCommand undoCommand = prepareUndoCommandIntegrated(Collections.singletonList(lessonDeleteCommand));

        //Check stack after Lesson Delete Command
        assertStackStatus(Collections.singletonList(lessonDeleteCommand), Collections.emptyList());

        //Undo Lesson Delete Command
        assertCommandSuccess(undoCommand, model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
        assertStackStatus(Collections.emptyList(), Collections.singletonList(lessonDeleteCommand));
    }

    private UndoCommand prepareUndoCommandIntegrated(List<Command> commandsBeforeUndo) {
        //preparing the stack
        undoRedoStack = new UndoRedoStack();
        commandsBeforeUndo.forEach(command -> {
            command.setDependencies(model, undoRedoStack);
            try {
                command.execute();
                undoRedoStack.pushUndoableCommand(command);
            } catch (CommandException ce) {
                throw new AssertionError("Commands prepared should not induce error.");
            }
        });
        UndoCommand undoCommand = new UndoCommand();
        undoCommand.setDependencies(model, undoRedoStack);
        return undoCommand;
    }

    /**
     * Asserts that {@code undoRedoStack#undoStack} equals {@code undoElements}, and {@code undoRedoStack#redoStack}
     * equals {@code redoElements}.
     */
    private void assertStackStatus(List<UndoableCommand> undoElements, List<UndoableCommand> redoElements) {
        assertEquals(prepareStack(undoElements, redoElements), undoRedoStack);
    }

    /**
     * Prepare Undo Redo stack with required undo and redo commands.
     *
     * @param undoCommands List of undo commands in desired sequence.
     * @param redoCommands List of redo commands in desired redo sequence.
     * @return prepared UndoRedoStack.
     */
    private UndoRedoStack prepareStack(List<UndoableCommand> undoCommands,
                                       List<UndoableCommand> redoCommands) {
        UndoRedoStack stack = new UndoRedoStack();
        undoCommands.forEach(stack :: pushUndoableCommand);

        Collections.reverse(redoCommands);
        redoCommands.forEach(stack :: pushUndoableCommand);
        redoCommands.forEach(toRedo -> stack.popUndo());

        return stack;
    }

    private class DummyCommand extends Command {
        @Override
        public CommandResult execute() {
            return new CommandResult("");
        }
    }

    private class DummyUndoableCommand extends UndoableCommand {
        @Override
        public CommandResult executeUndoableCommand() {
            return new CommandResult("");
        }
        @Override
        protected void undo() {}
        @Override
        protected void redo() {}
    }
}