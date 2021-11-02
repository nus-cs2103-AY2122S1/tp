package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.CategoryCode;
import seedu.address.model.person.IsFilterablePredicate;
import seedu.address.model.person.IsFindableContainsKeywordsPredicate;
import seedu.address.model.person.Rating;
import seedu.address.model.tag.Tag;

class FilterCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        Set<CategoryCode> firstCategoryCodeSet = Collections.singleton(new CategoryCode("att"));
        Set<CategoryCode> secondCategoryCodeSet = new HashSet<>(
                Arrays.asList(new CategoryCode("fnb"), new CategoryCode("att")));
        Rating firstRating = new Rating("2");
        Rating secondRating = new Rating("4");
        Set<Tag> firstTag = new HashSet<>(Arrays.asList(new Tag("outdoor")));
        Set<Tag> secondTag = Collections.emptySet();

        FilterCommand firstFilterCommand = new FilterCommand(firstCategoryCodeSet, firstRating, firstTag);
        FilterCommand secondFilterCommand = new FilterCommand(secondCategoryCodeSet, secondRating, secondTag);
        FilterCommand thirdFilterCommand = new FilterCommand(firstCategoryCodeSet, secondRating, firstTag);

        // same object -> returns true
        assertTrue(firstFilterCommand.equals(firstFilterCommand));

        // same values -> returns true
        FilterCommand firstFilterCommandCopy = new FilterCommand(firstCategoryCodeSet, firstRating, firstTag);
        assertTrue(firstFilterCommand.equals(firstFilterCommandCopy));

        // different types -> returns false
        assertFalse(firstFilterCommand.equals(1));

        // null -> returns false
        assertFalse(firstFilterCommand.equals(null));

        // different category codes -> returns false
        assertFalse(firstFilterCommand.equals(secondFilterCommand));

        // different ratings -> returns false
        assertFalse(firstFilterCommand.equals(thirdFilterCommand));
    }


    // TODO [LETHICIA]
    @Test
    public void execute_oneCategoryCode_noPersonFound() {

    }

    @Test
    public void execute_oneCategoryCode_singlePersonFound() {

    }

    @Test
    public void execute_oneCategoryCode_multiplePersonsFound() {

    }

    @Test
    public void execute_multipleCategoryCode_singlePersonsFound() {

    }

    @Test
    public void execute_multipleCategoryCode_multiplePersonsFound() {

    }

    @Test
    public void execute_oneRating_noPersonsFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 0);
        Set<CategoryCode> categoryCodes = Collections.emptySet();
        Rating rating = new Rating("1");
        Set<Tag> tags = Collections.emptySet();
        IsFilterablePredicate predicate = new IsFilterablePredicate(categoryCodes, rating, tags);
        FilterCommand command = new FilterCommand(categoryCodes, rating, tags);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneRating_singlePersonsFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 1);
        Set<CategoryCode> categoryCodes = Collections.emptySet();
        Rating rating = new Rating("3");
        Set<Tag> tags = Collections.emptySet();
        IsFilterablePredicate predicate = new IsFilterablePredicate(categoryCodes, rating, tags);
        FilterCommand command = new FilterCommand(categoryCodes, rating, tags);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneRating_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 3);
        Set<CategoryCode> categoryCodes = Collections.emptySet();
        Rating rating = new Rating("5");
        Set<Tag> tags = Collections.emptySet();
        IsFilterablePredicate predicate = new IsFilterablePredicate(categoryCodes, rating, tags);
        FilterCommand command = new FilterCommand(categoryCodes, rating, tags);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL, ELLE, FIONA), model.getFilteredPersonList());
    }

    // TODO [LETHICIA]
    @Test
    public void execute_oneTag_noPersonFound() {

    }

    @Test
    public void execute_multipleTag_noPersonFound() {

    }

    @Test
    public void execute_oneTag_singlePersonFound() {

    }

    @Test
    public void execute_oneTag_multiplePersonsFound() {

    }

    @Test
    public void execute_multipleTag_singlePersonsFound() {

    }

    @Test
    public void execute_multipleTag_multiplePersonsFound() {

    }

}
