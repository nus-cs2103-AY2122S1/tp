package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ModelStubAcceptingPersonAdded;

public class PasswordCommandTest {

    @Test
    public void execute_passwordCommand_successful() {
        String a = "password1234!";
        String b = "Password1234!";

        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();

        CommandResult commandResult = new PasswordCommand(a, b).execute(modelStub);

        assertEquals(String.format(PasswordCommand.MESSAGE_SUCCESS), commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        String a = "password1234!";
        String b = "Password1234!";
        String c = "Password1234!!";
        String d = "Password1234!!";

        PasswordCommand ab = new PasswordCommand(a, b);
        PasswordCommand cd = new PasswordCommand(c, d);

        // same object -> returns true
        assertTrue(ab.equals(ab));

        // same values -> returns true
        PasswordCommand abCopy = new PasswordCommand(a, b);
        assertTrue(ab.equals(abCopy));

        // different types -> returns false
        assertFalse(ab.equals(1));

        // null -> returns false
        assertFalse(ab.equals(null));

        // different password -> returns false
        assertFalse(ab.equals(cd));
        PasswordCommand ac = new PasswordCommand(a, c);
        assertFalse(ab.equals(ac));
    }

}
