package safeforhall.testutil;

import static safeforhall.logic.commands.CommandTestUtil.VALID_COLLECTIONDATE_AMY;
import static safeforhall.logic.commands.CommandTestUtil.VALID_COLLECTIONDATE_BOB;
import static safeforhall.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static safeforhall.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static safeforhall.logic.commands.CommandTestUtil.VALID_FACULTY_AMY;
import static safeforhall.logic.commands.CommandTestUtil.VALID_FACULTY_BOB;
import static safeforhall.logic.commands.CommandTestUtil.VALID_FETDATE_AMY;
import static safeforhall.logic.commands.CommandTestUtil.VALID_FETDATE_BOB;
import static safeforhall.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static safeforhall.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static safeforhall.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static safeforhall.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static safeforhall.logic.commands.CommandTestUtil.VALID_ROOM_AMY;
import static safeforhall.logic.commands.CommandTestUtil.VALID_ROOM_BOB;
import static safeforhall.logic.commands.CommandTestUtil.VALID_VACCSTATUS_AMY;
import static safeforhall.logic.commands.CommandTestUtil.VALID_VACCSTATUS_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import safeforhall.model.AddressBook;
import safeforhall.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withRoom("A100")
            .withPhone("94351253")
            .withEmail("alice@example.com")
            .withVaccStatus("T")
            .withFaculty("SoC")
            .withFet("03-10-2021")
            .withCollection("03-10-2021")
            .build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withRoom("A101")
            .withPhone("98765432")
            .withEmail("johnd@example.com")
            .withVaccStatus("T")
            .withFaculty("SoC")
            .withFet("12-10-2021")
            .withCollection("13-10-2021")
            .build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz")
            .withRoom("A102")
            .withPhone("95352563")
            .withEmail("heinz@example.com")
            .withVaccStatus("F")
            .withFaculty("SoC")
            .withFet("10-10-2021")
            .withCollection("03-10-2021")
            .build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withRoom("A103")
            .withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withVaccStatus("T")
            .withFaculty("SoC")
            .withFet("12-10-2021")
            .withCollection("13-10-2021")
            .build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer")
            .withRoom("A104")
            .withPhone("9482224")
            .withEmail("werner@example.com")
            .withVaccStatus("F")
            .withFaculty("SoC")
            .withFet("05-10-2021")
            .withCollection("16-10-2021")
            .build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withRoom("A105")
            .withPhone("9482427")
            .withEmail("lydia@example.com")
            .withVaccStatus("T")
            .withFaculty("SoC")
            .withFet("20-10-2021")
            .withCollection("04-10-2021")
            .build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best")
            .withRoom("A106")
            .withPhone("9482442")
            .withEmail("anna@example.com")
            .withVaccStatus("F")
            .withFaculty("SoC")
            .withFet("15-10-2021")
            .withCollection("01-10-2021")
            .build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier")
            .withPhone("8482424")
            .withEmail("stefan@example.com")
            .withRoom("A107")
            .withVaccStatus("T")
            .withFaculty("SoC")
            .withFet("15-10-2021")
            .withCollection("15-10-2021")
            .build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller")
            .withPhone("8482131")
            .withEmail("hans@example.com")
            .withRoom("C200")
            .withVaccStatus("F")
            .withFaculty("SoC")
            .withFet("20-10-2021")
            .withCollection("22-10-2021")
            .build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withRoom(VALID_ROOM_AMY)
            .withFaculty(VALID_FACULTY_AMY)
            .withVaccStatus(VALID_VACCSTATUS_AMY)
            .withFet(VALID_FETDATE_AMY)
            .withCollection(VALID_COLLECTIONDATE_AMY)
            .build();
    // Person AMY without FET or COLLECTION
    public static final Person AMY_NO_FET_COLLECTION = new PersonBuilder().withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withRoom(VALID_ROOM_AMY)
            .withFaculty(VALID_FACULTY_AMY)
            .withVaccStatus(VALID_VACCSTATUS_AMY)
            .build();
    // Person AMY without FET
    public static final Person AMY_NO_FET = new PersonBuilder().withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withRoom(VALID_ROOM_AMY)
            .withFaculty(VALID_FACULTY_AMY)
            .withVaccStatus(VALID_VACCSTATUS_AMY)
            .withCollection(VALID_COLLECTIONDATE_AMY)
            .build();
    // Person AMY without COLLECTION
    public static final Person AMY_NO_COLLECTION = new PersonBuilder().withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withRoom(VALID_ROOM_AMY)
            .withFaculty(VALID_FACULTY_AMY)
            .withVaccStatus(VALID_VACCSTATUS_AMY)
            .withFet(VALID_FETDATE_AMY)
            .build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withRoom(VALID_ROOM_BOB)
            .withFaculty(VALID_FACULTY_BOB)
            .withVaccStatus(VALID_VACCSTATUS_BOB)
            .withFet(VALID_FETDATE_BOB)
            .withCollection(VALID_COLLECTIONDATE_BOB)
            .build();

    // Typical persons to be imported
    public static final Person AARON = new PersonBuilder().withName("Aaron Bower")
            .withPhone("97601364")
            .withEmail("Maggielonely@yahoo.co.jp")
            .withRoom("D208")
            .withVaccStatus("T")
            .withFaculty("SoC")
            .withFet("03-08-2021")
            .withCollection("10-09-2021")
            .build();
    public static final Person BRAD = new PersonBuilder().withName("Brad Pitt")
            .withPhone("92353605")
            .withEmail("lazyHeidi@rambler.ru")
            .withRoom("A111")
            .withVaccStatus("T")
            .withFaculty("Fass")
            .withFet("20-08-2021")
            .withCollection("16-08-2021")
            .build();
    public static final Person CODY = new PersonBuilder().withName("Cody Miller")
            .withPhone("93510639")
            .withEmail("lonelyCole2@live.fr")
            .withRoom("A114")
            .withVaccStatus("T")
            .withFaculty("Sde")
            .withFet("23-08-2021")
            .withCollection("25-08-2021")
            .build();
    public static final Person DARREN = new PersonBuilder().withName("Darren Hia")
            .withPhone("92976221")
            .withEmail("elatedClaudia@chello.nl")
            .withRoom("A410")
            .withVaccStatus("F")
            .withFaculty("Foe")
            .withFet("29-08-2021")
            .withCollection("17-08-2021")
            .build();
    public static final Person ELLIE = new PersonBuilder().withName("Ellie Muslinger")
            .withPhone("97728537")
            .withEmail("wanderingBrandon4@yahoo.com.sg")
            .withRoom("B109")
            .withVaccStatus("F")
            .withFaculty("Biz")
            .withFet("17-08-2021")
            .withCollection("30-08-2021")
            .build();

    // Missing fet and collection for importing
    public static final Person CODY_WO_FET = new PersonBuilder().withName("Cody Miller")
            .withPhone("93510639")
            .withEmail("lonelyCole2@live.fr")
            .withRoom("A114")
            .withVaccStatus("T")
            .withFaculty("Sde")
            .withCollection("25-08-2021")
            .build();
    public static final Person ELLIE_WO_COLLECTION = new PersonBuilder().withName("Ellie Muslinger")
            .withPhone("97728537")
            .withEmail("wanderingBrandon4@yahoo.com.sg")
            .withRoom("B109")
            .withVaccStatus("F")
            .withFaculty("Biz")
            .withFet("17-08-2021")
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalImportedAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalImportedPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBookWithSpecifiedPersons(List<Person> list) {
        AddressBook ab = new AddressBook();
        for (Person person : list) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalImportedPersons() {
        return new ArrayList<>(Arrays.asList(AARON, BRAD, CODY, DARREN, ELLIE));
    }
}
