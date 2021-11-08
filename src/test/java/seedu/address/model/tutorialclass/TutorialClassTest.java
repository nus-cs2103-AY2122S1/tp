package seedu.address.model.tutorialclass;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASSCODE_G01;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASSCODE_G02;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_G01;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_G02;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTutorialClasses.G01;
import static seedu.address.testutil.TypicalTutorialClasses.G02;
import static seedu.address.testutil.TypicalTutorialGroups.TUT_01;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TutorialClassBuilder;

public class TutorialClassTest {
    private final TutorialClass tutorialClass = new TutorialClassBuilder().build();

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {

        assertThrows(UnsupportedOperationException.class, () -> tutorialClass.getTags().remove(0));
    }

    @Test
    public void contains_nullTutorialGroup() {
        assertThrows(NullPointerException.class, () -> tutorialClass.contains(null));
    }

    @Test
    public void contains_noTutorialGroup_retrunsFalse() {
        assertFalse(tutorialClass.contains(TUT_01));
    }

    @Test
    public void contains_tutorialGroupInClass_returnsTrue() {
        tutorialClass.addTutorialGroup(TUT_01);
        assertTrue(tutorialClass.contains(TUT_01));
    }

    @Test
    public void remove_tutorialExistingGroup() {
        tutorialClass.addTutorialGroup(TUT_01);
        tutorialClass.removeTutorialGroup(TUT_01);
        assertEquals(tutorialClass, new TutorialClassBuilder().build());
    }

    @Test
    public void isSameTutorialClass() {
        // same object -> returns true
        assertTrue(G01.isSameTutorialClass(G01));

        // null -> returns false
        assertFalse(G01.isSameTutorialClass(null));

        // same classCode, all other attributes different -> returns true
        TutorialClass editedG01 = new TutorialClassBuilder().withClassCode(VALID_CLASSCODE_G01)
                .withSchedule(VALID_SCHEDULE_G02).build();
        assertTrue(G01.isSameTutorialClass(editedG01));

        // different classCode all other attributes same -> returns false
        editedG01 = new TutorialClassBuilder().withClassCode(VALID_CLASSCODE_G02)
                .withSchedule(VALID_SCHEDULE_G01).build();
        assertFalse(G01.isSameTutorialClass(editedG01));

    }

    @Test
    public void equals() {
        // same values -> returns true
        TutorialClass g01Copy = new TutorialClassBuilder(G01).build();
        assertTrue(G01.equals(g01Copy));

        // same object -> returns true
        assertTrue(G01.equals(G01));

        // null -> returns falseF
        assertFalse(G01.equals(null));

        // different type -> returns false
        assertFalse(G01.equals(1));

        // different student -> returns false
        assertFalse(G01.equals(G02));

        // different classCode -> returns false
        TutorialClass editedG01 = new TutorialClassBuilder(G01).withClassCode(VALID_CLASSCODE_G02).build();
        assertFalse(G01.equals(editedG01));

        // different schedule -> return false
        editedG01 = new TutorialClassBuilder(G01).withSchedule(VALID_SCHEDULE_G02).build();
        assertFalse(G01.equals(editedG01));

    }
}
