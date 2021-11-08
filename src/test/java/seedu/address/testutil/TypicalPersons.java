package seedu.address.testutil;

import static seedu.address.logic.candidate.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.candidate.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.candidate.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.candidate.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.candidate.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.candidate.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.candidate.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.candidate.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.candidate.CommandTestUtil.VALID_STATUS_APPLIED;
import static seedu.address.logic.candidate.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.candidate.CommandTestUtil.VALID_TAG_HUSBAND;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import seedu.address.model.HrManager;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline").withPositions("Accountant")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withRemark("She likes aardvarks.")
            .withTags("friends").withStatus("Applied").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier").withPositions("HR Manager")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432").withRemark("He can't handle beer!")
            .withTags("owesMoney", "friends").withStatus("Scheduled").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").withStatus("Rejected").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends")
            .withStatus("Scheduled").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").withStatus("Withdrawn").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").withStatus("Applied").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").withStatus("Scheduled").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").withStatus("Interviewed")
            .withPositions("Bookkeeper").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").withStatus("Applied").withPositions("Bookkeeper")
            .build();

    public static final Person JOHN = new PersonBuilder().withName("John Doe").withPhone("98780121")
            .withEmail("johnDoe@example.com").withAddress("chicago").withStatus("Scheduled").withPositions("Clerk",
                    "Bookkeeper").build();


    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND)
            .withStatus(VALID_STATUS_APPLIED).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withStatus(VALID_STATUS_APPLIED).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {
    } // prevents instantiation

    /**
     * Returns an {@code HrManager} with all the typical persons.
     */
    public static HrManager getTypicalHrManager() {
        HrManager ab = new HrManager();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }

        for (Position position : getTypicalPositions()) {
            ab.addPosition(position);
        }

        for (Interview interview : getTypicalInterviews()) {
            ab.addInterview(interview);
        }

        return ab;
    }

    public static HrManager getTypicalHrManagerWithOnlyTypicalPersons() {
        HrManager hr = new HrManager();
        for (Person person : getTypicalPersons()) {
            hr.addPerson(person);
        }
        return hr;
    }

    public static HrManager getTypicalHrManagerWithOnlyTypicalInterviews() {
        HrManager hr = new HrManager();
        for (Interview interview : getTypicalInterviews()) {
            hr.addInterview(interview);
        }
        return hr;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<Position> getTypicalPositions() {
        return new ArrayList<>(Arrays.asList(TypicalPositions.ADMIN_ASSISTANT, TypicalPositions.BOOKKEEPER,
                TypicalPositions.HR_MANAGER, TypicalPositions.ACCOUNTANT, TypicalPositions.CLOSED_POSITION_CLERK));
    }

    //this follows typicalInterviewHrManager.json data
    public static List<Interview> getTypicalInterviews() {
        Interview firstEntry = new InterviewBuilder().withPosition(new Position(new Title("Accountant")))
                .withCandidates(new HashSet<>()).withDate(LocalDate.of(2021, 10, 15))
                .withStartTime(LocalTime.of(14, 0)).withDuration(Duration.ofMinutes(120))
                .withStatus(Interview.InterviewStatus.PENDING).build();
        Interview secondEntry = new InterviewBuilder().withPosition(new Position(new Title("Bookkeeper")))
                .withCandidates(new HashSet<>()).withDate(LocalDate.of(2021, 12, 15))
                .withStartTime(LocalTime.of(14, 0)).withDuration(Duration.ofMinutes(120))
                .withStatus(Interview.InterviewStatus.PENDING).build();
        return new ArrayList<>(Arrays.asList(firstEntry, secondEntry));
    }
}
