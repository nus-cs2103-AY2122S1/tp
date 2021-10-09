package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.ClaimCommand.MESSAGE_CLAIM_ADDED_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

public class ClaimCommandTest {


    @Test
    public void execute_validInput_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Person personToAddClaim = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        ClaimCommand claimCommand = new ClaimCommand(INDEX_FIRST_PERSON, CLAIM_AMY);

        Person personAfterAddClaim = new Person(personToAddClaim, Set.of(CLAIM_AMY));

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToAddClaim, personAfterAddClaim);

        String expectedMessage = String.format(MESSAGE_CLAIM_ADDED_SUCCESS, CLAIM_AMY);

        assertCommandSuccess(claimCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ClaimCommand claimCommand = new ClaimCommand(outOfBoundIndex, CLAIM_AMY);
        assertCommandFailure(claimCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        // same values -> return true
        assertTrue(new ClaimCommand(INDEX_FIRST_PERSON, CLAIM_AMY)
                .equals(new ClaimCommand(INDEX_FIRST_PERSON, CLAIM_AMY)));

        // same object -> return true
        ClaimCommand claimCommand = new ClaimCommand(INDEX_FIRST_PERSON, CLAIM_AMY);
        assertTrue(claimCommand.equals(claimCommand));

        // different index -> return false
        assertFalse(new ClaimCommand(INDEX_FIRST_PERSON, CLAIM_AMY)
                .equals(new ClaimCommand(INDEX_SECOND_PERSON, CLAIM_AMY)));

        // different claim -> return false
        assertFalse(new ClaimCommand(INDEX_FIRST_PERSON, CLAIM_AMY)
                .equals(new ClaimCommand(INDEX_FIRST_PERSON, CLAIM_BOB)));

        // different type -> return false
        assertFalse(new ClaimCommand(INDEX_FIRST_PERSON, CLAIM_AMY)
                .equals(2));
    }
}
