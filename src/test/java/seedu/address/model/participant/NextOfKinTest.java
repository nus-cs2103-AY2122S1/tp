package seedu.address.model.participant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;

public class NextOfKinTest {

    @Test
    public void testEquals() {
        NextOfKin sampleNextOfKin = new NextOfKin(new Name("Elira Pendora"), new Phone("96363672"),
                new Tag("Mother"));
        NextOfKin sampleNextOfKinCopy = new NextOfKin(new Name("Elira Pendora"), new Phone("96363672"),
                new Tag("Mother"));
        NextOfKin differentNameSampleNextOfKin = new NextOfKin(new Name("Elira Pendoro"), new Phone("96363672"),
                new Tag("Mother"));
        NextOfKin differentPhoneSampleNextOfKin = new NextOfKin(new Name("Elira Pendora"), new Phone("96363673"),
                new Tag("Mother"));
        NextOfKin differentTagSampleNextOfKin = new NextOfKin(new Name("Elira Pendora"), new Phone("96363672"),
                new Tag("Father"));

        // same object -> return true
        assertEquals(sampleNextOfKin, sampleNextOfKin);

        // different object, same value -> return true
        assertEquals(sampleNextOfKin, sampleNextOfKinCopy);

        // null object -> return false
        assertNotEquals(sampleNextOfKin, null);

        // different type -> return false
        assertNotEquals(sampleNextOfKin, 5);

        // different name -> return false
        assertNotEquals(sampleNextOfKin, differentNameSampleNextOfKin);

        // different phone number -> return false
        assertNotEquals(sampleNextOfKin, differentPhoneSampleNextOfKin);

        // different tag -> return false
        assertNotEquals(sampleNextOfKin, differentTagSampleNextOfKin);
    }
}
