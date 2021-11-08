package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEMBER;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_RESERVATION;
import static seedu.address.testutil.TypicalMembers.getTypicalEzFoodie;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditReservationCommand.EditReservationDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Account;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.member.Member;
import seedu.address.model.reservation.Reservation;
import seedu.address.testutil.EditReservationDescriptorBuilder;
import seedu.address.testutil.MemberBuilder;
import seedu.address.testutil.ReservationBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditReservationCommandTest {

    private Model model = new ModelManager(new Account(), getTypicalEzFoodie(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() throws ParseException {
        Member memberToEdit = model.getUpdatedMemberList().get(INDEX_FIRST_MEMBER.getZeroBased());
        Reservation reservationToEdit = memberToEdit.getReservations().get(INDEX_FIRST_RESERVATION.getZeroBased());
        Reservation editedReservation = new ReservationBuilder().withId(reservationToEdit.getId().value)
                .withRemark(reservationToEdit.getRemark().value).build();

        EditReservationDescriptor descriptor = new EditReservationDescriptorBuilder(editedReservation).build();
        EditReservationCommand editCommand =
                new EditReservationCommand(memberToEdit.getId(), reservationToEdit.getId(), descriptor);

        ModelManager expectedModel = new ModelManager(new Account(), model.getEzFoodie(), new UserPrefs());
        List<Reservation> expectedReservations = new ArrayList<>(memberToEdit.getReservations());
        expectedReservations.stream().filter(reservation -> reservation.isSameId(reservationToEdit)).findAny()
                .ifPresent(reservation -> expectedReservations
                        .set(expectedReservations.indexOf(reservation), editedReservation));
        Member expectedMember = new MemberBuilder(memberToEdit)
                .withReservations(expectedReservations.toArray(Reservation[]::new)).build();
        expectedModel.setMember(memberToEdit, expectedMember);

        Reservation updatedReservation = expectedMember.getReservations().stream()
                .filter(reservation -> reservation.isSameId(reservationToEdit)).findAny().orElse(null);
        String expectedMessage = String.format(EditReservationCommand.MESSAGE_SUCCESS, "Id: " + expectedMember.getId()
                + "; Name: " + expectedMember.getName()
                + "; Reservation: " + "[" + updatedReservation + "]");

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }
}
