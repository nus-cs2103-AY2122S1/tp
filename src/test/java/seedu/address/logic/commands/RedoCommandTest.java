package seedu.address.logic.commands;

import static seedu.address.logic.UndoRedoStackTestUtil.assertStackStatus;
import static seedu.address.logic.UndoRedoStackTestUtil.prepareStack;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.PersonBuilder.DEFAULT_NAME;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_LESSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.UndoRedoStack;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.LessonBuilder;
import seedu.address.testutil.PersonBuilder;


class RedoCommandTest {
    private static final Model DEFAULT_MODEL = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final DummyUndoableCommand dummyUndoableCommandOne = new DummyUndoableCommand();
    private final DummyUndoableCommand dummyUndoableCommandTwo = new DummyUndoableCommand();

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel;
    private UndoRedoStack undoRedoStack = new UndoRedoStack();
    private Person studentModified =
            DEFAULT_MODEL.getAddressBook().getPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

    @Test
    public void execute_success() {
        //prepare stack with 2 commands undone
        undoRedoStack = prepareStack(Collections.emptyList(),
                Arrays.asList(dummyUndoableCommandOne, dummyUndoableCommandTwo));
        RedoCommand redoCommand = new RedoCommand();

        String expectedMessage = String.format(RedoCommand.MESSAGE_SUCCESS, "Dummy", DEFAULT_NAME);
        //2 Undoable commands in redo stack
        redoCommand.setDependencies(model, undoRedoStack);
        assertCommandSuccess(redoCommand, model, expectedMessage, DEFAULT_MODEL);
        assertStackStatus(Collections.singletonList(dummyUndoableCommandTwo),
                Collections.singletonList(dummyUndoableCommandOne), undoRedoStack);

        //1 Undoable command in redo stack
        redoCommand.setDependencies(model, undoRedoStack);
        assertCommandSuccess(redoCommand, model, expectedMessage, DEFAULT_MODEL);
        assertStackStatus(Arrays.asList(dummyUndoableCommandTwo, dummyUndoableCommandOne),
                Collections.emptyList(), undoRedoStack);
    }

    @Test
    public void execute_failure() {
        //No commands to redo
        undoRedoStack = prepareStack(Collections.emptyList(), Collections.emptyList());
        RedoCommand redoCommand = new RedoCommand();
        redoCommand.setDependencies(model, undoRedoStack);
        assertCommandFailure(redoCommand, model, RedoCommand.MESSAGE_FAILURE);
    }

    @Test
    public void execute_redoAddCommand() {
        //Add valid person to empty default model and empty undoRedoStack
        Person validPerson = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validPerson);
        RedoCommand redoCommand = prepareRedoCommandIntegrated(Collections.singletonList(addCommand), 1);

        //Check stack after Command executed
        assertStackStatus(Collections.emptyList(), Collections.singletonList(addCommand), undoRedoStack);

        String expectedMessage = String.format(RedoCommand.MESSAGE_SUCCESS, AddCommand.COMMAND_ACTION, DEFAULT_NAME);
        //Redo Add command
        assertCommandSuccess(redoCommand, model, expectedMessage, expectedModel);
        assertStackStatus(Collections.singletonList(addCommand), Collections.emptyList(), undoRedoStack);
    }

    @Test
    public void execute_redoClearCommand() {
        ClearCommand clearCommand = new ClearCommand();
        RedoCommand redoCommand = prepareRedoCommandIntegrated(Collections.singletonList(clearCommand), 1);

        //Check stack after Command executed
        assertStackStatus(Collections.emptyList(), Collections.singletonList(clearCommand), undoRedoStack);

        String expectedMessage = ClearCommand.COMMAND_ACTION + " command has been redone.";
        //Redo Clear Command
        assertCommandSuccess(redoCommand, model, expectedMessage, expectedModel);
        assertStackStatus(Collections.singletonList(clearCommand), Collections.emptyList(), undoRedoStack);
    }

    @Test
    public void execute_redoDeleteCommand() {
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);
        RedoCommand redoCommand = prepareRedoCommandIntegrated(Collections.singletonList(deleteCommand), 1);

        //Check stack after Delete Command
        assertStackStatus(Collections.emptyList(), Collections.singletonList(deleteCommand), undoRedoStack);

        String expectedMessage = DeleteCommand.COMMAND_ACTION + " command has been redone.";
        //Redo Delete Command
        assertCommandSuccess(redoCommand, model, expectedMessage, expectedModel);
        assertStackStatus(Collections.singletonList(deleteCommand), Collections.emptyList(), undoRedoStack);
    }

    @Test
    public void execute_redoEditCommand() {
        Person editedPerson = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);
        RedoCommand redoCommand = prepareRedoCommandIntegrated(Collections.singletonList(editCommand), 1);

        //Check stack after Edit Command
        assertStackStatus(Collections.emptyList(), Collections.singletonList(editCommand), undoRedoStack);

        String expectedMessage = String.format(RedoCommand.MESSAGE_SUCCESS,
                EditCommand.COMMAND_ACTION, DEFAULT_NAME);
        //Redo Edit Command
        assertCommandSuccess(redoCommand, model, expectedMessage, expectedModel);
        assertStackStatus(Collections.singletonList(editCommand), Collections.emptyList(), undoRedoStack);
    }


    @Test
    public void execute_redoLessonAddCommand() {
        Lesson sampleLesson = new LessonBuilder().build();
        LessonAddCommand lessonAddCommand = new LessonAddCommand(INDEX_FIRST_PERSON, sampleLesson);
        RedoCommand redoCommand = prepareRedoCommandIntegrated(Collections.singletonList(lessonAddCommand), 1);

        //Check stack after Lesson Add
        assertStackStatus(Collections.emptyList(), Collections.singletonList(lessonAddCommand), undoRedoStack);

        String expectedMessage = String.format(RedoCommand.MESSAGE_SUCCESS,
                LessonAddCommand.COMMAND_ACTION, studentModified.getName());
        //Redo Lesson Add Command
        assertCommandSuccess(redoCommand, model, expectedMessage, expectedModel);
        assertStackStatus(Collections.singletonList(lessonAddCommand), Collections.emptyList(), undoRedoStack);
    }

    @Test
    public void execute_redoLessonDeleteCommand() {
        //Add lesson to first student
        Lesson lesson = new LessonBuilder().build();
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withLessons(lesson).build();
        model.setPerson(firstPerson, editedPerson);

        LessonDeleteCommand lessonDeleteCommand = new LessonDeleteCommand(INDEX_FIRST_PERSON, INDEX_FIRST_LESSON);
        RedoCommand redoCommand = prepareRedoCommandIntegrated(Collections.singletonList(lessonDeleteCommand), 1);

        //Check stack after Lesson Delete Command
        assertStackStatus(Collections.emptyList(), Collections.singletonList(lessonDeleteCommand), undoRedoStack);

        String expectedMessage = String.format(RedoCommand.MESSAGE_SUCCESS,
                LessonDeleteCommand.COMMAND_ACTION, studentModified.getName());
        //Redo Lesson Delete Command
        assertCommandSuccess(redoCommand, model, expectedMessage, expectedModel);
        assertStackStatus(Collections.singletonList(lessonDeleteCommand), Collections.emptyList(), undoRedoStack);
    }

    private Model snapshotModel() {
        return new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
    }

    /**
     * Prepares an UndoRedoStack by executing Commands to prepare undo stack
     * then executing UndoCommands to prepare redo stack.
     *
     * @param commandsBeforeRedo Commands executed before created redo command is executed.
     * @param undoTimes Number of commands to append to redo stack.
     * @return prepared Redo Command with correct model and undoRedoStack.
     */
    private RedoCommand prepareRedoCommandIntegrated(List<UndoableCommand> commandsBeforeRedo, int undoTimes) {
        undoRedoStack = new UndoRedoStack();
        commandsBeforeRedo.forEach(command -> {
            command.setDependencies(model, undoRedoStack);
            try {
                command.execute();
                undoRedoStack.pushUndoableCommand(command);
            } catch (CommandException ce) {
                throw new AssertionError("Commands prepared should not induce error.");
            }
        });

        expectedModel = snapshotModel();

        for (int i = 0; i < undoTimes; i++) {
            if (commandsBeforeRedo.get(i) instanceof UndoableCommand) {
                UndoCommand undoCommand = new UndoCommand();
                undoCommand.setDependencies(model, undoRedoStack);
                try {
                    undoCommand.execute();
                } catch (CommandException ce) {
                    throw new AssertionError("Commands should be undoable.");
                }
            }
        }
        RedoCommand redoCommand = new RedoCommand();
        redoCommand.setDependencies(model, undoRedoStack);
        return redoCommand;
    }

    private class DummyCommand extends Command {
        @Override
        public CommandResult execute() {
            return new CommandResult("");
        }
    }

    private class DummyUndoableCommand extends UndoableCommand {
        private DummyUndoableCommand() {
            super("Dummy");
        }
        @Override
        public CommandResult executeUndoableCommand() {
            return new CommandResult("");
        }
        @Override
        protected Person undo() {
            return new PersonBuilder().build();
        }
        @Override
        protected Person redo() {
            return new PersonBuilder().build();
        }
    }
}
