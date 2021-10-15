package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Classes;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;
import seedu.address.model.tuition.ClassLimit;
import seedu.address.model.tuition.ClassName;
import seedu.address.model.tuition.StudentList;
import seedu.address.model.tuition.Timeslot;
import seedu.address.model.tuition.TuitionClass;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static final Remark EMPTY_REMARK = new Remark("");
    public static final ArrayList<String> SAMPLE_STUDENT = new ArrayList<>();
    public static final StudentList SAMPLE_STUDENTS = new StudentList(SAMPLE_STUDENT);

    public static final ArrayList<Integer> SAMPLE_CLASSES = new ArrayList<>() {
        {
            add(1234567);
            add(2234567);
        }
    };

    public static Person[] getSamplePersons() {
        //sampleClasses.add(sampleTuitionClass);
        return new Person[] {
            new Person(new Name("a"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), EMPTY_REMARK,
                getTagSet("Physics | Mon 10:00-12:00", "Chemistry | Tue 10:00-12:00"), new Classes(SAMPLE_CLASSES)),
            new Person(new Name("b"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), EMPTY_REMARK,
                getTagSet(), getEmptyClasses()),
            new Person(new Name("c"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), EMPTY_REMARK,
                getTagSet(), getEmptyClasses()),
            new Person(new Name("d"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), EMPTY_REMARK,
                getTagSet(), getEmptyClasses()),
            new Person(new Name("e"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), EMPTY_REMARK,
                getTagSet(), getEmptyClasses()),
            new Person(new Name("f"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), EMPTY_REMARK,
                getTagSet(), getEmptyClasses())
        };
    }

    public static TuitionClass[] getSampleClass() {
        return new TuitionClass[] {
            new TuitionClass(new ClassName("Physics"),
                new ClassLimit(5), new Timeslot("Mon 10:00-12:00"),
                getSampleStudentList(), EMPTY_REMARK, 1234567),
            new TuitionClass(new ClassName("Chemistry"),
                new ClassLimit(10), new Timeslot("Tue 10:00-12:00"),
                getSampleStudentList(), EMPTY_REMARK, 2234567)
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);

        }
        for (TuitionClass tuitionClass: getSampleClass()) {
            sampleAb.addTuition(tuitionClass);
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

    private static Classes getEmptyClasses() {
        return new Classes(new ArrayList<>());
    }

    private static StudentList getSampleStudentList() {
        ArrayList<String> studentToAdd = new ArrayList<>();
        studentToAdd.add("a");
        StudentList students = new StudentList(studentToAdd);
        return students;
    }

}
