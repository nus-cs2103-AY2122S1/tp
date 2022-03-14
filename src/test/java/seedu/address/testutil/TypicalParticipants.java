package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.participant.Address;
import seedu.address.model.participant.BirthDate;
import seedu.address.model.participant.Email;
import seedu.address.model.participant.Name;
import seedu.address.model.participant.NextOfKin;
import seedu.address.model.participant.Participant;
import seedu.address.model.participant.Phone;
import seedu.address.model.tag.Tag;

public class TypicalParticipants {

    public static final Participant ALEX = new Participant(new Name("Alex Yeoh"), new Phone("87438807"), new Email(
            "alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"), BirthDate.of(2000, 8, 2),
            List.of(new NextOfKin(new Name("Janette Yeoh"), new Phone("93822312"), new Tag("Spouse"))));

    public static final Participant BERNICE = new Participant(new Name("Bernice Yu"), new Phone("99272758"), new Email(
            "berniceyu" + "@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
            BirthDate.of(1970, 4, 2),
            List.of(new NextOfKin(new Name("Bennett Yu"), new Phone("83612412"), new Tag("Parent"))));

    public static final Participant CHARLOTTE = new Participant(new Name("Charlotte Oliveiro"), new Phone("93210283"),
            new Email("charlotte" + "@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
            BirthDate.of(1982, 5, 13),
            List.of(new NextOfKin(new Name("Daniel Pruvos"), new Phone("83460328"), new Tag("Brother"))));

    public static final Participant DAVID = new Participant(new Name("David Li"), new Phone("91031282"),
            new Email("lidavid@example.com"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
            BirthDate.of(1999, 2, 2),
            List.of(new NextOfKin(new Name("Maria Li"), new Phone("84459923"), new Tag("Girlfriend"))));

    public static final Participant IRFAN = new Participant(new Name("Irfan Ibrahim"), new Phone("92492021"),
            new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
            BirthDate.of(2000, 5, 23),
            List.of(new NextOfKin(new Name("Juliet Ibrahim"), new Phone("89345342"), new Tag("Grandparent"))));

    public static final Participant ROY = new Participant(new Name("Roy Balakrishnan"), new Phone("92624417"),
            new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"),
            BirthDate.of(1999, 2, 8),
            List.of(new NextOfKin(new Name("Rouse Balakrishnan"), new Phone("93822312"), new Tag("Brother"))));

    public static final Participant ALEX_DIFFERENT_PHONE = new Participant(new Name("Alex Yeoh"), new Phone("87438808"),
            new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
            BirthDate.of(2000, 8, 2),
            List.of(new NextOfKin(new Name("Janette Yeoh"), new Phone("93822312"), new Tag("Spouse"))));

    public static final Participant ALEX_DIFFERENT_EMAIL = new Participant(new Name("Alex Yeoh"), new Phone("87438807"),
            new Email("alexyeoh2@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
            BirthDate.of(2000, 8, 2),
            List.of(new NextOfKin(new Name("Janette Yeoh"), new Phone("93822312"), new Tag("Spouse"))));

    public static final Participant ALEX_DIFFERENT_ADDRESS = new Participant(new Name("Alex Yeoh"),
            new Phone("87438807"), new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-42"),
            BirthDate.of(2000, 8, 2),
            List.of(new NextOfKin(new Name("Janette Yeoh"), new Phone("93822312"), new Tag("Spouse"))));


    public static final Participant ALEX_DIFFERENT_BIRTHDATE = new Participant(new Name("Alex Yeoh"),
            new Phone("87438807"), new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
            BirthDate.of(2000, 8, 3),
            List.of(new NextOfKin(new Name("Janette Yeoh"), new Phone("93822312"), new Tag("Spouse"))));

    public static final Participant ALEX_DIFFERENT_NOK = new Participant(new Name("Alex Yeoh"), new Phone("87438807"),
            new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
            BirthDate.of(2000, 8, 2),
            List.of(new NextOfKin(new Name("Jill Yeoh"), new Phone("93822313"), new Tag("Sister"))));

    /**
     * Returns an {@code AddressBook} with all the typical Participants.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Participant participant : getTypicalParticipants()) {
            ab.addParticipant(participant);
        }
        return ab;
    }

    public static List<Participant> getTypicalParticipants() {
        return new ArrayList<>(Arrays.asList(ALEX, BERNICE, CHARLOTTE, DAVID, IRFAN, ROY));
    }
}
