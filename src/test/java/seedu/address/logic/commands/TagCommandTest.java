package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandResult.DisplayType.TAGS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.TagCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class TagCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_tag_success() {
        CommandResult expectedCommandResult =
                new CommandResult(MESSAGE_SUCCESS, TAGS);
        assertCommandSuccess(prepareTagCommand(), model, expectedCommandResult, expectedModel);
    }

    /**
     * Generates a {@code TagCommand}.
     */
    private TagCommand prepareTagCommand() {
        TagCommand tagCommand = new TagCommand();
        tagCommand.setDependencies(model, new UndoRedoStack());
        return tagCommand;
    }
}
