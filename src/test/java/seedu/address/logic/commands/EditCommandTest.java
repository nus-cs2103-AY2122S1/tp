package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showApplicationAtIndex;
import static seedu.address.testutil.TypicalApplications.getTypicalInternship;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPLICATION;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditApplicationDescriptor;
import seedu.address.model.Internship;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.application.Application;
import seedu.address.testutil.ApplicationBuilder;
import seedu.address.testutil.EditApplicationDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalInternship(), new UserPrefs());

    @Test
    public void execute_allFieldsEditedUnfilteredList_success() {
        Application editedApplication = new ApplicationBuilder().build();

        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder(editedApplication).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICATION, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_APPLICATION_SUCCESS, editedApplication);

        Model expectedModel = new ModelManager(new Internship(model.getInternship()), new UserPrefs());
        expectedModel.setApplication(model.getFilteredApplicationList().get(0), editedApplication);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastApplication = Index.fromOneBased(model.getFilteredApplicationList().size());
        Application lastApplication = model.getFilteredApplicationList().get(indexLastApplication.getZeroBased());

        ApplicationBuilder applicationInList = new ApplicationBuilder(lastApplication);
        Application editedApplication = applicationInList.withCompany(VALID_NAME_BYTEDANCE)
                .withPosition(VALID_POSITION_AMAZON).build();

        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder().withCompany(VALID_NAME_BYTEDANCE)
                .withPosition(VALID_POSITION_AMAZON).build();

        EditCommand editCommand = new EditCommand(indexLastApplication, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_APPLICATION_SUCCESS, editedApplication);

        Model expectedModel = new ModelManager(new Internship(model.getInternship()), new UserPrefs());
        expectedModel.setApplication(lastApplication, editedApplication);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_filteredList_success() {
        showApplicationAtIndex(model, INDEX_FIRST_APPLICATION);

        Application applicationInFilteredList = model.getFilteredApplicationList()
                .get(INDEX_FIRST_APPLICATION.getZeroBased());
        Application editedApplication = new ApplicationBuilder(applicationInFilteredList)
                .withCompany(VALID_NAME_BYTEDANCE).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICATION,
                new EditApplicationDescriptorBuilder().withCompany(VALID_NAME_BYTEDANCE).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_APPLICATION_SUCCESS, editedApplication);

        Model expectedModel = new ModelManager(new Internship(model.getInternship()), new UserPrefs());
        expectedModel.setApplication(model.getFilteredApplicationList().get(0), editedApplication);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateApplicationUnfilteredList_failure() {
        Application firstApplication = model.getFilteredApplicationList().get(INDEX_FIRST_APPLICATION.getZeroBased());
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder(firstApplication).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_APPLICATION, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_APPLICATION);
    }

    @Test
    public void execute_duplicateApplicationFilteredList_failure() {
        showApplicationAtIndex(model, INDEX_FIRST_APPLICATION);

        // edit application in filtered list into a duplicate in Internship
        Application applicationInList = model.getInternship().getApplicationList()
                .get(INDEX_SECOND_APPLICATION.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICATION,
                new EditApplicationDescriptorBuilder(applicationInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_APPLICATION);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of Internship
     */
    @Test
    public void execute_invalidApplicationIndexFilteredList_failure() {
        showApplicationAtIndex(model, INDEX_FIRST_APPLICATION);
        Index outOfBoundIndex = INDEX_SECOND_APPLICATION;
        // ensures that outOfBoundIndex is still in bounds of Internship list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getInternship().getApplicationList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditApplicationDescriptorBuilder().withCompany(VALID_NAME_BYTEDANCE).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INDEX_EXCEEDS_LIST_LENGTH);
    }

    /**
     * Edits an application but the information provided is all the same as the old application
     */
    @Test
    public void execute_nothingActuallyEditedUnfilteredList_failure() {
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withCompany(VALID_NAME_AMAZON).build();

        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICATION, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_NOTHING_EDITED);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_APPLICATION, DESC_AMAZON);

        // same values -> returns true
        EditApplicationDescriptor copyDescriptor = new EditApplicationDescriptor(DESC_AMAZON);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_APPLICATION, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_APPLICATION, DESC_AMAZON)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_APPLICATION, DESC_BYTEDANCE)));
    }
}
