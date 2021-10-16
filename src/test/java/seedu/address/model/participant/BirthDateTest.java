package seedu.address.model.participant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.Period;

import org.junit.jupiter.api.Test;

public class BirthDateTest {

    @Test
    public void getAgeTest() {
        LocalDate date = LocalDate.of(2000, 8, 4);
        BirthDate birthDate = BirthDate.of(2000, 8, 4);
        assertEquals(Period.between(date, LocalDate.now()).getYears(), birthDate.getAge());
    }

    @Test
    public void notSpecifiedBirthDateTest() {
        BirthDate birthDate = BirthDate.notSpecified();
        assertEquals("N/A", birthDate.toString());
    }

    @Test
    public void ofFactoryMethod_integerParam_stringRepresentationTest() {
        BirthDate birthDate = BirthDate.of(2020, 8, 4);
        assertEquals("2020-08-04", birthDate.toString());
    }

    @Test
    public void ofFactoryMethod_localDateParam_stringRepresentationTest() {
        BirthDate birthDate = BirthDate.of(LocalDate.parse("2021-09-18"));
        assertEquals("2021-09-18", birthDate.toString());
    }

    @Test
    public void isPresentOrPastTest() {
        assertFalse(BirthDate.isPresentOrPast(LocalDate.MAX));

        assertFalse(BirthDate.isPresentOrPast(LocalDate.now().plusYears(20)));

        assertTrue(BirthDate.isPresentOrPast(LocalDate.now()));

        // for other use cases not strictly to participants
        // BirthDate is strictly what can be born now and in the past and not in the future only
        assertTrue(BirthDate.isPresentOrPast(LocalDate.now().minusYears(200)));

        assertTrue(BirthDate.isPresentOrPast(LocalDate.now().minusYears(50)));
    }

    @Test
    public void testEquals() {
        BirthDate sampleBirthDate = BirthDate.of(2020, 11, 11);
        BirthDate sampleBirthDateCopy = BirthDate.of(2020, 11, 11);
        BirthDate sampleBirthDateLocalDate = BirthDate.of(LocalDate.of(2020, 11, 11));
        BirthDate differentSampleBirthDate = BirthDate.of(2020, 11, 12);
        BirthDate nullSampleBirthDate = BirthDate.of(null);

        // same object -> return true
        assertEquals(sampleBirthDate, sampleBirthDate);

        // different object, same value -> return true
        assertEquals(sampleBirthDate, sampleBirthDateCopy);

        // same object -> return true
        assertEquals(sampleBirthDate, sampleBirthDate);

        // null object -> return false
        assertNotEquals(sampleBirthDate, null);

        // different type -> return false
        assertNotEquals(sampleBirthDate, 5);

        // same value, different constructors -> return true
        assertEquals(sampleBirthDate, sampleBirthDateLocalDate);

        // different values -> return false
        assertNotEquals(sampleBirthDate, differentSampleBirthDate);

        // one null value -> return false
        assertNotEquals(sampleBirthDate, nullSampleBirthDate);
        assertNotEquals(nullSampleBirthDate, sampleBirthDate);

        // both null values -> return true
        assertEquals(nullSampleBirthDate, nullSampleBirthDate);
    }
}
