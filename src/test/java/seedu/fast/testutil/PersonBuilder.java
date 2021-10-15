package seedu.fast.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.fast.model.person.Address;
import seedu.fast.model.person.Appointment;
import seedu.fast.model.person.AppointmentCount;
import seedu.fast.model.person.Email;
import seedu.fast.model.person.Name;
import seedu.fast.model.person.Person;
import seedu.fast.model.person.Phone;
import seedu.fast.model.person.Remark;
import seedu.fast.model.tag.Tag;
import seedu.fast.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_REMARK = "She likes aardvarks.";
    public static final String DEFAULT_APPOINTMENT = "10 Oct 2021";
    public static final String DEFAULT_APPOINTMENT_TIME = "2000";
    public static final String DEFAULT_APPOINTMENT_VENUE = "Jewel Changi";
    public static final String DEFAULT_APPOINTMENT_COUNT = "0";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private Remark remark;
    private Appointment appointment;
    private AppointmentCount count;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        remark = new Remark(DEFAULT_REMARK);
        appointment = new Appointment(DEFAULT_APPOINTMENT, DEFAULT_APPOINTMENT_TIME, DEFAULT_APPOINTMENT_VENUE);
        count = new AppointmentCount(DEFAULT_APPOINTMENT_COUNT);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
        remark = personToCopy.getRemark();
        appointment = personToCopy.getAppointment();
        count = personToCopy.getCount();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Person} that we are building.
     */
    public PersonBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    /**
     * Sets the {@code Appointment} of the {@code Person} that we are building.
     */
    public PersonBuilder withAppointment(String date, String time, String venue) {
        this.appointment = new Appointment(date, time, venue);
        return this;
    }

    /**
     * Sets the {@code AppointmentCount} of the {@code Person} that we are building.
     */
    public PersonBuilder withAppointmentCount(String count) {
        this.count = new AppointmentCount(count);
        return this;
    }


    public Person build() {
        return new Person(name, phone, email, address, remark, tags, appointment, count);
    }

}
