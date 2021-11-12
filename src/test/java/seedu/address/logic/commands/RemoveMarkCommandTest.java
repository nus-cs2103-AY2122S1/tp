package seedu.address.logic.commands;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Period;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.PersonContainsFieldsPredicate;

public class RemoveMarkCommandTest {

    private static final Index DEFAULT_FIRST = Index.fromOneBased(1);
    private static final Person DEFAULT_TEST_PERSON = ALICE;
    private static final Name DEFAULT_TEST_NAME = ALICE.getName();
    private static final Period DEFAULT_TEST_PERIOD = ALICE.getAbsentDates().stream().findFirst().get();
    private static final PersonContainsFieldsPredicate DEFAULT_PREDICATE =
            new PersonContainsFieldsPredicate();
    private static final PersonContainsFieldsPredicate PERSON_LOOKUP_BY_NAME =
            new PersonContainsFieldsPredicate(ALICE.getName());
    private static final Period INCORRECT_TEST_PERIOD =
            new Period(LocalDate.MIN);
    private static final RemoveMarkCommand DEFAULT =
            new RemoveMarkCommand(DEFAULT_PREDICATE, DEFAULT_FIRST,
                    DEFAULT_TEST_PERIOD);
    private static final RemoveMarkCommand WRONG_PERIOD =
            new RemoveMarkCommand(DEFAULT_PREDICATE, DEFAULT_FIRST,
                    INCORRECT_TEST_PERIOD);
    private static final RemoveMarkCommand REMOVE_MARK_BY_NAME =
            new RemoveMarkCommand(PERSON_LOOKUP_BY_NAME, DEFAULT_TEST_PERIOD);
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());


    @Test
    public void execute_command_success() {

        Person toRemoveMark = DEFAULT_TEST_PERSON;
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        //set the expected person in the output
        expectedModel.setPerson(toRemoveMark, toRemoveMark.unMark(DEFAULT_TEST_PERIOD));
        assertCommandSuccess(DEFAULT, model, String.format(RemoveMarkCommand.STAFF_UNMARKED,
                toRemoveMark.getName(), DEFAULT_TEST_PERIOD), expectedModel);

        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(REMOVE_MARK_BY_NAME, model,
                String.format(RemoveMarkCommand.STAFF_UNMARKED, toRemoveMark.getName(), DEFAULT_TEST_PERIOD),
                expectedModel);
    }

    @Test
    public void execute_command_failure() {
        //testing for incorrect period

        assertCommandFailure(WRONG_PERIOD, model, String.format(RemoveMarkCommand.STAFF_NOT_MARKED,
                RemoveMarkCommand.listToString(List.of(ALICE.getName().toString())), INCORRECT_TEST_PERIOD));

        //test for when the target does not exist
        model.deletePerson(DEFAULT_TEST_PERSON);

        assertCommandFailure(REMOVE_MARK_BY_NAME, model, RemoveMarkCommand.NO_STAFF_SATISFIES_QUERY);


    }

    @Test
    public void equals() {
        assertTrue(DEFAULT.equals(DEFAULT));
        assertTrue(REMOVE_MARK_BY_NAME.equals(REMOVE_MARK_BY_NAME));
        assertFalse(DEFAULT.equals(REMOVE_MARK_BY_NAME));
        assertFalse(REMOVE_MARK_BY_NAME.equals(DEFAULT));
        assertFalse(WRONG_PERIOD.equals(REMOVE_MARK_BY_NAME));
        assertFalse(DEFAULT.equals(WRONG_PERIOD));

    }

}
