package seedu.address.logic.commands;

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
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.HANNAH;
import static seedu.address.testutil.TypicalPersons.HANNAH_NO_BIRTHDAY;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.FindPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;


/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        List<Name> firstNameList = List.of(new Name(CARL.getFullName()));
        List<Tag> firstTagList = List.of(new Tag("football"), new Tag("friends"));
        List<Name> secondNameList = List.of(new Name(ELLE.getFullName()));
        List<Tag> secondTagList = List.of(new Tag("colleagues"));
        FindPredicate firstPredicateCaseInsensitive =
                new FindPredicate(firstNameList, firstTagList, false);
        FindPredicate secondPredicateCaseInsensitive =
                new FindPredicate(secondNameList, secondTagList, false);
        FindPredicate firstPredicateCaseSensitive =
                new FindPredicate(firstNameList, firstTagList, false);
        FindPredicate secondPredicateCaseSensitive =
                new FindPredicate(secondNameList, secondTagList, false);

        FindCommand findFirstCommandCaseInsensitive = new FindCommand(firstPredicateCaseInsensitive);
        FindCommand findSecondCommandCaseInsensitive = new FindCommand(secondPredicateCaseInsensitive);
        FindCommand findFirstCommandCaseSensitive = new FindCommand(firstPredicateCaseInsensitive);
        FindCommand findSecondCommandCaseSensitive = new FindCommand(secondPredicateCaseInsensitive);

        // same object -> returns true
        assertTrue(findFirstCommandCaseInsensitive.equals(findFirstCommandCaseSensitive));

        // same values -> returns true
        FindCommand findFirstCommandCaseInsensitiveCopy = new FindCommand(firstPredicateCaseInsensitive);
        assertTrue(findFirstCommandCaseInsensitive.equals(findFirstCommandCaseInsensitiveCopy));

        FindCommand findFirstCommandCaseSensitiveCopy = new FindCommand(firstPredicateCaseSensitive);
        assertTrue(findFirstCommandCaseSensitive.equals(findFirstCommandCaseSensitiveCopy));

        // different types -> returns false
        assertFalse(findFirstCommandCaseInsensitive.equals(1));

        // null -> returns false
        assertFalse(findFirstCommandCaseInsensitive.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommandCaseInsensitive.equals(findSecondCommandCaseInsensitive));
        assertFalse(findFirstCommandCaseSensitive.equals(findSecondCommandCaseSensitive));
    }

    @Test
    public void execute_nonExistentName_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        List<Name> firstNameList = List.of(new Name("zeke"));
        List<Tag> firstTagList = List.of();
        FindPredicate findPredicate = new FindPredicate(firstNameList, firstTagList, false);
        FindCommand command = new FindCommand(findPredicate);
        expectedModel.updateFilteredPersonList(findPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneExistentName_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        List<Name> firstNameList = List.of(new Name("benson"));
        List<Tag> firstTagList = List.of();
        FindPredicate findPredicate = new FindPredicate(firstNameList, firstTagList, false);
        FindCommand command = new FindCommand(findPredicate);
        expectedModel.updateFilteredPersonList(findPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneExistentName_twoPersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        List<Name> firstNameList = List.of(new Name("hannah"));
        List<Tag> firstTagList = List.of();
        FindPredicate findPredicate = new FindPredicate(firstNameList, firstTagList, false);
        FindCommand command = new FindCommand(findPredicate);
        expectedModel.updateFilteredPersonList(findPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(HANNAH, HANNAH_NO_BIRTHDAY), model.getFilteredPersonList());
    }

    @Test
    public void execute_twoExistentName_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        List<Name> firstNameList = List.of(new Name("alice"), new Name("benson"));
        List<Tag> firstTagList = List.of();
        FindPredicate findPredicate = new FindPredicate(firstNameList, firstTagList, false);
        FindCommand command = new FindCommand(findPredicate);
        expectedModel.updateFilteredPersonList(findPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_nonExistentTagCaseInsensitive_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        List<Name> firstNameList = List.of();
        List<Tag> firstTagList = List.of(new Tag("Chef"));
        FindPredicate findPredicate = new FindPredicate(firstNameList, firstTagList, false);
        FindCommand command = new FindCommand(findPredicate);
        expectedModel.updateFilteredPersonList(findPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_nonExistentTagCaseSensitive_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        List<Name> firstNameList = List.of();
        List<Tag> firstTagList = List.of(new Tag("Chef"));
        FindPredicate findPredicate = new FindPredicate(firstNameList, firstTagList, true);
        FindCommand command = new FindCommand(findPredicate);
        expectedModel.updateFilteredPersonList(findPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneExistentTagCaseInsensitive_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        List<Name> firstNameList = List.of();
        List<Tag> firstTagList = List.of(new Tag("FOOtball"));
        FindPredicate findPredicate = new FindPredicate(firstNameList, firstTagList, false);
        FindCommand command = new FindCommand(findPredicate);
        expectedModel.updateFilteredPersonList(findPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneExistentTagCaseSensitive_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        List<Name> firstNameList = List.of();
        List<Tag> firstTagList = List.of(new Tag("Football"));
        FindPredicate findPredicate = new FindPredicate(firstNameList, firstTagList, true);
        FindCommand command = new FindCommand(findPredicate);
        expectedModel.updateFilteredPersonList(findPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneExistentTagCaseInsensitive_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        List<Name> firstNameList = List.of();
        List<Tag> firstTagList = List.of(new Tag("friends"));
        FindPredicate findPredicate = new FindPredicate(firstNameList, firstTagList, false);
        FindCommand command = new FindCommand(findPredicate);
        expectedModel.updateFilteredPersonList(findPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneExistentTagCaseSensitive_noPersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        List<Name> firstNameList = List.of();
        List<Tag> firstTagList = List.of(new Tag("FRIENDS"));
        FindPredicate findPredicate = new FindPredicate(firstNameList, firstTagList, true);
        FindCommand command = new FindCommand(findPredicate);
        expectedModel.updateFilteredPersonList(findPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleExistentTagCaseInsensitive_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        List<Name> firstNameList = List.of();
        List<Tag> firstTagList = List.of(new Tag("friends"), new Tag("owesmoney"));
        FindPredicate findPredicate = new FindPredicate(firstNameList, firstTagList, false);
        FindCommand command = new FindCommand(findPredicate);
        expectedModel.updateFilteredPersonList(findPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleExistentTagCaseSensitive_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        List<Name> firstNameList = List.of();
        List<Tag> firstTagList = List.of(new Tag("friends"), new Tag("owesMoney"));
        FindPredicate findPredicate = new FindPredicate(firstNameList, firstTagList, true);
        FindCommand command = new FindCommand(findPredicate);
        expectedModel.updateFilteredPersonList(findPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_nameAndTagCaseSensitive_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        List<Name> firstNameList = List.of(new Name("benson"));
        List<Tag> firstTagList = List.of(new Tag("FRIENDS"));
        FindPredicate predicate = new FindPredicate(firstNameList, firstTagList, true);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_nameAndTagCaseInsensitive_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        List<Name> firstNameList = List.of(new Name("bEnSon"));
        List<Tag> firstTagList = List.of(new Tag("fRiENds"));
        FindPredicate predicate = new FindPredicate(firstNameList, firstTagList, false);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredPersonList());
    }
}
