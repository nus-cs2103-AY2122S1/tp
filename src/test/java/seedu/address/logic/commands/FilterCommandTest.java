package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalContacts.AIRZONE;
import static seedu.address.testutil.TypicalContacts.BATTLEBOX;
import static seedu.address.testutil.TypicalContacts.FUKTAKCHI;
import static seedu.address.testutil.TypicalContacts.GSEA;
import static seedu.address.testutil.TypicalContacts.HOTEL_SOLOHA;
import static seedu.address.testutil.TypicalContacts.ICHIBAN;
import static seedu.address.testutil.TypicalContacts.JCUBE;
import static seedu.address.testutil.TypicalContacts.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.CategoryCode;
import seedu.address.model.contact.IsFilterablePredicate;
import seedu.address.model.contact.Rating;
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


    @Test
    public void execute_oneCategoryCode_noContactFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 0);
        Set<CategoryCode> categoryCodes = Collections.singleton(new CategoryCode("tpt"));
        Rating rating = new Rating();
        Set<Tag> tags = Collections.emptySet();
        IsFilterablePredicate predicate = new IsFilterablePredicate(categoryCodes, rating, tags);
        FilterCommand command = new FilterCommand(categoryCodes, rating, tags);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredContactList());
    }

    @Test
    public void execute_oneCategoryCode_singleContactFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 1);
        Set<CategoryCode> categoryCodes = Collections.singleton(new CategoryCode("fnb"));
        Rating rating = new Rating();
        Set<Tag> tags = Collections.emptySet();
        IsFilterablePredicate predicate = new IsFilterablePredicate(categoryCodes, rating, tags);
        FilterCommand command = new FilterCommand(categoryCodes, rating, tags);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ICHIBAN), model.getFilteredContactList());
    }

    @Test
    public void execute_oneCategoryCode_multipleContactsFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 2);
        Set<CategoryCode> categoryCodes = Collections.singleton(new CategoryCode("oth"));
        Rating rating = new Rating();
        Set<Tag> tags = Collections.emptySet();
        IsFilterablePredicate predicate = new IsFilterablePredicate(categoryCodes, rating, tags);
        FilterCommand command = new FilterCommand(categoryCodes, rating, tags);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        assertEquals(Arrays.asList(BATTLEBOX, FUKTAKCHI), model.getFilteredContactList());
    }

    @Test
    public void execute_multipleCategoryCode_multipleContactsFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 3);
        Set<CategoryCode> categoryCodes = new HashSet<>(Arrays.asList(new CategoryCode("oth"),
                new CategoryCode("fnb")));
        Rating rating = new Rating();
        Set<Tag> tags = Collections.emptySet();
        IsFilterablePredicate predicate = new IsFilterablePredicate(categoryCodes, rating, tags);
        FilterCommand command = new FilterCommand(categoryCodes, rating, tags);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        assertEquals(Arrays.asList(BATTLEBOX, FUKTAKCHI, ICHIBAN), model.getFilteredContactList());
    }

    @Test
    public void execute_oneRating_noContactsFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 0);
        Set<CategoryCode> categoryCodes = Collections.emptySet();
        Rating rating = new Rating("1");
        Set<Tag> tags = Collections.emptySet();
        IsFilterablePredicate predicate = new IsFilterablePredicate(categoryCodes, rating, tags);
        FilterCommand command = new FilterCommand(categoryCodes, rating, tags);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredContactList());
    }

    @Test
    public void execute_oneRating_singleContactsFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 1);
        Set<CategoryCode> categoryCodes = Collections.emptySet();
        Rating rating = new Rating("3");
        Set<Tag> tags = Collections.emptySet();
        IsFilterablePredicate predicate = new IsFilterablePredicate(categoryCodes, rating, tags);
        FilterCommand command = new FilterCommand(categoryCodes, rating, tags);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(AIRZONE), model.getFilteredContactList());
    }

    @Test
    public void execute_oneRating_multipleContactsFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 4);
        Set<CategoryCode> categoryCodes = Collections.emptySet();
        Rating rating = new Rating("5");
        Set<Tag> tags = Collections.emptySet();
        IsFilterablePredicate predicate = new IsFilterablePredicate(categoryCodes, rating, tags);
        FilterCommand command = new FilterCommand(categoryCodes, rating, tags);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        assertEquals(Arrays.asList(FUKTAKCHI, GSEA, HOTEL_SOLOHA, JCUBE), model.getFilteredContactList());
    }

    // TODO [LETHICIA]
    @Test
    public void execute_oneTag_noContactFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 0);
        Set<CategoryCode> categoryCodes = Collections.EMPTY_SET;
        Rating rating = new Rating();
        Set<Tag> tags = new HashSet<Tag>(Arrays.asList(new Tag("neighbour")));;
        IsFilterablePredicate predicate = new IsFilterablePredicate(categoryCodes, rating, tags);
        FilterCommand command = new FilterCommand(categoryCodes, rating, tags);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredContactList());

    }


    @Test
    public void execute_oneTag_multipleContactsFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 3);
        Set<CategoryCode> categoryCodes = Collections.EMPTY_SET;
        Rating rating = new Rating();
        Set<Tag> tags = new HashSet<Tag>(Arrays.asList(new Tag("friends")));
        IsFilterablePredicate predicate = new IsFilterablePredicate(categoryCodes, rating, tags);
        FilterCommand command = new FilterCommand(categoryCodes, rating, tags);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        assertEquals(Arrays.asList(AIRZONE, BATTLEBOX, FUKTAKCHI), model.getFilteredContactList());

    }

    @Test
    public void execute_multipleTag_multipleContactFound() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 2);
        Set<CategoryCode> categoryCodes = Collections.EMPTY_SET;
        Rating rating = new Rating();
        Set<Tag> tags = new HashSet<Tag>(Arrays.asList(new Tag("new"), new Tag("fun")));
        IsFilterablePredicate predicate = new IsFilterablePredicate(categoryCodes, rating, tags);
        FilterCommand command = new FilterCommand(categoryCodes, rating, tags);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        assertEquals(Arrays.asList(AIRZONE, BATTLEBOX), model.getFilteredContactList());


    }

    // combined criteria
    @Test
    public void execute_categoryCodeWithRating() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 2);
        Set<CategoryCode> categoryCodes = new HashSet<CategoryCode>(Arrays.asList(new CategoryCode("oth"),
                new CategoryCode("att")));
        Rating rating = new Rating("5");
        Set<Tag> tags = Collections.emptySet();
        IsFilterablePredicate predicate = new IsFilterablePredicate(categoryCodes, rating, tags);
        FilterCommand command = new FilterCommand(categoryCodes, rating, tags);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        assertEquals(Arrays.asList(FUKTAKCHI, GSEA), model.getFilteredContactList());
    }

    @Test
    public void execute_categoryCodeWithTag() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 2);
        Set<CategoryCode> categoryCodes = new HashSet<CategoryCode>(Arrays.asList(new CategoryCode("oth"),
                new CategoryCode("acc")));
        Rating rating = new Rating();
        Set<Tag> tags = new HashSet<Tag>(Arrays.asList(new Tag("friends")));
        IsFilterablePredicate predicate = new IsFilterablePredicate(categoryCodes, rating, tags);
        FilterCommand command = new FilterCommand(categoryCodes, rating, tags);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        assertEquals(Arrays.asList(BATTLEBOX, FUKTAKCHI), model.getFilteredContactList());
    }

    @Test
    public void execute_tagWithRating() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 1);
        Set<CategoryCode> categoryCodes = Collections.EMPTY_SET;
        Rating rating = new Rating("5");
        Set<Tag> tags = new HashSet<Tag>(Arrays.asList(new Tag("friends")));
        IsFilterablePredicate predicate = new IsFilterablePredicate(categoryCodes, rating, tags);
        FilterCommand command = new FilterCommand(categoryCodes, rating, tags);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        assertEquals(Arrays.asList(FUKTAKCHI), model.getFilteredContactList());
    }

    @Test
    public void execute_allCriteria() {
        String expectedMessage = String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 1);
        Set<CategoryCode> categoryCodes = new HashSet<CategoryCode>(Arrays.asList(new CategoryCode("oth"),
                new CategoryCode("acc"), new CategoryCode("tpt")));
        Rating rating = new Rating("4");
        Set<Tag> tags = new HashSet<Tag>(Arrays.asList(new Tag("fun")));
        IsFilterablePredicate predicate = new IsFilterablePredicate(categoryCodes, rating, tags);
        FilterCommand command = new FilterCommand(categoryCodes, rating, tags);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        assertEquals(Arrays.asList(BATTLEBOX), model.getFilteredContactList());
    }

}
