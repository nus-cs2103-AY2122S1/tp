package seedu.modulink.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.modulink.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.modulink.testutil.TypicalPersons.ALICE;
import static seedu.modulink.testutil.TypicalPersons.BENSON;
import static seedu.modulink.testutil.TypicalPersons.CARL;
import static seedu.modulink.testutil.TypicalPersons.DANIEL;
import static seedu.modulink.testutil.TypicalPersons.ELLE;
import static seedu.modulink.testutil.TypicalPersons.FIONA;
import static seedu.modulink.testutil.TypicalPersons.GEORGE;
import static seedu.modulink.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.modulink.commons.core.Messages;
import seedu.modulink.model.Model;
import seedu.modulink.model.ModelManager;
import seedu.modulink.model.UserPrefs;
import seedu.modulink.model.person.IsFavouritePredicate;


/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListFavCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_showFavouriteSuccess() {
        String expectedMessage = ListFavCommand.MESSAGE_SUCCESS;
        IsFavouritePredicate predicate = new IsFavouritePredicate();
        ListFavCommand command = new ListFavCommand();
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL), model.getFilteredPersonList());
    }

    @Test
    public void execute_noFavourite_success() {
        String expectedMessage = Messages.MESSAGE_NO_FAVOURITES;
        ListFavCommand command = new ListFavCommand();

        model.deletePerson(ALICE);
        model.deletePerson(BENSON);
        model.deletePerson(CARL);

        expectedModel.deletePerson(ALICE);
        expectedModel.deletePerson(BENSON);
        expectedModel.deletePerson(CARL);

        IsFavouritePredicate predicate = new IsFavouritePredicate();

        expectedModel.updateFilteredPersonList(predicate);

        assertEquals(Arrays.asList(DANIEL, ELLE, FIONA, GEORGE), model.getFilteredPersonList());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
}
