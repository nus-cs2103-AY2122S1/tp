package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEMBER;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_RESERVATION;
import static seedu.address.testutil.TypicalMembers.getTypicalEzFoodie;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.model.Account;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.member.Member;
import seedu.address.model.reservation.Reservation;
import seedu.address.testutil.MemberBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteReservationCommandTest {

    private Model model = new ModelManager(new Account(), getTypicalEzFoodie(), new UserPrefs());

    @Test
    public void execute_validMemberIdReservationIdUnfilteredList_success() {
        Member memberToEdit = model.getUpdatedMemberList().get(INDEX_FIRST_MEMBER.getZeroBased());
        Reservation reservationToDelete = memberToEdit.getReservations().get(INDEX_FIRST_RESERVATION.getZeroBased());
        DeleteReservationCommand deleteCommand =
                new DeleteReservationCommand(memberToEdit.getId(), reservationToDelete.getId());

        ModelManager expectedModel = new ModelManager(new Account(), model.getEzFoodie(), new UserPrefs());
        List<Reservation> expectedReservations = memberToEdit.getReservations().stream()
                .filter(reservation -> !reservation.equals(reservationToDelete)).collect(Collectors.toList());

        Member expectedMember = new MemberBuilder(memberToEdit)
                .withReservations(expectedReservations.toArray(Reservation[]::new)).build();
        expectedModel.setMember(memberToEdit, expectedMember);
        String expectedMessage = String.format(DeleteReservationCommand.MESSAGE_SUCCESS, "Id: " + expectedMember.getId()
                + "; Name: " + expectedMember.getName()
                + "; Reservation: " + "[" + reservationToDelete + "]");

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }
}
