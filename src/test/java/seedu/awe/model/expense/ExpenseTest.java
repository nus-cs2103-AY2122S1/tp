package seedu.awe.model.expense;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.awe.logic.commands.CommandTestUtil.VALID_COST_BUFFET;
import static seedu.awe.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BUFFET;
import static seedu.awe.testutil.Assert.assertThrows;
import static seedu.awe.testutil.TypicalExpenses.HOLIDAY;
import static seedu.awe.testutil.TypicalExpenses.PIZZA;
import static seedu.awe.testutil.TypicalPersons.ALICE;
import static seedu.awe.testutil.TypicalPersons.BOB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.awe.model.person.Person;
import seedu.awe.testutil.ExpenseBuilder;

public class ExpenseTest {


    @Test
    public void constructor_null_throwsNullPointerException() {

        // all null
        assertThrows(NullPointerException.class, () -> new Expense(null, null, null,
                null));
        assertThrows(NullPointerException.class, () -> new Expense(null, null, null,
                null, null));

        // person null
        assertThrows(NullPointerException.class, () -> new Expense(null, HOLIDAY.getCost(),
                HOLIDAY.getDescription(), new ArrayList<>(), new HashMap<>()));

        // cost null
        assertThrows(NullPointerException.class, () -> new Expense(ALICE, null,
                HOLIDAY.getDescription(), new ArrayList<>(), new HashMap<>()));

        // description null
        assertThrows(NullPointerException.class, () -> new Expense(ALICE, HOLIDAY.getCost(),
                null, new ArrayList<>(), new HashMap<>()));

        // included null
        assertThrows(NullPointerException.class, () -> new Expense(ALICE, HOLIDAY.getCost(),
                HOLIDAY.getDescription(), null, new HashMap<>()));

        // individualExpenses null
        assertThrows(NullPointerException.class, () -> new Expense(ALICE, HOLIDAY.getCost(),
                HOLIDAY.getDescription(), new ArrayList<>(), null));
    }

    @Test
    public void updatePerson_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> HOLIDAY.updatePerson(null, BOB));
        assertThrows(NullPointerException.class, () -> HOLIDAY.updatePerson(ALICE, null));
        assertThrows(NullPointerException.class, () -> HOLIDAY.updatePerson(null, null));
    }

    @Test
    public void updatePerson_validArgs_personUpdated() {
        Expense holidayCopy = new ExpenseBuilder(HOLIDAY).withPayer(BOB).build();

        // no change
        assertEquals(Optional.ofNullable(HOLIDAY), HOLIDAY.updatePerson(ALICE, ALICE));

        // target not in expense
        assertEquals(Optional.empty(), HOLIDAY.updatePerson(BOB, ALICE));

        // successful
        assertEquals(Optional.ofNullable(holidayCopy), HOLIDAY.updatePerson(ALICE, BOB));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Expense holidayCopy = new ExpenseBuilder(HOLIDAY).build();
        assertTrue(HOLIDAY.equals(holidayCopy));

        // same object -> returns true
        assertTrue(HOLIDAY.equals(HOLIDAY));

        // null -> returns false
        assertFalse(HOLIDAY.equals(null));

        // different type -> returns false
        assertFalse(HOLIDAY.equals(5));

        // different person -> returns false
        assertFalse(HOLIDAY.equals(PIZZA));

        // different cost -> returns false
        Expense editedPizza = new ExpenseBuilder(PIZZA).withCost(VALID_COST_BUFFET).build();
        assertFalse(PIZZA.equals(editedPizza));

        // different description -> returns false
        editedPizza = new ExpenseBuilder(PIZZA).withDescription(VALID_DESCRIPTION_BUFFET).build();
        assertFalse(PIZZA.equals(editedPizza));

        // different payer -> returns false
        editedPizza = new ExpenseBuilder(PIZZA).withPayer(ALICE).build();
        assertFalse(PIZZA.equals(editedPizza));


        // different individual expenses -> returns false
        Map<Person, Cost> individualExpense = new HashMap<>();
        individualExpense.put(ALICE, new Cost("2"));
        editedPizza = new ExpenseBuilder(PIZZA).withIndividualExpenses(individualExpense).build();
        assertFalse(PIZZA.equals(editedPizza));
    }

    @Test
    public void hashcode() {
        assertEquals(HOLIDAY.hashCode(), HOLIDAY.hashCode());
        assertEquals(HOLIDAY.hashCode(), new Expense(HOLIDAY.getPayer(), HOLIDAY.getCost(),
                HOLIDAY.getDescription(), HOLIDAY.getIncluded(), HOLIDAY.getIndividualExpenses()).hashCode());
    }

    @Test
    public void toStringTest() {
        String expected = HOLIDAY.getPayer()
                + " paid "
                + HOLIDAY.getCost()
                + " for "
                + HOLIDAY.getDescription()
                + ".";
        assertEquals(HOLIDAY.toString(), expected);
    }
}
