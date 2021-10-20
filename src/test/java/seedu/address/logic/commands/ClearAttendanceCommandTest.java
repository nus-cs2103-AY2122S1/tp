package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;


class ClearAttendanceCommandTest {
    private Model model = new ModelManager();

    @Test
    public void execute_allMembersPresent_success() {
        Person firstPerson = ALICE;
        Person secondPerson = BENSON;
        firstPerson.setPresent();
        secondPerson.setPresent();
        model.addPerson(firstPerson);
        model.addPerson(secondPerson);

        ClearAttendanceCommand command = new ClearAttendanceCommand();
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.resetTodayAttendance();

        assertCommandSuccess(command, model, ClearAttendanceCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_allMembersAbsent_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        ClearAttendanceCommand command = new ClearAttendanceCommand();
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.resetTodayAttendance();

        assertCommandSuccess(command, model, ClearAttendanceCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
