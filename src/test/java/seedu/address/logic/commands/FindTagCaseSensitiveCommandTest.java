package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PersonTagsContainsCaseSensitiveTagsPredicate;
import seedu.address.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) for {@code FindTagsCommand}.
 */
public class FindTagCaseSensitiveCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        List<Tag> firstTagList = new ArrayList<>();
        firstTagList.add(new Tag("first"));
        List<Tag> secondTagList = new ArrayList<>();
        secondTagList.add(new Tag("second"));
        PersonTagsContainsCaseSensitiveTagsPredicate firstPredicate =
                new PersonTagsContainsCaseSensitiveTagsPredicate(firstTagList);
        PersonTagsContainsCaseSensitiveTagsPredicate secondPredicate =
                new PersonTagsContainsCaseSensitiveTagsPredicate(secondTagList);

        FindTagCaseSensitiveCommand findFirstTagCommand = new FindTagCaseSensitiveCommand(firstPredicate);
        FindTagCaseSensitiveCommand findSecondTagCommand = new FindTagCaseSensitiveCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstTagCommand.equals(findFirstTagCommand));

        // same values -> returns true
        FindTagCaseSensitiveCommand findFirstTagCommandCopy = new FindTagCaseSensitiveCommand(firstPredicate);
        assertTrue(findFirstTagCommand.equals(findFirstTagCommandCopy));

        // different types -> returns false
        assertFalse(findFirstTagCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstTagCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstTagCommand.equals(findSecondTagCommand));
    }

    @Test
    public void execute_oneTag_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonTagsContainsCaseSensitiveTagsPredicate predicate = preparePredicate("sdfiojoij");
        FindTagCaseSensitiveCommand command = new FindTagCaseSensitiveCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneTag_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonTagsContainsCaseSensitiveTagsPredicate predicate = preparePredicate("friends");
        FindTagCaseSensitiveCommand command = new FindTagCaseSensitiveCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneTag_noPersonWithCaseSensitivityFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonTagsContainsCaseSensitiveTagsPredicate predicate = preparePredicate("frieNds");
        FindTagCaseSensitiveCommand command = new FindTagCaseSensitiveCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleTag_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        PersonTagsContainsCaseSensitiveTagsPredicate predicate = preparePredicate("football");
        FindTagCaseSensitiveCommand command = new FindTagCaseSensitiveCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleTags_multiplePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PersonTagsContainsCaseSensitiveTagsPredicate predicate = preparePredicate("friends owesMoney");
        FindTagCaseSensitiveCommand command = new FindTagCaseSensitiveCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private PersonTagsContainsCaseSensitiveTagsPredicate preparePredicate(String userInput) {
        String[] userArguments = userInput.split("\\s+");
        List<Tag> tagList = Arrays.stream(userArguments).map(Tag::new).collect(Collectors.toList());
        return new PersonTagsContainsCaseSensitiveTagsPredicate(tagList);
    }
}
