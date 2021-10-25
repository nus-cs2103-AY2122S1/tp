package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.facility.Facility;
import seedu.address.model.person.PersonAvailableOnDayPredicate;
import seedu.address.testutil.FacilityBuilder;

public class SplitCommandTest {
    private Model model = new ModelManager(new AddressBook(getTypicalAddressBook()), new UserPrefs());
    private Model expectedModel = new ModelManager(new AddressBook(getTypicalAddressBook()), new UserPrefs());

    @Test
    public void execute_validDay_success() {
        SplitCommand command = new SplitCommand(1);
        String expectedMessage = String.format(SplitCommand.MESSAGE_SUCCESS,
                DayOfWeek.of(1).getDisplayName(TextStyle.FULL, Locale.getDefault()));
        // Amy is the only member available on Monday
        model.setPerson(ALICE, AMY);
        expectedModel.setPerson(ALICE, AMY);
        PersonAvailableOnDayPredicate predicate = new PersonAvailableOnDayPredicate(1);
        expectedModel.split(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noMembersAvailable_throwsCommandException() {
        SplitCommand command = new SplitCommand(1);
        assertCommandFailure(command, model, String.format(
                SplitCommand.MESSAGE_NO_MEMBERS_AVAILABLE,
                DayOfWeek.of(1).getDisplayName(TextStyle.FULL, Locale.getDefault())));
    }

    @Test
    public void execute_emptyMemberList_throwsCommandException() {
        SplitCommand command = new SplitCommand(1);
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        assertCommandFailure(command, model, String.format(
                SplitCommand.MESSAGE_NO_MEMBERS_AVAILABLE,
                DayOfWeek.of(1).getDisplayName(TextStyle.FULL, Locale.getDefault())));
    }

    @Test
    public void execute_insufficientFacilities_throwsCommandException() {
        SplitCommand command = new SplitCommand(1);
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        Facility f = new FacilityBuilder().withCapacity("1").build();
        model.addFacility(f);
        model.addPerson(AMY);
        model.addPerson(BOB);
        String expectedMessage = String.format(SplitCommand.MESSAGE_INSUFFICIENT_FACILITIES,
                DayOfWeek.of(1).getDisplayName(TextStyle.FULL, Locale.getDefault()), 1);
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_emptyFacilityList_throwsCommandException() {
        SplitCommand command = new SplitCommand(1);
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        model.addPerson(AMY);
        model.addPerson(BOB);
        String expectedMessage = String.format(SplitCommand.MESSAGE_INSUFFICIENT_FACILITIES,
                DayOfWeek.of(1).getDisplayName(TextStyle.FULL, Locale.getDefault()), 2);
        assertCommandFailure(command, model, expectedMessage);
    }



    @Test
    public void equals() {
        SplitCommand firstCommand = new SplitCommand(1);
        SplitCommand secondCommand = new SplitCommand(2);

        assertTrue(firstCommand.equals(firstCommand));

        SplitCommand firstCommandCopy = new SplitCommand(1);
        assertTrue(firstCommand.equals(firstCommandCopy));

        assertFalse(firstCommand.equals(secondCommand));
        assertFalse(firstCommand.equals(null));
        assertFalse(firstCommand.equals(1));
    }
}
