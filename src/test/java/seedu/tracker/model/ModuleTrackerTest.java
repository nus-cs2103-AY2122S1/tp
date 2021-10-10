package seedu.tracker.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tracker.logic.commands.CommandTestUtil.VALID_TAG_GE;
import static seedu.tracker.logic.commands.CommandTestUtil.VALID_TITLE_GEQ1000;
import static seedu.tracker.testutil.Assert.assertThrows;
import static seedu.tracker.testutil.TypicalModules.CS2103T;
import static seedu.tracker.testutil.TypicalModules.getTypicalModuleTracker;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.tracker.model.module.Module;
import seedu.tracker.model.module.exceptions.DuplicateModuleException;
import seedu.tracker.testutil.ModuleBuilder;

public class ModuleTrackerTest {

    private final ModuleTracker moduleTracker = new ModuleTracker();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), moduleTracker.getModuleList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> moduleTracker.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyModuleTracker_replacesData() {
        ModuleTracker newData = getTypicalModuleTracker();
        moduleTracker.resetData(newData);
        assertEquals(newData, moduleTracker);
    }

    @Test
    public void resetData_withDuplicateModules_throwsDuplicateModuleException() {
        // Two persons with the same identity fields
        Module editedCs2103t = new ModuleBuilder(CS2103T).withTitle(VALID_TITLE_GEQ1000).withTags(VALID_TAG_GE)
                .build();
        List<Module> newModules = Arrays.asList(CS2103T, editedCs2103t);
        ModuleTrackerStub newData = new ModuleTrackerStub(newModules);

        assertThrows(DuplicateModuleException.class, () -> moduleTracker.resetData(newData));
    }

    @Test
    public void hasModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> moduleTracker.hasModule(null));
    }

    @Test
    public void hasModule_moduleNotInModuleTracker_returnsFalse() {
        assertFalse(moduleTracker.hasModule(CS2103T));
    }

    @Test
    public void hasModule_moduleInModuleTracker_returnsTrue() {
        moduleTracker.addModule(CS2103T);
        assertTrue(moduleTracker.hasModule(CS2103T));
    }

    @Test
    public void hasModule_moduleWithSameIdentityFieldsInModuleTracker_returnsTrue() {
        moduleTracker.addModule(CS2103T);
        Module editedCs2103t = new ModuleBuilder(CS2103T).withTitle(VALID_TITLE_GEQ1000).withTags(VALID_TAG_GE)
                .build();
        assertTrue(moduleTracker.hasModule(editedCs2103t));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> moduleTracker.getModuleList().remove(0));
    }

    /**
     * A stub ReadOnlyModuleTracker whose persons list can violate interface constraints.
     */
    private static class ModuleTrackerStub implements ReadOnlyModuleTracker {
        private final ObservableList<Module> modules = FXCollections.observableArrayList();

        ModuleTrackerStub(Collection<Module> modules) {
            this.modules.setAll(modules);
        }


        @Override
        public ObservableList<Module> getModuleList() {
            return modules;
        }
    }

}
