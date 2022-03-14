package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;

import java.util.Arrays;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.participant.Address;
import seedu.address.model.participant.BirthDate;
import seedu.address.model.participant.Email;
import seedu.address.model.participant.Name;
import seedu.address.model.participant.NextOfKin;
import seedu.address.model.participant.Participant;
import seedu.address.model.participant.Phone;
import seedu.address.model.tag.Tag;

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
    private BirthDate birthDate;
    private ObservableList<NextOfKin> nextOfKins;

    /**
     * Creates a {@code ParticipantBuilder} with the default details.
     */
    public ParticipantBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        birthDate = BirthDate.notSpecified();
        nextOfKins = FXCollections.observableArrayList();
    }

    /**
     * Initializes the ParticipantBuilder with the data of {@code participantToCopy}.
     */
    public ParticipantBuilder(Participant participantToCopy) {
        name = participantToCopy.getName();
        phone = participantToCopy.getPhone();
        email = participantToCopy.getEmail();
        address = participantToCopy.getAddress();
        birthDate = participantToCopy.getBirthDate();
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
     * Sets the {@code nextOfKins} of the {@code Participant} that we are building.
     */
    public ParticipantBuilder withNextOfKins(NextOfKin... nextOfKins) {
        this.nextOfKins = FXCollections.observableArrayList(Arrays.stream(nextOfKins).collect(Collectors.toList()));
        return this;
    }

    /**
     * Returns a new instance of Participant with the details set in {@code ParticipantBuilder}.
     *
     * @return a Participant.
     */
    public Participant build() {
        return new Participant(name, phone, email, address, birthDate, nextOfKins);
    }

}
