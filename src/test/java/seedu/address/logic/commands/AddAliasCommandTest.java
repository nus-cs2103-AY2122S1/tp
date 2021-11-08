package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.alias.Alias;
import seedu.address.model.alias.CommandWord;
import seedu.address.model.alias.Shortcut;

public class AddAliasCommandTest {

    @Test
    public void constructor_null_exceptionThrown() {
        assertThrows(NullPointerException.class, () -> new AddAliasCommand(null));
    }

    @Test
    public void execute_addAlias_success() {
        ModelManager model = new ModelManager();
        Model expectedModel = new ModelManager(model.getSportsPa(), new UserPrefs());
        Alias alias = new Alias(new Shortcut("lf"), new CommandWord("listf"));
        expectedModel.addAlias(alias);
        String expectedMessage = String.format(AddAliasCommand.MESSAGE_SUCCESS, "listf", "lf");
        assertCommandSuccess(new AddAliasCommand(alias),
                model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addDuplicateShortcutReplacesExisting_success() {
        ModelManager model = new ModelManager();
        model.addAlias(new Alias(new Shortcut("l"), new CommandWord("listf")));

        Model expectedModel = new ModelManager(model.getSportsPa(), new UserPrefs());
        Alias alias = new Alias(new Shortcut("l"), new CommandWord("listm"));
        expectedModel.addAlias(alias);

        String expectedMessage = String.format(AddAliasCommand.MESSAGE_SUCCESS, "listm", "l");
        assertCommandSuccess(new AddAliasCommand(alias), model, expectedMessage, expectedModel);

    }

    @Test
    public void equals() {
        AddAliasCommand command1 = new AddAliasCommand(new Alias(new Shortcut("lf"), new CommandWord("listf")));
        AddAliasCommand command2 = new AddAliasCommand(new Alias(new Shortcut("lyf"), new CommandWord("listf")));
        AddAliasCommand command1Copy = new AddAliasCommand(new Alias(new Shortcut("lf"), new CommandWord("listf")));

        assertTrue(command1.equals(command1));
        assertTrue(command1.equals(command1Copy));
        assertFalse(command1.equals(command2));
        assertFalse(command1.equals(null));
        assertFalse(command1.equals("10"));
    }

}
