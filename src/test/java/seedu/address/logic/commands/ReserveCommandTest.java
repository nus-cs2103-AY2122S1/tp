package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.ReservationCommandTestUtil.VALID_DATE_TIME_AMY;
import static seedu.address.logic.commands.ReservationCommandTestUtil.VALID_DATE_TIME_BOB;
import static seedu.address.logic.commands.ReservationCommandTestUtil.VALID_NUMBER_OF_PEOPLE_AMY;
import static seedu.address.logic.commands.ReservationCommandTestUtil.VALID_NUMBER_OF_PEOPLE_BOB;
import static seedu.address.logic.commands.ReservationCommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.ReservationCommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.ReservationCommandTestUtil.VALID_REMARK;
import static seedu.address.testutil.TypicalCustomers.getTypicalRhrhCustomers;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Phone;
import seedu.address.model.reservation.Remark;
import seedu.address.model.reservation.Reservation;
import seedu.address.model.table.Table;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.CustomerBuilder;


class ReserveCommandTest {
    private static final int DUMMY_NUMBER_OF_PEOPLE = VALID_NUMBER_OF_PEOPLE_AMY;
    private static final Phone DUMMY_PHONE = new Phone(VALID_PHONE_AMY);
    private static final LocalDateTime DUMMY_DATE_TIME = LocalDateTime.parse(VALID_DATE_TIME_AMY);
    private static final Table DUMMY_TABLE = new Table(5, 10);
    private static final Remark DUMMY_REMARK = new Remark(VALID_REMARK);
    private static final Set<Tag> DUMMY_TAG_LIST = Set.of(new Tag(VALID_TAG_FRIEND));
    private static final int DIFFERENT_NUMBER_OF_PEOPLE = VALID_NUMBER_OF_PEOPLE_BOB;
    private static final Phone DIFFERENT_PHONE = new Phone(VALID_PHONE_BOB);
    private static final LocalDateTime DIFFERENT_DATE_TIME = LocalDateTime.parse(VALID_DATE_TIME_BOB);

    private Model model = new ModelManager(getTypicalRhrhCustomers(), new UserPrefs());

    @Test
    public void constructor_nullArgument_throwsNullPointerException() {
        // null phone
        assertThrows(NullPointerException.class, () ->
                new ReserveCommand(null, DUMMY_NUMBER_OF_PEOPLE, DUMMY_DATE_TIME, DUMMY_REMARK, DUMMY_TAG_LIST));

        // null date time
        assertThrows(NullPointerException.class, () ->
                new ReserveCommand(DUMMY_PHONE, DUMMY_NUMBER_OF_PEOPLE, null, DUMMY_REMARK, DUMMY_TAG_LIST));
    }

    @Test
    public void execute_validReservationButNoTables_throwsCommandException() throws Exception {
        ReserveCommand command = new ReserveCommand(
                DUMMY_PHONE, DUMMY_NUMBER_OF_PEOPLE, DUMMY_DATE_TIME, DUMMY_REMARK, DUMMY_TAG_LIST
        );
        assertThrows(CommandException.class, () -> command.execute(model));
    }

    @Test
    public void execute_validReservationWithTablesSetAlready_addSuccessful() throws Exception {
        // add customer first
        model.addCustomer(
                new CustomerBuilder().withPhone(DUMMY_PHONE.value).build()
        );
        ReserveCommand command = new ReserveCommand(
                DUMMY_PHONE, DUMMY_NUMBER_OF_PEOPLE, DUMMY_DATE_TIME, DUMMY_REMARK, DUMMY_TAG_LIST
        );
        model.setTableList(Collections.singletonList(DUMMY_TABLE));
        Reservation expectedReservation = new Reservation(
                DUMMY_PHONE, DUMMY_NUMBER_OF_PEOPLE, DUMMY_DATE_TIME, DUMMY_TABLE, DUMMY_REMARK, DUMMY_TAG_LIST
        );

        command.execute(model);

        assertTrue(model.hasReservation(expectedReservation));
    }

    @Test
    public void execute_duplicateReservation_throwsCommandException() {
        ReserveCommand reserveCommand = new ReserveCommand(
                DUMMY_PHONE, DUMMY_NUMBER_OF_PEOPLE, DUMMY_DATE_TIME, DUMMY_REMARK, DUMMY_TAG_LIST
        );
        model.addReservation(new Reservation(
                DUMMY_PHONE, DUMMY_NUMBER_OF_PEOPLE, DUMMY_DATE_TIME, DUMMY_TABLE, DUMMY_REMARK, DUMMY_TAG_LIST
        ));
        assertThrows(CommandException.class, () -> reserveCommand.execute(model));
    }

    @Test
    public void equals() {
        ReserveCommand reserveCommand = new ReserveCommand(
                DUMMY_PHONE, DUMMY_NUMBER_OF_PEOPLE, DUMMY_DATE_TIME, DUMMY_REMARK, DUMMY_TAG_LIST
        );
        ReserveCommand reserveCommandCopied = new ReserveCommand(
                DUMMY_PHONE, DUMMY_NUMBER_OF_PEOPLE, DUMMY_DATE_TIME, DUMMY_REMARK, DUMMY_TAG_LIST
        );

        // same object -> returns true
        assertTrue(reserveCommand.equals(reserveCommand));

        // same values -> returns true
        assertTrue(reserveCommand.equals(reserveCommandCopied));

        // different types -> returns false
        assertFalse(reserveCommand.equals(2));

        // null -> returns false
        assertFalse(reserveCommand.equals(null));

        // different phone numbers -> returns false
        assertFalse(reserveCommand.equals(new ReserveCommand(
                DIFFERENT_PHONE,
                DUMMY_NUMBER_OF_PEOPLE,
                DUMMY_DATE_TIME,
                DUMMY_REMARK,
                DUMMY_TAG_LIST
        )));

        // different number of people
        assertFalse(reserveCommand.equals(new ReserveCommand(
                DUMMY_PHONE,
                DIFFERENT_NUMBER_OF_PEOPLE,
                DUMMY_DATE_TIME,
                DUMMY_REMARK,
                DUMMY_TAG_LIST
        )));

        // different date time
        assertFalse(reserveCommand.equals(new ReserveCommand(
                DUMMY_PHONE,
                DUMMY_NUMBER_OF_PEOPLE,
                DIFFERENT_DATE_TIME,
                DUMMY_REMARK,
                DUMMY_TAG_LIST
        )));
    }
}
