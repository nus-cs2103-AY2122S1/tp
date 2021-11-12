package seedu.tracker.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tracker.logic.commands.CommandTestUtil.DESC_CS2103T;
import static seedu.tracker.logic.commands.CommandTestUtil.DESC_GEQ1000;
import static seedu.tracker.logic.commands.CommandTestUtil.VALID_CODE_CP3108A;
import static seedu.tracker.logic.commands.CommandTestUtil.VALID_CODE_CS1101S;
import static seedu.tracker.logic.commands.CommandTestUtil.VALID_CODE_GEQ1000;
import static seedu.tracker.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CP3108A;
import static seedu.tracker.logic.commands.CommandTestUtil.VALID_MC_CP3108A;
import static seedu.tracker.logic.commands.CommandTestUtil.VALID_TAG_CORE;
import static seedu.tracker.logic.commands.CommandTestUtil.VALID_TITLE_CP3108A;
import static seedu.tracker.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.tracker.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tracker.logic.commands.CommandTestUtil.showModuleAtIndex;
import static seedu.tracker.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.tracker.testutil.TypicalIndexes.INDEX_SECOND_MODULE;
import static seedu.tracker.testutil.TypicalModules.getTypicalModuleTracker;

import org.junit.jupiter.api.Test;

import seedu.tracker.commons.core.Messages;
import seedu.tracker.commons.core.index.Index;
import seedu.tracker.logic.commands.EditCommand.EditModuleDescriptor;
import seedu.tracker.model.Model;
import seedu.tracker.model.ModelManager;
import seedu.tracker.model.ModuleTracker;
import seedu.tracker.model.UserInfo;
import seedu.tracker.model.UserPrefs;
import seedu.tracker.model.module.Module;
import seedu.tracker.testutil.EditModuleDescriptorBuilder;
import seedu.tracker.testutil.ModuleBuilder;

/**
* Contains integration tests (interaction with the Model) and unit tests for EditCommand.
*/
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalModuleTracker(), new UserPrefs(), new UserInfo());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Module editedModule = new ModuleBuilder().withCode(VALID_CODE_CP3108A)
                .withTitle(VALID_TITLE_CP3108A)
                .withDescription(VALID_DESCRIPTION_CP3108A)
                .withMc(VALID_MC_CP3108A)
                .withTags(VALID_TAG_CORE)
                .build();
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(editedModule).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_MODULE, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_MODULE_SUCCESS, editedModule);

        Model expectedModel = new ModelManager(new ModuleTracker(model.getModuleTracker()),
                new UserPrefs(), new UserInfo());
        expectedModel.setModule(model.getFilteredModuleList().get(0), editedModule);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastModule = Index.fromOneBased(model.getFilteredModuleList().size());
        Module lastModule = model.getFilteredModuleList().get(indexLastModule.getZeroBased());

        ModuleBuilder moduleInList = new ModuleBuilder(lastModule);
        Module editedModule = moduleInList.withCode(VALID_CODE_CP3108A).withTitle(VALID_TITLE_CP3108A)
                .withTags(VALID_TAG_CORE).build();

        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withCode(VALID_CODE_CP3108A)
                    .withTitle(VALID_TITLE_CP3108A).withTags(VALID_TAG_CORE).build();
        EditCommand editCommand = new EditCommand(indexLastModule, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_MODULE_SUCCESS, editedModule);

        Model expectedModel = new ModelManager(new ModuleTracker(model.getModuleTracker()),
                new UserPrefs(), new UserInfo());
        expectedModel.setModule(lastModule, editedModule);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedWithAcademicCalendarUnfilteredList_success() {
        Index indexLastModule = Index.fromOneBased(model.getFilteredModuleList().size());
        Module lastModule = model.getFilteredModuleList().get(indexLastModule.getZeroBased());

        ModuleBuilder moduleInList = new ModuleBuilder(lastModule);
        Module moduleWithAcademicCal = moduleInList.withAcademicCalendar(3, 2).build();
        Module editedModule = moduleInList.withCode(VALID_CODE_CP3108A).withTitle(VALID_TITLE_CP3108A)
                .withTags(VALID_TAG_CORE).withAcademicCalendar(3, 2).build();

        Model modelToEdit = new ModelManager(new ModuleTracker(model.getModuleTracker()),
                new UserPrefs(), new UserInfo());
        modelToEdit.setModule(lastModule, moduleWithAcademicCal);

        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withCode(VALID_CODE_CP3108A)
                .withTitle(VALID_TITLE_CP3108A).withTags(VALID_TAG_CORE).build();
        EditCommand editCommand = new EditCommand(indexLastModule, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_MODULE_SUCCESS, editedModule);

        Model expectedModel = new ModelManager(new ModuleTracker(model.getModuleTracker()),
                new UserPrefs(), new UserInfo());
        expectedModel.setModule(lastModule, editedModule);

        assertCommandSuccess(editCommand, modelToEdit, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);

        Module moduleInFilteredList = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        Module editedModule = new ModuleBuilder(moduleInFilteredList).withCode(VALID_CODE_CS1101S).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_MODULE,
                new EditModuleDescriptorBuilder().withCode(VALID_CODE_CS1101S).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_MODULE_SUCCESS, editedModule);

        Model expectedModel = new ModelManager(new ModuleTracker(model.getModuleTracker()),
                new UserPrefs(), new UserInfo());
        expectedModel.setModule(model.getFilteredModuleList().get(0), editedModule);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_failure() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_MODULE, new EditModuleDescriptor());
        Module editedModule = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_SAME_VALUE);
    }

    @Test
    public void execute_duplicateModuleUnfilteredList_failure() {
        Module firstModule = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(firstModule).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_MODULE, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_MODULE);
    }

    @Test
    public void execute_duplicateModuleFilteredList_failure() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);

        // edit person in filtered list into a duplicate in address book
        Module moduleInList = model.getModuleTracker().getModuleList().get(INDEX_SECOND_MODULE.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_MODULE,
                new EditModuleDescriptorBuilder(moduleInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_MODULE);
    }

    @Test
    public void execute_invalidModuleIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredModuleList().size() + 1);
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withCode(VALID_CODE_GEQ1000).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidModuleIndexFilteredList_failure() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);
        Index outOfBoundIndex = INDEX_SECOND_MODULE;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getModuleTracker().getModuleList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditModuleDescriptorBuilder().withCode(VALID_CODE_GEQ1000).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_MODULE, DESC_CS2103T);

        // same values -> returns true
        EditModuleDescriptor copyDescriptor = new EditModuleDescriptor(DESC_CS2103T);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_MODULE, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ExitCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_MODULE, DESC_CS2103T)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_MODULE, DESC_GEQ1000)));
    }

}

