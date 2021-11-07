package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalSportsPa.getTypicalSportsPa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.alias.Alias;
import seedu.address.model.alias.CommandWord;
import seedu.address.model.alias.Shortcut;

public class ShowAliasesCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSportsPa(), new UserPrefs());
        model.addAlias(new Alias(new Shortcut("lf"), new CommandWord("listf")));
        expectedModel = new ModelManager(model.getSportsPa(), model.getUserPrefs());
    }

    @Test
    public void execute_showsCorrectList() {
        String expectedList = model.getAliases().toString();
        assertCommandSuccess(new ShowAliasesCommand(), model,
                String.format(ShowAliasesCommand.MESSAGE_SUCCESS, expectedList),
                expectedModel);
    }
}
