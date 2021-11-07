package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ALIAS_D1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ALIAS_L;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMMAND_D1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMMAND_L;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class AliasCommandTest {
    private Model model = new ModelManager();

    @Test
    public void setAliasMap_null_throwsNullPointerException() {
        AliasCommand command = new AliasCommand(VALID_ALIAS_L, VALID_COMMAND_L);
        command.setMap(null);

        assertThrows(NullPointerException.class, () -> command.execute(model));
    }

    @Test
    public void execute_validInputs_addSuccessful() throws Exception {
        HashMap<String, String> aliasMap = new HashMap<>();
        AliasCommand command = new AliasCommand(VALID_ALIAS_L, VALID_COMMAND_L);
        command.setMap(aliasMap);
        CommandResult commandResult = command.execute(model);

        assertEquals(String.format(AliasCommand.MESSAGE_SUCCESS, VALID_ALIAS_L, VALID_COMMAND_L),
                commandResult.getFeedbackToUser());
        assertSame(VALID_COMMAND_L, aliasMap.get(VALID_ALIAS_L));
    }

    @Test
    public void equals() {
        AliasCommand lCommand = new AliasCommand(VALID_ALIAS_L, VALID_COMMAND_L);
        AliasCommand d1Command = new AliasCommand(VALID_ALIAS_D1, VALID_COMMAND_D1);

        // same object -> returns true
        assertTrue(lCommand.equals(lCommand));

        // same values -> returns true
        AliasCommand lCommandcopy = new AliasCommand(VALID_ALIAS_L, VALID_COMMAND_L);
        assertTrue(lCommand.equals(lCommandcopy));

        // different types -> returns false
        assertFalse(lCommand.equals(1));

        // null -> returns false
        assertFalse(lCommand.equals(null));

        // different person -> returns false
        assertFalse(lCommand.equals(d1Command));
    }
}
