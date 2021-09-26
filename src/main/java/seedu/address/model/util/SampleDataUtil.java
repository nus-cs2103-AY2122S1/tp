package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.Tuition.*;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static final Remark EMPTY_REMARK = new Remark("");
    public static final ArrayList<Person> sampleStudent = new ArrayList<>();
    public static final Student sampleStudents = new Student(sampleStudent);
    public static final TuitionClass sampleTuitionClass = new TuitionClass(new ClassName("Physics"), new ClassLimit(10), new Counter(5), new Timeslot("Monday 10am"), sampleStudents);

    public static final ArrayList<TuitionClass> sampleClasses = new ArrayList<TuitionClass>() {
        {
            add(sampleTuitionClass);
            add(sampleTuitionClass);
            add(sampleTuitionClass);
        }
    };



    public static Classes EMPTY_CLASSES;

    public static Person[] getSamplePersons() {
//        sampleClasses.add(sampleTuitionClass);

        EMPTY_CLASSES = new Classes(sampleClasses);
        System.out.println(EMPTY_CLASSES.toString());
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),EMPTY_REMARK,
                getTagSet("friends"), EMPTY_CLASSES),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),EMPTY_REMARK,
                getTagSet("colleagues", "friends"), EMPTY_CLASSES),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),EMPTY_REMARK,
                getTagSet("neighbours"), EMPTY_CLASSES),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),EMPTY_REMARK,
                getTagSet("family"),EMPTY_CLASSES),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),EMPTY_REMARK,
                getTagSet("classmates"), EMPTY_CLASSES),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),EMPTY_REMARK,
                getTagSet("colleagues"), EMPTY_CLASSES)
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);

        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
