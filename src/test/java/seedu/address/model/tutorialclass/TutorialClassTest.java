package seedu.address.model.tutorialclass;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.TutorialClassBuilder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASSCODE_G101;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASSCODE_G102;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_G101;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_G102;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTutorialClasses.G101;
import static seedu.address.testutil.TypicalTutorialClasses.G102;

public class TutorialClassTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        TutorialClass tutorialClass = new TutorialClassBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> tutorialClass.getTags().remove(0));
    }

    @Test
    public void isSameTutorialClass() {
        // same object -> returns true
        assertTrue(G101.isSameTutorialClass(G101));

        // null -> returns false
        assertFalse(G101.isSameTutorialClass(null));

        // same classCode, all other attributes different -> returns true
        TutorialClass editedG101 = new TutorialClassBuilder().withClassCode(VALID_CLASSCODE_G101).withSchedule(VALID_SCHEDULE_G102).build();
        assertTrue(G101.isSameTutorialClass(editedG101));

        // different classCode all other attributes same -> returns false
        editedG101 = new TutorialClassBuilder().withClassCode(VALID_CLASSCODE_G102).withSchedule(VALID_SCHEDULE_G101).build();
        assertFalse(G101.isSameTutorialClass(editedG101));

    }

    @Test
    public void equals() {
        // same values -> returns true
        TutorialClass G101Copy = new TutorialClassBuilder(G101).build();
        assertTrue(G101.equals(G101Copy));

        // same object -> returns true
        assertTrue(G101.equals(G101));

        // null -> returns false
        assertFalse(G101.equals(null));

        // different type -> returns false
        assertFalse(G101.equals(1));

        // different student -> returns false
        assertFalse(G101.equals(G102));

        // different classCode -> returns false
        TutorialClass editedG101 = new TutorialClassBuilder(G101).withClassCode(VALID_CLASSCODE_G102).build();
        assertFalse(G101.equals(editedG101));

        // different schedule -> return false
        editedG101 = new TutorialClassBuilder(G101).withSchedule(VALID_SCHEDULE_G102).build();
        assertFalse(G101.equals(editedG101));

    }
}
