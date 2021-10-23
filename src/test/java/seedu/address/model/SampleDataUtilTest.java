package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.model.util.SampleDataUtil;

public class SampleDataUtilTest {
    @Test
    public void checkSampleData() {
        AddressBook newAddressBook = new AddressBook();
        Person[] persons = SampleDataUtil.getSamplePersons();
        for (int i = 0; i < persons.length; i++) {
            newAddressBook.addPerson(persons[i]);
        }

        List<Person> listOfPersons = SampleDataUtil.getSampleAddressBook().getPersonList();
        for (int i = 0; i < persons.length; i++) {
            assertTrue(listOfPersons.get(i).equals(persons[i]));
        }
    }

    @Test void createPersonTest() {
        Person[] persons = SampleDataUtil.getSamplePersons();
        Person samplePerson = persons[0];
        SampleDataUtil test = new SampleDataUtil();
        Person createdPerson = test.createPerson("Alex Yeoh", "87438807", "e0123456@u.nus.edu",
                "Blk 30 Geylang Street 29, #06-40", "test1", "e0123456", "student",
                "A0123456A", "01", "friends");
        assertTrue(samplePerson.equals(createdPerson));

    }
}
