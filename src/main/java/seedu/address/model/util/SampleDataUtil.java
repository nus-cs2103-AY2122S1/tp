package seedu.address.model.util;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.HrManager;
import seedu.address.model.ReadOnlyHrManager;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Status;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code HrManager} with sample data.
 */
public class SampleDataUtil {

    public static final Remark EMPTY_REMARK = new Remark("");

    private static final Person ALEX = new Person(new Name("Alex Yeoh"), new Phone("87438807"),
            new Email("alexyeoh@example.com"),
            new Address("Blk 30 Geylang Street 29, #06-40"), EMPTY_REMARK,
            new HashSet<>(), Status.APPLIED, getPositionSet("Bookkeeper"));

    private static final Person BERNICE = new Person(new Name("Bernice Yu"), new Phone("99272758"),
            new Email("berniceyu@example.com"),
            new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), EMPTY_REMARK,
            new HashSet<>(), Status.ACCEPTED, getPositionSet("Admin Assistant", "Bookkeeper"));

    private static final Person CHARLOTTE = new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"),
            new Email("charlotte@example.com"),
            new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), EMPTY_REMARK,
            new HashSet<>(), Status.INTERVIEWED, getPositionSet("Accountant"));

    private static final Person DAVID = new Person(new Name("David Li"), new Phone("91031282"),
            new Email("lidavid@example.com"),
            new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), EMPTY_REMARK,
            new HashSet<>(), Status.NONE, getPositionSet("Senior Engineer"));

    private static final Person IRFAN = new Person(new Name("Irfan Ibrahim"), new Phone("92492021"),
            new Email("irfan@example.com"),
            new Address("Blk 47 Tampines Street 20, #17-35"), EMPTY_REMARK,
            new HashSet<>(), Status.REJECTED, getPositionSet("Project Manager", "Sales Representative"));

    private static final Person ROY = new Person(new Name("Roy Balakrishnan"), new Phone("92624417"),
            new Email("royb@example.com"),
            new Address("Blk 45 Aljunied Street 85, #11-31"), EMPTY_REMARK,
            new HashSet<>(), Status.REJECTED, getPositionSet("Sales Representative"));

    private static final Position BOOKKEEPER = new Position(new Title("Bookkeeper"));
    private static final Position ADMIN_ASSISTANT = new Position(new Title("Admin Assistant"));
    private static final Position PROJECT_MANAGER = new Position(new Title("Project Manager"));
    private static final Position SALES_REP = new Position(new Title("Sales Representative"));
    private static final Position SEN_ENGINEER = new Position(new Title("Senior Engineer"));
    private static final Position ACCOUNTANT = new Position(new Title("Accountant"));
    private static final Position SECRETARY = new Position(new Title("Secretary"),
            Position.PositionStatus.CLOSED);
    private static final Position SECURITY_GUARD = new Position(new Title("Security Guard"),
            Position.PositionStatus.CLOSED);

    public static Person[] getSamplePersons() {
        return new Person[] {
            ALEX, BERNICE, CHARLOTTE, DAVID, IRFAN, ROY
        };
    }

    public static Position[] getSamplePositions() {
        return new Position[] {
            BOOKKEEPER, ADMIN_ASSISTANT, PROJECT_MANAGER, SALES_REP, SEN_ENGINEER, ACCOUNTANT, SECRETARY, SECURITY_GUARD
        };
    }

    public static Interview[] getSampleInterviews() {
        Set<Person> sampleCandidatesA = new HashSet<>();
        sampleCandidatesA.add(IRFAN);
        sampleCandidatesA.add(ROY);

        Set<Person> sampleCandidatesB = new HashSet<>();
        sampleCandidatesB.add(ALEX);
        sampleCandidatesB.add(BERNICE);


        return new Interview[] {
            new Interview(SALES_REP, sampleCandidatesA, LocalDate.of(2021, 10, 15),
                        LocalTime.of(10, 0), Duration.ofHours(1), Interview.InterviewStatus.PENDING),
            new Interview(BOOKKEEPER, sampleCandidatesB, LocalDate.of(2021, 10, 26),
                        LocalTime.of(12, 30), Duration.ofMinutes(45), Interview.InterviewStatus.PENDING)
        };
    }


    public static ReadOnlyHrManager getSampleHrManager() {
        HrManager sampleHm = new HrManager();
        for (Person samplePerson : getSamplePersons()) {
            sampleHm.addPerson(samplePerson);
        }
        for (Position samplePosition : getSamplePositions()) {
            sampleHm.addPosition(samplePosition);
        }
        for (Interview sampleInterview : getSampleInterviews()) {
            Set<Person> candidates = sampleInterview.getCandidates();
            for (Person person : sampleHm.getPersonList()) {
                for (Person candidate : candidates) {
                    if (candidate.equals(person)) {
                        person.addInterview(sampleInterview);
                    }
                }
            }
            sampleHm.addInterview(sampleInterview);
        }
        return sampleHm;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    public static Set<Position> getPositionSet(String... strings) {
        return Arrays.stream(strings)
                .map(Title::new)
                .map(Position::new)
                .collect(Collectors.toSet());
    }
}
