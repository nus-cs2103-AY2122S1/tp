package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.SortCommand.MESSAGE_SORT_CONTACT_SUCCESS;
import static seedu.address.testutil.TypicalContacts.AIRZONE;
import static seedu.address.testutil.TypicalContacts.BATTLEBOX;
import static seedu.address.testutil.TypicalContacts.CARLTON;
import static seedu.address.testutil.TypicalContacts.FUKTAKCHI;
import static seedu.address.testutil.TypicalContacts.GSEA;
import static seedu.address.testutil.TypicalContacts.HOTEL_SOLOHA;
import static seedu.address.testutil.TypicalContacts.ICHIBAN;
import static seedu.address.testutil.TypicalContacts.JCUBE;
import static seedu.address.testutil.TypicalContacts.MARITIME;
import static seedu.address.testutil.TypicalContacts.getRandomTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.CategoryCode;
import seedu.address.model.contact.IsFilterablePredicate;
import seedu.address.model.contact.IsFindableContainsKeywordsPredicate;
import seedu.address.model.contact.Rating;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code SortCommandRating}.
 */
public class SortCommandRatingTest {

    private Model model = new ModelManager(getRandomTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getRandomTypicalAddressBook(), new UserPrefs());

    // sort empty list
    @Test
    public void execute_emptyList_sorted() {
        // create emptyModel and emptyExpectedModel of empty filteredContacts;
        AddressBook ab = new AddressBook();

        Model emptyModel = new ModelManager(ab, new UserPrefs());
        Model emptyExpectedModel = new ModelManager(ab, new UserPrefs());

        String expectedMessage = String.format(MESSAGE_SORT_CONTACT_SUCCESS, "by rating");

        SortCommandRating command = new SortCommandRating();

        emptyExpectedModel.sortList("rating");

        assertCommandSuccess(command, emptyModel, expectedMessage, emptyExpectedModel);

        assertEquals(Collections.emptyList(), emptyModel.getFilteredContactList());
    }

    // sort list with one contact
    @Test
    public void execute_listWithOneElement_sorted() {
        // create ab with one element
        AddressBook ab = new AddressBook();
        ab.addContact(MARITIME);

        Model oneElementModel = new ModelManager(ab, new UserPrefs());
        Model oneElementExpectedModel = new ModelManager(ab, new UserPrefs());

        String expectedMessage = String.format(MESSAGE_SORT_CONTACT_SUCCESS, "by rating");

        SortCommandRating command = new SortCommandRating();

        oneElementExpectedModel.sortList("rating");

        assertCommandSuccess(command, oneElementModel, expectedMessage, oneElementExpectedModel);

        assertEquals(Arrays.asList(MARITIME), oneElementModel.getFilteredContactList());
    }

    // sort original list
    @Test
    public void execute_originalList_sorted() {
        String expectedMessage = String.format(MESSAGE_SORT_CONTACT_SUCCESS, "by rating");

        SortCommandRating command = new SortCommandRating();

        expectedModel.sortList("rating");

        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        assertEquals(Arrays.asList(FUKTAKCHI, JCUBE, GSEA, HOTEL_SOLOHA, BATTLEBOX, AIRZONE, CARLTON, ICHIBAN),
            model.getFilteredContactList());
    }


    // sort list after filter command
    @Test
    public void execute_listAfterFilterCommand_sorted() {
        // filter model by c/att
        IsFilterablePredicate predicate = new IsFilterablePredicate(
                Collections.singleton(new CategoryCode("att")),
                new Rating("5"),
                Collections.emptySet());
        model.updateFilteredContactList(predicate);

        String expectedMessage = String.format(MESSAGE_SORT_CONTACT_SUCCESS, "by rating");

        SortCommandRating command = new SortCommandRating();

        // filter expectedModel by c/att
        expectedModel.updateFilteredContactList(predicate);

        expectedModel.sortList("rating");

        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        assertEquals(Arrays.asList(GSEA), model.getFilteredContactList());
    }

    // sort list after find command
    @Test
    public void execute_listAfterFindCommand_sorted() {
        // find "Meier" in model
        IsFindableContainsKeywordsPredicate predicate =
                new IsFindableContainsKeywordsPredicate(Arrays.asList("museum".split("\\s+")));

        model.updateFilteredContactList(predicate);

        String expectedMessage = String.format(MESSAGE_SORT_CONTACT_SUCCESS, "by rating");

        SortCommandRating command = new SortCommandRating();

        // find "Meier" in expectedModel
        expectedModel.updateFilteredContactList(predicate);

        expectedModel.sortList("rating");

        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        assertEquals(Arrays.asList(FUKTAKCHI, BATTLEBOX), model.getFilteredContactList());
    }


    // sort list after sort rating command
    @Test
    public void execute_listAfterSortRatingCommand_sorted() {
        // sort model by rating
        model.sortList("rating");

        String expectedMessage = String.format(MESSAGE_SORT_CONTACT_SUCCESS, "by rating");

        SortCommandRating command = new SortCommandRating();

        expectedModel.sortList("rating");
        expectedModel.sortList("rating");

        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        assertEquals(Arrays.asList(FUKTAKCHI, JCUBE, GSEA, HOTEL_SOLOHA, BATTLEBOX, AIRZONE, CARLTON, ICHIBAN),
            model.getFilteredContactList());

    }

    // sort list after sort name command
    @Test
    public void execute_listAfterSortNameCommand_sorted() {
        // sort model by name
        model.sortList("name");

        String expectedMessage = String.format(MESSAGE_SORT_CONTACT_SUCCESS, "by rating");

        SortCommandRating command = new SortCommandRating();

        expectedModel.sortList("name");
        expectedModel.sortList("rating");

        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        assertEquals(Arrays.asList(FUKTAKCHI, GSEA, HOTEL_SOLOHA, JCUBE, BATTLEBOX, AIRZONE, CARLTON, ICHIBAN),
            model.getFilteredContactList());
    }
}
