package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;


class ClearAttendanceCommandTest {
    private Model model = new ModelManager(new AddressBook(getTypicalAddressBook()), new UserPrefs());

    @Test
    public void execute_someMembersPresent_success() {
        Person person = new PersonBuilder().build();
        person.setPresent();
        model.addPerson(person);

        ClearAttendanceCommand command = new ClearAttendanceCommand();
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.resetTodayAttendance();

        assertCommandSuccess(command, model, ClearAttendanceCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_allMembersAbsent_success() {
        Model model = new ModelManager(new AddressBook(getTypicalAddressBook()), new UserPrefs());

        ClearAttendanceCommand command = new ClearAttendanceCommand();
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.resetTodayAttendance();

        assertCommandSuccess(command, model, ClearAttendanceCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
