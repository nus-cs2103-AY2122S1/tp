package seedu.tracker.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tracker.logic.commands.CommandTestUtil.VALID_CODE_CS1101S;
import static seedu.tracker.logic.commands.CommandTestUtil.VALID_CODE_CS2100;
import static seedu.tracker.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CP3108A;
import static seedu.tracker.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CS1101S;
import static seedu.tracker.logic.commands.CommandTestUtil.VALID_MC_CP3108A;
import static seedu.tracker.logic.commands.CommandTestUtil.VALID_TAG_UE;
import static seedu.tracker.logic.commands.CommandTestUtil.VALID_TITLE_CS1101S;
import static seedu.tracker.testutil.Assert.assertThrows;
import static seedu.tracker.testutil.TypicalModules.CS2101;
import static seedu.tracker.testutil.TypicalModules.CS2103T;

import org.junit.jupiter.api.Test;

import seedu.tracker.testutil.ModuleBuilder;

class ModuleTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Module module = new ModuleBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> module.getTags().remove(0));
    }

    @Test
    public void isSameModule() {
        // same object -> returns true
        assertTrue(CS2103T.isSameModule(CS2103T));

        // null -> returns false
        assertFalse(CS2103T.isSameModule(null));

        // same code, all other attributes different -> returns true
        Module editedCS2103T = new ModuleBuilder(CS2103T).withTitle(VALID_TITLE_CS1101S)
                .withMc(VALID_MC_CP3108A)
                .withDescription(VALID_DESCRIPTION_CS1101S)
                .withTags(VALID_TAG_UE).build();
        assertTrue(CS2103T.isSameModule(editedCS2103T));

        // different code, all other attributes same -> returns false
        editedCS2103T = new ModuleBuilder(CS2103T).withCode(VALID_CODE_CS1101S).build();
        assertFalse(CS2103T.isSameModule(editedCS2103T));

        // code differs in case, all other attributes same -> returns false
        Module editedCS2101 = new ModuleBuilder(CS2101).withCode(VALID_CODE_CS2100.toLowerCase()).build();
        assertFalse(CS2101.isSameModule(editedCS2101));

        // code has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_CODE_CS2100 + " ";
        editedCS2101 = new ModuleBuilder(CS2101).withCode(nameWithTrailingSpaces).build();
        assertFalse(CS2101.isSameModule(editedCS2101));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Module cs2103tCopy = new ModuleBuilder(CS2103T).build();
        assertTrue(CS2103T.equals(cs2103tCopy));

        // same object -> returns true
        assertTrue(CS2103T.equals(CS2103T));

        // null -> returns false
        assertFalse(CS2103T.equals(null));

        // different type -> returns false
        assertFalse(CS2103T.equals(5));

        // different module -> returns false
        assertFalse(CS2103T.equals(CS2101));

        // different title -> returns false
        Module editedCS2103T = new ModuleBuilder(CS2103T).withTitle(VALID_TITLE_CS1101S).build();
        assertFalse(CS2103T.equals(editedCS2103T));

        // different mc -> returns false
        editedCS2103T = new ModuleBuilder(CS2103T).withMc(VALID_MC_CP3108A).build();
        assertFalse(CS2103T.equals(editedCS2103T));

        // different description -> returns false
        editedCS2103T = new ModuleBuilder(CS2103T).withDescription(VALID_DESCRIPTION_CP3108A).build();
        assertFalse(CS2103T.equals(editedCS2103T));

        // different tags -> returns false
        editedCS2103T = new ModuleBuilder(CS2103T).withTags(VALID_TAG_UE).build();
        assertFalse(CS2103T.equals(editedCS2103T));


    }

    @Test
    public void equals_withAcademicCalendar() {
        int academicYear = 2;
        int semester = 1;

        //one module has academic calendar while the other doesn't -> false
        Module editedCS2103T = new ModuleBuilder(CS2103T)
                .withAcademicCalendar(academicYear, semester).build();
        assertFalse(CS2103T.equals(editedCS2103T));

        //different academic calendar value -> false
        academicYear = 3;
        Module secondEditedCS2103T = new ModuleBuilder(CS2103T)
                .withAcademicCalendar(academicYear, semester).build();
        assertFalse(editedCS2103T.equals(secondEditedCS2103T));

        //same academic calendar value -> true
        academicYear = 2;
        Module thirdEditedCS2103T = new ModuleBuilder(CS2103T)
                .withAcademicCalendar(academicYear, semester).build();
        assertTrue(editedCS2103T.equals(thirdEditedCS2103T));
    }
}
