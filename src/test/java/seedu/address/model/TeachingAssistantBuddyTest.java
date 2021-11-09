package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_0;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.module.Module;
import seedu.address.model.module.student.Student;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.TypicalModules;

public class TeachingAssistantBuddyTest {

    private final TeachingAssistantBuddy teachingAssistantBuddy = new TeachingAssistantBuddy();
    private final Module module = new ModuleBuilder().withName(MODULE_NAME_0).build();
    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), teachingAssistantBuddy.getModuleList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> teachingAssistantBuddy.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTeachingAssistantBuddy_replacesData() {
        TeachingAssistantBuddy newData = TypicalModules.getTypicalBuddy();
        teachingAssistantBuddy.resetData(newData);
        assertEquals(newData, teachingAssistantBuddy);
    }

    @Test
    public void hasModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> teachingAssistantBuddy.hasModule(null));
    }

    @Test
    public void hasModule_moduleNotInTeachingAssistantBuddy_returnsFalse() {
        assertFalse(teachingAssistantBuddy.hasModule(module));
    }

    @Test
    public void hasModule_moduleInTeachingAssistantBuddy_returnsTrue() {
        teachingAssistantBuddy.addModule(module);
        assertTrue(teachingAssistantBuddy.hasModule(module));
    }

    @Test
    public void getModuleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> teachingAssistantBuddy.getModuleList().remove(0));
    }

    /**
     * A stub ReadOnlyTeachingAssistantBuddy whose students list can violate interface constraints.
     */
    private static class TeachingAssistantBuddyStub implements ReadOnlyTeachingAssistantBuddy {
        private final ObservableList<Module> modules = FXCollections.observableArrayList();

        TeachingAssistantBuddyStub(Collection<Module> modules) {
            this.modules.setAll(modules);
        }

        @Override
        public ObservableList<Module> getModuleList() {
            return null;
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return null;
        }
    }

}
