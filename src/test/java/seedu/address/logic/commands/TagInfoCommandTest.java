package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalDate.getTypicalCountdown;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Shortcut;
import seedu.address.model.UserPrefs;

public class TagInfoCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalCountdown(), new UserPrefs(), new Shortcut());
    }


    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        assertCommandSuccess(new TagInfoCommand("list", true), model, TagInfoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonemptyAddressBook_success() {
        assertCommandSuccess(new TagInfoCommand("list", true), model, TagInfoCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_randomValidTag_success() throws CommandException {
        assertCommandSuccess(new TagInfoCommand("Ben Tan Test".split(" "), true),
                model,
                TagInfoCommand.MESSAGE_SUCCESS,
                model);
    }
}
