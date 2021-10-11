package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REVENUE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.Money;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Revenue;
import seedu.address.testutil.PersonBuilder;

public class RevenueCommandTest {

    private static final String REVENUE_STUB = "100.21f";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addRevenueUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withRevenue(REVENUE_STUB).build();

        RevenueCommand revenueCommand = new RevenueCommand(INDEX_FIRST_PERSON,
                new Revenue(editedPerson.getRevenue().value));

        String expectedMessage = String.format(RevenueCommand.MESSAGE_ADD_REVENUE_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(revenueCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addRevenueFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
                .withRevenue(REVENUE_STUB).build();

        RevenueCommand revenueCommand = new RevenueCommand(INDEX_FIRST_PERSON,
                new Revenue(editedPerson.getRevenue().value));

        String expectedMessage = String.format(RevenueCommand.MESSAGE_ADD_REVENUE_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(revenueCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Money moneyOfBob = new Money(Float.valueOf(VALID_REVENUE_BOB));
        RevenueCommand revenueCommand = new RevenueCommand(outOfBoundIndex, new Revenue(moneyOfBob));

        assertCommandFailure(revenueCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        Money moneyOfBob = new Money(Float.valueOf(VALID_REVENUE_BOB));
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        RevenueCommand revenueCommand = new RevenueCommand(outOfBoundIndex, new Revenue(moneyOfBob));

        assertCommandFailure(revenueCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_resultingRevenueNegativeUnfilteredList_failure() {
        Money negativeMoneyOfBob = new Money((-1) * Float.valueOf(REVENUE_STUB));
        RevenueCommand revenueCommand = new RevenueCommand(INDEX_FIRST_PERSON, new Revenue(negativeMoneyOfBob));

        assertCommandFailure(revenueCommand, model, RevenueCommand.MESSAGE_ADD_REVENUE_FAIL);
    }
}
