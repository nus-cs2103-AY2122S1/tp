package seedu.times.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.times.model.ReadOnlyTimesTable;
import seedu.times.model.TimesTable;
import seedu.times.model.person.Address;
import seedu.times.model.person.Email;
import seedu.times.model.person.Name;
import seedu.times.model.person.Nok;
import seedu.times.model.person.Phone;
import seedu.times.model.person.Student;
import seedu.times.model.tag.Tag;
import seedu.times.model.tuitionclass.ClassName;
import seedu.times.model.tuitionclass.ClassTiming;
import seedu.times.model.tuitionclass.Location;
import seedu.times.model.tuitionclass.Rate;
import seedu.times.model.tuitionclass.StudentNameList;
import seedu.times.model.tuitionclass.TuitionClass;

/**
 * Contains utility methods for populating {@code TimesTable} with sample data.
 */
public class SampleDataUtil {
    private static Student[] getSamplePersons() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@gmail.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"),
                    new Nok(
                            new Name("Elise Yeoh"),
                            new Phone("93292556"),
                            new Email("eliseyeoh@gmail.com"),
                            new Address("Blk 30 Geylang Street 29, #06-40")
                    ),
                    getTagSet("A Math", "Sec 4")),
            new Student(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@gmail.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new Nok(
                            new Name("Daniel Yu"),
                            new Phone("87338920"),
                            new Email("danielyu@gmail.com"),
                            new Address("Blk 28 Geylang Street 29, #08-30")
                    ),
                    getTagSet("C Math", "Sec 3")),
            new Student(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@yahoo.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    new Nok(
                            new Name("Annabeth Parker"),
                            new Phone("94896758"),
                            new Email("annabethp@yahoo.com"),
                            new Address("Blk 6 Petir Road, #07-16")
                    ),
                    getTagSet("H2 Physics", "JC2")),
            new Student(new Name("David Li"), new Phone("91031282"), new Email("lidavid@gmail.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    new Nok(
                            new Name("Jacob Li"),
                            new Phone("84739056"),
                            new Email("jacobli@gmail.com"),
                            new Address("Blk 436 Serangoon Gardens Street 26, #16-43")
                    ),
                    getTagSet("A Math", "Sec 4")),
            new Student(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@yahoo.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                    new Nok(
                            new Name("Hafez Ibrahim"),
                            new Phone("90308891"),
                            new Email("hafez@gmail.com"),
                            new Address("Blk 47 Tampines Street 20, #17-35")
                    ),
                    getTagSet("H2 Math", "JC1")),
            new Student(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@gmail.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    new Nok(
                            new Name("Bob Balakrishnan"),
                            new Phone("97776300"),
                            new Email("bobbyb@yahoo.com"),
                            new Address("Blk 45 Aljunied Street 85, #11-31")
                    ),
                    getTagSet("Chemistry", "Sec 3")),
            new Student(new Name("Angelica Holcomb"), new Phone("85917748"), new Email("lorem@magnased.com"),
                    new Address("Ap #909-605 Ante St"),
                    new Nok(
                            new Name("Robert Holcomb"),
                            new Phone("85998446"),
                            new Email("robhol@yahoo.com"),
                            new Address("Ap #909-605 Ante St")
                    ),
                    getTagSet("Math", "Sec 2")),
            new Student(new Name("Illana Page"), new Phone("86549382"), new Email("elit@dui.org"),
                    new Address("Ap #558-850 Amet, Rd"),
                    new Nok(
                            new Name("Annabeth Page"),
                            new Phone("97373300"),
                            new Email("annabp@yahoo.com"),
                            new Address("Ap #558-850 Amet, Rd")
                    ),
                    getTagSet("Physical Sciences", "Sec 1")),
            new Student(new Name("Warren Campos"), new Phone("87718622"), new Email("magna@elit.ca"),
                    new Address("Ap #824-4482 Egestas Avenue"),
                    new Nok(
                            new Name("Boris Roth"),
                            new Phone("81308576"),
                            new Email("aliquet.vel.vulputate@phasellusdapibus.ca"),
                            new Address("987-5408 Dignissim Road")
                    ),
                    getTagSet("Chemistry", "Sec 1"))
        };
    }

    private static TuitionClass[] getSampleClasses() {
        return new TuitionClass[] {
            new TuitionClass(new ClassName("Sec 4 A Math"),
                    new ClassTiming("MON 11:30-13:30"),
                    new Location("Blk 30 Geylang Street 29, #06-40"),
                    new Rate("50"),
                    new StudentNameList(new Name("Alex Yeoh"), new Name("David Li"))),
            new TuitionClass(new ClassName("Sec 3 C Math"),
                    new ClassTiming("MON 14:30-16:30"),
                    new Location("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new Rate("60"),
                    new StudentNameList(new Name("Bernice Yu"))),
            new TuitionClass(new ClassName("Sec 2 Physics"),
                    new ClassTiming("TUE 11:00-12:30"),
                    new Location("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    new Rate("40"),
                    new StudentNameList(new Name("Charlotte Oliveiro"))),
            new TuitionClass(new ClassName("Sec 4 A Math 2"),
                    new ClassTiming("THU 11:30-13:30"),
                    new Location("Blk 30 Geylang Street 29, #06-40"),
                    new Rate("50"),
                    new StudentNameList(new Name("Alex Yeoh"), new Name("David Li"))),
            new TuitionClass(new ClassName("JC1 H2 Math"),
                    new ClassTiming("THU 16:30-18:30"),
                    new Location("Blk 30 Geylang Street 29, #06-40"),
                    new Rate("50"),
                    new StudentNameList(new Name("Bernice Yu"))),
            new TuitionClass(new ClassName("IB Year 5 HL Math"),
                    new ClassTiming("FRI 17:00-19:00"),
                    new Location("Blk 30 Geylang Street 29, #06-40"),
                    new Rate("70"),
                    new StudentNameList(new Name("Warren Campos"))),
            new TuitionClass(new ClassName("Sec 3 Chemistry"),
                    new ClassTiming("FRI 14:00-16:00"),
                    new Location("Blk 30 Geylang Street 29, #06-40"),
                    new Rate("40"),
                    new StudentNameList(new Name("Angelica Holcomb"), new Name("David Li"))),
            new TuitionClass(new ClassName("Sec 1 Math"),
                    new ClassTiming("SAT 11:30-13:00"),
                    new Location("Blk 30 Geylang Street 29, #06-40"),
                    new Rate("40"),
                    new StudentNameList(new Name("Alex Yeoh"), new Name("Angelica Holcomb"))),
            new TuitionClass(new ClassName("JC2 H2 Physics"),
                    new ClassTiming("SUN 11:30-13:30"),
                    new Location("Blk 30 Geylang Street 29, #06-40"),
                    new Rate("60"),
                    new StudentNameList(new Name("Charlotte Oliveiro"), new Name("David Li"))),
            new TuitionClass(new ClassName("JC2 H1 Math"),
                    new ClassTiming("SUN 14:30-16:30"),
                    new Location("Blk 30 Geylang Street 29, #06-40"),
                    new Rate("50"),
                    new StudentNameList(new Name("Alex Yeoh"), new Name("David Li"))),
            new TuitionClass(new ClassName("Sec 4 Physics"),
                    new ClassTiming("SAT 16:00-18:00"),
                    new Location("Blk 30 Geylang Street 29, #06-40"),
                    new Rate("40"),
                    new StudentNameList(new Name("Alex Yeoh"), new Name("Irfan Ibrahim"))),
            new TuitionClass(new ClassName("Sec 1 Physical Sciences"),
                    new ClassTiming("THU 09:30-11:00"),
                    new Location("Blk 30 Geylang Street 29, #06-40"),
                    new Rate("35"),
                    new StudentNameList(new Name("Illana Page")))
        };
    }

    public static ReadOnlyTimesTable getSampleTimesTable() {
        TimesTable sampleAb = new TimesTable();
        for (Student sampleStudent : getSamplePersons()) {
            sampleAb.addStudent(sampleStudent);
        }
        for (TuitionClass sampleClass : getSampleClasses()) {
            sampleAb.addTuitionClass(sampleClass);
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
