package seedu.plannermd.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.plannermd.testutil.Assert.assertThrows;
import static seedu.plannermd.testutil.appointment.TypicalAppointments.FIVE_MIN_APPOINTMENT;
import static seedu.plannermd.testutil.appointment.TypicalAppointments.TWO_HOUR_APPOINTMENT;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.plannermd.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.plannermd.model.appointment.exceptions.DuplicateAppointmentException;
import seedu.plannermd.testutil.appointment.AppointmentBuilder;

class UniqueAppointmentListTest {

    private final UniqueAppointmentList uniqueAppointmentList = new UniqueAppointmentList();

    @Test
    public void contains_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.contains(null));
    }

    @Test
    public void contains_appointmentNotInList_returnsFalse() {
        assertFalse(uniqueAppointmentList.contains(TWO_HOUR_APPOINTMENT));
    }

    @Test
    public void contains_appointmentInList_returnsTrue() {
        uniqueAppointmentList.add(TWO_HOUR_APPOINTMENT);
        assertTrue(uniqueAppointmentList.contains(TWO_HOUR_APPOINTMENT));
    }

    @Test
    public void contains_appointmentWithSameIdentityFieldsInList_returnsTrue() {
        uniqueAppointmentList.add(TWO_HOUR_APPOINTMENT);
        Appointment editedTwoHourAppointment = new AppointmentBuilder(TWO_HOUR_APPOINTMENT)
                .withRemark(FIVE_MIN_APPOINTMENT.getRemark().value).build();
        assertTrue(uniqueAppointmentList.contains(editedTwoHourAppointment));
    }

    @Test
    public void isClash_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.isClash(null));
    }

    @Test
    public void isClash_noClashAppointment_returnsFalse() {
        uniqueAppointmentList.add(TWO_HOUR_APPOINTMENT);
        assertFalse(uniqueAppointmentList.isClash(FIVE_MIN_APPOINTMENT));
    }

    @Test
    public void isClash_clashAppointment_returnsTrue() {
        uniqueAppointmentList.add(TWO_HOUR_APPOINTMENT);
        Appointment clashAppointment = new AppointmentBuilder(FIVE_MIN_APPOINTMENT)
                .withPatient(TWO_HOUR_APPOINTMENT.getPatient())
                .withDate(TWO_HOUR_APPOINTMENT.getAppointmentDate().toInputStringFormat())
                .withSession(TWO_HOUR_APPOINTMENT.getSession().toInputStringFormat(),
                        TWO_HOUR_APPOINTMENT.getSession().getMinutes()).build();
        assertTrue(uniqueAppointmentList.isClash(clashAppointment));

        clashAppointment = new AppointmentBuilder(FIVE_MIN_APPOINTMENT)
                .withDoctor(TWO_HOUR_APPOINTMENT.getDoctor())
                .withDate(TWO_HOUR_APPOINTMENT.getAppointmentDate().toInputStringFormat())
                .withSession(TWO_HOUR_APPOINTMENT.getSession().toInputStringFormat(),
                        TWO_HOUR_APPOINTMENT.getSession().getMinutes()).build();

        assertTrue(uniqueAppointmentList.isClash(clashAppointment));
    }


    @Test
    public void add_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.add(null));
    }

    @Test
    public void add_duplicateAppointment_throwsDuplicatePersonException() {
        uniqueAppointmentList.add(TWO_HOUR_APPOINTMENT);
        assertThrows(DuplicateAppointmentException.class, () -> uniqueAppointmentList.add(TWO_HOUR_APPOINTMENT));
    }

    @Test
    public void setAppointment_nullTargetAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.setAppointment(null,
                TWO_HOUR_APPOINTMENT));
    }

    @Test
    public void setAppointment_nullEditedAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.setAppointment(TWO_HOUR_APPOINTMENT,
                null));
    }

    @Test
    public void setAppointment_targetAppointmentNotInList_throwsAppointmentNotFoundException() {
        assertThrows(AppointmentNotFoundException.class, () -> uniqueAppointmentList.setAppointment(
                TWO_HOUR_APPOINTMENT, TWO_HOUR_APPOINTMENT));
    }

    @Test
    public void setAppointment_editedAppointmentIsSameAppointment_success() {
        uniqueAppointmentList.add(TWO_HOUR_APPOINTMENT);
        uniqueAppointmentList.setAppointment(TWO_HOUR_APPOINTMENT, TWO_HOUR_APPOINTMENT);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(TWO_HOUR_APPOINTMENT);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointment_editedAppointmentHasSameIdentity_success() {
        uniqueAppointmentList.add(TWO_HOUR_APPOINTMENT);
        Appointment editedTwoHourAppointment = new AppointmentBuilder(TWO_HOUR_APPOINTMENT)
                .withRemark(FIVE_MIN_APPOINTMENT.getRemark().value).build();
        uniqueAppointmentList.setAppointment(TWO_HOUR_APPOINTMENT, editedTwoHourAppointment);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(editedTwoHourAppointment);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointment_editedAppointmentHasDifferentIdentity_success() {
        uniqueAppointmentList.add(TWO_HOUR_APPOINTMENT);
        uniqueAppointmentList.setAppointment(TWO_HOUR_APPOINTMENT, FIVE_MIN_APPOINTMENT);
        UniqueAppointmentList expectedUniquePersonList = new UniqueAppointmentList();
        expectedUniquePersonList.add(FIVE_MIN_APPOINTMENT);
        assertEquals(expectedUniquePersonList, uniqueAppointmentList);
    }

    @Test
    public void setAppointment_editedAppointmentHasNonUniqueIdentity_throwsDuplicateAppointmentException() {
        uniqueAppointmentList.add(TWO_HOUR_APPOINTMENT);
        uniqueAppointmentList.add(FIVE_MIN_APPOINTMENT);
        assertThrows(DuplicateAppointmentException.class, () -> uniqueAppointmentList
                .setAppointment(TWO_HOUR_APPOINTMENT, FIVE_MIN_APPOINTMENT));
    }

    @Test
    public void remove_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.remove(null));
    }

    @Test
    public void remove_appointmentDoesNotExist_throwsAppointmentNotFoundException() {
        assertThrows(AppointmentNotFoundException.class, () -> uniqueAppointmentList.remove(TWO_HOUR_APPOINTMENT));
    }

    @Test
    public void remove_existingAppointment_removesAppointment() {
        uniqueAppointmentList.add(TWO_HOUR_APPOINTMENT);
        uniqueAppointmentList.remove(TWO_HOUR_APPOINTMENT);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointments_nullUniqueAppointmentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList
                .setAppointments((UniqueAppointmentList) null));
    }

    @Test
    public void setAppointments_uniqueAppointmentList_replacesOwnListWithProvidedUniqueAppointmentList() {
        uniqueAppointmentList.add(TWO_HOUR_APPOINTMENT);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(FIVE_MIN_APPOINTMENT);
        uniqueAppointmentList.setAppointments(expectedUniqueAppointmentList);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointments_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList
                .setAppointments((List<Appointment>) null));
    }

    @Test
    public void setAppointments_list_replacesOwnListWithProvidedList() {
        uniqueAppointmentList.add(TWO_HOUR_APPOINTMENT);
        List<Appointment> appointmentList = Collections.singletonList(FIVE_MIN_APPOINTMENT);
        uniqueAppointmentList.setAppointments(appointmentList);
        UniqueAppointmentList expectedUniquePersonList = new UniqueAppointmentList();
        expectedUniquePersonList.add(FIVE_MIN_APPOINTMENT);
        assertEquals(expectedUniquePersonList, uniqueAppointmentList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Appointment> listWithDuplicateAppointments = Arrays.asList(TWO_HOUR_APPOINTMENT, TWO_HOUR_APPOINTMENT);
        assertThrows(DuplicateAppointmentException.class, () -> uniqueAppointmentList
                .setAppointments(listWithDuplicateAppointments));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> uniqueAppointmentList.asUnmodifiableObservableList()
                .remove(0));
    }

}
