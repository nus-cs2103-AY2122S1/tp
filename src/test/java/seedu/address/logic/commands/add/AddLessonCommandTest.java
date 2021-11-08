package seedu.address.logic.commands.add;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyModBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.exam.Exam;
import seedu.address.model.module.lesson.Lesson;
import seedu.address.testutil.builders.LessonBuilder;
import seedu.address.testutil.builders.ModuleBuilder;

public class AddLessonCommandTest {

    @Test
    public void constructor_nullLesson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddLessonCommand(null));
    }

    @Test
    public void execute_lessonAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingLessonAdded modelStub = new ModelStubAcceptingLessonAdded();
        Lesson validLesson = new LessonBuilder().withName("test").build();
        Module validModule = new ModuleBuilder().build();

        CommandResult commandResult = new AddLessonCommand(validLesson).execute(modelStub);

        assertEquals(String.format(AddLessonCommand.MESSAGE_SUCCESS, validLesson), commandResult.getFeedbackToUser());
        assertEquals(List.of(validLesson), modelStub.lessonsAdded);
    }

    @Test
    public void execute_duplicateModule_throwsCommandException() {
        Lesson validLesson = new LessonBuilder().build();
        Module validModule = new ModuleBuilder().build();

        AddLessonCommand addCommand = new AddLessonCommand(validLesson);
        ModelStub modelStub = new ModelStubWithLesson(validLesson);

        assertThrows(CommandException.class, AddLessonCommand.MESSAGE_DUPLICATE_LESSON, () ->
                addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Lesson alice = new LessonBuilder().withName("Alice").build();
        Lesson bob = new LessonBuilder().withName("Bob").build();
        AddLessonCommand addAliceCommand = new AddLessonCommand(alice);
        AddLessonCommand addBobCommand = new AddLessonCommand(bob);

        // same object -> returns true
        assertEquals(addAliceCommand, addAliceCommand);

        // same values -> returns true
        AddLessonCommand addAliceCommandCopy = new AddLessonCommand(alice);
        assertEquals(addAliceCommand, addAliceCommandCopy);

        // different types -> returns false
        assertNotEquals(1, addAliceCommand);

        // null -> returns false
        assertNotEquals(null, addAliceCommand);

        // different Lesson -> returns false
        assertNotEquals(addAliceCommand, addBobCommand);
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addModule(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Module getModule(ModuleCode modCode) {
            return null;
        }

        @Override
        public boolean moduleHasLesson(Module module, Lesson lesson) {
            return false;
        }

        @Override
        public void addLessonToModule(Module module, Lesson lesson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean moduleHasExam(Module module, Exam exam) {
            return false;
        }

        @Override
        public void addExamToModule(Module module, Exam exam) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasModule(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteModule(Module target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setModule(Module target, Module editedModule) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Module> getFilteredModuleList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredModuleList(Predicate<Module> predicate) {
            return; // add lesson actually uses this to go into details view
        }

        @Override
        public Path getModBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setModBookFilePath(Path modBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setModBook(ReadOnlyModBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyModBook getModBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteExam(Module module, Exam target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteLesson(Module module, Lesson target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setExam(Module module, Exam target, Exam newExam) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setLesson(Module module, Lesson target, Lesson newLesson) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * An ObservableList stub that contains a single Module.
     */
    private class ObservableListModule extends ObservableListBase<Module> {
        @Override
        public Module get(int index) {
            return new ModuleBuilder().build();
        }

        @Override
        public int size() {
            return 0;
        }
    }

    /**
     * A Model stub that contains a single Lesson.
     */
    private class ModelStubWithLesson extends ModelStub {
        private final Lesson lesson;

        ModelStubWithLesson(Lesson lesson) {
            requireNonNull(lesson);
            this.lesson = lesson;
        }

        @Override
        public ObservableList<Module> getFilteredModuleList() {
            return new ObservableListModule();
        }

        @Override
        public boolean moduleHasLesson(Module module, Lesson lesson) {
            requireNonNull(lesson);
            return this.lesson.isSameLesson(lesson);
        }
    }

    /**
     * A Model stub that always accept the Lesson being added.
     */
    private class ModelStubAcceptingLessonAdded extends ModelStub {
        final ArrayList<Lesson> lessonsAdded = new ArrayList<>();

        @Override
        public ObservableList<Module> getFilteredModuleList() {
            return new ObservableListModule();
        }

        @Override
        public boolean moduleHasLesson(Module module, Lesson lesson) {
            requireNonNull(lesson);
            return lessonsAdded.stream().anyMatch(lesson::isSameLesson);
        }

        @Override
        public void addLessonToModule(Module module, Lesson lesson) {
            requireNonNull(lesson);
            lessonsAdded.add(lesson);
        }

        @Override
        public ReadOnlyModBook getModBook() {
            return new ModBook();
        }
    }
}
