package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.module.Name;
import seedu.address.model.module.event.Event;
import seedu.address.model.module.event.EventDate;
import seedu.address.model.module.member.Address;
import seedu.address.model.module.member.Email;
import seedu.address.model.module.member.Member;
import seedu.address.model.module.member.Phone;
import seedu.address.model.module.member.position.Position;
import seedu.address.model.module.task.Task;
import seedu.address.model.module.task.TaskDeadline;
import seedu.address.model.module.task.TaskList;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Member[] getSampleMembers() {
        return new Member[] {
            new Member(new Name("Alex Yeoh"), new Phone("87438807"),
                new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getPositionSet("President")),
            new Member(new Name("Bernice Yu"), new Phone("99272758"),
                new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getPositionSet("Vice President", "Manpower Head")),
            new Member(new Name("Charlotte Tan"), new Phone("93210283"),
                new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getPositionSet("Logistics Head")),
            new Member(new Name("David Li"), new Phone("91031282"),
                new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getPositionSet("Logistics Assistant")),
            new Member(new Name("Irfan Ibrahim"), new Phone("92492021"),
                new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getPositionSet("Logistics Assistant")),
            new Member(new Name("Roy Balakrishnan"), new Phone("92624417"),
                new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getPositionSet("Program Head"))
        };
    }

    public static TaskList getSampleTaskList() {
        TaskList result = new TaskList();
        result.setTasks(Arrays.asList(
                new Task(new Name("create interview slots"), new TaskDeadline("31/10/2021 20:00")),
                new Task(new Name("submit indemnity forms"), new TaskDeadline("02/11/2021 23:59")),
                new Task(new Name("submit project draft report"), new TaskDeadline("05/11/2021 14:00"))
        ));
        return result;
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Member sampleMember : getSampleMembers()) {
            if (sampleMember.getName().equals(new Name("Alex Yeoh"))) {
                sampleMember.setTaskList(getSampleTaskList());
            }
            sampleAb.addMember(sampleMember);
        }
        Set<Member> set = new HashSet<>();

        set.addAll(sampleAb.getMemberList());
        sampleAb.addEvent(new Event(new Name("Freshmen Orientation Camp Project"), new EventDate("11/07/2022"),
                        set));
        sampleAb.addEvent(new Event(new Name("October Monthly Meeting 2021"), new EventDate("03/10/2021"),
                set));
        sampleAb.addEvent(new Event(new Name("November Monthly Meeting 2021"), new EventDate("07/11/2021"),
                set));
        return sampleAb;
    }

    /**
     * Returns a position set containing the list of strings given.
     */
    public static Set<Position> getPositionSet(String... strings) {
        return Arrays.stream(strings)
                .map(Position::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a set containing the list of members given.
     */
    public static Set<Member> getMemberSet(Member... members) {
        return Arrays.stream(members).collect(Collectors.toSet());
    }

    /**
     * Returns a list of tasks containing the list of tasks given.
     */
    public static List<Task> getTaskList(Task ... tasks) {
        return Arrays.stream(tasks).collect(Collectors.toList());
    }
}
