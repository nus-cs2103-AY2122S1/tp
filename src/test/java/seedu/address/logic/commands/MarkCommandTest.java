package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.RemoveMarkCommand.listToString;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Period;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.PersonContainsFieldsPredicate;

public class MarkCommandTest {
    private static final Index DEFAULT_FIRST = Index.fromOneBased(1);
    private static final Person DEFAULT_TEST_PERSON = ALICE;
    private static final Name DEFAULT_TEST_NAME = ALICE.getName();
    private static final Period DEFAULT_TEST_PERIOD = ALICE.getAbsentDates().stream().findFirst().get();
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_command_success() {
        PersonContainsFieldsPredicate predicate = new PersonContainsFieldsPredicate();
        predicate.addFieldToTest(DEFAULT_TEST_NAME);
        ArrayList<String> ls = new ArrayList<>();
        //placeholder string
        ls.add(DEFAULT_TEST_NAME.toString());

        model.setPerson(DEFAULT_TEST_PERSON, DEFAULT_TEST_PERSON.unMark(DEFAULT_TEST_PERIOD));
        String expectedResult = String.format(MarkCommand.DEFAULT_EXECUTION, 1,
                DEFAULT_TEST_PERIOD, listToString(ls));
        MarkCommand defaultCommand = new MarkCommand(predicate, DEFAULT_TEST_PERIOD);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setPerson(DEFAULT_TEST_PERSON, DEFAULT_TEST_PERSON.mark(DEFAULT_TEST_PERIOD));
        assertCommandSuccess(defaultCommand, model, expectedResult, expectedModel);

    }

    @Test
    public void execute_command_failure() {
        PersonContainsFieldsPredicate predicate = new PersonContainsFieldsPredicate();
        MarkCommand defaultCommand = new MarkCommand(predicate, DEFAULT_TEST_PERIOD);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setPerson(DEFAULT_TEST_PERSON, DEFAULT_TEST_PERSON.mark(DEFAULT_TEST_PERIOD));
        assertCommandFailure(defaultCommand, model, String.format(MarkCommand.NOTHING_CHANGED,
                DEFAULT_TEST_PERIOD, ALICE.getName()));
    }

}
