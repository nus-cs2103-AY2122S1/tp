package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FACILITY_NAME_COURT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FACILITY_NAME_FIELD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_COURT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_FIELD;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showFacilityAtIndex;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditFacilityCommand.EditFacilityDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.facility.Facility;
import seedu.address.testutil.EditFacilityDescriptorBuilder;
import seedu.address.testutil.FacilityBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditFacilityCommand.
 */
public class EditFacilityCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Facility editedFacility = new FacilityBuilder().build();

        EditFacilityDescriptor descriptor = new EditFacilityDescriptorBuilder(editedFacility).build();
        EditFacilityCommand command = new EditFacilityCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditFacilityCommand.MESSAGE_EDIT_FACILITY_SUCCESS, editedFacility);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setFacility(model.getFilteredFacilityList().get(0), editedFacility);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastFacility = Index.fromOneBased(model.getFilteredFacilityList().size());
        Facility lastFacility = model.getFilteredFacilityList().get(indexLastFacility.getZeroBased());

        FacilityBuilder facilityInList = new FacilityBuilder(lastFacility);
        Facility editedFacility = facilityInList.withFacilityName(VALID_FACILITY_NAME_COURT)
                .withLocation(VALID_LOCATION_COURT).build();

        EditFacilityDescriptor descriptor =
                new EditFacilityDescriptorBuilder().withFacilityName(VALID_FACILITY_NAME_COURT)
                        .withLocation(VALID_LOCATION_COURT).build();
        EditFacilityCommand command = new EditFacilityCommand(indexLastFacility, descriptor);

        String expectedMessage = String.format(EditFacilityCommand.MESSAGE_EDIT_FACILITY_SUCCESS, editedFacility);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setFacility(lastFacility, editedFacility);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    @Disabled
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditFacilityCommand command = new EditFacilityCommand(INDEX_FIRST, new EditFacilityDescriptor());
        Facility editedFacility = model.getFilteredFacilityList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(EditFacilityCommand.MESSAGE_EDIT_FACILITY_SUCCESS, editedFacility);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showFacilityAtIndex(model, INDEX_FIRST);

        Facility facilityInFilteredList = model.getFilteredFacilityList().get(INDEX_FIRST.getZeroBased());
        Facility editedFacility = new FacilityBuilder(facilityInFilteredList)
                .withFacilityName(VALID_FACILITY_NAME_COURT).build();
        EditFacilityCommand command = new EditFacilityCommand(INDEX_FIRST,
                new EditFacilityDescriptorBuilder().withFacilityName(VALID_FACILITY_NAME_COURT).build());

        String expectedMessage = String.format(EditFacilityCommand.MESSAGE_EDIT_FACILITY_SUCCESS, editedFacility);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setFacility(model.getFilteredFacilityList().get(0), editedFacility);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateFacilityUnfilteredList_failure() {
        Facility firstFacility = model.getFilteredFacilityList().get(INDEX_FIRST.getZeroBased());
        EditFacilityDescriptor descriptor = new EditFacilityDescriptorBuilder(firstFacility).build();
        EditFacilityCommand command = new EditFacilityCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(command, model, EditFacilityCommand.MESSAGE_DUPLICATE_FACILITY);
    }

    @Test
    public void execute_duplicateFacilityFilteredList() {
        showFacilityAtIndex(model, INDEX_FIRST);

        Facility facilityInList = model.getAddressBook().getFacilityList().get(INDEX_SECOND.getZeroBased());
        EditFacilityCommand command = new EditFacilityCommand(INDEX_FIRST,
                new EditFacilityDescriptorBuilder(facilityInList).build());

        assertCommandFailure(command, model, EditFacilityCommand.MESSAGE_DUPLICATE_FACILITY);
    }

    @Test
    public void execute_invalidFacilityIndexUnfilteredList_failure() {
        Index outOfBoundsIndex = Index.fromOneBased(model.getFilteredFacilityList().size() + 1);
        EditFacilityDescriptor descriptor = new EditFacilityDescriptorBuilder()
                .withFacilityName(VALID_FACILITY_NAME_COURT).build();
        EditFacilityCommand command = new EditFacilityCommand(outOfBoundsIndex, descriptor);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_FACILITY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidFacilityIndexFilteredList_failure() {
        showFacilityAtIndex(model, INDEX_FIRST);

        Index outOfBoundsIndex = INDEX_SECOND;
        assertTrue(outOfBoundsIndex.getZeroBased() < model.getAddressBook().getFacilityList().size());

        EditFacilityDescriptor descriptor = new EditFacilityDescriptorBuilder()
                .withFacilityName(VALID_FACILITY_NAME_COURT).build();
        EditFacilityCommand command = new EditFacilityCommand(outOfBoundsIndex, descriptor);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_FACILITY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        EditFacilityDescriptor desc1 = new EditFacilityDescriptorBuilder().withFacilityName(VALID_FACILITY_NAME_COURT)
                .withLocation(VALID_LOCATION_COURT).build();
        EditFacilityDescriptor desc2 = new EditFacilityDescriptorBuilder().withFacilityName(VALID_FACILITY_NAME_FIELD)
                .withLocation(VALID_LOCATION_FIELD).build();

        final EditFacilityCommand standardCommand = new EditFacilityCommand(INDEX_FIRST, desc1);

        // same values -> returns true
        EditFacilityDescriptor copyDescriptor = new EditFacilityDescriptor(desc1);
        EditFacilityCommand commandWithSameValues = new EditFacilityCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearMembersCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditFacilityCommand(INDEX_SECOND, desc1)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditFacilityCommand(INDEX_FIRST, desc2)));
    }
}
