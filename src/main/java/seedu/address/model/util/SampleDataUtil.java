package seedu.address.model.util;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.id.UniqueId;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.NoOverlapLessonList;
import seedu.address.model.lesson.Subject;
import seedu.address.model.lesson.Timeslot;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Exam;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static Person[] getSamplePersons() {
        Person[] persons = new Person[]{};
        try {
            persons = new Person[] {
                    new Person(UniqueId.generateId("8df2379c-d093-4ab5-9f82-4f05d5f70c23"), new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                            new Address("Blk 30 Geylang Street 29, #06-40"),
                            getTagSet("friends"), getUniqueIdSet("20453a92-15bb-4871-b810-139826f024a0"), new HashMap<>(),
                            new NoOverlapLessonList().addLesson(new Lesson(new Timeslot("18:00", "20:00"), new Subject("Maths"),
                                    ParserUtil.parseDayOfWeek("Mon"))), new ArrayList<Exam>(), getUniqueIdSet("f01ff39c-e483-4191-b923-fcdbc1c64341")).addExam(new Exam(new Subject("Maths"),
                            ParserUtil.parseLocalDateTime("2021-12-20 14:00"))),
                    new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                            new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                            getTagSet("colleagues", "friends"), new HashSet<>(), new HashMap<>(),
                            new NoOverlapLessonList(), new ArrayList<Exam>(), new HashSet<>()),
                    new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                            new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                            getTagSet("neighbours"), new HashSet<>(), new HashMap<>(),
                            new NoOverlapLessonList(), new ArrayList<Exam>(), new HashSet<>()),
                    new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                            new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                            getTagSet("family"), new HashSet<>(), new HashMap<>(),
                            new NoOverlapLessonList(), new ArrayList<Exam>(), new HashSet<>()),

                    new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                            new Address("Blk 47 Tampines Street 20, #17-35"),
                            getTagSet("classmates"), new HashSet<>(), new HashMap<>(),
                            new NoOverlapLessonList(), new ArrayList<Exam>(), new HashSet<>()),
                    new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                            new Address("Blk 45 Aljunied Street 85, #11-31"),
                            getTagSet("colleagues"), new HashSet<>(), new HashMap<>(),
                            new NoOverlapLessonList(), new ArrayList<Exam>(), new HashSet<>())
            };
        } catch (ParseException pe) {
            System.out.println("error!");;
        }
        return persons;
    }

    public static Task[] getSampleTasks() {
        Task[] tasks = new Task[] {
                new Task(new Description("Complete math assignment"), new Deadline("2021-10-10"), UniqueId.generateId("20453a92-15bb-4871-b810-139826f024a0")),
                new Task(new Description("Complete english assignment"), new Deadline("2021-12-12"), UniqueId.generateId("3328c8ab-ca7e-495c-ab3a-9e0e8e78138b"))
        };
        return tasks;
    }

    public static Group[] getSampleGroups() {
        Group[] groups = new Group[] {
                new Group(new GroupName("CS2100 tutorial"), UniqueId.generateId("f01ff39c-e483-4191-b923-fcdbc1c64341"), new HashSet<>(),
                        getUniqueIdSet("8df2379c-d093-4ab5-9f82-4f05d5f70c23"),
                        new NoOverlapLessonList()),
                new Group(new GroupName("Stats tutorial"), UniqueId.generateId("7e73ef07-09f9-4a94-b3c7-75c7bc199223"))
        };
        return groups;
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Task sampleTask : getSampleTasks()) {
            sampleAb.addTask(sampleTask);
        }
        for (Group sampleGroup : getSampleGroups()) {
            sampleAb.addGroup(sampleGroup);
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

    /**
     * Returns a unique id set containing the list of strings given.
     */
    public static Set<UniqueId> getUniqueIdSet(String... strings) {
        return Arrays.stream(strings)
                .map(UniqueId::generateId)
                .collect(Collectors.toSet());
    }

}