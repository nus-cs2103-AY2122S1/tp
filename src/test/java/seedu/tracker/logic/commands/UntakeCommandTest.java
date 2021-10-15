package seedu.tracker.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tracker.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.tracker.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tracker.logic.commands.CommandTestUtil.showModuleAtIndex;
import static seedu.tracker.testutil.TypicalIndexes.INDEX_FIFTH_MODULE;
import static seedu.tracker.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.tracker.testutil.TypicalIndexes.INDEX_FOURTH_MODULE;
import static seedu.tracker.testutil.TypicalIndexes.INDEX_SECOND_MODULE;
import static seedu.tracker.testutil.TypicalModules.getTypicalModuleTracker;

import org.junit.jupiter.api.Test;

import java.util.Set;
import seedu.tracker.commons.core.Messages;
import seedu.tracker.commons.core.index.Index;
import seedu.tracker.model.Model;
import seedu.tracker.model.ModelManager;
import seedu.tracker.model.UserPrefs;
import seedu.tracker.model.module.Code;
import seedu.tracker.model.module.Description;
import seedu.tracker.model.module.Mc;
import seedu.tracker.model.module.Module;
import seedu.tracker.model.module.Title;
import seedu.tracker.model.tag.Tag;

public class UntakeCommandTest {

    private Model model = new ModelManager(getTypicalModuleTracker(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Module moduleToUntake = model.getFilteredModuleList().get(INDEX_FOURTH_MODULE.getZeroBased());
        UntakeCommand untakeCommand = new UntakeCommand(INDEX_FOURTH_MODULE);

        ModelManager expectedModel = new ModelManager(model.getModuleTracker(), new UserPrefs());
        Module expectedModule = createUnscheduledModule(moduleToUntake);
        expectedModel.setModule(moduleToUntake, expectedModule);

        String expectedMessage = String.format(UntakeCommand.MESSAGE_UNTAKE_MODULE_SUCCESS, expectedModule);

        assertCommandSuccess(untakeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredModuleList().size() + 1);
        UntakeCommand untakeCommand = new UntakeCommand(outOfBoundIndex);

        assertCommandFailure(untakeCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showModuleAtIndex(model, INDEX_FOURTH_MODULE);

        Index outOfBoundIndex = INDEX_SECOND_MODULE;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getModuleTracker().getModuleList().size());

        UntakeCommand untakeCommand = new UntakeCommand(outOfBoundIndex);

        assertCommandFailure(untakeCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_moduleAlreadyUnscheduled_throwsCommandException() {
        Module moduleToUntake = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        UntakeCommand untakeCommand = new UntakeCommand(INDEX_FIRST_MODULE);

        ModelManager expectedModel = new ModelManager(model.getModuleTracker(), new UserPrefs());
        Module expectedModule = createUnscheduledModule(moduleToUntake);
        expectedModel.setModule(moduleToUntake, expectedModule);

        String expectedMessage = String.format(UntakeCommand.MESSAGE_MODULE_ALREADY_UNSCHEDULED, expectedModule);

        assertCommandFailure(untakeCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        UntakeCommand untakeFirstCommand = new UntakeCommand(INDEX_FOURTH_MODULE);
        UntakeCommand untakeSecondCommand = new UntakeCommand(INDEX_FIFTH_MODULE);

        // same object -> returns true
        assertTrue(untakeFirstCommand.equals(untakeFirstCommand));

        // same values -> returns true
        UntakeCommand deleteFirstCommandCopy = new UntakeCommand(INDEX_FOURTH_MODULE);
        assertTrue(untakeFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(untakeFirstCommand.equals(1));

        // null -> returns false
        assertFalse(untakeFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(untakeFirstCommand.equals(untakeSecondCommand));
    }

    /**
     * Creates a {@code Module} without an academicCalendar attribute.
     * @param module to be unscheduled.
     * @return {@code Module} without the academicCalendar attribute.
     */
    private Module createUnscheduledModule(Module module) {
        Code code = module.getCode();
        Title title = module.getTitle();
        Description desc = module.getDescription();
        Mc mc = module.getMc();
        Set<Tag> tag = module.getTags();
        return new Module(code, title, desc, mc, tag);
    }
}
