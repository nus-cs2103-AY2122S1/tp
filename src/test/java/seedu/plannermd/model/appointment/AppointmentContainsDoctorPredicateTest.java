package seedu.plannermd.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class AppointmentContainsDoctorPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        AppointmentContainsDoctorPredicate firstPredicate =
                new AppointmentContainsDoctorPredicate(firstPredicateKeywordList);
        AppointmentContainsDoctorPredicate secondPredicate =
                new AppointmentContainsDoctorPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        AppointmentContainsDoctorPredicate firstPredicateCopy =
                new AppointmentContainsDoctorPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicateCopy, firstPredicate);

        // different types -> returns false
        assertNotEquals(firstPredicate, 1);
        assertNotEquals(new AppointmentContainsPatientPredicate(firstPredicateKeywordList), firstPredicate);

        // null -> returns false
        assertNotEquals(firstPredicate, null);

        // different person -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_nullKeywords_throwsException() {
        assertThrows(NullPointerException.class, () -> new AppointmentContainsDoctorPredicate(null));
    }
}
