package safeforhall.logic.commands.find;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static safeforhall.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import safeforhall.commons.core.Messages;
import safeforhall.logic.commands.find.FindPersonCommand.FindCompositePredicate;
import safeforhall.model.Model;
import safeforhall.model.ModelManager;
import safeforhall.model.UserPrefs;
import safeforhall.model.person.Email;
import safeforhall.model.person.Faculty;
import safeforhall.model.person.Name;
import safeforhall.model.person.Phone;
import safeforhall.model.person.VaccStatus;
import safeforhall.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) for {@code FindPersonCommand}.
 */
public class FindPersonCommandTest {
    private Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        FindCompositePredicate firstPredicate = new FindCompositePredicate();
        firstPredicate.setName(new Name("Alice"));
        firstPredicate.setRoom("A100");
        firstPredicate.setFaculty(new Faculty("SoC"));
        firstPredicate.setVaccStatus(new VaccStatus("T"));

        FindCompositePredicate secondPredicate = new FindCompositePredicate();

        FindPersonCommand findFirstCommand = new FindPersonCommand(firstPredicate);
        FindPersonCommand findSecondCommand = new FindPersonCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindPersonCommand findFirstCommandCopy = new FindPersonCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        FindCompositePredicate predicate = preparePredicate("null", null, null, null, null, null);
        FindPersonCommand command = new FindPersonCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        FindCompositePredicate predicate = preparePredicate("Kurz Elle Kunz", null, null, null, null, null);
        FindPersonCommand command = new FindPersonCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalPersons.CARL, TypicalPersons.ELLE, TypicalPersons.FIONA),
                model.getFilteredPersonList());
    }

    @Test
    public void execute_multiplePredicates_multiplePersonsFound() {
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        FindCompositePredicate predicate = preparePredicate(null, null, null, null, "F", null);
        FindPersonCommand command = new FindPersonCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalPersons.CARL, TypicalPersons.ELLE, TypicalPersons.GEORGE),
                model.getFilteredPersonList());
    }

    @Test
    public void execute_multiplePredicates_multiplePersonsFound2() {
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 4);
        FindCompositePredicate predicate = preparePredicate("kurz elle kunz best", null, null, null, null, "soc");
        FindPersonCommand command = new FindPersonCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalPersons.CARL, TypicalPersons.ELLE,
                TypicalPersons.FIONA, TypicalPersons.GEORGE),
                model.getFilteredPersonList());
    }

    @Test
    public void execute_missingFieldWithPrefix_fail() {
        try {
            String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
            FindCompositePredicate predicate = preparePredicate("", null, null, null, null, null);
            FindPersonCommand command = new FindPersonCommand(predicate);
            expectedModel.updateFilteredPersonList(predicate);
            assertCommandSuccess(command, model, expectedMessage, expectedModel);
            assertEquals(Arrays.asList(TypicalPersons.CARL, TypicalPersons.ELLE, TypicalPersons.GEORGE),
                    model.getFilteredPersonList());
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(1, 1);
        }
    }

    @Test
    public void execute_invalidRoom_fail() {
        try {
            String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
            FindCompositePredicate predicate = preparePredicate(null, "A12", null, null, null, null);
            FindPersonCommand command = new FindPersonCommand(predicate);
            expectedModel.updateFilteredPersonList(predicate);
            assertCommandSuccess(command, model, expectedMessage, expectedModel);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(1, 1);
        }
    }

    @Test
    public void execute_invalidRoom2_fail() {
        try {
            String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
            FindCompositePredicate predicate = preparePredicate(null, "1A", null, null, null, null);
            FindPersonCommand command = new FindPersonCommand(predicate);
            expectedModel.updateFilteredPersonList(predicate);
            assertCommandSuccess(command, model, expectedMessage, expectedModel);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(1, 1);
        }
    }

    @Test
    public void execute_validRoom_fail() {
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        FindCompositePredicate predicate = preparePredicate(null, "a1", null, null, null, null);
        FindPersonCommand command = new FindPersonCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalPersons.ALICE, TypicalPersons.BENSON, TypicalPersons.CARL,
                TypicalPersons.DANIEL, TypicalPersons.ELLE, TypicalPersons.FIONA, TypicalPersons.GEORGE),
                model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code FindCompositePredicate}.
     */
    private FindCompositePredicate preparePredicate(String name, String room , String phone, String email,
                                                    String vaccStatus, String faculty) {
        FindCompositePredicate f = new FindCompositePredicate();

        if (name != null) {
            f.setName(new Name(name));
        }
        if (room != null) {
            f.setRoom(room);
        }
        if (phone != null) {
            f.setPhone(new Phone(phone));
        }
        if (email != null) {
            f.setEmail(new Email(email));
        }
        if (vaccStatus != null) {
            f.setVaccStatus(new VaccStatus(vaccStatus));
        }
        if (faculty != null) {
            f.setFaculty(new Faculty(faculty));
        }

        return f;
    }
}
