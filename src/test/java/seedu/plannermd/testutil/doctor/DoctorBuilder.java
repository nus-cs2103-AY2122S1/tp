package seedu.plannermd.testutil.doctor;

import static seedu.plannermd.testutil.PersonBuilder.DEFAULT_ADDRESS;
import static seedu.plannermd.testutil.PersonBuilder.DEFAULT_EMAIL;
import static seedu.plannermd.testutil.PersonBuilder.DEFAULT_NAME;
import static seedu.plannermd.testutil.PersonBuilder.DEFAULT_PHONE;
import static seedu.plannermd.testutil.PersonBuilder.DEFAULT_REMARK;

import java.util.HashSet;
import java.util.Set;

import seedu.plannermd.model.doctor.Doctor;
import seedu.plannermd.model.person.Address;
import seedu.plannermd.model.person.Email;
import seedu.plannermd.model.person.Name;
import seedu.plannermd.model.person.Person;
import seedu.plannermd.model.person.Phone;
import seedu.plannermd.model.person.Remark;
import seedu.plannermd.model.tag.Tag;
import seedu.plannermd.model.util.SampleDataUtil;

/**
 * A utility class to help with building Doctor objects.
 */
public class DoctorBuilder {

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Remark remark;
    private Set<Tag> tags;

    /**
     * Creates a {@code DoctorBuilder} with the default details.
     */
    public DoctorBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        remark = new Remark(DEFAULT_REMARK);
        tags = new HashSet<>();
    }

    /**
     * Initializes the DoctorBuilder with the data of {@code personToCopy}.
     */
    public DoctorBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        remark = personToCopy.getRemark();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Initializes the DoctorBuilder with the data of {@code doctorToCopy}.
     */
    public DoctorBuilder(Doctor doctorToCopy) {
        name = doctorToCopy.getName();
        phone = doctorToCopy.getPhone();
        email = doctorToCopy.getEmail();
        address = doctorToCopy.getAddress();
        remark = doctorToCopy.getRemark();
        tags = new HashSet<>(doctorToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Doctor} that we are building.
     */
    public DoctorBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Doctor} that we are building.
     */
    public DoctorBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Doctor} that we are building.
     */
    public DoctorBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Doctor} that we are building.
     */
    public DoctorBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Doctor} that we are building.
     */
    public DoctorBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Doctor} that we are building.
     */
    public DoctorBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    public Doctor build() {
        return new Doctor(name, phone, email, address, remark, tags);
    }

}
