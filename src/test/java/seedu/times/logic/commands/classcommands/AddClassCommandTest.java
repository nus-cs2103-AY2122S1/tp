package seedu.times.logic.commands.classcommands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.times.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.times.commons.core.GuiSettings;
import seedu.times.commons.core.Messages;
import seedu.times.logic.commands.CommandResult;
import seedu.times.logic.commands.exceptions.CommandException;
import seedu.times.model.Model;
import seedu.times.model.ReadOnlyTimesTable;
import seedu.times.model.ReadOnlyUserPrefs;
import seedu.times.model.TimesTable;
import seedu.times.model.person.Name;
import seedu.times.model.person.Student;
import seedu.times.model.tuitionclass.TuitionClass;
import seedu.times.testutil.TimesTableBuilder;
import seedu.times.testutil.TuitionClassBuilder;

public class AddClassCommandTest {

    @Test
    public void constructor_nullTuitionClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddClassCommand(null));
    }

    @Test
    public void execute_tuitionClassAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTuitionClassAdded modelStub = new ModelStubAcceptingTuitionClassAdded();

        //create TuitionClass to be added
        TuitionClass classToAdd = new TuitionClassBuilder().build();
        CommandResult commandResult = new AddClassCommand(classToAdd).execute(modelStub);

        assertEquals(String.format(AddClassCommand.MESSAGE_SUCCESS, classToAdd), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(classToAdd), modelStub.tuitionClassesAdded);
    }

    @Test
    public void execute_duplicateTuitionClass_throwsCommandException() {
        TuitionClass validTuitionClass = new TuitionClassBuilder().build();
        AddClassCommand addClassCommand = new AddClassCommand(validTuitionClass);
        ModelStub modelStub = new ModelStubWithTuitionClass(validTuitionClass);

        assertThrows(CommandException.class, Messages.MESSAGE_CLASHING_CLASS_TIMING, ()
            -> addClassCommand.execute(modelStub));
    }

    @Test
    public void execute_tuitionClassWithOverlappingClassTiming_throwsCommandException() {
        TuitionClass validTuitionClass1 = new TuitionClassBuilder().withClassTiming("SUN 11:30-13:30").build();
        TuitionClass validTuitionClass2 = new TuitionClassBuilder().withClassTiming("SUN 09:30-12:00").build();
        AddClassCommand addClassCommand = new AddClassCommand(validTuitionClass2);
        ModelStub modelStub = new ModelStubWithTuitionClass(validTuitionClass1);

        assertThrows(CommandException.class, Messages.MESSAGE_CLASHING_CLASS_TIMING, ()
            -> addClassCommand.execute(modelStub));
    }

    @Test
    public void equals_className() {
        TuitionClass classTiming13 = new TuitionClassBuilder().withClassName("Maths").build();
        TuitionClass classTiming12 = new TuitionClassBuilder().withClassName("Timing13").build();
        AddClassCommand addTiming12ClassCommand = new AddClassCommand(classTiming13);
        AddClassCommand addTiming13ClassCommand = new AddClassCommand(classTiming12);

        // same object -> returns true
        assertTrue(addTiming12ClassCommand.equals(addTiming12ClassCommand));

        // same values -> returns true
        AddClassCommand addTiming12ClassCommandCopy = new AddClassCommand(classTiming13);
        assertTrue(addTiming12ClassCommand.equals(addTiming12ClassCommandCopy));

        // different types -> returns false
        assertFalse(addTiming12ClassCommand.equals("null"));

        // null -> returns false
        assertFalse(addTiming12ClassCommand.equals(null));

        // different tuition class -> returns false
        assertFalse(addTiming12ClassCommand.equals(addTiming13ClassCommand));
    }

    @Test
    public void equals_classTiming() {
        TuitionClass classTiming12 = new TuitionClassBuilder().withClassTiming("TUE 12:00-13:30").build();
        TuitionClass classTiming13 = new TuitionClassBuilder().withClassTiming("TUE 13:00-15:00").build();
        AddClassCommand addTiming12ClassCommand = new AddClassCommand(classTiming12);
        AddClassCommand addTiming13ClassCommand = new AddClassCommand(classTiming13);

        // same object -> returns true
        assertTrue(addTiming12ClassCommand.equals(addTiming12ClassCommand));

        // same values -> returns true
        AddClassCommand addTiming12ClassCommandCopy = new AddClassCommand(classTiming12);
        assertTrue(addTiming12ClassCommand.equals(addTiming12ClassCommandCopy));

        // different types -> returns false
        assertFalse(addTiming12ClassCommand.equals("null"));

        // null -> returns false
        assertFalse(addTiming12ClassCommand.equals(null));

        // different tuition class -> returns false
        assertFalse(addTiming12ClassCommand.equals(addTiming13ClassCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
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
        public Path getTimesTableFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTimesTableFilePath(Path timesTableFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTimesTable getTimesTable() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTimesTable(ReadOnlyTimesTable newData) {
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
        public boolean hasTuitionClass(TuitionClass tuitionClass) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTuitionClass(TuitionClass tuitionClass) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTuitionClass(TuitionClass target) {
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
        public void updateFilteredClassList(Predicate<TuitionClass> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<TuitionClass> getFilteredTuitionClassList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClass(TuitionClass target, TuitionClass editedClass) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClasses(List<TuitionClass> classes) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStudents(List<Student> students) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateClassStudentLists(Name newName, Name oldName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortStudents(Comparator<? super Student> c) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortClasses(Comparator<? super TuitionClass> c) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single tuition class.
     */
    private class ModelStubWithTuitionClass extends ModelStub {
        private final TimesTable timesTable;

        ModelStubWithTuitionClass(TuitionClass tuitionClass) {
            requireNonNull(tuitionClass);
            this.timesTable = new TimesTableBuilder().withTuitionClass(tuitionClass).build();
        }

        @Override
        public void addTuitionClass(TuitionClass tuitionClass) {
            requireNonNull(tuitionClass);
            timesTable.addTuitionClass(tuitionClass);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingTuitionClassAdded extends ModelStub {
        final ArrayList<TuitionClass> tuitionClassesAdded = new ArrayList<>();

        @Override
        public void addTuitionClass(TuitionClass tuitionClass) {
            requireNonNull(tuitionClass);
            tuitionClassesAdded.add(tuitionClass);
        }

        @Override
        public ReadOnlyTimesTable getTimesTable() {
            return new TimesTable();
        }
    }
}
