package seedu.tuitione.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.tuitione.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.tuitione.commons.core.GuiSettings;
import seedu.tuitione.logic.commands.exceptions.CommandException;
import seedu.tuitione.model.Model;
import seedu.tuitione.model.ReadOnlyTuitione;
import seedu.tuitione.model.ReadOnlyUserPrefs;
import seedu.tuitione.model.Tuitione;
import seedu.tuitione.model.lesson.Lesson;
import seedu.tuitione.model.lesson.LessonCode;
import seedu.tuitione.model.student.Name;
import seedu.tuitione.model.student.Student;
import seedu.tuitione.testutil.LessonBuilder;

public class AddLessonCommandTest {

    @Test
    public void constructor_nullLesson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddLessonCommand(null));
    }

    @Test
    public void execute_lessonAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingLessonAdded modelStub = new ModelStubAcceptingLessonAdded();
        Lesson validLesson = new LessonBuilder().build();

        CommandResult commandResult = new AddLessonCommand(validLesson).execute(modelStub);

        assertEquals(String.format(AddLessonCommand.MESSAGE_SUCCESS, validLesson), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validLesson), modelStub.lessonsAdded);
    }

    @Test
    public void execute_duplicateLesson_throwsCommandException() {
        Lesson validLesson = new LessonBuilder().build();
        AddLessonCommand addLessonCommand = new AddLessonCommand(validLesson);
        ModelStub modelStub = new ModelStubWithLesson(validLesson);

        assertThrows(CommandException.class,
                AddLessonCommand.MESSAGE_DUPLICATE_LESSON, () -> addLessonCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Lesson science = new LessonBuilder().withSubject("science").build();
        Lesson maths = new LessonBuilder().withSubject("maths").build();
        AddLessonCommand addScienceCommand = new AddLessonCommand(science);
        AddLessonCommand addMathsCommand = new AddLessonCommand(maths);

        // same object -> returns true
        assertEquals(addScienceCommand, addScienceCommand);

        // same values -> return true
        AddLessonCommand addScienceCommandCopy = new AddLessonCommand(science);
        assertEquals(addScienceCommand, addScienceCommandCopy);

        // different types -> return false
        assertNotEquals(1, addScienceCommand);

        // null -> return false
        assertNotEquals(null, addScienceCommand);

        //different lesson -> return false
        assertNotEquals(addMathsCommand, addScienceCommand);
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private static class ModelStub implements Model {
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
        public Path getTuitioneFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTuitioneFilePath(Path tuitioneFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTuitione(ReadOnlyTuitione newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTuitione getTuitione() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteStudent(Student target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStudent(Student target, Student editedStudent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredStudentList(Predicate<Student> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredLessonList(Predicate<Lesson> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasLesson(Lesson lesson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteLesson(Lesson lesson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addLesson(Lesson lesson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setLesson(Lesson target, Lesson editedLesson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Lesson> searchLessons(LessonCode lessonCode) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Student> searchStudents(Name lessonCode) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Lesson> getFilteredLessonList() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single lesson.
     */
    private class ModelStubWithLesson extends ModelStub {

        private final Lesson lesson;

        ModelStubWithLesson(Lesson lesson) {
            requireNonNull(lesson);
            this.lesson = lesson;
        }

        @Override
        public boolean hasLesson(Lesson lesson) {
            requireNonNull(lesson);
            return this.lesson.isSameLesson(lesson);
        }
    }

    /**
     * A Model stub that always accept the lesson being added.
     */
    private class ModelStubAcceptingLessonAdded extends ModelStub {
        final ArrayList<Lesson> lessonsAdded = new ArrayList<>();

        @Override
        public boolean hasLesson(Lesson lesson) {
            requireNonNull(lesson);
            return lessonsAdded.stream().anyMatch(lesson::isSameLesson);
        }

        @Override
        public void addLesson(Lesson lesson) {
            requireNonNull(lesson);
            lessonsAdded.add(lesson);
        }

        @Override
        public ReadOnlyTuitione getTuitione() {
            return new Tuitione();
        }
    }

}
