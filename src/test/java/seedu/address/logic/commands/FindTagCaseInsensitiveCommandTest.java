package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.DANIEL;
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
import seedu.address.model.person.PersonTagsContainsCaseInsensitiveTagsPredicate;
import seedu.address.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) for {@code FindTagsCommand}.
 */
public class FindTagCaseInsensitiveCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        List<Tag> firstTagList = new ArrayList<>();
        firstTagList.add(new Tag("first"));
        List<Tag> secondTagList = new ArrayList<>();
        secondTagList.add(new Tag("second"));
        PersonTagsContainsCaseInsensitiveTagsPredicate firstPredicate =
                new PersonTagsContainsCaseInsensitiveTagsPredicate(firstTagList);
        PersonTagsContainsCaseInsensitiveTagsPredicate secondPredicate =
                new PersonTagsContainsCaseInsensitiveTagsPredicate(secondTagList);

        FindTagCaseInsensitiveCommand findFirstTagCommand = new FindTagCaseInsensitiveCommand(firstPredicate);
        FindTagCaseInsensitiveCommand findSecondTagCommand = new FindTagCaseInsensitiveCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstTagCommand.equals(findFirstTagCommand));

        // same values -> returns true
        FindTagCaseInsensitiveCommand findFirstTagCommandCopy = new FindTagCaseInsensitiveCommand(firstPredicate);
        assertTrue(findFirstTagCommand.equals(findFirstTagCommandCopy));

        // different types -> returns false
        assertFalse(findFirstTagCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstTagCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstTagCommand.equals(findSecondTagCommand));
    }

    @Test
    public void execute_oneTags_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonTagsContainsCaseInsensitiveTagsPredicate predicate = preparePredicate("sdfiojoij");
        FindTagCaseInsensitiveCommand command = new FindTagCaseInsensitiveCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneTag_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonTagsContainsCaseInsensitiveTagsPredicate predicate = preparePredicate("friends");
        FindTagCaseInsensitiveCommand command = new FindTagCaseInsensitiveCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneTag_multiplePersonsWithCaseSensitivityFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonTagsContainsCaseInsensitiveTagsPredicate predicate = preparePredicate("frieNds");
        FindTagCaseInsensitiveCommand command = new FindTagCaseInsensitiveCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleTag_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PersonTagsContainsCaseInsensitiveTagsPredicate predicate = preparePredicate("friends owesMoney");
        FindTagCaseInsensitiveCommand command = new FindTagCaseInsensitiveCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private PersonTagsContainsCaseInsensitiveTagsPredicate preparePredicate(String userInput) {
        String[] userArguments = userInput.split("\\s+");
        List<Tag> tagList = Arrays.stream(userArguments).map(Tag::new).collect(Collectors.toList());
        return new PersonTagsContainsCaseInsensitiveTagsPredicate(tagList);
    }
}
