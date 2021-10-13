package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.participant.Address;
import seedu.address.model.participant.BirthDate;
import seedu.address.model.participant.Email;
import seedu.address.model.participant.Name;
import seedu.address.model.participant.NextOfKin;
import seedu.address.model.participant.Note;
import seedu.address.model.participant.Participant;
import seedu.address.model.participant.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;


public class ParticipantBuilder {

    public static final String DEFAULT_NAME = VALID_NAME_AMY;
    public static final String DEFAULT_PHONE = VALID_PHONE_AMY;
    public static final String DEFAULT_EMAIL = VALID_EMAIL_AMY;
    public static final String DEFAULT_ADDRESS = VALID_ADDRESS_AMY;
    public static final BirthDate DEFAULT_BIRTHDATE = BirthDate.of(2000, 8, 4);
    public static final NextOfKin DEFAULT_NEXT_OF_KIN = new NextOfKin(new Name("Bebe Bee"), new Phone("80232345"),
        new Tag("Spouse"));

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private BirthDate birthDate;
    private Set<Note> notes;
    private ArrayList<NextOfKin> nextOfKins;

    /**
     * Creates a {@code ParticipantBuilder} with the default details.
     */
    public ParticipantBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        birthDate = BirthDate.notSpecified();
        notes = new HashSet<>();
        nextOfKins = new ArrayList<>();
    }

    /**
     * Initializes the ParticipantBuilder with the data of {@code participantToCopy}.
     */
    public ParticipantBuilder(Participant participantToCopy) {
        name = participantToCopy.getName();
        phone = participantToCopy.getPhone();
        email = participantToCopy.getEmail();
        address = participantToCopy.getAddress();
        tags = new HashSet<>(participantToCopy.getTags());
        birthDate = participantToCopy.getBirthDate();
        notes = new HashSet<>(participantToCopy.getNotes());
        nextOfKins = participantToCopy.getNextOfKins();
    }

    /**
     * Sets the {@code Name} of the {@code Participant} that we are building.
     */
    public ParticipantBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Participant} that we are building.
     */
    public ParticipantBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Participant} that we are building.
     */
    public ParticipantBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Participant} that we are building.
     */
    public ParticipantBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Participant} that we are building.
     */
    public ParticipantBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code birthDate} of the {@code Participant} that we are building.
     */
    public ParticipantBuilder withBirthDate(BirthDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    /**
     * Sets the {@code birthDate} of the {@code Participant} that we are building.
     */
    public ParticipantBuilder withBirthDate(int year, int month, int dayOfMonth) {
        this.birthDate = BirthDate.of(year, month, dayOfMonth);
        return this;
    }

    /**
     * Sets the {@code notes} of the {@code Participant} that we are building.
     */
    public ParticipantBuilder withNotes(Set<Note> notes) {
        this.notes = notes;
        return this;
    }

    /**
     * Sets the {@code notes} of the {@code Participant} that we are building.
     */
    public ParticipantBuilder withNotes(Note... notes) {
        this.notes = SampleDataUtil.getNoteSet(notes);
        return this;
    }

    /**
     * Sets the {@code nextOfKins} of the {@code Participant} that we are building.
     */
    public ParticipantBuilder withNextOfKins(NextOfKin... nextOfKins) {
        this.nextOfKins = new ArrayList<>(Arrays.stream(nextOfKins).collect(Collectors.toList()));
        return this;
    }

    /**
     * Returns a new instance of Participant with the details set in {@code ParticipantBuilder}.
     *
     * @return a Participant.
     */
    public Participant build() {
        return new Participant(name, phone, email, address, tags, birthDate, notes, nextOfKins);
    }

}
