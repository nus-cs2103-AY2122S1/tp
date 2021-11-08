package seedu.address.model.position;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class TitleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Title(null));
    }

    @Test
    public void constructor_invalidTitle_throwsIllegalArgumentException() {
        String invalidTitle = "";
        assertThrows(IllegalArgumentException.class, () -> new Title(invalidTitle));
    }

    @Test
    public void isValidTitle() {
        // null position name
        assertThrows(NullPointerException.class, () -> Title.isValidTitle(null));
    }

    @Test
    public void variousPositionNames() {
        //invalid position names
        assertFalse(Title.isValidTitle(("Administrative & Finance Assistant")));
        assertFalse(Title.isValidTitle(("Administrative Assistant #2")));
        assertFalse(Title.isValidTitle(("Administrative Assistant *")));
        assertFalse(Title.isValidTitle(("Administrative Assistant ^^")));
        assertFalse(Title.isValidTitle(("Acc-Manager")));
        assertFalse(Title.isValidTitle(("")));
        assertFalse(Title.isValidTitle((" ")));
        assertFalse(Title.isValidTitle(("Administrative Manager (temp)")));
        assertFalse(Title.isValidTitle(("'Fun!' project manager")));
        assertFalse(Title.isValidTitle(("data + admin clerk")));

        //valid position names
        assertTrue(Title.isValidTitle(("Administrative Assistant")));
        assertTrue(Title.isValidTitle(("Receptionist")));
        assertTrue(Title.isValidTitle(("Office Manager")));
        assertTrue(Title.isValidTitle(("Auditing Clerk")));
        assertTrue(Title.isValidTitle(("Bookkeeper")));
        assertTrue(Title.isValidTitle(("Account Executive Officer Assistant")));
        assertTrue(Title.isValidTitle(("Branch Manager")));
        assertTrue(Title.isValidTitle(("Business Manager")));
        assertTrue(Title.isValidTitle(("Quality Control Coordinator")));
        assertTrue(Title.isValidTitle(("Administrative Manager")));
        assertTrue(Title.isValidTitle(("Chief Executive Officer")));
        assertTrue(Title.isValidTitle(("Business Analyst")));
        assertTrue(Title.isValidTitle(("Risk Manager")));
        assertTrue(Title.isValidTitle(("Human Resources")));
        assertTrue(Title.isValidTitle(("Office Assistant")));
        assertTrue(Title.isValidTitle(("Secretary")));
        assertTrue(Title.isValidTitle(("Office Clerk")));
        assertTrue(Title.isValidTitle(("File Clerk")));
        assertTrue(Title.isValidTitle(("Account Collector")));
        assertTrue(Title.isValidTitle(("Administrative Specialist")));
        assertTrue(Title.isValidTitle(("Executive Assistant")));
        assertTrue(Title.isValidTitle(("Program Administrator")));
        assertTrue(Title.isValidTitle(("Program Manager")));
        assertTrue(Title.isValidTitle(("Administrative Analyst")));
        assertTrue(Title.isValidTitle(("Data Entry")));
    }
}
