package seedu.plannermd.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.plannermd.model.doctor.Doctor;
import seedu.plannermd.testutil.appointment.AppointmentBuilder;
import seedu.plannermd.testutil.doctor.DoctorBuilder;

public class AppointmentContainsDoctorPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        AppointmentContainsDoctorPredicate firstPredicate =
                new AppointmentContainsDoctorPredicate(firstPredicateKeywordList);
        AppointmentContainsDoctorPredicate secondPredicate =
                new AppointmentContainsDoctorPredicate(secondPredicateKeywordList);

        // same object -> equals
        assertEquals(firstPredicate, firstPredicate);

        // same values -> equals
        AppointmentContainsDoctorPredicate firstPredicateCopy =
                new AppointmentContainsDoctorPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicateCopy, firstPredicate);

        // different types -> not equals
        assertNotEquals(firstPredicate, 1);
        assertNotEquals(new AppointmentContainsPatientPredicate(firstPredicateKeywordList), firstPredicate);

        // null -> not equals
        assertNotEquals(firstPredicate, null);

        // different person -> not equals
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_nullKeywords_throwsException() {
        assertThrows(NullPointerException.class, () -> new AppointmentContainsDoctorPredicate(null));
    }

    @Test
    public void test_appointmentContainsDoctor_returnsTrue() {

        // One keyword
        AppointmentContainsDoctorPredicate predicate =
                new AppointmentContainsDoctorPredicate(Collections.singletonList("Alice"));
        Doctor doctor = new DoctorBuilder().withName("Alice Bob").build();
        assertTrue(predicate.test(new AppointmentBuilder().withDoctor(doctor).build()));

        // Multiple keywords
        predicate = new AppointmentContainsDoctorPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new AppointmentBuilder().withDoctor(doctor).build()));

        // Only one matching keywords
        predicate = new AppointmentContainsDoctorPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new AppointmentBuilder().withDoctor(doctor).build()));

        // Mixed-case keywords
        predicate = new AppointmentContainsDoctorPredicate(Arrays.asList("BOB", "aliCe"));
        assertTrue(predicate.test(new AppointmentBuilder().withDoctor(doctor).build()));
    }

    @Test
    public void test_appointmentDoesNotContainDoctor_returnsFalse() {

        // Zero keywords
        AppointmentContainsDoctorPredicate predicate =
                new AppointmentContainsDoctorPredicate(Collections.emptyList());
        Doctor doctor = new DoctorBuilder().withName("Alice Bob").build();
        assertFalse(predicate.test(new AppointmentBuilder().withDoctor(doctor).build()));

        // Non-matching keywords
        predicate = new AppointmentContainsDoctorPredicate(Collections.singletonList("John"));
        assertFalse(predicate.test(new AppointmentBuilder().withDoctor(doctor).build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new AppointmentContainsDoctorPredicate(Arrays.asList("12345",
                "alice@email.com", "Main", "Street"));
        doctor = new DoctorBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build();
        assertFalse(predicate.test(new AppointmentBuilder().withDoctor(doctor).build()));

    }
}
