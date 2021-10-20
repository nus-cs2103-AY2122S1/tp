package seedu.address.logic.commands.abcommand;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.File;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class AbListCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        AbListCommand abListCommand1 = new AbListCommand();
        AbListCommand abListCommand2 = new AbListCommand();

        // same object -> returns true
        assertTrue(abListCommand1.equals(abListCommand1));

        // different types -> returns false
        assertFalse(abListCommand1.equals(1));

        // null -> returns false
        assertFalse(abListCommand1.equals(null));

        // different person -> returns false
        assertFalse(abListCommand1.equals(abListCommand2));
    }

    @Test
    public void execute_success() {
        AbListCommand command = new AbListCommand();
        File fileDir = new File(Path.of("data").toString());
        String[] list = fileDir.list();
        StringBuilder result = new StringBuilder();
        if (list != null) {
            for (String s: list) {
                result.append("\n- ").append(StringUtil.getStringWithoutSuffix(s, StringUtil.JSON_FILE_PREFIX));
            }
        }

        assertCommandSuccess(command, model, String.format(AbListCommand.MESSAGE_SUCCESS, result), model);
    }
}
