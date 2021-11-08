package safeforhall.model.util;

import safeforhall.model.AddressBook;
import safeforhall.model.ReadOnlyAddressBook;
import safeforhall.model.event.Capacity;
import safeforhall.model.event.Event;
import safeforhall.model.event.EventDate;
import safeforhall.model.event.EventName;
import safeforhall.model.event.EventTime;
import safeforhall.model.event.ResidentList;
import safeforhall.model.event.Venue;
import safeforhall.model.person.Email;
import safeforhall.model.person.Faculty;
import safeforhall.model.person.LastDate;
import safeforhall.model.person.Name;
import safeforhall.model.person.Person;
import safeforhall.model.person.Phone;
import safeforhall.model.person.Room;
import safeforhall.model.person.VaccStatus;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static final String NAME_ALEX = "Alex Yeoh";
    public static final String NAME_BERNICE = "Bernice Yu";
    public static final String NAME_CHARLOTTE = "Charlotte Oliveiro";
    public static final String NAME_DAVID = "David Li";
    public static final String NAME_IRFAN = "Irfan Ibrahim";
    public static final String NAME_ROY = "Roy Balakrishnan";

    public static final Person ALEX = new Person(new Name(NAME_ALEX), new Room("E417"), new Phone("87438807"),
            new Email("alexyeoh@example.com"), new VaccStatus("T"),
            new Faculty("SoC"), new LastDate("05-11-2021"), new LastDate("05-11-2021"));
    public static final Person BERNICE = new Person(new Name(NAME_BERNICE), new Room("A213"), new Phone("99272758"),
            new Email("berniceyu@example.com"), new VaccStatus("F"),
            new Faculty("FASS"), new LastDate("10-10-2021"), new LastDate("11-10-2021"));
    public static final Person CHARLOTTE = new Person(new Name(NAME_CHARLOTTE), new Room("B423"), new Phone("93210283"),
            new Email("charlotte@example.com"), new VaccStatus("T"),
            new Faculty("SoC"), new LastDate("11-10-2021"), new LastDate("12-10-2021"));
    public static final Person DAVID = new Person(new Name(NAME_DAVID), new Room("C112"), new Phone("91031282"),
            new Email("lidavid@example.com"), new VaccStatus("T"),
            new Faculty("SDE"), new LastDate("27-10-2021"), new LastDate("01-10-2021"));
    public static final Person IRFAN = new Person(new Name(NAME_IRFAN), new Room("D422"), new Phone("92492021"),
            new Email("irfan@example.com"), new VaccStatus("T"),
            new Faculty("FoE"), new LastDate("03-11-2021"), new LastDate("06-11-2021"));
    public static final Person ROY = new Person(new Name(NAME_ROY), new Room("A309"), new Phone("92624417"),
            new Email("royb@example.com"), new VaccStatus("T"),
            new Faculty("BIZ"), new LastDate("29-10-2021"), new LastDate("21-10-2021"));

    public static Person[] getSamplePersons() {
        return new Person[] { ALEX, BERNICE, CHARLOTTE, DAVID, IRFAN, ROY };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }

        sampleAb.addEvent(new Event(new EventName("Powerlifting"), new EventDate("09-11-2021"),
                new EventTime("0800"), new Venue("Gym"), new Capacity("5"), new ResidentList(
                String.join(", ", NAME_DAVID, NAME_IRFAN), String.join(", ",
                DAVID.toString(), IRFAN.toString()))));

        sampleAb.addEvent(new Event(new EventName("Basketball"), new EventDate("07-11-2021"),
                new EventTime("0830"), new Venue("basketball court"), new Capacity("5"), new ResidentList(
                        ResidentList.DEFAULT_LIST, ResidentList.DEFAULT_LIST)));

        sampleAb.addEvent(new Event(new EventName("Volleyball"), new EventDate("08-11-2021"),
                new EventTime("0800"), new Venue("volleyball court"), new Capacity("6"), new ResidentList(
                String.join(", ", NAME_ALEX, NAME_DAVID, NAME_CHARLOTTE), String.join(", ",
                ALEX.toString(), DAVID.toString(), CHARLOTTE.toString()))));

        sampleAb.addEvent(new Event(new EventName("Swimming"), new EventDate("01-11-2021"),
                new EventTime("0830"), new Venue("Pool"), new Capacity("7"), new ResidentList(
                String.join(", ", NAME_BERNICE, NAME_DAVID, NAME_CHARLOTTE), String.join(", ",
                BERNICE.toString(), DAVID.toString(), CHARLOTTE.toString()))));

        return sampleAb;
    }
}
