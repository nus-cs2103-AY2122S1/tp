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
import seedu.address.testutil.builders.ExamBuilder;
import seedu.address.testutil.builders.ModuleBuilder;

public class AddExamCommandTest {

    @Test
    public void constructor_nullExam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddExamCommand(null));
    }

    @Test
    public void execute_examAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingExamAdded modelStub = new ModelStubAcceptingExamAdded();
        Exam validExam = new ExamBuilder().build();

        CommandResult commandResult = new AddExamCommand(validExam).execute(modelStub);

        assertEquals(String.format(AddExamCommand.MESSAGE_SUCCESS, validExam), commandResult.getFeedbackToUser());
        assertEquals(List.of(validExam), modelStub.examsAdded);
    }

    @Test
    public void execute_duplicateModule_throwsCommandException() {
        Exam validExam = new ExamBuilder().build();

        AddExamCommand addCommand = new AddExamCommand(validExam);
        ModelStub modelStub = new ModelStubWithExam(validExam);

        assertThrows(CommandException.class, AddExamCommand.MESSAGE_DUPLICATE_EXAM, () ->
                addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Exam alice = new ExamBuilder().withName("Alice").build();
        Exam bob = new ExamBuilder().withName("Bob").build();
        AddExamCommand addAliceCommand = new AddExamCommand(alice);
        AddExamCommand addBobCommand = new AddExamCommand(bob);

        // same object -> returns true
        assertEquals(addAliceCommand, addAliceCommand);

        // same values -> returns true
        AddExamCommand addAliceCommandCopy = new AddExamCommand(alice);
        assertEquals(addAliceCommand, addAliceCommandCopy);

        // different types -> returns false
        assertNotEquals(1, addAliceCommand);

        // null -> returns false
        assertNotEquals(null, addAliceCommand);

        // different Exam -> returns false
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
            return; // add exam actually uses this to go into details view
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
     * A Model stub that contains a single Exam.
     */
    private class ModelStubWithExam extends ModelStub {
        private final Exam exam;

        ModelStubWithExam(Exam exam) {
            requireNonNull(exam);
            this.exam = exam;
        }

        @Override
        public ObservableList<Module> getFilteredModuleList() {
            return new ObservableListModule();
        }

        @Override
        public boolean moduleHasExam(Module module, Exam exam) {
            requireNonNull(exam);
            return this.exam.isSameExam(exam);
        }
    }

    /**
     * A Model stub that always accept the Exam being added.
     */
    private class ModelStubAcceptingExamAdded extends ModelStub {
        final ArrayList<Exam> examsAdded = new ArrayList<>();

        @Override
        public ObservableList<Module> getFilteredModuleList() {
            return new ObservableListModule();
        }

        @Override
        public boolean moduleHasExam(Module module, Exam exam) {
            requireNonNull(exam);
            return examsAdded.stream().anyMatch(exam::isSameExam);
        }

        @Override
        public void addExamToModule(Module module, Exam exam) {
            requireNonNull(exam);
            examsAdded.add(exam);
        }

        @Override
        public ReadOnlyModBook getModBook() {
            return new ModBook();
        }
    }
}
