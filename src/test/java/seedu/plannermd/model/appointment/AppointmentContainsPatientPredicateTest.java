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

import seedu.plannermd.model.patient.Patient;
import seedu.plannermd.testutil.appointment.AppointmentBuilder;
import seedu.plannermd.testutil.patient.PatientBuilder;

public class AppointmentContainsPatientPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        AppointmentContainsPatientPredicate firstPredicate =
                new AppointmentContainsPatientPredicate(firstPredicateKeywordList);
        AppointmentContainsPatientPredicate secondPredicate =
                new AppointmentContainsPatientPredicate(secondPredicateKeywordList);

        // same object -> equals
        assertEquals(firstPredicate, firstPredicate);

        // same values -> equals
        AppointmentContainsPatientPredicate firstPredicateCopy =
                new AppointmentContainsPatientPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicateCopy, firstPredicate);

        // different types -> not equals
        assertNotEquals(firstPredicate, 1);
        assertNotEquals(new AppointmentContainsDoctorPredicate(firstPredicateKeywordList), firstPredicate);

        // null -> not equals
        assertNotEquals(firstPredicate, null);

        // different person -> not equals
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_nullKeywords_throwsException() {
        assertThrows(NullPointerException.class, () -> new AppointmentContainsPatientPredicate(null));
    }

    @Test
    public void test_appointmentContainsPatient_returnsTrue() {

        // One keyword
        AppointmentContainsPatientPredicate predicate =
                new AppointmentContainsPatientPredicate(Collections.singletonList("Alice"));
        Patient patient = new PatientBuilder().withName("Alice Bob").build();
        assertTrue(predicate.test(new AppointmentBuilder().withPatient(patient).build()));

        // Multiple keywords
        predicate = new AppointmentContainsPatientPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new AppointmentBuilder().withPatient(patient).build()));

        // Only one matching keywords
        predicate = new AppointmentContainsPatientPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new AppointmentBuilder().withPatient(patient).build()));

        // Mixed-case keywords
        predicate = new AppointmentContainsPatientPredicate(Arrays.asList("BOB", "aliCe"));
        assertTrue(predicate.test(new AppointmentBuilder().withPatient(patient).build()));
    }

    @Test
    public void test_appointmentDoesNotContainPatient_returnsFalse() {

        // Zero keywords
        AppointmentContainsPatientPredicate predicate =
                new AppointmentContainsPatientPredicate(Collections.emptyList());
        Patient patient = new PatientBuilder().withName("Alice Bob").build();
        assertFalse(predicate.test(new AppointmentBuilder().withPatient(patient).build()));

        // Non-matching keywords
        predicate = new AppointmentContainsPatientPredicate(Collections.singletonList("John"));
        assertFalse(predicate.test(new AppointmentBuilder().withPatient(patient).build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new AppointmentContainsPatientPredicate(Arrays.asList("12345",
                "alice@email.com", "Main", "Street"));
        patient = new PatientBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build();
        assertFalse(predicate.test(new AppointmentBuilder().withPatient(patient).build()));

    }
}
