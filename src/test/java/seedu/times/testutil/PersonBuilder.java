package seedu.times.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.times.model.person.Address;
import seedu.times.model.person.Email;
import seedu.times.model.person.Name;
import seedu.times.model.person.Nok;
import seedu.times.model.person.Phone;
import seedu.times.model.person.Student;
import seedu.times.model.tag.Tag;
import seedu.times.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_NOK_NAME = "Zhenglin Ong";
    public static final String DEFAULT_NOK_PHONE = "97762839";
    public static final String DEFAULT_NOK_EMAIL = "zhenglin@gmail.com";
    public static final String DEFAULT_NOK_ADDRESS = "345, Clementi Ave 6, #02-141";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;

    private Name nokName;
    private Phone nokPhone;
    private Email nokEmail;
    private Address nokAddress;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();

        nokName = new Name(DEFAULT_NOK_NAME);
        nokPhone = new Phone(DEFAULT_NOK_PHONE);
        nokEmail = new Email(DEFAULT_NOK_EMAIL);
        nokAddress = new Address(DEFAULT_NOK_ADDRESS);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        phone = studentToCopy.getPhone();
        email = studentToCopy.getEmail();
        address = studentToCopy.getAddress();
        tags = new HashSet<>(studentToCopy.getTags());
        nokName = studentToCopy.getNok().getName();
        nokPhone = studentToCopy.getNok().getPhone();
        nokEmail = studentToCopy.getNok().getEmail();
        nokAddress = studentToCopy.getNok().getAddress();
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
     * Sets the {@code NokName} of the {@code EditPersonDescriptor} that we are building.
     */
    public PersonBuilder withNokName(String name) {
        this.nokName = new Name(name);
        return this;
    }

    /**
     * Sets the {@code NokPhone} of the {@code EditPersonDescriptor} that we are building.
     */
    public PersonBuilder withNokPhone(String phone) {
        this.nokPhone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code NokEmail} of the {@code EditPersonDescriptor} that we are building.
     */
    public PersonBuilder withNokEmail(String email) {
        this.nokEmail = new Email(email);
        return this;
    }

    /**
     * Sets the {@code NokAddress} of the {@code EditPersonDescriptor} that we are building.
     */
    public PersonBuilder withNokAddress(String address) {
        this.nokAddress = new Address(address);
        return this;
    }

    /**
     * Builds student with all details included in the builder.
     *
     * @return Student with all the deatils included in the builder.
     */
    public Student build() {
        Nok nok = new Nok(nokName, nokPhone, nokEmail, nokAddress);
        return new Student(name, phone, email, address, nok, tags);
    }

}
