package seedu.tracker.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tracker.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.tracker.commons.core.GuiSettings;
import seedu.tracker.logic.commands.exceptions.CommandException;
import seedu.tracker.model.Model;
import seedu.tracker.model.ModuleTracker;
import seedu.tracker.model.ReadOnlyModuleTracker;
import seedu.tracker.model.ReadOnlyUserInfo;
import seedu.tracker.model.ReadOnlyUserPrefs;
import seedu.tracker.model.calendar.AcademicCalendar;
import seedu.tracker.model.module.Mc;
import seedu.tracker.model.module.McProgress;
import seedu.tracker.model.module.Module;
import seedu.tracker.testutil.ModuleBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_moduleAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingModuleAdded modelStub = new ModelStubAcceptingModuleAdded();
        Module validModule = new ModuleBuilder().build();

        CommandResult commandResult = new AddCommand(validModule).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validModule), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validModule), modelStub.modulesAdded);
    }

    @Test
    public void execute_duplicateModule_throwsCommandException() {
        Module validModule = new ModuleBuilder().build();
        AddCommand addCommand = new AddCommand(validModule);
        ModelStub modelStub = new ModelStubWithModule(validModule);
        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_MODULE, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Module cs1231 = new ModuleBuilder().withCode("CS1231").build();
        Module cs1101s = new ModuleBuilder().withCode("CS1101S").build();
        AddCommand addCs1231Command = new AddCommand(cs1231);
        AddCommand addCs1101sCommand = new AddCommand(cs1101s);

        // same object -> returns true
        assertTrue(addCs1231Command.equals(addCs1231Command));

        // same values -> returns true
        AddCommand addCs1231CommandCopy = new AddCommand(cs1231);
        assertTrue(addCs1231Command.equals(addCs1231CommandCopy));

        // different types -> returns false
        assertFalse(addCs1231Command.equals(1));

        // null -> returns false
        assertFalse(addCs1231Command.equals(null));

        // different person -> returns false
        assertFalse(addCs1231Command.equals(addCs1101sCommand));
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
        public Path getModuleTrackerFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setModuleTrackerFilePath(Path moduleTrackerFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addModule(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setModuleTracker(ReadOnlyModuleTracker newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyModuleTracker getModuleTracker() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCurrentSemester(AcademicCalendar academicCalendar) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserInfo(ReadOnlyUserInfo userInfo) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserInfo getUserInfo() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public AcademicCalendar getCurrentSemester() {
            return null;
        }

        @Override
        public void setMcGoal(Mc mcGoal) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Mc getMcGoal() {
            return null;
        }

        @Override
        public ObservableList<McProgress> getMcProgressList() {
            throw new AssertionError("This method should not be called");
        };

        @Override
        public void updateMcProgress() {
            throw new AssertionError("This method should not be called");
        };

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
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredModuleList() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithModule extends ModelStub {
        private final Module module;

        ModelStubWithModule(Module module) {
            requireNonNull(module);
            this.module = module;
        }

        @Override
        public boolean hasModule(Module module) {
            requireNonNull(module);
            return this.module.isSameModule(module);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingModuleAdded extends ModelStub {
        final ArrayList<Module> modulesAdded = new ArrayList<>();

        @Override
        public boolean hasModule(Module module) {
            requireNonNull(module);
            return modulesAdded.stream().anyMatch(module::isSameModule);
        }

        @Override
        public void addModule(Module module) {
            requireNonNull(module);
            modulesAdded.add(module);
        }

        @Override
        public ReadOnlyModuleTracker getModuleTracker() {
            return new ModuleTracker();
        }
    }

}
