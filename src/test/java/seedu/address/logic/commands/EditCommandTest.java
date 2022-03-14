package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_P_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_P_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showParticipantAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PARTICIPANT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PARTICIPANT;
import static seedu.address.testutil.TypicalParticipants.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditParticipantDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.participant.Participant;
import seedu.address.testutil.EditParticipantDescriptorBuilder;
import seedu.address.testutil.ParticipantBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private UserPrefs standardUserPrefs = new UserPrefs();
    private Model model = new ModelManager(getTypicalAddressBook(), standardUserPrefs);
    private Model anotherModel = new ModelManager(getTypicalAddressBook(), standardUserPrefs);

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Participant editedParticipant = new ParticipantBuilder().build();
        EditParticipantDescriptor descriptor = new EditParticipantDescriptorBuilder(editedParticipant).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PARTICIPANT, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PARTICIPANT_SUCCESS, editedParticipant);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), standardUserPrefs);
        expectedModel.setParticipant(model.getFilteredParticipantList().get(0), editedParticipant);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastParticipant = Index.fromOneBased(model.getFilteredParticipantList().size());
        Participant lastParticipant = model.getFilteredParticipantList().get(indexLastParticipant.getZeroBased());

        ParticipantBuilder participantInList = new ParticipantBuilder(lastParticipant);
        Participant editedParticipant = participantInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB).build();

        EditParticipantDescriptor descriptor = new EditParticipantDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).build();
        EditCommand editCommand = new EditCommand(indexLastParticipant, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PARTICIPANT_SUCCESS, editedParticipant);

        Model expectedModel = new ModelManager(new AddressBook(anotherModel.getAddressBook()), standardUserPrefs);
        expectedModel.setParticipant(lastParticipant, editedParticipant);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PARTICIPANT, new EditParticipantDescriptor());
        Participant editedParticipant = model.getFilteredParticipantList().get(INDEX_FIRST_PARTICIPANT.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PARTICIPANT_SUCCESS, editedParticipant);

        Model expectedModel = new ModelManager(new AddressBook(anotherModel.getAddressBook()), standardUserPrefs);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showParticipantAtIndex(model, INDEX_FIRST_PARTICIPANT);

        Participant participantInFilteredList = model.getFilteredParticipantList()
                .get(INDEX_FIRST_PARTICIPANT.getZeroBased());
        Participant editedParticipant = new ParticipantBuilder(participantInFilteredList)
                .withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PARTICIPANT,
                new EditParticipantDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PARTICIPANT_SUCCESS, editedParticipant);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), standardUserPrefs);
        showParticipantAtIndex(expectedModel, INDEX_FIRST_PARTICIPANT);
        expectedModel.setParticipant(model.getFilteredParticipantList().get(0), editedParticipant);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateParticipantUnfilteredList_failure() {
        Participant firstParticipant = model.getFilteredParticipantList().get(INDEX_FIRST_PARTICIPANT.getZeroBased());
        EditParticipantDescriptor descriptor = new EditParticipantDescriptorBuilder(firstParticipant).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_PARTICIPANT, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PARTICIPANT);
    }

    @Test
    public void execute_duplicateParticipantFilteredList_failure() {
        showParticipantAtIndex(model, INDEX_FIRST_PARTICIPANT);

        // edit participant in filtered list into a duplicate in address book
        Participant participantInList = model.getAddressBook()
                .getParticipantList().get(INDEX_SECOND_PARTICIPANT.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PARTICIPANT,
                new EditParticipantDescriptorBuilder(participantInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PARTICIPANT);
    }

    @Test
    public void execute_invalidParticipantIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredParticipantList().size() + 1);
        EditParticipantDescriptor descriptor = new EditParticipantDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PARTICIPANT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidParticipantIndexFilteredList_failure() {
        showParticipantAtIndex(model, INDEX_FIRST_PARTICIPANT);
        Index outOfBoundIndex = INDEX_SECOND_PARTICIPANT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getParticipantList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditParticipantDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PARTICIPANT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PARTICIPANT, DESC_P_AMY);

        // same values -> returns true
        EditParticipantDescriptor copyDescriptor = new EditParticipantDescriptor(DESC_P_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PARTICIPANT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PARTICIPANT, DESC_P_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PARTICIPANT, DESC_P_BOB)));
    }

}
