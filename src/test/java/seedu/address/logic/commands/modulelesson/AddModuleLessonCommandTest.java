package seedu.address.logic.commands.modulelesson;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Conthacks;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyConthacks;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.modulelesson.ModuleLesson;
import seedu.address.model.person.Person;
import seedu.address.testutil.ModuleLessonBuilder;

public class AddModuleLessonCommandTest {

    @Test
    public void constructor_nullModuleLesson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddModuleLessonCommand(null));
    }

    @Test
    public void execute_moduleLessonAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingModuleLessonAdded modelStub = new ModelStubAcceptingModuleLessonAdded();
        ModuleLesson validModuleLesson = new ModuleLessonBuilder().build();

        CommandResult commandResult = new AddModuleLessonCommand(validModuleLesson).execute(modelStub);

        assertEquals(String.format(AddModuleLessonCommand.MESSAGE_SUCCESS, validModuleLesson),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validModuleLesson), modelStub.moduleLessonsAdded);
    }

    @Test
    public void execute_duplicateModuleLesson_throwsCommandException() {
        ModuleLesson validModuleLesson = new ModuleLessonBuilder().build();
        AddModuleLessonCommand addModuleLessonCommand = new AddModuleLessonCommand(validModuleLesson);
        ModelStub modelStub = new ModelStubWithModuleLesson(validModuleLesson);

        assertThrows(CommandException.class,
                AddModuleLessonCommand.MESSAGE_DUPLICATE_LESSON, () -> addModuleLessonCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        ModuleLesson computerOrganisation = new ModuleLessonBuilder().withModuleCode("CS2100 B12").build();
        ModuleLesson softwareEngineering = new ModuleLessonBuilder().withModuleCode("CS2103T T12").build();
        AddModuleLessonCommand addComputerOrganisation = new AddModuleLessonCommand(computerOrganisation);
        AddModuleLessonCommand addSoftwareEngineering = new AddModuleLessonCommand(softwareEngineering);

        // same object -> returns true;
        assertTrue(addComputerOrganisation.equals(addComputerOrganisation));

        // same values -> returns true;
        AddModuleLessonCommand addComputerOrganisationCopy = new AddModuleLessonCommand(computerOrganisation);
        assertTrue(addComputerOrganisation.equals(addComputerOrganisationCopy));

        // different types -> return false
        assertFalse(addComputerOrganisation.equals(1));

        // null -> returns false
        assertFalse(addComputerOrganisation.equals(null));

        // different module lesson -> return false
        assertFalse(addComputerOrganisation.equals(addSoftwareEngineering));
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
        public Path getConthacksFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setConthacksFilePath(Path conthacksFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addLesson(ModuleLesson moduleLesson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setConthacks(ReadOnlyConthacks conthacks) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortConthacks() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyConthacks getConthacks() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasModuleLesson(ModuleLesson lesson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasModuleLessonClashingWith(ModuleLesson moduleLesson) {
            throw new AssertionError("This method should not be called.");

        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteLesson(ModuleLesson target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setModuleLesson(ModuleLesson target, ModuleLesson editedLesson) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void clearPersons() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void clearLessons() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<ModuleLesson> getFilteredModuleLessonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredModuleLessonList(Predicate<ModuleLesson> predicate) {
            throw new AssertionError("This method should not be called");
        }
    }

    /**
     * A Model stub that contains a single module lesson.
     */
    private class ModelStubWithModuleLesson extends AddModuleLessonCommandTest.ModelStub {
        private final ModuleLesson moduleLesson;

        ModelStubWithModuleLesson(ModuleLesson moduleLesson) {
            requireNonNull(moduleLesson);
            this.moduleLesson = moduleLesson;
        }

        @Override
        public boolean hasModuleLesson(ModuleLesson moduleLesson) {
            requireNonNull(moduleLesson);
            return this.moduleLesson.isSameModuleLesson(moduleLesson);
        }
    }

    /**
     * A Model stub that always accept the module lesson being added.
     */
    private class ModelStubAcceptingModuleLessonAdded extends AddModuleLessonCommandTest.ModelStub {
        final ArrayList<ModuleLesson> moduleLessonsAdded = new ArrayList<>();

        @Override
        public boolean hasModuleLesson(ModuleLesson moduleLesson) {
            requireNonNull(moduleLesson);
            return moduleLessonsAdded.stream().anyMatch(moduleLesson::isSameModuleLesson);
        }

        @Override
        public boolean hasModuleLessonClashingWith(ModuleLesson moduleLesson) {
            requireNonNull(moduleLesson);
            return moduleLessonsAdded.stream().anyMatch(ml -> ml.clashesWith(moduleLesson));
        }

        @Override
        public void addLesson(ModuleLesson moduleLesson) {
            requireNonNull(moduleLesson);
            moduleLessonsAdded.add(moduleLesson);
        }

        @Override
        public ReadOnlyConthacks getConthacks() {
            return new Conthacks();
        }
    }
}
