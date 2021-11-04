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
import seedu.address.model.person.FindAnyPredicate;
import seedu.address.model.person.FindPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;


public class FindAnyCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        List<Name> firstNameList = List.of(new Name(CARL.getFullName()));
        List<Tag> firstTagList = List.of(new Tag("FOOTBALL"), new Tag("friends"));
        List<Name> secondNameList = List.of(new Name(ELLE.getFullName()));
        List<Tag> secondTagList = List.of(new Tag("colleaGues"));
        FindAnyPredicate firstPredicateCaseInsensitive =
                new FindAnyPredicate(firstNameList, firstTagList, false);
        FindAnyPredicate secondPredicateCaseInsensitive =
                new FindAnyPredicate(secondNameList, secondTagList, false);
        FindAnyPredicate firstPredicateCaseSensitive =
                new FindAnyPredicate(firstNameList, firstTagList, true);
        FindAnyPredicate secondPredicateCaseSensitive =
                new FindAnyPredicate(secondNameList, secondTagList, true);

        FindAnyCommand findFirstCommandCaseInsensitive = new FindAnyCommand(firstPredicateCaseInsensitive);
        FindAnyCommand findSecondCommandCaseInsensitive = new FindAnyCommand(secondPredicateCaseInsensitive);
        FindAnyCommand findFirstCommandCaseSensitive = new FindAnyCommand(firstPredicateCaseSensitive);
        FindAnyCommand findSecondCommandCaseSensitive = new FindAnyCommand(secondPredicateCaseSensitive);

        // same object -> returns true
        assertTrue(findFirstCommandCaseInsensitive.equals(findFirstCommandCaseInsensitive));
        assertTrue(findFirstCommandCaseSensitive.equals(findFirstCommandCaseSensitive));


        // same values -> returns true
        FindAnyCommand findFirstCommandCaseInsensitiveCopy = new FindAnyCommand(firstPredicateCaseInsensitive);
        assertTrue(findFirstCommandCaseInsensitiveCopy.equals(findFirstCommandCaseInsensitiveCopy));
        FindAnyCommand findFirstCommandCaseSensitiveCopy = new FindAnyCommand(firstPredicateCaseSensitive);
        assertTrue(findFirstCommandCaseSensitiveCopy.equals(findFirstCommandCaseSensitiveCopy));


        // different types -> returns false
        assertFalse(findFirstCommandCaseInsensitive.equals(1));
        assertFalse(findFirstCommandCaseSensitive.equals(1));

        // null -> returns false
        assertFalse(findFirstCommandCaseInsensitive.equals(null));
        assertFalse(findFirstCommandCaseSensitive.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommandCaseInsensitive.equals(findSecondCommandCaseInsensitive));
        assertFalse(findFirstCommandCaseSensitive.equals(findSecondCommandCaseSensitive));
    }

    @Test
    public void execute_nonExistentName_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        List<Name> firstNameList = List.of(new Name("zeke"));
        List<Tag> firstTagList = List.of();
        FindAnyPredicate findAnyPredicate = new FindAnyPredicate(firstNameList, firstTagList, false);
        FindAnyCommand command = new FindAnyCommand(findAnyPredicate);
        expectedModel.updateFilteredPersonList(findAnyPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneExistentName_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        List<Name> firstNameList = List.of(new Name("benson"));
        List<Tag> firstTagList = List.of();
        FindAnyPredicate findAnyPredicate = new FindAnyPredicate(firstNameList, firstTagList, false);
        FindAnyCommand command = new FindAnyCommand(findAnyPredicate);
        expectedModel.updateFilteredPersonList(findAnyPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneExistentName_twoPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        List<Name> firstNameList = List.of(new Name("hannah"));
        List<Tag> firstTagList = List.of();
        FindAnyPredicate findAnyPredicate = new FindAnyPredicate(firstNameList, firstTagList, false);
        FindAnyCommand command = new FindAnyCommand(findAnyPredicate);
        expectedModel.updateFilteredPersonList(findAnyPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(HANNAH, HANNAH_NO_BIRTHDAY), model.getFilteredPersonList());
    }

    @Test
    public void execute_twoExistentName_twoPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        List<Name> firstNameList = List.of(new Name("alice"), new Name("benson"));
        List<Tag> firstTagList = List.of();
        FindAnyPredicate findAnyPredicate = new FindAnyPredicate(firstNameList, firstTagList, false);
        FindAnyCommand command = new FindAnyCommand(findAnyPredicate);
        expectedModel.updateFilteredPersonList(findAnyPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_nonExistentTagCaseInsensitive_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        List<Name> firstNameList = List.of();
        List<Tag> firstTagList = List.of(new Tag("Chef"));
        FindAnyPredicate findAnyPredicate = new FindAnyPredicate(firstNameList, firstTagList, false);
        FindAnyCommand command = new FindAnyCommand(findAnyPredicate);
        expectedModel.updateFilteredPersonList(findAnyPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_nonExistentTagCaseSensitive_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        List<Name> firstNameList = List.of();
        List<Tag> firstTagList = List.of(new Tag("CHEF"));
        FindAnyPredicate findAnyPredicate = new FindAnyPredicate(firstNameList, firstTagList, true);
        FindAnyCommand command = new FindAnyCommand(findAnyPredicate);
        expectedModel.updateFilteredPersonList(findAnyPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneExistentTagCaseInsensitive_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        List<Name> firstNameList = List.of();
        List<Tag> firstTagList = List.of(new Tag("Football"));
        FindAnyPredicate findAnyPredicate = new FindAnyPredicate(firstNameList, firstTagList, false);
        FindAnyCommand command = new FindAnyCommand(findAnyPredicate);
        expectedModel.updateFilteredPersonList(findAnyPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneExistentTagCaseSensitive_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        List<Name> firstNameList = List.of();
        List<Tag> firstTagList = List.of(new Tag("football"));
        FindAnyPredicate findAnyPredicate = new FindAnyPredicate(firstNameList, firstTagList, true);
        FindAnyCommand command = new FindAnyCommand(findAnyPredicate);
        expectedModel.updateFilteredPersonList(findAnyPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneExistentTagCaseInsensitive_multiplePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        List<Name> firstNameList = List.of();
        List<Tag> firstTagList = List.of(new Tag("friENds"));
        FindAnyPredicate findAnyPredicate = new FindAnyPredicate(firstNameList, firstTagList, false);
        FindAnyCommand command = new FindAnyCommand(findAnyPredicate);
        expectedModel.updateFilteredPersonList(findAnyPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneExistentTagCaseSensitive_zeroPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        List<Name> firstNameList = List.of();
        List<Tag> firstTagList = List.of(new Tag("friENds"));
        FindAnyPredicate findAnyPredicate = new FindAnyPredicate(firstNameList, firstTagList, true);
        FindAnyCommand command = new FindAnyCommand(findAnyPredicate);
        expectedModel.updateFilteredPersonList(findAnyPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleExistentTagCaseInsensitive_multiplePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        List<Name> firstNameList = List.of();
        List<Tag> firstTagList = List.of(new Tag("friends"), new Tag("owesmoney"));
        FindAnyPredicate findPredicate = new FindAnyPredicate(firstNameList, firstTagList, false);
        FindAnyCommand command = new FindAnyCommand(findPredicate);
        expectedModel.updateFilteredPersonList(findPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleExistentTagCaseSensitive_multiplePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        List<Name> firstNameList = List.of();
        List<Tag> firstTagList = List.of(new Tag("friends"), new Tag("owesMoney"));
        FindAnyPredicate findPredicate = new FindAnyPredicate(firstNameList, firstTagList, true);
        FindAnyCommand command = new FindAnyCommand(findPredicate);
        expectedModel.updateFilteredPersonList(findPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_nameAndTagCaseInsensitive_onePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        List<Name> firstNameList = List.of(new Name("best"));
        List<Tag> firstTagList = List.of(new Tag("FOOTBALL"));
        FindPredicate predicate = new FindPredicate(firstNameList, firstTagList, false);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_nameAndTagCaseSensitive_onePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        List<Name> firstNameList = List.of(new Name("best"));
        List<Tag> firstTagList = List.of(new Tag("football"));
        FindPredicate predicate = new FindPredicate(firstNameList, firstTagList, true);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_namesAndTagsCaseSensitive_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        List<Name> firstNameList = List.of(new Name("carl"), new Name("benson"));
        List<Tag> firstTagList = List.of(new Tag("FRIENDS"));
        FindAnyPredicate predicate = new FindAnyPredicate(firstNameList, firstTagList, true);
        FindAnyCommand command = new FindAnyCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, CARL), model.getFilteredPersonList());
    }

    @Test
    public void execute_namesAndTagsCaseInsensitive_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 4);
        List<Name> firstNameList = List.of(new Name("cArL"), new Name("bEnsOn"));
        List<Tag> firstTagList = List.of(new Tag("friENDs"));
        FindAnyPredicate predicate = new FindAnyPredicate(firstNameList, firstTagList, false);
        FindAnyCommand command = new FindAnyCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL), model.getFilteredPersonList());
    }
}
