package seedu.address.model.util;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventTime;
import seedu.address.model.participant.Address;
import seedu.address.model.participant.BirthDate;
import seedu.address.model.participant.Email;
import seedu.address.model.participant.Name;
import seedu.address.model.participant.NextOfKin;
import seedu.address.model.participant.Note;
import seedu.address.model.participant.Participant;
import seedu.address.model.participant.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Participant[] getSampleParticipants() {
        return new Participant[]{
            new Participant(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends"), BirthDate.of(2000, 8, 2),
                getNoteSet(new Note("Vegetarian", Note.Importance.VERY_HIGH)),
                List.of(new NextOfKin(new Name("Janette Yeoh"), new Phone("93822312"), new Tag("Spouse")))),

            new Participant(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends"), BirthDate.of(1970, 4, 2),
                getNoteSet(new Note("Allergic to Alcohol", Note.Importance.VERY_HIGH)),
                List.of(new NextOfKin(new Name("Bennett Yu"), new Phone("83612412"), new Tag("Parent")))),

            new Participant(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours"), BirthDate.of(1982, 5, 13),
                getNoteSet(new Note("Dislike seafood", Note.Importance.MEDIUM)),
                List.of(new NextOfKin(new Name("Daniel Pruvos"), new Phone("83460328"), new Tag("Brother")))),

            new Participant(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family"), BirthDate.of(1999, 2, 2), getNoteSet(new Note("Vegan",
                Note.Importance.VERY_HIGH)),
                List.of(new NextOfKin(new Name("Maria Li"), new Phone("84459923"), new Tag("Girlfriend")))),

            new Participant(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"), BirthDate.of(2000, 5, 23), getNoteSet(new Note("Allergic to Nuts",
                Note.Importance.VERY_HIGH)),
                List.of(new NextOfKin(new Name("Juliet Ibrahim"), new Phone("89345342"), new Tag("Grandparent")))),

            new Participant(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"), BirthDate.of(1999, 2, 8),
                getNoteSet(new Note("3 Month Paternity Leave beginning 2021-10-01",
                Note.Importance.HIGH)),
                List.of(new NextOfKin(new Name("Rouse Balakrishnan"), new Phone("93822312"), new Tag("Brother"))))
        };
    }

    public static Event[] getSampleEvents() {
        return new Event[]{
            new Event(new EventName("Investment Pitch with Sequoia Capital"), new EventDate("2022-08-20"), new EventTime("0800")),
            new Event(new EventName("Mangera Marketing Team Retreat"), new EventDate("2022-08-20"), new EventTime("0900")),
            new Event(new EventName("Managera Product Team Retreat"), new EventDate("2021-09-21"), new EventTime("0800")),
            new Event(new EventName("Managera Annual Shareholder Meeting"), new EventDate("2021-11-20"), new EventTime("0900")),
            new Event(new EventName("Managera Dinner and Dance"), new EventDate("2021-09-30")),
            new Event(new EventName("Monthly Roundup #28"), new EventDate("2021-09-26"), new EventTime("0900")),
            new Event(new EventName("Monthly Roundup #29"), new EventDate("2021-10-28"), new EventTime("0900")),
            new Event(new EventName("Monthly Roundup #30"), new EventDate("2021-11-28"), new EventTime("0900")),
            new Event(new EventName("Managera Alpha Release Milestone"), new EventDate("2021-09-20")),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Participant sampleParticipant : getSampleParticipants()) {
            sampleAb.addParticipant(sampleParticipant);
        }
        for (Event sampleEvent : getSampleEvents()) {
            sampleAb.addEvent(sampleEvent);
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
     * Return a note set containing the list of notes
     *
     * @param notes An array of notes.
     * @return Set of Notes.
     */
    public static Set<Note> getNoteSet(Note... notes) {
        return Arrays.stream(notes).collect(Collectors.toSet());
    }
}
