package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.model.person.PersonTestUtil.createPeriod;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Field;
import seedu.address.model.person.predicates.PersonContainsFieldsPredicate;
import seedu.address.stubs.model.FieldStub;

public class StaffIndividualStatisticsCommandTest {

    private static final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private static StaffIndividualStatisticsCommand createCommand(int start, int end, Field... args) {
        return new StaffIndividualStatisticsCommand(new PersonContainsFieldsPredicate(args),
                createPeriod(start, end));
    }

    private static StaffIndividualStatisticsCommand createCommand(int start, int end, int index, Field... args) {
        return new StaffIndividualStatisticsCommand(new PersonContainsFieldsPredicate(args), Index.fromOneBased(index),
                createPeriod(start, end));
    }


    @Test
    public void equals() {
        StaffIndividualStatisticsCommand command1 = createCommand(1, 2, new FieldStub(0),
                new FieldStub(1), new FieldStub(2));
        StaffIndividualStatisticsCommand command2 = createCommand(1, 2, 3);
        assertFalse(command1.equals(command2));
        assertTrue(command1.equals(command1));
        assertTrue(command2.equals(command2));
    }

    @Test
    public void execute_noStaffSatisfies() {
        StaffIndividualStatisticsCommand testCommand = createCommand(1, 10, new FieldStub(2));
        assertCommandFailure(testCommand, model, StaffIndividualStatisticsCommand.NO_STAFF_SATISFIES_QUERY);

        testCommand = createCommand(1, 10, 2, new FieldStub(3));
        assertCommandFailure(testCommand, model, StaffIndividualStatisticsCommand.NO_STAFF_SATISFIES_QUERY);
    }

}
