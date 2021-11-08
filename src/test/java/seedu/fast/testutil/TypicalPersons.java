package seedu.fast.testutil;

import static seedu.fast.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_APPOINTMENT_AMY;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_APPOINTMENT_BOB;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_APPOINTMENT_TIME_AMY;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_APPOINTMENT_TIME_BOB;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_APPOINTMENT_VENUE_AMY;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_APPOINTMENT_VENUE_BOB;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.fast.model.Fast;
import seedu.fast.model.person.Person;
import seedu.fast.model.tag.InvestmentPlanTag;
import seedu.fast.model.tag.PriorityTag;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {
    public static final String NO_APPT = "No Appointment Scheduled Yet";
    public static final String NO_APPT_TIME = "";
    public static final String NO_APPT_VENUE = "";
    public static final String INITIAL_COUNT = "0";


    public static final Person ALICE = new PersonBuilder()
        .withName("Alice Pauline")
        .withAddress("123, Jurong West Ave 6, #08-111")
        .withEmail("alice@example.com")
        .withPhone("94351253")
        .withRemark("She likes aardvarks.")
        .withTags("friends")
        .withAppointment(NO_APPT, NO_APPT_TIME, NO_APPT_VENUE)
        .withAppointmentCount(INITIAL_COUNT)
        .build();

    public static final Person BENSON = new PersonBuilder()
        .withName("Benson Meier")
        .withAddress("311, Clementi Ave 2, #02-25")
        .withRemark("He can't take beer!")
        .withEmail("johnd@example.com")
        .withPhone("98765432")
        .withTags("owesMoney", "friends")
        .withAppointment("10 Nov 2025", NO_APPT_TIME, NO_APPT_VENUE)
        .withAppointmentCount(INITIAL_COUNT)
        .build();

    public static final Person CARL = new PersonBuilder()
        .withName("Carl Kurz")
        .withPhone("95352563")
        .withEmail("heinz@example.com")
        .withAddress("wall street")
        .withAppointment(NO_APPT, NO_APPT_TIME, NO_APPT_VENUE)
        .withAppointmentCount(INITIAL_COUNT)
        .build();

    public static final Person DANIEL = new PersonBuilder()
        .withName("Daniel Meier")
        .withPhone("87652533")
        .withEmail("cornelia@example.com")
        .withAddress("10th street")
        .withTags("friends")
        .withAppointment(NO_APPT, NO_APPT_TIME, NO_APPT_VENUE)
        .withAppointmentCount(INITIAL_COUNT)
        .build();
    public static final Person ELLE = new PersonBuilder()
        .withName("Elle Meyer")
        .withPhone("9482224")
        .withEmail("werner@example.com")
        .withAddress("michegan ave")
        .withAppointment("07 Dec 2021", NO_APPT_TIME, NO_APPT_VENUE)
        .withAppointmentCount(INITIAL_COUNT)
        .build();

    public static final Person FIONA = new PersonBuilder()
        .withName("Fiona Kunz")
        .withPhone("9482427")
        .withEmail("lydia@example.com")
        .withAddress("little tokyo")
        .withAppointment("11 Oct 2021", NO_APPT_TIME, NO_APPT_VENUE)
        .withAppointmentCount("1")
        .build();

    public static final Person GEORGE = new PersonBuilder()
        .withName("George Best")
        .withPhone("9482442")
        .withEmail("anna@example.com")
        .withAddress("4th street")
        .withAppointment(NO_APPT, NO_APPT_TIME, NO_APPT_VENUE)
        .withAppointmentCount("3")
        .build();

    //----------Manually added persons, not in TypicalPersons --------------

    public static final Person HOON = new PersonBuilder()
        .withName("Hoon Meier")
        .withPhone("8482424")
        .withEmail("stefan@example.com")
        .withAddress("little india")
        .withTags(PriorityTag.MediumPriority.NAME, InvestmentPlanTag.TravelInsurance.NAME,
            InvestmentPlanTag.Savings.NAME)
        .withAppointment(NO_APPT, NO_APPT_TIME, NO_APPT_VENUE)
        .withAppointmentCount(INITIAL_COUNT)
        .build();

    public static final Person IDA = new PersonBuilder()
        .withName("Ida Mueller")
        .withPhone("8482131")
        .withEmail("hans@example.com")
        .withAddress("chicago ave")
        .withTags(PriorityTag.LowPriority.NAME, InvestmentPlanTag.LifeInsurance.NAME, InvestmentPlanTag.Savings.NAME)
        .withAppointment(NO_APPT, NO_APPT_TIME, NO_APPT_VENUE)
        .withAppointmentCount(INITIAL_COUNT)
        .build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder()
        .withName(VALID_NAME_AMY)
        .withPhone(VALID_PHONE_AMY)
        .withEmail(VALID_EMAIL_AMY)
        .withAddress(VALID_ADDRESS_AMY)
        .withTags(VALID_TAG_FRIEND, PriorityTag.HighPriority.NAME, InvestmentPlanTag.TravelInsurance.NAME,
            InvestmentPlanTag.PropertyInsurance.NAME, InvestmentPlanTag.HealthInsurance.NAME,
            InvestmentPlanTag.LifeInsurance.NAME, InvestmentPlanTag.MotorInsurance.NAME,
            InvestmentPlanTag.Savings.NAME, InvestmentPlanTag.Investment.NAME)
        .withAppointment(VALID_APPOINTMENT_AMY, VALID_APPOINTMENT_TIME_AMY, VALID_APPOINTMENT_VENUE_AMY)
        .withAppointmentCount(INITIAL_COUNT)
        .build();

    public static final Person BOB = new PersonBuilder()
        .withName(VALID_NAME_BOB)
        .withPhone(VALID_PHONE_BOB)
        .withEmail(VALID_EMAIL_BOB)
        .withAddress(VALID_ADDRESS_BOB)
        .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND, PriorityTag.LowPriority.NAME,
            InvestmentPlanTag.Savings.NAME, InvestmentPlanTag.TravelInsurance.NAME)
        .withAppointment(VALID_APPOINTMENT_BOB, VALID_APPOINTMENT_TIME_BOB, VALID_APPOINTMENT_VENUE_BOB)
        .withAppointmentCount(INITIAL_COUNT)
        .build();

    // Manually added - for findCommand
    public static final Person JOE = new PersonBuilder()
        .withName("Joe Mama")
        .withPhone("92349123")
        .withEmail("joe@mama.com")
        .withAddress("final destination")
        .withTags(PriorityTag.HighPriority.NAME, InvestmentPlanTag.Savings.NAME)
        .withAppointment("07 Dec 2021", "0000", NO_APPT_VENUE)
        .withAppointmentCount(INITIAL_COUNT)
        .build();

    public static final Person GRABAHAN = new PersonBuilder()
        .withName("Grabahan Ful")
        .withPhone("89172389")
        .withEmail("grabahan@ful.com")
        .withAddress("deez lane")
        .withTags(PriorityTag.LowPriority.NAME, InvestmentPlanTag.Savings.NAME,
            InvestmentPlanTag.MotorInsurance.NAME, InvestmentPlanTag.Investment.NAME)
        .withAppointment("07 Dec 2021", "1800", NO_APPT_VENUE)
        .withAppointmentCount(INITIAL_COUNT)
        .build();

    // Manually added - for find remark
    public static final Person SUGON = new PersonBuilder()
        .withName("Sugon Deez")
        .withPhone("89172389")
        .withEmail("sugondeez@nutz.com")
        .withAddress("gottem")
        .withTags(PriorityTag.LowPriority.NAME, InvestmentPlanTag.TravelInsurance.NAME,
            InvestmentPlanTag.Investment.NAME, InvestmentPlanTag.Savings.NAME)
        .withRemark("What's DN?")
        .withAppointment(NO_APPT, NO_APPT_TIME, NO_APPT_VENUE)
        .withAppointmentCount(INITIAL_COUNT)
        .build();

    public static final Person CAVE = new PersonBuilder()
        .withName("CAVE JOHNSON")
        .withPhone("92292299")
        .withEmail("cave@johnson.com")
        .withAddress("Badwater")
        .withTags(PriorityTag.MediumPriority.NAME, InvestmentPlanTag.Savings.NAME)
        .withRemark("Heavy")
        .withAppointment(NO_APPT, NO_APPT_TIME, NO_APPT_VENUE)
        .withAppointmentCount(INITIAL_COUNT)
        .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {
    } // prevents instantiation

    /**
     * Returns an {@code Fast} with all the typical persons.
     */
    public static Fast getTypicalFast() {
        Fast fast = new Fast();
        for (Person person : getTypicalPersons()) {
            fast.addPerson(person);
        }
        return fast;
    }

    /**
     * Returns an {@code Fast} with all the typical persons.
     */
    public static Fast getTypicalFastSetTwo() {
        Fast fast = new Fast();
        for (Person person : getTypicalPersonsSetTwo()) {
            fast.addPerson(person);
        }
        return fast;
    }

    /**
     * Returns an {@code Fast} with all the typical persons.
     */
    public static Fast getTypicalFastSetThree() {
        Fast fast = new Fast();
        for (Person person : getTypicalPersons()) {
            fast.addPerson(person);
        }

        for (Person person : getTypicalPersonsSetTwo()) {
            fast.addPerson(person);
        }
        return fast;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<Person> getTypicalPersonsSetTwo() {
        return new ArrayList<>(Arrays.asList(HOON, IDA, AMY, BOB, JOE, GRABAHAN, SUGON, CAVE));
    }
}
