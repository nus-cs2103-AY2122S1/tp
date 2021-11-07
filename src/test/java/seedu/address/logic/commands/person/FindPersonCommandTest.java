package seedu.address.logic.commands.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalConthacks;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ModuleCodesContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindPersonCommandTest {
    private Model model = new ModelManager(getTypicalConthacks(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalConthacks(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindPersonCommand findFirstNameCommand = new FindPersonCommand(firstNamePredicate);
        FindPersonCommand findSecondSecondCommand = new FindPersonCommand(secondNamePredicate);

        // same object -> returns true
        assertTrue(findFirstNameCommand.equals(findFirstNameCommand));

        // same values -> returns true
        FindPersonCommand findFirstNameCommandCopy = new FindPersonCommand(firstNamePredicate);
        assertTrue(findFirstNameCommand.equals(findFirstNameCommandCopy));

        // different types -> returns false
        assertFalse(findFirstNameCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstNameCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstNameCommand.equals(findSecondSecondCommand));

        ModuleCodesContainsKeywordsPredicate firstModulePredicate =
                new ModuleCodesContainsKeywordsPredicate(Collections.singletonList("first"));
        ModuleCodesContainsKeywordsPredicate secondModulePredicate =
                new ModuleCodesContainsKeywordsPredicate(Collections.singletonList("second"));

        FindPersonCommand findFirstModuleCommand = new FindPersonCommand(firstModulePredicate);
        FindPersonCommand findSecondModuleCommand = new FindPersonCommand(secondModulePredicate);

        // same object -> returns true
        assertTrue(findFirstModuleCommand.equals(findFirstModuleCommand));

        // same values -> returns true
        FindPersonCommand findFirstModuleCommandCopy = new FindPersonCommand(firstModulePredicate);
        assertTrue(findFirstModuleCommand.equals(findFirstModuleCommandCopy));

        // different types -> returns false
        assertFalse(findFirstModuleCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstModuleCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstModuleCommand.equals(findSecondModuleCommand));
    }

    @Test
    public void execute_oneName_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate("Elle");
        FindPersonCommand command = new FindPersonCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleModuleCodes_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ModuleCodesContainsKeywordsPredicate predicate = prepareModulePredicate("CS2103T CS2040");
        FindPersonCommand command = new FindPersonCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleModuleCodes_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        ModuleCodesContainsKeywordsPredicate predicate = prepareModulePredicate("CS2030S CS2040");
        FindPersonCommand command = new FindPersonCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code ModuleCodesContainsKeywordsPredicate}.
     */
    private ModuleCodesContainsKeywordsPredicate prepareModulePredicate(String userInput) {
        List<String> moduleKeywordsList = Arrays.stream(userInput.split("\\s+"))
                .collect(Collectors.toList());
        return new ModuleCodesContainsKeywordsPredicate(moduleKeywordsList);
    }
}
