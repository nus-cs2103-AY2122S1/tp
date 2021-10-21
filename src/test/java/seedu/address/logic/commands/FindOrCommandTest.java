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
import seedu.address.model.person.FindOrPredicate;
import seedu.address.model.person.FindPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;


public class FindOrCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        List<Name> firstNameList = List.of(new Name(CARL.getFullName()));
        List<Tag> firstTagList = List.of(new Tag("football"), new Tag("friends"));
        List<Name> secondNameList = List.of(new Name(ELLE.getFullName()));
        List<Tag> secondTagList = List.of(new Tag("colleagues"));
        FindOrPredicate firstPredicate = new FindOrPredicate(firstNameList, firstTagList);
        FindOrPredicate secondPredicate = new FindOrPredicate(secondNameList, secondTagList);

        FindOrCommand findFirstCommand = new FindOrCommand(firstPredicate);
        FindOrCommand findSecondCommand = new FindOrCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindOrCommand findFirstCommandCopy = new FindOrCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_nonExistentName_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        List<Name> firstNameList = List.of(new Name("zeke"));
        List<Tag> firstTagList = List.of();
        FindOrPredicate findOrPredicate = new FindOrPredicate(firstNameList, firstTagList);
        FindOrCommand command = new FindOrCommand(findOrPredicate);
        expectedModel.updateFilteredPersonList(findOrPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneExistentName_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        List<Name> firstNameList = List.of(new Name("benson"));
        List<Tag> firstTagList = List.of();
        FindOrPredicate findOrPredicate = new FindOrPredicate(firstNameList, firstTagList);
        FindOrCommand command = new FindOrCommand(findOrPredicate);
        expectedModel.updateFilteredPersonList(findOrPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneExistentName_twoPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        List<Name> firstNameList = List.of(new Name("hannah"));
        List<Tag> firstTagList = List.of();
        FindOrPredicate findOrPredicate = new FindOrPredicate(firstNameList, firstTagList);
        FindOrCommand command = new FindOrCommand(findOrPredicate);
        expectedModel.updateFilteredPersonList(findOrPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(HANNAH, HANNAH_NO_BIRTHDAY), model.getFilteredPersonList());
    }

    @Test
    public void execute_twoExistentName_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        List<Name> firstNameList = List.of(new Name("alice"), new Name("benson"));
        List<Tag> firstTagList = List.of();
        FindOrPredicate findOrPredicate = new FindOrPredicate(firstNameList, firstTagList);
        FindOrCommand command = new FindOrCommand(findOrPredicate);
        expectedModel.updateFilteredPersonList(findOrPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_nonExistentTag_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        List<Name> firstNameList = List.of();
        List<Tag> firstTagList = List.of(new Tag("Chef"));
        FindOrPredicate findOrPredicate = new FindOrPredicate(firstNameList, firstTagList);
        FindOrCommand command = new FindOrCommand(findOrPredicate);
        expectedModel.updateFilteredPersonList(findOrPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneExistentTag_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        List<Name> firstNameList = List.of();
        List<Tag> firstTagList = List.of(new Tag("football"));
        FindOrPredicate findOrPredicate = new FindOrPredicate(firstNameList, firstTagList);
        FindOrCommand command = new FindOrCommand(findOrPredicate);
        expectedModel.updateFilteredPersonList(findOrPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneExistentTag_multiplePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        List<Name> firstNameList = List.of();
        List<Tag> firstTagList = List.of(new Tag("friends"));
        FindOrPredicate findOrPredicate = new FindOrPredicate(firstNameList, firstTagList);
        FindOrCommand command = new FindOrCommand(findOrPredicate);
        expectedModel.updateFilteredPersonList(findOrPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleExistentTag_multiplePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        List<Name> firstNameList = List.of();
        List<Tag> firstTagList = List.of(new Tag("friends"), new Tag("owesMoney"));
        FindOrPredicate findPredicate = new FindOrPredicate(firstNameList, firstTagList);
        FindOrCommand command = new FindOrCommand(findPredicate);
        expectedModel.updateFilteredPersonList(findPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_nameAndTag_onePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        List<Name> firstNameList = List.of(new Name("best"));
        List<Tag> firstTagList = List.of(new Tag("football"));
        FindPredicate predicate = new FindPredicate(firstNameList, firstTagList);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_namesAndTags_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 4);
        List<Name> firstNameList = List.of(new Name("carl"), new Name("benson"));
        List<Tag> firstTagList = List.of(new Tag("friends"));
        FindOrPredicate predicate = new FindOrPredicate(firstNameList, firstTagList);
        FindOrCommand command = new FindOrCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_namesAndTagsCaseInsensitive_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 4);
        List<Name> firstNameList = List.of(new Name("cArL"), new Name("bEnsOn"));
        List<Tag> firstTagList = List.of(new Tag("friENDs"));
        FindOrPredicate predicate = new FindOrPredicate(firstNameList, firstTagList);
        FindOrCommand command = new FindOrCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL), model.getFilteredPersonList());
    }
}
